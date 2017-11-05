package com.lucifer.model.hfc;

import com.lucifer.utils.StringHelper;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/15.
 */
public class News {

    private Long id;

    private String title;

    private String summary;

    private String logo;

    private String source;

    private Long categoryId;

    private String mode;

    private String content;

    private Integer clickCount;

    private String categoryName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishAt;

    private Date createdAt;

    private Date updatedAt;

    private Integer isDeleted;

    private Float top;

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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Float getTop() {
        return top;
    }

    public void setTop(Float top) {
        this.top = top;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String rightRecommendTitle(){
        if (StringHelper.isEmpty(this.title)) {
            return "";
        }
        if (this.title.length() >= 17) {
            return this.title.substring(0,17) + "...";
        }
        return this.title;
    }
}
