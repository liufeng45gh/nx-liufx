package com.lucifer.controller.web;

import com.lucifer.dao.hfc.ArtistInterviewDao;
import com.lucifer.dao.hfc.ArtistLectureDao;
import com.lucifer.model.hfc.ArtistInterview;
import com.lucifer.model.hfc.ArtistLecture;
import com.lucifer.model.hfc.ArtistRecommend;
import com.lucifer.service.hfc.ArtistService;
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
 * Created by liufx on 17/2/10.
 */

//@Controller
//@RequestMapping("/artist")
public class WebArtistLectureController {

    @Resource
    private ArtistService artistService;

    @Resource
    private ArtistLectureDao artistLectureDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/lecture",method = RequestMethod.GET)
    public String interview(HttpServletRequest request, @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<ArtistLecture> artistLectureList = artistLectureDao.lectureList(null,offset,pageSize);
        request.setAttribute("artistLectureList",artistLectureList);

        List<ArtistRecommend> artistRecommendList = artistService.artistRecommendList();
        request.setAttribute("artistRecommendList",artistRecommendList);
        return "/web/artist/lecture";
    }

    @RequestMapping(value="/lecture-list",method = RequestMethod.GET)
    public String list(HttpServletRequest request, @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<ArtistLecture> artistLectureList = artistLectureDao.lectureList(null,offset,pageSize);
        request.setAttribute("artistLectureList",artistLectureList);
        return "/web/artist/lecture-list";
    }

    @RequestMapping(value="/lecture/{id}/detail",method = RequestMethod.GET)
    public String detail(HttpServletRequest request,@PathVariable Long id){
        List<ArtistRecommend> artistRecommendList = artistService.artistRecommendList();
        request.setAttribute("artistRecommendList",artistRecommendList);

        ArtistLecture artistLecture = artistLectureDao.getArtistLecture(id);
        request.setAttribute("entity",artistLecture);
        return "/web/artist/lecture-detail";
    }

}
