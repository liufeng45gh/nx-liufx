package com.lucifer.service.hfc;

import com.lucifer.dao.hfc.NewsDao;
import com.lucifer.model.hfc.News;
import com.lucifer.model.hfc.NewsRecommend;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liufx on 17/1/16.
 */
@Component
public class NewsService {

    @Resource
    private NewsDao newsDao;

    public  List<NewsRecommend> newsRecommendList(){
        List<NewsRecommend> newsRecommendList = newsDao.newsRecommendList();
        for(NewsRecommend newsRecommend: newsRecommendList) {
            News news = newsDao.getNews(newsRecommend.getNewsId());
            newsRecommend.setNews(news);
        }

        return newsRecommendList;
    }

    public List<News> searchList(String title,Integer offset,Integer count){
         return null;
    }

    public void loadNewsRightData(HttpServletRequest request){
        List<News> hotList = newsDao.webHotNewsList();
        request.setAttribute("hotList",hotList);


        List<NewsRecommend> newsRecommendList = this.newsRecommendList();
        request.setAttribute("recommendList",newsRecommendList);

    }


}
