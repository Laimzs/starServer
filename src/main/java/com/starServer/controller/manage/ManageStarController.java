package com.starServer.controller.manage;

import com.google.gson.Gson;
import com.starServer.entity.Star;
import com.starServer.entity.response.ResponseData;
import com.starServer.entity.vo.FilmVo;
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
import java.util.List;

/**
 * Created by zhangsong on 2018/6/27.
 */
@Api(value = "Admin", description = "后台明星管理")
@Controller
@RequestMapping("/manage")
public class ManageStarController {
    @Autowired
    private StarService starService;
    @RequestMapping(value = "/star/{id}", method = {RequestMethod.DELETE})
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "根据明星ID删除明星", notes = "")
    public void deleteStarById(@ApiParam("id") @PathVariable int id, HttpServletRequest request,
                                                HttpServletResponse response) {
        starService.deleteStar(id);
    }

    @RequestMapping(value = "/star/{id}", method = {RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "根据明星Id获取明星", notes = "")
    public ResponseData<Star> getStarById(@ApiParam("id") @PathVariable int id) {
        ResponseData<Star> responseData=new ResponseData<>();
        responseData.jsonFill(1,null,starService.getStarById(id));
        return responseData;
    }

    @ApiOperation(value = "更新明星信息", notes = "")
    @RequestMapping(value = "/star/{id}", method = {RequestMethod.POST})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseData<Boolean> updateStar(
            @ApiParam("明星id") @PathVariable(value = "id") Integer id,
            @ApiParam("明星名字") @RequestParam(value = "starName", required = false) String starName,
            @ApiParam("名字首字母（注意修改名字，也同时需要修改这个字段）") @RequestParam(value = "nameInitials", required = false) String nameInitials,
            @ApiParam("参演电影") @RequestParam(value = "filmContent", required = false) List<FilmVo> filmContent,
            @ApiParam("参演电视剧") @RequestParam(value = "tvPlayContent", required = false) List<String> tvPlayContent,
            @ApiParam("抖音视频") @RequestParam(value = "tremblingContent", required = false) List<String> tremblingContent,
            @ApiParam("明星头像") @RequestParam(value = "starHeadImg", required = false) String starHeadImg,
            @ApiParam("明星写真") @RequestParam(value = "starPortrait", required = false) String starPortrait,
            HttpServletRequest request, HttpServletResponse response) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        Star star = starService.getStarById(id);
        if (star == null) {
            responseData.jsonFill(2, "明星不存在", false);
            return responseData;
        }
        if (starName != null) {
            star.setStarName(starName);
            if (nameInitials==null){
                responseData.jsonFill(2, "你修改了名字，需要修首字母", false);
                return responseData;
            }
            star.setNameInitials(nameInitials);
        }
        Gson gson =new Gson();
        if (filmContent != null)
            star.setFilmContent(gson.toJson(filmContent));
        if (tvPlayContent!= null)
            star.setTvPlayContent(gson.toJson(tvPlayContent));
        if (tremblingContent != null)
            star.setTremblingContent(gson.toJson(tremblingContent));
        if (starHeadImg != null)
            star.setStarHeadImg(starHeadImg);
        if (starPortrait != null)
            star.setStarPortrait(starPortrait);
        int res = starService.updateStar(star);
        if (res == 1) {
            responseData.jsonFill(1, null, true);
        } else {
            responseData.jsonFill(2, "更新失败", false);
        }
        return responseData;
    }

    @ApiOperation(value = "新建明星信息", notes = "")
    @RequestMapping(value = "/star", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseData<Boolean> createStar(
            @ApiParam("明星名字") @RequestParam(value = "starName") String starName,
            @ApiParam("名字首字母（注意修改名字，也同时需要修改这个字段）") @RequestParam(value = "nameInitials") String nameInitials,
            @ApiParam("参演电影") @RequestParam(value = "filmContent") List<FilmVo> filmContent,
            @ApiParam("参演电视剧") @RequestParam(value = "tvPlayContent") List<String> tvPlayContent,
            @ApiParam("抖音视频") @RequestParam(value = "tremblingContent") List<String> tremblingContent,
            @ApiParam("明星头像") @RequestParam(value = "starHeadImg") String starHeadImg,
            @ApiParam("明星写真") @RequestParam(value = "starPortrait") String starPortrait,
            HttpServletRequest request, HttpServletResponse response) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        Star star = new Star();
        star.setStarName(starName);
        star.setNameInitials(nameInitials);
        Gson gson =new Gson();
        star.setFilmContent(gson.toJson(filmContent));
        star.setTvPlayContent(gson.toJson(tvPlayContent));
        star.setTremblingContent(gson.toJson(tremblingContent));
        star.setStarHeadImg(starHeadImg);
        star.setStarPortrait(starPortrait);
        int res = starService.saveStar(star);
        if (res == 1) {
            responseData.jsonFill(1, null, true);
        } else {
            responseData.jsonFill(2, "新增失败", false);
        }
        return responseData;
    }

    @ApiOperation(value = "分页获取所有明星", notes = "")
    @RequestMapping(value = "/stars", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData<List<Star>> getAllStar(@ApiParam("page") @RequestParam Integer page,
                                               @ApiParam("pageSize") @RequestParam Integer pageSize, HttpServletRequest request,
                                               HttpServletResponse response) {
        ResponseData responseData = starService.getStarListByPage(page, pageSize);
        return responseData;
    }
}
