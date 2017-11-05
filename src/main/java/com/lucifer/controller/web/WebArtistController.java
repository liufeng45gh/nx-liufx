package com.lucifer.controller.web;

import com.lucifer.dao.hfc.ArtistDao;
import com.lucifer.model.hfc.Artist;
import com.lucifer.model.hfc.ArtistRecommend;
import com.lucifer.service.hfc.ArtistSearchService;
import com.lucifer.service.hfc.ArtistService;
import com.lucifer.utils.Constant;
import com.lucifer.utils.PageInfoWriter;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by liufx on 17/2/9.
 */
//@Controller
//@RequestMapping("/artist")
public class WebArtistController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ArtistDao artistDao;

    @Resource
    private ArtistService artistService;

    @Resource
    private ArtistSearchService artistSearchService;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request, @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<Artist> artistList = artistDao.artistList(null,offset,pageSize);
        request.setAttribute("artistList",artistList);

        List<ArtistRecommend> artistRecommendList = artistService.artistRecommendList();
        request.setAttribute("artistRecommendList",artistRecommendList);
        return "/web/artist/index";
    }


    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(HttpServletRequest request, @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<Artist> artistList = artistDao.artistList(null,offset,pageSize);
        request.setAttribute("artistList",artistList);
        return "/web/artist/list";
    }

    @RequestMapping(value="/search",method = RequestMethod.GET)
    public String artistSearch(HttpServletRequest request,
                               @RequestParam(value = "page",required=false,defaultValue="1")Integer page,
                               @RequestParam(value = "name",required=false,defaultValue="") String name) throws IOException, JSONException {
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        PageInfoWriter pageInfo = artistSearchService.searchList(name,page,pageSize);
        logger.info("pageInfo.getDataList().size(): {}",pageInfo.getDataList().size());
        //List<Artist> artistList = artistDao.artistList(name,offset,pageSize);
        request.setAttribute("artistList",pageInfo.getDataList());

        Integer matchRecordCount = pageInfo.getAllRecordCount();

        List<ArtistRecommend> artistRecommendList = artistService.artistRecommendList();
        request.setAttribute("artistRecommendList",artistRecommendList);
        request.setAttribute("name",name);
        return "/web/artist/search";
    }

    @RequestMapping(value="/search-list",method = RequestMethod.GET)
    public String searchList(HttpServletRequest request,
                             @RequestParam(value = "page",required=false,defaultValue="1")Integer page,
                             @RequestParam(value = "name",required=false,defaultValue="") String name) throws IOException, JSONException {
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        PageInfoWriter pageInfo = artistSearchService.searchList(name,page,pageSize);
        logger.info("pageInfo.getDataList().size(): {}",pageInfo.getDataList().size());
        //List<Artist> artistList = artistDao.artistList(name,offset,pageSize);
        request.setAttribute("artistList",pageInfo.getDataList());
        Integer matchRecordCount = pageInfo.getAllRecordCount();
        return "/web/artist/list";
    }

    @RequestMapping(value="/{id}/detail",method = RequestMethod.GET)
    public String detail(HttpServletRequest request,@PathVariable Long id){
        List<ArtistRecommend> artistRecommendList = artistService.artistRecommendList();
        request.setAttribute("artistRecommendList",artistRecommendList);

        Artist artist = artistDao.getArtist(id);
        request.setAttribute("entity",artist);

        return "/web/artist/detail";
    }


}
