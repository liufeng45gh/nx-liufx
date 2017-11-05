package com.lucifer.model.hfc;

import com.lucifer.utils.StringHelper;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by liufx on 17/1/16.
 */
public class ArtistLecture {

    private Long id;

    private String title;

    private String summary;

    private String logo;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishAt;

    private Date createdAt;

    private Date updatedAt;

    private Float top;

    private Integer isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishAt() {
        return publishAt;
    }

    public void setPublishAt(Date publishAt) {
        this.publishAt = publishAt;
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

    public Float getTop() {
        return top;
    }

    public void setTop(Float top) {
        this.top = top;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean isLogoHave(){
        if (StringHelper.isEmpty(this.logo)) {
            return false;
        }
        if ("/cms/images/logo.png".equals(this.logo)){
            return false;
        }
        return true;
    }

    public String summaryText(){
        if (null == this.summary) {
            return "";
        }
        if (this.isLogoHave()){
            if (this.summary.length()<74) {
                return this.summary;
            }
            return this.summary.substring(0,74)+"...";
        } else {
            if (this.summary.length()<110) {
                return this.summary;
            }
            return this.summary.substring(0,110)+"...";
        }

    }
}
