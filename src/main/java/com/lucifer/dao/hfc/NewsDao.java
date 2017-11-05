package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.Carousel;
import com.lucifer.model.hfc.News;
import com.lucifer.model.hfc.NewsCategory;
import com.lucifer.model.hfc.NewsRecommend;
import com.lucifer.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Administrator on 2017/1/15.
 */
@Component
public class NewsDao extends IBatisBaseDao {
    public List<News> cmsNewsList(String title,Integer offset,Integer count){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("cmsNewsList",param);
    }

    public Integer matchRecordCount(String title){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        return this.hfcSqlSession.selectOne("cmsNewsMatchRecordCount",param);
    }

    public Integer insertNews(News news) {
        news.setCreatedAt(DateUtils.now());
        news.setUpdatedAt(DateUtils.now());
        //news.setTop(0f);
        news.setClickCount(0);
        return this.hfcSqlSession.insert("insertNews",news);
    }

    public News getNews(Long id) {
        return this.hfcSqlSession.selectOne("getNews",id);
    }

    public Integer updateNews(News news){
        return this.hfcSqlSession.update("updateNews",news);
    }

    public Integer deleteNews(Long id){
        return this.hfcSqlSession.delete("deleteNews",id);
    }

    public List<NewsCategory> cmsNewsCategoryList(){
        return this.hfcSqlSession.selectList("cmsNewsCategoryList");
    }

    public Integer addNewsCategory(NewsCategory newsCategory) {
        return this.hfcSqlSession.insert("addNewsCategory",newsCategory);
    }

    public Integer deleteNewsCategory(Long id){
        return this.hfcSqlSession.delete("deleteNewsCategory",id);
    }

    public NewsCategory getNewsCategory(Long id) {
        return this.hfcSqlSession.selectOne("getNewsCategory",id);
    }

    public Integer newsCategoryUpdate(NewsCategory newsCategory) {
        return this.hfcSqlSession.update("newsCategoryUpdate",newsCategory);
    }

    public List<NewsRecommend> newsRecommendList() {
        return this.hfcSqlSession.selectList("newsRecommendList");
    }

    public NewsRecommend getNewsRecommend(Long id) {
        return this.hfcSqlSession.selectOne("getNewsRecommend",id);
    }

    public Integer updateNewsRecommend(NewsRecommend newsRecommend){
        //newsRecommend.setTop(0f);
        return this.hfcSqlSession.update("updateNewsRecommend",newsRecommend);
    }

    public Integer addNewsRecommend(NewsRecommend newsRecommend){
        newsRecommend.setTop(0f);
        return this.hfcSqlSession.insert("addNewsRecommend",newsRecommend);
    }

    public Integer deleteNewsRecommend(Long id){
        return this.hfcSqlSession.delete("deleteNewsRecommend",id);
    }

    public List<News> webNewsList(Long categoryId,Integer offset,Integer count){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("categoryId",categoryId);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("webNewsList",param);
    }

    public List<News> webHotNewsList(){
        return this.hfcSqlSession.selectList("webHotNewsList");
    }

    public List<News> newsListOrderByUpdatedAt(Date updatedAt, int count){
        Map param = new HashMap();
        param.put("updatedAt", updatedAt);
        param.put("count", count);
        return this.hfcSqlSession.selectList("newsListOrderByUpdatedAt",param);
    }

}
