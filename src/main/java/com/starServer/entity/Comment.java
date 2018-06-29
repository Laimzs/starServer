package com.starServer.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.util.Date;

@Api(value="评论实体类")
public class Comment {
    @ApiModelProperty(value="主键")
    private Integer id;
    @ApiModelProperty(value="明星id")
    private Integer starId;
    @ApiModelProperty(value="用户id")
    private Integer userId;
    @ApiModelProperty(value="评论内容")
    private String content;
    @ApiModelProperty(value="喜爱次数")
    private Integer likeCount=0;
    @ApiModelProperty(value="是否加精")
    private Integer cream=0;
    @ApiModelProperty(value="图片url")
    private String picUrls;
    @ApiModelProperty(value="新建时间")
    private Date createTime;
    @ApiModelProperty(value="状态码")
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStarId() {
        return starId;
    }

    public void setStarId(Integer starId) {
        this.starId = starId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCream() {
        return cream;
    }

    public void setCream(Integer cream) {
        this.cream = cream;
    }

    public String getPicUrls() {
        return picUrls;
    }

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls == null ? null : picUrls.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}