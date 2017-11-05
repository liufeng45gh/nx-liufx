package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.AppreciateDao;
import com.lucifer.dao.hfc.AppreciateKnowledgeDao;
import com.lucifer.model.hfc.Appreciate;
import com.lucifer.model.hfc.AppreciateCategory;
import com.lucifer.model.hfc.AppreciateKnowledge;
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
 * Created by fx on 2017/1/30.
 */
//@Controller
//@RequestMapping("/cms/appreciate-knowledge")
public class CmsAppreciateKnowledgeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AppreciateKnowledgeDao appreciateKnowledgeDao;

    @Resource
    private AppreciateDao appreciateDao;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String appreciateList(HttpServletRequest request, @RequestParam(value = "title",required=false,defaultValue="") String title,
                                 @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<AppreciateKnowledge> appreciateKnowledgeList = appreciateKnowledgeDao.appreciateKnowledgeList(title,offset,pageSize);
        request.setAttribute("appreciateKnowledgeList",appreciateKnowledgeList);

        Integer matchRecordCount=appreciateKnowledgeDao.matchRecordCount(title);

        Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);

        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        request.setAttribute("title",title);
        return "/cms/appreciate-knowledge/list";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String appreciateKnowledgeAddInput(HttpServletRequest request){
        List<AppreciateCategory> appreciateCategoryList = appreciateDao.appreciateCategoryList();
        request.setAttribute("appreciateCategoryList",appreciateCategoryList);

        AppreciateKnowledge appreciateKnowledge = new AppreciateKnowledge();
        request.setAttribute("entity",appreciateKnowledge);
        appreciateKnowledge.setLogo("/cms/images/logo.png");
        appreciateKnowledge.setPublishAt(DateUtils.now());
        return "/cms/appreciate-knowledge/add";
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result appreciateKnowledgeAddSubmit(AppreciateKnowledge appreciateKnowledge){
        logger.info(" appreciateKnowledge AddSubmit has been called");
        appreciateKnowledgeDao.insertAppreciateKnowledge(appreciateKnowledge);
        return Result.ok();
    }
    @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String appreciateKnowledgeUpdateInput(HttpServletRequest request,@PathVariable Long id){
        AppreciateKnowledge appreciateKnowledge = appreciateKnowledgeDao.getAppreciateKnowledge(id);
        request.setAttribute("entity",appreciateKnowledge);
//        request.setAttribute("publishAt",news.getPublishAt());

        List<AppreciateCategory> appreciateCategoryList = appreciateDao.appreciateCategoryList();
        request.setAttribute("appreciateCategoryList",appreciateCategoryList);
        return "/cms/appreciate-knowledge/update";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Result appreciateKnowledgeUpdateSubmit(AppreciateKnowledge appreciateKnowledge){
        appreciateKnowledgeDao.updateAppreciateKnowledge(appreciateKnowledge);

        return Result.ok();
    }

    @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteAppreciateKnowledge(@PathVariable Long id) {
        appreciateKnowledgeDao.deleteAppreciateKnowledge(id);
        return Result.ok();
    }

}
