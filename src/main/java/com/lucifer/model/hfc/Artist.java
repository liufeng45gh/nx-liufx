package com.lucifer.model.hfc;

import java.util.Date;

/**
 * Created by liufx on 17/1/16.
 */
public class Artist {

    private Long id;

    private String name;

    private String avatar;

    private Float top;

    private String intro;

    private String detail;

    private Date createdAt;

    private Date updatedAt;

    private Integer isDeleted;

    private String namePy;

    private String tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Float getTop() {
        return top;
    }

    public void setTop(Float top) {
        this.top = top;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String shortIntro(){
        if (null == this.intro) {
            return "";
        }
        if (this.intro.length()>88) {
            return this.intro.substring(0,88) + "...";
        }
        return this.intro;
    }

    public String longIntro(){
        if (null == this.intro) {
            return "";
        }
        if (this.intro.length()>3000) {
            return this.intro.substring(0,3000) + "...";
        }
        return this.intro;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getNamePy() {
        return namePy;
    }

    public void setNamePy(String namePy) {
        this.namePy = namePy;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
