package com.cncf.controller.manage;

import com.cncf.entity.FireBehaviorInformation;
import com.cncf.entity.ResponseData;
import com.cncf.entity.User;
import com.cncf.service.FireInformationService;
import com.cncf.service.UserService;
import com.cncf.util.TokenConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangsong on 2018/1/4.
 */
@Controller
@RequestMapping("/user")
public class FireInformationController {
    @Autowired
    FireInformationService fireInformationService;
    @Autowired
    UserService userService;

    //获取火情信息
    @RequestMapping(value = "/getFireInformation", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData getFireInformation(HttpServletRequest request, HttpServletResponse response) {
        ResponseData<List<FireBehaviorInformation>> responseData = new ResponseData<>();
        User user = (User) request.getAttribute(TokenConfig.DEFAULT_USERID_REQUEST_ATTRIBUTE_NAME);
        if (user == null) {
            responseData.jsonFill(2, "请先登录", null);
            return responseData;
        }
        user=userService.getUserById(user.getId());
        List<FireBehaviorInformation> list = fireInformationService.getFireInformation(user.getLevel());
        responseData.jsonFill(1, null, list);
        return responseData;
    }

    //驳回火情信息
    @RequestMapping(value = "/rejectFireInformation", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData rejectFireInformation(
            @RequestParam("fireInformationId") Integer fireInformationId,
            HttpServletRequest request, HttpServletResponse response) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        User user = (User) request.getAttribute(TokenConfig.DEFAULT_USERID_REQUEST_ATTRIBUTE_NAME);
        if (user == null) {
            responseData.jsonFill(2, "请先登录", false);
            return responseData;
        }
        FireBehaviorInformation fireBehaviorInformation = fireInformationService.getFireInformationById(fireInformationId);
        if (fireBehaviorInformation.getLevel() < 1) {
            responseData.jsonFill(2, "该消息已经驳回", false);
            return responseData;
        }
        if (user.getLevel().equals(fireBehaviorInformation.getLevel())) {
            //取相反数
            fireBehaviorInformation.setLevel(0 - fireBehaviorInformation.getLevel());
            fireBehaviorInformation.setUpdateTime(new Date());
            fireInformationService.updateFireInformation(fireBehaviorInformation);
            responseData.jsonFill(1, null, true);
            return responseData;
        }
        responseData.jsonFill(2, "你没有权限驳回该消息", false);
        return responseData;
    }

    //上报火情信息
    @RequestMapping(value = "/reportFireInformation", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData reportFireInformation(
            @RequestParam("fireInformationId") Integer fireInformationId,
            HttpServletRequest request, HttpServletResponse response) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        User user = (User) request.getAttribute(TokenConfig.DEFAULT_USERID_REQUEST_ATTRIBUTE_NAME);
        if (user == null) {
            responseData.jsonFill(2, "请先登录", false);
            return responseData;
        }
        FireBehaviorInformation fireBehaviorInformation = fireInformationService.getFireInformationById(fireInformationId);
        if (fireBehaviorInformation.getLevel() < 1) {
            responseData.jsonFill(2, "该消息已经驳回", false);
            return responseData;
        }
        if (user.getLevel().equals(fireBehaviorInformation.getLevel())) {
            //消息等级加一
            fireBehaviorInformation.setLevel(fireBehaviorInformation.getLevel()+1);
            fireBehaviorInformation.setUpdateTime(new Date());
            fireInformationService.updateFireInformation(fireBehaviorInformation);
            responseData.jsonFill(1, null, true);
            return responseData;
        }
        responseData.jsonFill(2, "你没有权限上报该消息", false);
        return responseData;
    }

    //获取火情历史记录

    @RequestMapping(value = "/getFireInformationHistory", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData getFireInformationHistory(HttpServletRequest request, HttpServletResponse response) {
        ResponseData<List<FireBehaviorInformation>> responseData = new ResponseData<>();
        User user = (User) request.getAttribute(TokenConfig.DEFAULT_USERID_REQUEST_ATTRIBUTE_NAME);
        if (user == null) {
            responseData.jsonFill(2, "请先登录", null);
            return responseData;
        }
        List<FireBehaviorInformation> list = fireInformationService.getFireInformationHistory(user.getLevel());
        responseData.jsonFill(1, null, list);
        return responseData;
    }
}
