package com.starServer.controller.manage;

import com.starServer.entity.User;
import com.starServer.entity.response.BooleanResponse;
import com.starServer.entity.response.ResponseData;
import com.starServer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangsong on 2018/6/25.
 */

@Api(value = "Admin", description = "后台用户管理")
@Controller
@RequestMapping("/manage")
public class ManageUserController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/user/{id}", method = {RequestMethod.DELETE})
    @ResponseBody
    @ApiOperation(value = "根据用户ID删除用户", notes = "")
    public ResponseData<Boolean> deleteUserById(@ApiParam("id") @PathVariable int id, HttpServletRequest request,
                                                HttpServletResponse response) {
        ResponseData<Boolean> responseData = new ResponseData();
        BooleanResponse booleanResponse = userService.deleteUser(id);
        if (booleanResponse.getResult()){
            responseData.jsonFill(1,null,true);
        }else {
            responseData.jsonFill(1,booleanResponse.getErrMsg(),true);
        }
        return responseData;
    }

    @RequestMapping(value = "/user/{id}", method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据用户Id获取用户", notes = "")
    public ResponseData<User> getUserById(@ApiParam("用户id") @PathVariable int id) {
        ResponseData<User> responseData = new ResponseData<>();
        responseData.jsonFill(1,null,userService.getUserById(id));
        return responseData;
    }

    @ApiOperation(value = "更新用户信息", notes = "")
    @RequestMapping(value = "/user/{id}", method = {RequestMethod.POST})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Boolean> updateUser(
            @ApiParam("用户id") @PathVariable(value = "id") Integer id,
            @ApiParam("用户名") @RequestParam(value = "nickname", required = false) String nickname,
            @ApiParam("密码") @RequestParam(value = "password", required = false) String password,
            @ApiParam("性别") @RequestParam(value = "sex", required = false) String sex,
            @ApiParam("城市") @RequestParam(value = "city", required = false) String city,
            @ApiParam("个性签名") @RequestParam(value = "signature", required = false) String signature,
            @ApiParam("手机号码") @RequestParam(value = "mobile", required = false) String mobile,
            @ApiParam("用户头像") @RequestParam(value = "headImgUrl", required = false) String headImgUrl,
            HttpServletRequest request, HttpServletResponse response) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        User user = userService.getUserById(id);
        if (user == null) {
            responseData.jsonFill(2, "用户不存在", false);
            return responseData;
        }
        if (nickname != null) user.setNickName(nickname);
        if (password != null) user.setPassword(password);
        user.setUpdateTime(new Date());
        if (city != null)
            user.setCity(city);
        if (sex != null)
            user.setSex(sex);
        if (mobile != null)
            user.setMobile(mobile);
        if (signature != null)
            user.setSignature(signature);

        if (headImgUrl != null)
            user.setHeadImgUrl(headImgUrl);
        int res = userService.updateUser(user);
        if (res == 1) {
            responseData.jsonFill(1, null, true);
        } else {
            responseData.jsonFill(2, "更新失败", false);
        }
        return responseData;
    }

    @ApiOperation(value = "分页获取所有用户", notes = "")
    @RequestMapping(value = "/users", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData<List<User>> getAllUsers(@ApiParam("page") @RequestParam Integer page,
                                                          @ApiParam("pageSize") @RequestParam Integer pageSize, HttpServletRequest request,
                                                          HttpServletResponse response) {
        ResponseData responseData = userService.getUserListByPage(page, pageSize);
        return responseData;
    }

}
