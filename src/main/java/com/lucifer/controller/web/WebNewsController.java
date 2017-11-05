package com.lucifer.controller.web;

import com.lucifer.dao.hfc.CarouseDao;
import com.lucifer.dao.hfc.NewsDao;
import com.lucifer.model.hfc.Carousel;
import com.lucifer.model.hfc.News;
import com.lucifer.model.hfc.NewsCategory;
import com.lucifer.model.hfc.NewsRecommend;
import com.lucifer.service.hfc.NewsSearchService;
import com.lucifer.service.hfc.NewsService;
import com.lucifer.utils.Constant;
import com.lucifer.utils.PageInfoWriter;
import com.lucifer.utils.PageUtil;
import com.lucifer.utils.Result;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by liufx on 17/2/6.
 */
//@Controller
//@RequestMapping("/news")
public class WebNewsController {
    @Resource
    private NewsDao newsDao;

    @Resource
    private NewsService newsService;

    @Resource
    private CarouseDao carouseDao;

    @Resource
    private NewsSearchService newsSearchService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String newsIndex(HttpServletRequest request, @RequestParam(value = "categoryId",required=false,defaultValue="") Long categoryId,
                           @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<News> newsList = newsDao.webNewsList(categoryId,offset,pageSize);
        request.setAttribute("newsList",newsList);

        //Integer matchRecordCount=newsDao.matchRecordCount(title);

        //Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);

//        PageUtil pageUtil = new PageUtil(request);
//        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
//        request.setAttribute("pageDiv",pageDiv);
//        request.setAttribute("title",title);

//        List<News> hotList = newsDao.webHotNewsList();
//        request.setAttribute("hotList",hotList);
//
//
//        List<NewsRecommend> newsRecommendList = newsService.newsRecommendList();
//        request.setAttribute("recommendList",newsRecommendList);
        newsService.loadNewsRightData(request);

        List<NewsCategory> newsCategoryList = newsDao.cmsNewsCategoryList();
        request.setAttribute("newsCategoryList",newsCategoryList);

        Carousel carousel = carouseDao.firstNewsCarousel();
        request.setAttribute("carousel",carousel);

        return "/web/news/index";
    }

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String newsList(HttpServletRequest request, @RequestParam(value = "categoryId",required=false,defaultValue="") Long categoryId,
                           @RequestParam(value = "page",required=false,defaultValue="1")Integer page){

        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<News> newsList = newsDao.webNewsList(categoryId,offset,pageSize);
        request.setAttribute("newsList",newsList);

        return "/web/news/list";
    }

    @RequestMapping(value="/search",method = RequestMethod.GET)
    public String newsSearch(HttpServletRequest request, @RequestParam(value = "title",required=false,defaultValue="") String title,
                            @RequestParam(value = "page",required=false,defaultValue="1")Integer page) throws IOException, JSONException {
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;

        PageInfoWriter pageInfo = newsSearchService.searchList(title,page,pageSize);
        logger.info("pageInfo.getDataList().size(): {}",pageInfo.getDataList().size());
        //List<News> newsList = newsDao.cmsNewsList(title,offset,pageSize);
        request.setAttribute("newsList",pageInfo.getDataList());

        Integer matchRecordCount = pageInfo.getAllRecordCount();


        //Integer matchRecordCount=newsDao.matchRecordCount(title);

        //Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);

//        PageUtil pageUtil = new PageUtil(request);
//        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
//        request.setAttribute("pageDiv",pageDiv);
//        request.setAttribute("title",title);

        newsService.loadNewsRightData(request);

        List<NewsCategory> newsCategoryList = newsDao.cmsNewsCategoryList();
        request.setAttribute("newsCategoryList",newsCategoryList);
        request.setAttribute("title",title);

        return "/web/news/search";
    }

    @RequestMapping(value="/search-list",method = RequestMethod.GET)
    public String searchList(HttpServletRequest request, @RequestParam(value = "title",required=false,defaultValue="") String title,
                           @RequestParam(value = "page",required=false,defaultValue="1")Integer page) throws IOException, JSONException {

        Integer pageSize = Constant.PAGESIZE;
        //Integer offset = (page-1) * pageSize;
        PageInfoWriter pageInfo = newsSearchService.searchList(title,page,pageSize);
        logger.info("pageInfo.getDataList().size(): {}",pageInfo.getDataList().size());
        //List<News> newsList = newsDao.cmsNewsList(title,offset,pageSize);
        request.setAttribute("newsList",pageInfo.getDataList());
//        List<News> newsList = newsService.searchList(title,offset,pageSize);
//        request.setAttribute("newsList",newsList);
        Integer matchRecordCount = pageInfo.getAllRecordCount();
        return "/web/news/search-list";
    }

    @RequestMapping(value="/{id}/detail",method = RequestMethod.GET)
    public String newsDetail(HttpServletRequest request,@PathVariable Long id){
        newsService.loadNewsRightData(request);

        News news = newsDao.getNews(id);
        request.setAttribute("entity",news);

        return "/web/news/detail";
    }

    @RequestMapping(value="/search-test",method = RequestMethod.GET)
    @ResponseBody
    public Object searchTest( @RequestParam(value = "title",required=false,defaultValue="") String title) throws IOException, JSONException {
        PageInfoWriter pageInfo = newsSearchService.searchList(title,1,20);
        return pageInfo;
    }
}
