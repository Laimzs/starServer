package com.starServer.controller.manage;


import com.starServer.entity.Comment;
import com.starServer.entity.Star;
import com.starServer.entity.response.ResponseData;
import com.starServer.service.CommentService;
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
 * Created by zhangsong on 2017/12/11.
 */
@Api(value = "Admin", description = "评论管理接口")
@Controller()
@RequestMapping("/manage")
public class ManageCommentController {
    @Autowired
    CommentService commentService;



    @ApiOperation("根据明星id获取评论")
    @RequestMapping(value = "/getCommentsByStarId", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData<List<Comment>> getCommentsByStarId(@ApiParam("明星的Id") @RequestParam(value = "starId") Integer starId,
                                                           @ApiParam("page") @RequestParam(value = "page") Integer page,
                                                           @ApiParam("pageSize") @RequestParam(value = "pageSize") Integer pageSize) {
        ResponseData<List<Comment>> responseData = commentService.getCommentsByStarId(starId, page, pageSize);
        return responseData;
    }

    @ApiOperation("评论加精")
    @RequestMapping(value = "/commentAddCream", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData<Boolean> commentAddCream(@ApiParam("评论id") @RequestParam(value = "id") Integer id) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        boolean res = commentService.addCream(id);
        responseData.jsonFill(1, null, res);
        return responseData;
    }

    @ApiOperation("评论取消加精")
    @RequestMapping(value = "/cancelCream", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData<Boolean> cancelCream(@ApiParam("评论id") @RequestParam(value = "id") Integer id) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        boolean res = commentService.deleteCream(id);
        responseData.jsonFill(1, null, res);
        return responseData;
    }

    @ApiOperation("非法评论放出小黑屋")
    @RequestMapping(value = "/releaseComment", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseData<Boolean> releaseComment(@ApiParam("评论id") @RequestParam(value = "id") Integer id) {
        ResponseData<Boolean> responseData = new ResponseData<>();
        boolean res = commentService.releaseComment(id);
        responseData.jsonFill(1, null, res);
        return responseData;
    }

    @ApiOperation("删除评论")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/comment/{id}", method = {RequestMethod.DELETE})
    @ResponseBody
    public void deleteComment(@ApiParam("评论id") @PathVariable(value = "id") Integer id,
                                               HttpServletRequest request) {
      commentService.deleteComment(id);

    }

    @ApiOperation(value = "分页获取所有评论", notes = "")
    @RequestMapping(value = "/comments", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData<List<Star>> getAllComments(@ApiParam("page") @RequestParam Integer page,
                                               @ApiParam("pageSize") @RequestParam Integer pageSize, HttpServletRequest request,
                                               HttpServletResponse response) {
        ResponseData responseData = commentService.getComments(page, pageSize);
        return responseData;
    }
}
