package com.cncf.entity;

import java.util.Date;

public class FireBehaviorInformation {
    private Integer id;

    private String fireBehaviorType;

    private Date createTime;

    private Date updateTime;

    private String pic;

    private Integer level;

    private String place;

    private String other;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFireBehaviorType() {
        return fireBehaviorType;
    }

    public void setFireBehaviorType(String fireBehaviorType) {
        this.fireBehaviorType = fireBehaviorType == null ? null : fireBehaviorType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}