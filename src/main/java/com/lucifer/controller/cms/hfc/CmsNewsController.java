package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.NewsDao;
import com.lucifer.model.hfc.News;
import com.lucifer.model.hfc.NewsCategory;
import com.lucifer.model.hfc.NewsRecommend;
import com.lucifer.service.hfc.NewsService;
import com.lucifer.utils.Constant;
import com.lucifer.utils.DateUtils;
import com.lucifer.utils.PageUtil;
import com.lucifer.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/15.
 */
@Controller
@RequestMapping("/cms")
public class CmsNewsController {

    @Resource
    private NewsDao newsDao;

    @Resource
    private NewsService newsService;

    private  Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/news/list",method = RequestMethod.GET)
    public String newsList(HttpServletRequest request,@RequestParam(value = "title",required=false,defaultValue="") String title,
                            @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<News> newsList = newsDao.cmsNewsList(title,offset,pageSize);
        request.setAttribute("newsList",newsList);

        Integer matchRecordCount=newsDao.matchRecordCount(title);

        Integer totalPageCount=PageUtil.getTotalPageCount(matchRecordCount, pageSize);

        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        request.setAttribute("title",title);
        return "/cms/news/list";
    }

    @RequestMapping(value="/news/add",method = RequestMethod.GET)
    public String newsAddInput(HttpServletRequest request){
        List<NewsCategory> newsCategoryList = newsDao.cmsNewsCategoryList();
        request.setAttribute("newsCategoryList",newsCategoryList);
        Date publishAt = DateUtils.now();
        request.setAttribute("publishAt",publishAt);
        News news = new News();
        request.setAttribute("news",news);
        news.setLogo("/cms/images/logo.png");
        return "/cms/news/add";
    }

    @RequestMapping(value="/news/add",method = RequestMethod.POST)
    @ResponseBody
    public Result newsAddSubmit(News news){
        logger.info("newsAddSubmit has been called");
        newsDao.insertNews(news);
        return Result.ok();
    }
    @RequestMapping(value="/news/{id}/update",method = RequestMethod.GET)
    public String newsUpdateInput(HttpServletRequest request,@PathVariable Long id){
        News news = newsDao.getNews(id);
        request.setAttribute("news",news);
        request.setAttribute("publishAt",news.getPublishAt());

        List<NewsCategory> newsCategoryList = newsDao.cmsNewsCategoryList();
        request.setAttribute("newsCategoryList",newsCategoryList);
        return "/cms/news/update";
    }

    @RequestMapping(value="/news/update",method = RequestMethod.POST)
    @ResponseBody
    public Result newsUpdateSubmit(News news){
       newsDao.updateNews(news);

        return Result.ok();
    }

    @RequestMapping(value="/news/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteNews(@PathVariable Long id) {
        newsDao.deleteNews(id);
        return Result.ok();
    }

    @RequestMapping(value="/news/category/list",method = RequestMethod.GET)
    public String categoryList(HttpServletRequest request){
        List<NewsCategory> newsCategoryList = newsDao.cmsNewsCategoryList();
        request.setAttribute("newsCategoryList",newsCategoryList);
        return "/cms/news/category_list";
    }

    @RequestMapping(value="/news/category/list.json",method = RequestMethod.GET)
    @ResponseBody
    public List<NewsCategory>  categoryListForJSON(HttpServletRequest request){
        List<NewsCategory> newsCategoryList = newsDao.cmsNewsCategoryList();
        return newsCategoryList;
    }

    @RequestMapping(value="/news/category/add",method = RequestMethod.POST)
    public String categoryAdd(NewsCategory newsCategory) {
        newsDao.addNewsCategory(newsCategory);
        return "redirect:/cms/news/category/list";
    }

    @RequestMapping(value="/news/category/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result newsCategoryDelete(Long id) {
        newsDao.deleteNewsCategory(id);
        return Result.ok();
    }

    @RequestMapping(value="/news/category/{id}/update",method = RequestMethod.GET)
    public String newsCategoryUpdateInput(@PathVariable Long id,HttpServletRequest request) {
        NewsCategory newsCategory = newsDao.getNewsCategory(id);
        request.setAttribute("newsCategory",newsCategory);
        return "/cms/news/category_update";
    }

    @RequestMapping(value="/news/category/update",method = RequestMethod.POST)
    @ResponseBody
    public Result newsCategoryUpdateSubmit(NewsCategory newsCategory){
        newsDao.newsCategoryUpdate(newsCategory);
        return Result.ok();
    }

    @RequestMapping(value="/news/recommend/list",method = RequestMethod.GET)
    public String recommendList(HttpServletRequest request) {
        List<NewsRecommend> newsRecommendList = newsService.newsRecommendList();
        request.setAttribute("newsRecommendList",newsRecommendList);
        return "/cms/news/recommend_list";
    }

    @RequestMapping(value="/news/recommend/{id}/update",method = RequestMethod.GET)
    public String updateNewsRecommend(@PathVariable Long id,HttpServletRequest request){
        NewsRecommend newsRecommend = newsDao.getNewsRecommend(id);
        request.setAttribute("newsRecommend",newsRecommend);
        return "/cms/news/recommend_update";
    }

    @RequestMapping(value="/news/recommend/update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateNewsRecommendSubmit(NewsRecommend newsRecommend){
        newsDao.updateNewsRecommend(newsRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/news/recommend/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteNewsRecommend(@PathVariable Long id){
        newsDao.deleteNewsRecommend(id);
        return Result.ok();
    }

    @RequestMapping(value="/news/recommend/add",method = RequestMethod.POST)
    @ResponseBody
    public Result addNewsRecommendSubmit(NewsRecommend newsRecommend){
        newsDao.addNewsRecommend(newsRecommend);
        return Result.ok();
    }

}
