package com.cncf.controller.manage;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.cncf.entity.ResponseData;
import com.cncf.entity.User;
import com.cncf.service.UserService;
import com.cncf.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by zhangsong on 2018/1/1.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //获取手机验证码
    @RequestMapping(value = "/getVerifyCodeByMobile", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData<User> getVerifyCodeByMobile(@RequestParam("phone") String phone,
                                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseData<User> responseData = new ResponseData();

        User user = userService.getUserByPhone(phone);
        if (user == null) {
            String code = RandCharsUtils.getRandomNumber(4);
            SendSmsResponse smsResponse = SmsUtils.sendMessage("SMS_126460392", phone, code);
            User user2 = new User();
            user2.setPhone(phone);
            Util.setNewAccessToken(user2);
            user2.setVerifyCode(code);
            user2.setName(phone);
            user2.setCreateTime(new Date());
            user2.setUpdateTime(new Date());
            user2.setValid(0);
            userService.saveUser(user2);
            responseData.jsonFill(1, null, user2);
        } else if (user.getValid().equals(0)) {
            String code = RandCharsUtils.getRandomNumber(4);
            SendSmsResponse smsResponse = SmsUtils.sendMessage("SMS_126460392", phone, code);
            Util.setNewAccessToken(user);
            user.setVerifyCode(code);
            user.setUpdateTime(new Date());
            userService.updateUser(user);
            responseData.jsonFill(1, null, user);
        } else {
            responseData.jsonFill(2, "该手机已注册", null);
        }
        return responseData;
    }

    //验证验证码是否成功
    @RequestMapping(value = "/confirmationVerifyCode", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData<Boolean> getVerifyCodeByMobile(@RequestParam("phone") String phone,
                                                       @RequestParam("code") String code,
                                                       HttpServletRequest request, HttpServletResponse response) {

        ResponseData responseData = new ResponseData();
        User user = userService.getUserByPhone(phone);
        if (user != null) {
            long dValue = System.currentTimeMillis() - user.getExpireTime().getTime();
            if (dValue <= 900000L) {
                if (user.getVerifyCode().equals(code)) {
                    responseData.jsonFill(1, null, true);
                    return responseData;
                }
                responseData.jsonFill(2, "验证码错误", false);
                return responseData;
            }
            responseData.jsonFill(2, "验证码超时", false);
            return responseData;
        }
        responseData.jsonFill(2, "请先使用该手机号获取验证码", false);
        return responseData;
    }

    //设置密码填写信息
    @RequestMapping(value = "/fillInInformation", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData<Boolean> fillInInformation(@RequestParam("phone") String phone,
                                                   @RequestParam("name") String name,
                                                   @RequestParam(value = "passWord", required = false) String passWord,
                                                   @RequestParam("job") String job,
                                                   @RequestParam("company") String company,
                                                   HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = new ResponseData();
        User user = userService.getUserByPhone(phone);
        if (user != null) {
            user.setJob(job);
            user.setCompany(company);
            user.setValid(1);
            user.setName(name);
            if (passWord != null)
                user.setPassWord(passWord);
            userService.updateUser(user);
            responseData.jsonFill(1, null, true);
            return responseData;
        }
        responseData.jsonFill(2, "用户不存在", false);
        return responseData;
    }

    //登录
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData<String> login(@RequestParam("phone") String phone,
                                      @RequestParam("passWord") String passWord,
                                      HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = new ResponseData();
        User user = userService.getUserByPhone(phone);
        if (user != null) {
            if (passWord.equals(user.getPassWord())) {
                Util.setNewAccessToken(user);

                //登录信息写入缓存
                Jedis jedis = null;
                try {
                    jedis = JedisUtil.getJedis();
                    jedis.set(user.getAccessToken().getBytes(), ObjectAndByte.toByteArray(user));
                    jedis.expire(user.getAccessToken().getBytes(), 60 * 60 * 1);//会话1小时失效
                } catch (Exception e) {
                    responseData.jsonFill(2, "登录失败，服务器错误。", null);
                    return responseData;
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }

                responseData.jsonFill(1, null, user.getAccessToken());
                return responseData;
            }
            responseData.jsonFill(2, "密码错误", null);
            return responseData;
        }
        responseData.jsonFill(2, "用户不存在", null);
        return responseData;
    }


    //修改密码
    @RequestMapping(value = "/updatePassword", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData updatePassword(@RequestParam("phone") String phone,
                                       @RequestParam("code") String code,
                                       @RequestParam("passWord") String passWord,
                                       HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = new ResponseData<>();
        User user = userService.getUserByPhone(phone);
        if (user == null) {
            responseData.setErrorMes("用户不存在");
            responseData.setStatus(2);
            return responseData;
        }
        long dValue = System.currentTimeMillis() - user.getExpireTime().getTime();
        if (dValue <= 900000L) {
            if (user.getVerifyCode().equals(code)) {
                user.setUpdateTime(new Date());
                user.setPassWord(passWord);
                int success = userService.updateUser(user);
                if (success == 1) {
                    responseData.setStatus(1);
                    return responseData;
                }
                responseData.setStatus(2);
                responseData.setErrorMes("修改密码失败");
                return responseData;
            }
            responseData.setStatus(2);
            responseData.setErrorMes("验证码错误");
            return responseData;
        }
        responseData.setStatus(2);
        responseData.setErrorMes("验证码超时");
        return responseData;
    }

    //获取用户信息
    @RequestMapping(value = "/getUserInformation", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData getUserInformation(HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = new ResponseData();
        User user = (User) request.getAttribute(TokenConfig.DEFAULT_USERID_REQUEST_ATTRIBUTE_NAME);
        if (user == null) {
            responseData.jsonFill(2, "请先登录", false);
            return responseData;
        }
        //刷新下user信息
        user = userService.getUserById(user.getId());
        responseData.jsonFill(1, null, user);
        return responseData;
    }

    //获取验证码修改密码
    @RequestMapping(value = "/getForgetPasswordVerifyCode", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData<Boolean> getForgetPasswordVerifyCode(@RequestParam("phone") String phone,
                                                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResponseData<Boolean> responseData = new ResponseData();
        User user = userService.getUserByPhone(phone);
        if (user != null) {
            if (user.getValid() == 1) {
                String code = RandCharsUtils.getRandomNumber(4);
                SendSmsResponse smsResponse = SmsUtils.sendMessage("SMS_101110009", phone, code);
                user.setVerifyCode(code);
                user.setExpireTime(new Date());
                int res = userService.updateUser(user);
                responseData.jsonFill(1, null, true);
                return responseData;
            }
            responseData.jsonFill(2, "该用户不存在", null);
        } else {
            responseData.jsonFill(2, "该用户不存在", null);
        }
        return responseData;
    }


}
