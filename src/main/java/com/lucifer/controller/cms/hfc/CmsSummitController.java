package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.CulturalFinanceDao;
import com.lucifer.dao.hfc.SummitDao;
import com.lucifer.model.hfc.CulturalFinance;
import com.lucifer.model.hfc.CulturalFinanceCategory;
import com.lucifer.model.hfc.Summit;
import com.lucifer.model.hfc.SummitCategory;
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
import java.util.List;

/**
 * Created by liufx on 17/1/16.
 */
//@Controller
//@RequestMapping("/cms/summit")
public class CmsSummitController {

    @Resource
    private SummitDao summitDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/category/list",method = RequestMethod.GET)
    public String categoryList(HttpServletRequest request){
        List<SummitCategory> summitCategoryList = summitDao.summitCategoryList();
        request.setAttribute("summitCategoryList",summitCategoryList);
        return "/cms/summit/category_list";
    }

    @RequestMapping(value="/category/list.json",method = RequestMethod.GET)
    @ResponseBody
    public List<SummitCategory>  categoryListForJSON(HttpServletRequest request){
        List<SummitCategory> summitCategoryList = summitDao.summitCategoryList();
        return summitCategoryList;
    }

    @RequestMapping(value="/category/add",method = RequestMethod.POST)
    public String categoryAdd(SummitCategory summitCategory) {
        summitDao.addSummitCategory(summitCategory);
        return "redirect:/cms/summit/category/list";
    }

    @RequestMapping(value="/category/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result categoryDelete(Long id) {
        summitDao.deleteSummitCategory(id);
        return Result.ok();
    }

    @RequestMapping(value="/category/{id}/update",method = RequestMethod.GET)
    public String categoryUpdateInput(@PathVariable Long id, HttpServletRequest request) {
        SummitCategory category = summitDao.getSummitCategory(id);
        request.setAttribute("entity",category);
        return "/cms/summit/category_update";
    }

    @RequestMapping(value="/category/update",method = RequestMethod.POST)
    @ResponseBody
    public Result categoryUpdateSubmit(SummitCategory summitCategory){
        summitDao.updateSummitCategory(summitCategory);
        return Result.ok();
    }





    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String culturalFinanceList(HttpServletRequest request,@RequestParam(value = "title",required=false,defaultValue="") String title,
                                      @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<Summit> summitList = summitDao.summitList(title,offset,pageSize);
        request.setAttribute("summitList",summitList);

        Integer matchRecordCount=summitDao.matchRecordCount(title);

        Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);

        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        request.setAttribute("title",title);
        return "/cms/summit/list";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String culturalFinanceAddInput(HttpServletRequest request){
        List<SummitCategory> summitCategoryList = summitDao.summitCategoryList();
        request.setAttribute("summitCategoryList",summitCategoryList);

        Summit summit = new Summit();
        request.setAttribute("entity",summit);
        summit.setLogo("/cms/images/logo.png");
        summit.setPublishAt(DateUtils.now());
        return "/cms/summit/add";
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result summitAddSubmit(Summit summit){
        logger.info("Summit AddSubmit has been called");
        summitDao.insertSummit(summit);
        return Result.ok();
    }
    @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String summitUpdateInput(HttpServletRequest request,@PathVariable Long id){
        Summit summit = summitDao.getSummit(id);
        request.setAttribute("entity",summit);

        List<SummitCategory> summitCategoryList = summitDao.summitCategoryList();
        request.setAttribute("summitCategoryList",summitCategoryList);
        return "/cms/summit/update";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Result summitUpdateSubmit(Summit summit){
        summitDao.updateSummit(summit);

        return Result.ok();
    }

    @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteSummit(@PathVariable Long id) {
        summitDao.deleteSummit(id);
        return Result.ok();
    }

}
