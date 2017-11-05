package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.ArtistLectureDao;
import com.lucifer.model.hfc.ArtistInterview;
import com.lucifer.model.hfc.ArtistLecture;
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
 * Created by liufx on 17/1/22.
 */
//@Controller
//@RequestMapping("/cms/artist/lecture")
public class CmsArtistLectureController {

    @Resource
    private ArtistLectureDao artistLectureDao;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String lectureList(HttpServletRequest request, @RequestParam(value = "title",required=false,defaultValue="") String title,
                                @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<ArtistLecture> lectureList = artistLectureDao.lectureList(title,offset,pageSize);
        request.setAttribute("lectureList",lectureList);

        Integer matchRecordCount=artistLectureDao.matchRecordCount(title);

        Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);

        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        request.setAttribute("title",title);
        return "/cms/artist/lecture_list";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String lectureAddInput(HttpServletRequest request){
        ArtistLecture artistLecture = new ArtistLecture();
        artistLecture.setLogo("/cms/images/logo.png");
        request.setAttribute("entity",artistLecture);
        artistLecture.setPublishAt(DateUtils.now());
        return "/cms/artist/lecture_add";
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result artistLectureAddSubimt(ArtistLecture artistLecture){
        artistLectureDao.insertArtistLecture(artistLecture);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String artistUpdateInput(HttpServletRequest request,@PathVariable Long id){
        ArtistLecture artistLecture = artistLectureDao.getArtistLecture(id);
        request.setAttribute("entity",artistLecture);
        return "/cms/artist/lecture_update";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST)
    @ResponseBody
    public Result artistUpdateSubmit(ArtistLecture artistLecture){
        artistLectureDao.updateArtistLecture(artistLecture);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result artistDelete(HttpServletRequest request,@PathVariable Long id){
        artistLectureDao.deleteArtistLecture(id);
        return Result.ok();
    }


}
