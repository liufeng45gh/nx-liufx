package com.lucifer.controller.web;

import com.lucifer.dao.hfc.MemberActivityDao;
import com.lucifer.model.hfc.CulturalFinance;
import com.lucifer.model.hfc.CulturalFinanceCategory;
import com.lucifer.model.hfc.MemberActivity;
import com.lucifer.model.hfc.MemberActivityCategory;
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
//@RequestMapping("/member-activity")
public class WebMemberActivityController {

    @Resource
    private NewsService newsService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MemberActivityDao memberActivityDao;


    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String newsIndex(HttpServletRequest request, @RequestParam(value = "categoryId",required=false,defaultValue="") Long categoryId,
                            @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<MemberActivity> entityList = memberActivityDao.memberActivityList(categoryId,null,offset,pageSize);
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

        List<MemberActivityCategory> categoryList = memberActivityDao.memberActivityCategoryList();
        request.setAttribute("categoryList",categoryList);



        return "/web/member-activity/index";
    }

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String entityList(HttpServletRequest request, @RequestParam(value = "categoryId",required=false,defaultValue="") Long categoryId,
                             @RequestParam(value = "page",required=false,defaultValue="1")Integer page){

        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<MemberActivity> entityList = memberActivityDao.memberActivityList(categoryId,null,offset,pageSize);
        request.setAttribute("entityList",entityList);

        return "/web/member-activity/list";
    }

    @RequestMapping(value="/{id}/detail",method = RequestMethod.GET)
    public String detail(HttpServletRequest request,@PathVariable Long id){
        newsService.loadNewsRightData(request);

        MemberActivity memberActivity = memberActivityDao.getMemberActivity(id);
        request.setAttribute("entity",memberActivity);

        return "/web/member-activity/detail";
    }
}
