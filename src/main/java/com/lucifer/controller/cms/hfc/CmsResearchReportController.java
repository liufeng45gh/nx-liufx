package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.ArtistLectureDao;
import com.lucifer.dao.hfc.ResearchReportDao;
import com.lucifer.model.hfc.ArtistLecture;
import com.lucifer.model.hfc.ResearchReport;
import com.lucifer.utils.Constant;
import com.lucifer.utils.DateUtils;
import com.lucifer.utils.PageUtil;
import com.lucifer.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liufx on 17/1/16.
 */
//@Controller
//@RequestMapping("/cms/research-report")
public class CmsResearchReportController {
    @Resource
    private ResearchReportDao researchReportDao;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String researchReportList(HttpServletRequest request, @RequestParam(value = "title",required=false,defaultValue="") String title,
                              @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<ResearchReport> researchReportList = researchReportDao.researchReportList(title,offset,pageSize);
        request.setAttribute("researchReportList",researchReportList);

        Integer matchRecordCount=researchReportDao.matchRecordCount(title);

        Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);

        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        request.setAttribute("title",title);
        return "/cms/research/list";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String researchReportAddInput(HttpServletRequest request){
        ResearchReport researchReport = new ResearchReport();
        researchReport.setLogo("/cms/images/logo.png");
        request.setAttribute("entity",researchReport);
        researchReport.setPublishAt(DateUtils.now());
        return "/cms/research/add";
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result researchReportAddSubimt(ResearchReport researchReport){
        researchReportDao.insertResearchReport(researchReport);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String researchReportUpdateInput(HttpServletRequest request,@PathVariable Long id){
        ResearchReport researchReport = researchReportDao.getResearchReport(id);
        request.setAttribute("entity",researchReport);
        return "/cms/research/update";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Result researchReportUpdateSubmit(ResearchReport researchReport){
        researchReportDao.updateResearchReport(researchReport);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result researchReportDelete(HttpServletRequest request,@PathVariable Long id){
        researchReportDao.deleteResearchReport(id);
        return Result.ok();
    }
}
