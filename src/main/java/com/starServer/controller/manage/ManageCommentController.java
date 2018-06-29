package com.starServer.controller.manage;


import com.google.gson.Gson;
import com.starServer.entity.Comment;
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
import java.util.Date;
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

    @ApiOperation("根据评论id获取评论")
    @RequestMapping(value = "/comment/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData<Comment> getCommentsById(@ApiParam("评论Id") @PathVariable(value = "id") Integer id) {
        ResponseData<Comment> responseData =new ResponseData<>();
        responseData.jsonFill(1,null,commentService.getCommentById(id));
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

    @ApiOperation(value = "分页获取所有评论(增加明星id表示搜索)", notes = "")
    @RequestMapping(value = "/comments", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseData<List<Comment>> getAllComments(@ApiParam("page") @RequestParam Integer page,
                                                    @ApiParam("pageSize") @RequestParam Integer pageSize,
                                                   @ApiParam("明星id") @RequestParam Integer starId,
                                                   HttpServletResponse response) {
        ResponseData<List<Comment>> responseData=new ResponseData<>();
        if (starId != null){
            responseData = commentService.getCommentsByStarId(starId, page, pageSize);
            return responseData;
        }
        responseData = commentService.getComments(page, pageSize);
        return responseData;
    }

    @ApiOperation(value = "新增评论", notes = "")
    @RequestMapping(value = "/comments", method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseData<Boolean> createComments(@ApiParam("明星id") @RequestParam Integer starId,
                                                      @ApiParam("用户id") @RequestParam Integer userId,
                                                      @ApiParam("内容") @RequestParam String content,
                                                      @ApiParam("评论图片url") @RequestParam(value = "picUrls",required = false) List<String> picUrls,
                                                      HttpServletResponse response) {
        ResponseData<Boolean> responseData=new ResponseData<>();
        Comment comment=new Comment();
        comment.setStarId(starId);
        comment.setContent(content);
        comment.setUserId(userId);
        comment.setCreateTime(new Date());
        if (picUrls !=null){
            comment.setPicUrls(new Gson().toJson(picUrls));
        }
        Integer res = commentService.saveComment(comment);
        if (res !=1){
            responseData.jsonFill(2,"新增失败",false);
        }else {
            responseData.jsonFill(1,null,true);
        }
        return responseData;
    }
}
