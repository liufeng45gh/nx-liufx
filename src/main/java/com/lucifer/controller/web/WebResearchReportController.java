package com.lucifer.controller.web;

import com.lucifer.dao.hfc.ResearchReportDao;
import com.lucifer.model.hfc.MemberActivity;
import com.lucifer.model.hfc.MemberActivityCategory;
import com.lucifer.model.hfc.ResearchReport;
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
 * Created by Administrator on 2017/2/18.
 */
//@Controller
//@RequestMapping("/research-report")
public class WebResearchReportController {

    @Resource
    private NewsService newsService;

    @Resource
    private ResearchReportDao researchReportDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request,
                            @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<ResearchReport> entityList = researchReportDao.researchReportList(null,offset,pageSize);
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





        return "/web/research-report/index";
    }

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String entityList(HttpServletRequest request,
                             @RequestParam(value = "page",required=false,defaultValue="1")Integer page){

        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<ResearchReport> entityList = researchReportDao.researchReportList(null,offset,pageSize);
        request.setAttribute("entityList",entityList);

        return "/web/research-report/list";
    }

    @RequestMapping(value="/{id}/detail",method = RequestMethod.GET)
    public String detail(HttpServletRequest request,@PathVariable Long id){
        newsService.loadNewsRightData(request);

        ResearchReport researchReport = researchReportDao.getResearchReport(id);
        request.setAttribute("entity",researchReport);

        return "/web/research-report/detail";
    }
}
