package com.lucifer.controller.web;

import com.lucifer.dao.hfc.CulturalFinanceDao;
import com.lucifer.model.hfc.*;
import com.lucifer.service.hfc.NewsService;
import com.lucifer.utils.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liufx on 17/2/18.
 */
//@Controller
//@RequestMapping("/cultural-finance")
public class WebCulturalFinanceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CulturalFinanceDao culturalFinanceDao;

    @Resource
    private NewsService newsService;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String newsIndex(HttpServletRequest request, @RequestParam(value = "categoryId",required=false,defaultValue="") Long categoryId,
                            @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<CulturalFinance> entityList = culturalFinanceDao.culturalFinanceList(categoryId,null,offset,pageSize);
        request.setAttribute("entityList",entityList);

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

        List<CulturalFinanceCategory> culturalFinanceCategoryList = culturalFinanceDao.culturalFinanceCategoryList();
        request.setAttribute("categoryList",culturalFinanceCategoryList);



        return "/web/cultural-finance/index";
    }

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String entityList(HttpServletRequest request, @RequestParam(value = "categoryId",required=false,defaultValue="") Long categoryId,
                           @RequestParam(value = "page",required=false,defaultValue="1")Integer page){

        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<CulturalFinance> entityList  = culturalFinanceDao.culturalFinanceList(categoryId,null,offset,pageSize);
        request.setAttribute("entityList",entityList);

        return "/web/cultural-finance/list";
    }

    @RequestMapping(value="/{id}/detail",method = RequestMethod.GET)
    public String detail(HttpServletRequest request,@PathVariable Long id){
        newsService.loadNewsRightData(request);

        CulturalFinance culturalFinance = culturalFinanceDao.getCulturalFinance(id);
        request.setAttribute("entity",culturalFinance);

        return "/web/cultural-finance/detail";
    }
}
