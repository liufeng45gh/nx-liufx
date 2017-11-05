package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.CulturalFinanceDao;
import com.lucifer.model.hfc.CulturalFinance;
import com.lucifer.model.hfc.CulturalFinanceCategory;
import com.lucifer.model.hfc.News;
import com.lucifer.model.hfc.NewsCategory;
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
 * Created by liufx on 17/1/16.
 */
//@Controller
//@RequestMapping("/cms/cultural-finance")
public class CmsCulturalFinanceController {

    @Resource
    private CulturalFinanceDao culturalFinanceDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/category/list",method = RequestMethod.GET)
    public String categoryList(HttpServletRequest request){
        List<CulturalFinanceCategory> culturalFinanceCategoryList = culturalFinanceDao.culturalFinanceCategoryList();
        request.setAttribute("culturalFinanceCategoryList",culturalFinanceCategoryList);
        return "/cms/cultural-finance/category_list";
    }

    @RequestMapping(value="/category/list.json",method = RequestMethod.GET)
    @ResponseBody
    public List<CulturalFinanceCategory>  categoryListForJSON(HttpServletRequest request){
        List<CulturalFinanceCategory> culturalFinanceCategoryList = culturalFinanceDao.culturalFinanceCategoryList();
        return culturalFinanceCategoryList;
    }

    @RequestMapping(value="/category/add",method = RequestMethod.POST)
    public String categoryAdd(CulturalFinanceCategory culturalFinanceCategory) {
        culturalFinanceDao.addCulturalFinanceCategory(culturalFinanceCategory);
        return "redirect:/cms/cultural-finance/category/list";
    }

    @RequestMapping(value="/category/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result categoryDelete(Long id) {
        culturalFinanceDao.deleteCulturalFinanceCategory(id);
        return Result.ok();
    }

    @RequestMapping(value="/category/{id}/update",method = RequestMethod.GET)
    public String categoryUpdateInput(@PathVariable Long id, HttpServletRequest request) {
        CulturalFinanceCategory category = culturalFinanceDao.getCulturalFinanceCategory(id);
        request.setAttribute("entity",category);
        return "/cms/cultural-finance/category_update";
    }

    @RequestMapping(value="/category/update",method = RequestMethod.POST)
    @ResponseBody
    public Result categoryUpdateSubmit(CulturalFinanceCategory culturalFinanceCategory){
        culturalFinanceDao.updateCulturalFinanceCategory(culturalFinanceCategory);
        return Result.ok();
    }





    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String culturalFinanceList(HttpServletRequest request,@RequestParam(value = "title",required=false,defaultValue="") String title,
                           @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<CulturalFinance> culturalFinanceList = culturalFinanceDao.culturalFinanceList(null,title,offset,pageSize);
        request.setAttribute("culturalFinanceList",culturalFinanceList);

        Integer matchRecordCount=culturalFinanceDao.matchRecordCount(title);

        Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);

        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        request.setAttribute("title",title);
        return "/cms/cultural-finance/list";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String culturalFinanceAddInput(HttpServletRequest request){
        List<CulturalFinanceCategory> culturalFinanceCategoryList = culturalFinanceDao.culturalFinanceCategoryList();
        request.setAttribute("culturalFinanceCategoryList",culturalFinanceCategoryList);

        CulturalFinance culturalFinance = new CulturalFinance();
        request.setAttribute("entity",culturalFinance);
        culturalFinance.setLogo("/cms/images/logo.png");
        culturalFinance.setPublishAt(DateUtils.now());
        return "/cms/cultural-finance/add";
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result culturalFinanceAddSubmit(CulturalFinance culturalFinance){
        logger.info("CulturalFinance AddSubmit has been called");
        culturalFinanceDao.insertCulturalFinance(culturalFinance);
        return Result.ok();
    }
    @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String culturalFinanceUpdateInput(HttpServletRequest request,@PathVariable Long id){
        CulturalFinance culturalFinance = culturalFinanceDao.getCulturalFinance(id);
        request.setAttribute("entity",culturalFinance);
        request.setAttribute("publishAt",culturalFinance.getPublishAt());

        List<CulturalFinanceCategory> culturalFinanceCategoryList = culturalFinanceDao.culturalFinanceCategoryList();
        request.setAttribute("culturalFinanceCategoryList",culturalFinanceCategoryList);
        return "/cms/cultural-finance/update";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Result culturalFinanceUpdateSubmit(CulturalFinance culturalFinance){
        culturalFinanceDao.updateCulturalFinance(culturalFinance);

        return Result.ok();
    }

    @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteCulturalFinance(@PathVariable Long id) {
        culturalFinanceDao.deleteCulturalFinance(id);
        return Result.ok();
    }


}
