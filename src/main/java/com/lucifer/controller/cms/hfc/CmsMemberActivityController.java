package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.MemberActivityDao;
import com.lucifer.dao.hfc.SummitDao;
import com.lucifer.model.hfc.MemberActivity;
import com.lucifer.model.hfc.MemberActivityCategory;
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
//@RequestMapping("/cms/member-activity")
public class CmsMemberActivityController {

    @Resource
    private MemberActivityDao memberActivityDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/category/list",method = RequestMethod.GET)
    public String categoryList(HttpServletRequest request){
        List<MemberActivityCategory> memberActivityCategoryList = memberActivityDao.memberActivityCategoryList();
        request.setAttribute("memberActivityCategoryList",memberActivityCategoryList);
        return "/cms/member-activity/category_list";
    }

    @RequestMapping(value="/category/list.json",method = RequestMethod.GET)
    @ResponseBody
    public List<MemberActivityCategory>  categoryListForJSON(HttpServletRequest request){
        List<MemberActivityCategory> memberActivityCategoryList = memberActivityDao.memberActivityCategoryList();
        return memberActivityCategoryList;
    }

    @RequestMapping(value="/category/add",method = RequestMethod.POST)
    public String categoryAdd(MemberActivityCategory memberActivityCategory) {
        memberActivityDao.addMemberActivityCategory(memberActivityCategory);
        return "redirect:/cms/member-activity/category/list";
    }

    @RequestMapping(value="/category/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result categoryDelete(Long id) {
        memberActivityDao.deleteMemberActivityCategory(id);
        return Result.ok();
    }

    @RequestMapping(value="/category/{id}/update",method = RequestMethod.GET)
    public String categoryUpdateInput(@PathVariable Long id, HttpServletRequest request) {
        MemberActivityCategory category = memberActivityDao.getMemberActivityCategory(id);
        request.setAttribute("entity",category);
        return "/cms/member-activity/category_update";
    }

    @RequestMapping(value="/category/update",method = RequestMethod.POST)
    @ResponseBody
    public Result categoryUpdateSubmit(MemberActivityCategory memberActivityCategory){
        memberActivityDao.updateMemberActivityCategory(memberActivityCategory);
        return Result.ok();
    }





    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String memberActivityList(HttpServletRequest request,@RequestParam(value = "title",required=false,defaultValue="") String title,
                                      @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<MemberActivity> memberActivityList = memberActivityDao.memberActivityList(null,title,offset,pageSize);
        request.setAttribute("memberActivityList",memberActivityList);

        Integer matchRecordCount=memberActivityDao.matchRecordCount(title);

        Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);

        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        request.setAttribute("title",title);
        return "/cms/member-activity/list";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String memberActivityListAddInput(HttpServletRequest request){
        List<MemberActivityCategory> memberActivityCategoryList = memberActivityDao.memberActivityCategoryList();
        request.setAttribute("memberActivityCategoryList",memberActivityCategoryList);

        MemberActivity memberActivity = new MemberActivity();
        request.setAttribute("entity",memberActivity);
        memberActivity.setLogo("/cms/images/logo.png");
        memberActivity.setPublishAt(DateUtils.now());
        return "/cms/member-activity/add";
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result memberActivityAddSubmit(MemberActivity memberActivity){
        logger.info("memberActivity AddSubmit has been called");
        memberActivityDao.insertMemberActivity(memberActivity);
        return Result.ok();
    }
    @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String memberActivityUpdateInput(HttpServletRequest request,@PathVariable Long id){
        MemberActivity memberActivity = memberActivityDao.getMemberActivity(id);
        request.setAttribute("entity",memberActivity);

        List<MemberActivityCategory> memberActivityCategoryList  = memberActivityDao.memberActivityCategoryList();
        request.setAttribute("memberActivityCategoryList",memberActivityCategoryList);
        return "/cms/member-activity/update";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Result memberActivityUpdateSubmit(MemberActivity memberActivity){
        memberActivityDao.updateMemberActivity(memberActivity);

        return Result.ok();
    }

    @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteMemberActivity(@PathVariable Long id) {
        memberActivityDao.deleteMemberActivity(id);
        return Result.ok();
    }

}
