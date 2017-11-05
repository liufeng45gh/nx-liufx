package com.lucifer.controller.web;

import com.lucifer.dao.hfc.AtlasDao;
import com.lucifer.dao.hfc.PictureDao;
import com.lucifer.model.hfc.Artist;
import com.lucifer.model.hfc.ArtistRecommend;
import com.lucifer.model.hfc.Atlas;
import com.lucifer.model.hfc.Picture;
import com.lucifer.utils.Constant;
import com.lucifer.utils.PageUtil;
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
 * Created by fx on 2017/5/5.
 */
//@Controller
//@RequestMapping("/atlas")
public class WebAtlasController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AtlasDao atlasDao;

    @Resource
    private PictureDao pictureDao;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request, @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<Atlas> atlasList = atlasDao.atlasList(offset,pageSize);
        request.setAttribute("atlasList",atlasList);

        //Integer totalPageCount = Integer.MAX_VALUE;
        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(Constant.maxPage,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);


        return "/web/atlas/index";
    }

    @RequestMapping(value="/{id}/detail",method = RequestMethod.GET)
    public String show(HttpServletRequest request,@PathVariable Long id){
        Atlas atlas = atlasDao.getAtlas(id);
        request.setAttribute("atlas",atlas);
        List<Picture> pictureList = pictureDao.pictureList(id);
        request.setAttribute("pictureList",pictureList);
        return "/web/atlas/detail";
    }
}
