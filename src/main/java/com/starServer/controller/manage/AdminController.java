package com.starServer.controller.manage;


import com.starServer.entity.Admin;
import com.starServer.entity.ResponseData;
import com.starServer.entity.vo.LoginResponseVo;
import com.starServer.service.AdminService;
import com.starServer.util.StringUtil;
import com.starServer.util.Util;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xmc1993 on 2017/5/15.AdminService
 */

@Api(value = "Admin", description = "权限管理接口")
@Controller
@RequestMapping("/manage")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation(value = "登录", notes = "")
    @RequestMapping(value = "/auth", method = {RequestMethod.POST})
    @ResponseBody
    public LoginResponseVo login(
            @ApiParam("用户名") @RequestParam("username") String username,
            @ApiParam("密码(用md5加密)") @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response) {
        Admin admin = adminService.getAdminByUsername(username);
        if (admin == null) {
            throw new RuntimeException("用户不存在");
        }
        // todo 更严格的安全校验
        if (!admin.getPassword().equals(password)) {
            throw new RuntimeException("密码错误");
        }
        admin.setAccessToken(Util.getToken());
        long currentTime = System.currentTimeMillis();
        currentTime += 1000 * 60 * 60 * 6;// 设置为6小时后失效
        admin.setExpireTime(new Date(currentTime));
        boolean res = adminService.updateAccessToken(admin);
        if (!res) {
            throw new RuntimeException("登录失败，服务器错误。");
        }

        redisTemplate.opsForValue().set(admin.getAccessToken(), admin);
        redisTemplate.expire(admin.getAccessToken(), 60 * 60 * 6, TimeUnit.SECONDS);
        LoginResponseVo loginResponseVo = new LoginResponseVo();
        loginResponseVo.setId(admin.getId());
        loginResponseVo.setAccessToken(admin.getAccessToken());
        return loginResponseVo;
    }


    @ApiOperation(value = "登出", notes = "")
    @RequestMapping(value = "/auth", method = {RequestMethod.DELETE})
    @ResponseBody
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String accessToken = request.getHeader("Authorization");
        if (!StringUtil.isEmpty(accessToken)) {
            redisTemplate.delete(accessToken);
        }
    }


    @ApiOperation(value = "新增后台用户", notes = "")
    @RequestMapping(value = "/admins", method = {RequestMethod.POST})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Admin publishAdmin(@ApiParam("徽章类型项") @RequestBody Admin admin, HttpServletRequest request,
                              HttpServletResponse response) {
        admin.setPassword(Util.getMd5(admin.getPassword()));
        admin.setCreateTime(new Date());
        admin.setUpdateTime(new Date());
        adminService.saveAdmin(admin);
        return admin;
    }

    @ApiOperation(value = "删除后台用户", notes = "")
    @RequestMapping(value = "/admins/{id}", method = {RequestMethod.DELETE})
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@ApiParam("ID") @PathVariable int id, HttpServletRequest request,
                            HttpServletResponse response) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        boolean success = adminService.deleteAdmin(id);
        if (!success) {
            throw new RuntimeException("删除失败");
        }
    }

    @ApiOperation(value = "分页获得后台用户", notes = "")
    @RequestMapping(value = "/getAdminListByPage", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData<List<Admin>> getAdminListByPage(@ApiParam("PAGE") @RequestParam int page,
                                                        @ApiParam("SIZE") @RequestParam int pageSize, HttpServletRequest request, HttpServletResponse response) {
        ResponseData<List<Admin>> responseData = new ResponseData<>();
        List<Admin> adminList = adminService.getAdminListByPage(page, pageSize);
        responseData.jsonFill(1, null, adminList);
        responseData.setCount(adminService.getAdminCount());
        return responseData;
    }
}
