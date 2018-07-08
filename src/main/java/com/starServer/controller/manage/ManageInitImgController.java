package com.starServer.controller.manage;

import com.starServer.entity.InitImage;
import com.starServer.entity.response.ResponseData;
import com.starServer.service.InitImageService;
import com.starServer.service.StarService;
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

@Api(value = "Admin", description = "后台开屏页管理")
@Controller
@RequestMapping("/manage")
public class ManageInitImgController {
    @Autowired
    private InitImageService initImageService;
    @RequestMapping(value = "/initImage/{id}", method = {RequestMethod.DELETE})
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "根据ID删除开屏页", notes = "")
    public void deleteInitImageById(@ApiParam("id") @PathVariable int id, HttpServletRequest request,
                               HttpServletResponse response) {
        initImageService.deleteInitImageById(id);
    }

    @RequestMapping(value = "/initImage/{id}", method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据开屏页Id获取开屏页", notes = "")
    public ResponseData<InitImage> getInitImageById(@ApiParam("id") @PathVariable int id) {
        ResponseData<InitImage> responseData=new ResponseData<>();
        responseData.jsonFill(1,null,initImageService.getInitImageById(id));
        return responseData;
    }

    @ApiOperation(value = "更新开屏页信息", notes = "")
    @RequestMapping(value = "/initImage/{id}", method = {RequestMethod.POST})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Boolean> updateInitImage(
            @ApiParam("开屏页id") @PathVariable(value = "id") Integer id,
            @ApiParam("开屏页名字") @RequestParam(value = "imgName", required = false) String imgName,
            @ApiParam("开屏页Url") @RequestParam(value = "imgUrl", required = false) String imgUrl,
            @ApiParam("是否显示") @RequestParam(value = "isShow", required = false)  Integer  isShow,
            @ApiParam("广告点击跳转") @RequestParam(value = "advertisementUrl", required = false) String advertisementUrl) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        InitImage initImage = initImageService.getInitImageById(id);
        if (initImage == null) {
            responseData.jsonFill(2, "开屏页不存在", false);
            return responseData;
        }
        if (imgName != null) {
            initImage.setImgName(imgName);
        }
        if (imgUrl != null)
            initImage.setImgUrl(imgUrl);
        if (isShow != null)
            initImage.setIsShow(isShow);
        if (advertisementUrl != null)
            initImage.setAdvertisementUrl(advertisementUrl);
        int res = initImageService.updateInitImage(initImage);
        if (res == 1) {
            responseData.jsonFill(1, null, true);
        } else {
            responseData.jsonFill(2, "更新失败", false);
        }
        return responseData;
    }


    @ApiOperation(value = "新建开屏页信息", notes = "")
    @RequestMapping(value = "/initImage", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseData<Boolean> createInitImage(
            @ApiParam("开屏页名字") @RequestParam(value = "imgName") String imgName,
            @ApiParam("开屏页Url") @RequestParam(value = "imgUrl") String imgUrl,
            @ApiParam("是否显示") @RequestParam(value = "isShow")  Integer isShow,
            @ApiParam("广告点击跳转") @RequestParam(value = "advertisementUrl") String advertisementUrl) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        InitImage initImage = new InitImage();
        initImage.setIsShow(isShow);
        initImage.setAdvertisementUrl(advertisementUrl);
        initImage.setImgUrl(imgUrl);
        initImage.setImgName(imgName);
        initImage.setUpdateTime(new Date());
        initImage.setCreateTime(new Date());
        int res = initImageService.addInitImage(initImage);
        if (res == 1) {
            responseData.jsonFill(1, null, true);
        } else {
            responseData.jsonFill(2, "新增失败", false);
        }
        return responseData;
    }

    @ApiOperation(value = "获取所有开屏页", notes = "")
    @RequestMapping(value = "/initImages", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData<List<InitImage>> getAllInitImages(@ApiParam("page") @RequestParam Integer page,
                                                          @ApiParam("pageSize") @RequestParam Integer pageSize, HttpServletRequest request,
                                                          HttpServletResponse response) {
        ResponseData responseData = initImageService.getAllInitImageByPage(page, pageSize);
        return responseData;
    }
}
