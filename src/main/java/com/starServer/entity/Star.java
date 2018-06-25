package com.starServer.entity;

public class Star {
    private Integer id;

    private String starName;

    private String nameInitials;

    private String filmContent;

    private String tvPlayContent;

    private String tremblingContent;

    private String extreContent;

    private String starHeadImg;

    private String starPortrait;

    private String likeCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName == null ? null : starName.trim();
    }

    public String getNameInitials() {
        return nameInitials;
    }

    public void setNameInitials(String nameInitials) {
        this.nameInitials = nameInitials == null ? null : nameInitials.trim();
    }

    public String getFilmContent() {
        return filmContent;
    }

    public void setFilmContent(String filmContent) {
        this.filmContent = filmContent == null ? null : filmContent.trim();
    }

    public String getTvPlayContent() {
        return tvPlayContent;
    }

    public void setTvPlayContent(String tvPlayContent) {
        this.tvPlayContent = tvPlayContent == null ? null : tvPlayContent.trim();
    }

    public String getTremblingContent() {
        return tremblingContent;
    }

    public void setTremblingContent(String tremblingContent) {
        this.tremblingContent = tremblingContent == null ? null : tremblingContent.trim();
    }

    public String getExtreContent() {
        return extreContent;
    }

    public void setExtreContent(String extreContent) {
        this.extreContent = extreContent == null ? null : extreContent.trim();
    }

    public String getStarHeadImg() {
        return starHeadImg;
    }

    public void setStarHeadImg(String starHeadImg) {
        this.starHeadImg = starHeadImg == null ? null : starHeadImg.trim();
    }

    public String getStarPortrait() {
        return starPortrait;
    }

    public void setStarPortrait(String starPortrait) {
        this.starPortrait = starPortrait == null ? null : starPortrait.trim();
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount == null ? null : likeCount.trim();
    }
}