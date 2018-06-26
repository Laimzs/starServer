package com.starServer.controller.manage;


import com.starServer.entity.response.ResponseData;
import com.starServer.entity.vo.UploadResVo;
import com.starServer.util.RandCharsUtils;
import com.starServer.util.UploadFileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * Created by xmc1993 on 2017/5/15.
 */

@Api(value = "Admin", description = "上传文件接口")
@Controller
@RequestMapping("/manage")
public class ManageUploadController {

    private static final String ICON_ROOT = "/pic/";
    private static final String HEADIMG_ROOT = "/audios/";

    @ApiOperation(value = "上传明星写真", notes = "")
    @RequestMapping(value = "/uploadStarPortrait", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData<UploadResVo> uploadStarPortrait(
            @ApiParam("文件") @RequestParam(value = "icon") MultipartFile icon,
            HttpServletRequest request, HttpServletResponse response) {
        ResponseData<UploadResVo> responseData = new ResponseData<>();
        String url = uploadFile(icon, ICON_ROOT);
        if (url == null) {
            responseData.jsonFill(2, "上传失败", null);
        } else {
            UploadResVo uploadResVo = new UploadResVo();
            uploadResVo.setUrl(url);
            responseData.jsonFill(1, null, uploadResVo);
        }
        return responseData;
    }


    @ApiOperation(value = "批量上传明星写真", notes = "")
    @RequestMapping(value = "/uploadMultiStarPortrait", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData<UploadResVo> uploadMultiStarPortrait(
            @ApiParam("多文件") @RequestParam(value = "files") MultipartFile[] files,
            HttpServletRequest request, HttpServletResponse response) {
        ResponseData<UploadResVo> responseData = new ResponseData<>();
        UploadResVo uploadResVo = new UploadResVo();
        ArrayList<String> urls = new ArrayList<>();
        uploadResVo.setMultiUrls(urls);
        responseData.jsonFill(1, null, uploadResVo);
        if (files != null){
            for (MultipartFile file : files) {
                if (!file.isEmpty()){
                    String type = file.getContentType();
                    String[] strs = type.split("/");
                    String suffix = strs[strs.length -1];
                    urls.add(uploadFile(file, ICON_ROOT, suffix));
                }
            }
        }

        return responseData;
    }

    @ApiOperation(value = "明星头像", notes = "")
    @RequestMapping(value = "/uploadStarHeadImg", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData<UploadResVo> uploadStarHeadImg(
            @ApiParam("文件") @RequestParam(value = "icon", required = true) MultipartFile icon,
            HttpServletRequest request, HttpServletResponse response) {
        ResponseData<UploadResVo> responseData = new ResponseData<>();
        String url = uploadFile(icon, HEADIMG_ROOT);
        if (url == null) {
            responseData.jsonFill(2, "上传失败", null);
        } else {
            UploadResVo uploadResVo = new UploadResVo();
            uploadResVo.setUrl(url);
            responseData.jsonFill(1, null, uploadResVo);
        }
        return responseData;
    }

    /**
     * 上传ICON文件
     *
     * @param file
     * @return
     */
    private String uploadFile(MultipartFile file, String root) {
        String realPath = UploadFileUtil.getBaseUrl() + root;
        String fileName = RandCharsUtils.getRandomString(16) + "." + UploadFileUtil.getSuffix(file.getOriginalFilename());
        boolean success = UploadFileUtil.mvFile(file, realPath, fileName);
        if (!success) {
            throw new RuntimeException("文件上传失败");
        }
        String url = UploadFileUtil.SOURCE_BASE_URL + root + fileName;
        return url;
    }

    private String uploadFile(MultipartFile file, String root, String suffix) {
        String realPath = UploadFileUtil.getBaseUrl() + root;
        String fileName = RandCharsUtils.getRandomString(16) + "." + suffix;
        boolean success = UploadFileUtil.mvFile(file, realPath, fileName);
        if (!success) {
            throw new RuntimeException("文件上传失败");
        }
        String url = UploadFileUtil.SOURCE_BASE_URL + root + fileName;
        return url;
    }

}
