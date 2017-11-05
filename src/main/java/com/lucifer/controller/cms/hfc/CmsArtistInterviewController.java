package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.ArtistInterviewDao;
import com.lucifer.model.hfc.ArtistInterview;
import com.lucifer.model.hfc.News;
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
 * Created by Administrator on 2017/1/21.
 */

//@Controller
//@RequestMapping("/cms/artist/interview")
public class CmsArtistInterviewController {

    @Resource
    private ArtistInterviewDao artistInterviewDao;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String interviewList(HttpServletRequest request, @RequestParam(value = "title",required=false,defaultValue="") String title,
                                @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<ArtistInterview> interviewList = artistInterviewDao.interviewList(title,offset,pageSize);
        request.setAttribute("interviewList",interviewList);

        Integer matchRecordCount=artistInterviewDao.matchRecordCount(title);

        Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);

        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        request.setAttribute("title",title);
        return "/cms/artist/interview_list";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String interviewAddInput(HttpServletRequest request){
        ArtistInterview artistInterview = new ArtistInterview();
        artistInterview.setLogo("/cms/images/logo.png");
        request.setAttribute("entity",artistInterview);
        artistInterview.setPublishAt(DateUtils.now());
        return "/cms/artist/interview_add";
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result interviewAddSubimt(ArtistInterview artistInterview){
        artistInterviewDao.insertArtistInterview(artistInterview);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String interviewUpdateInput(HttpServletRequest request,@PathVariable Long id){
        ArtistInterview artistInterview = artistInterviewDao.getArtistInterview(id);
        request.setAttribute("entity",artistInterview);
        return "/cms/artist/interview_update";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Result interviewUpdateSubmit(ArtistInterview artistInterview){
        artistInterviewDao.updateArtistInterview(artistInterview);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result interviewDelete(HttpServletRequest request,@PathVariable Long id){
        artistInterviewDao.deleteArtistInterview(id);
        return Result.ok();
    }


}
