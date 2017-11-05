package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.ArtistDao;
import com.lucifer.dao.hfc.NewsDao;
import com.lucifer.model.hfc.*;
import com.lucifer.service.hfc.ArtistService;
import com.lucifer.utils.Constant;
import com.lucifer.utils.PageUtil;
import com.lucifer.utils.Result;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
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
//@RequestMapping("/cms/artist")
public class CmsArtistController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ArtistDao artistDao;

    @Resource
    private ArtistService artistService;


    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String artistList(HttpServletRequest request, @RequestParam(value = "name",required=false,defaultValue="") String name,
                           @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<Artist> artistList = artistDao.artistList(name,offset,pageSize);
        request.setAttribute("artistList",artistList);
        Integer matchRecordCount=artistDao.matchRecordCount(name);
        Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);
        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        request.setAttribute("name",name);
        return "/cms/artist/list";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String artistAddInput(HttpServletRequest request){
        Artist artist = new Artist();
        artist.setAvatar("/images/artist_default_avatar.jpg");
        request.setAttribute("artist",artist);
        return "/cms/artist/add";
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result artistAddSubmit(Artist artist) throws BadHanyuPinyinOutputFormatCombination {
        artistService.saveArtist(artist);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String artistUpdateInput(HttpServletRequest request,@PathVariable Long id){
        Artist artist = artistDao.getArtist(id);
        request.setAttribute("artist",artist);

        return "/cms/artist/update";
    }
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public Result artistUpdateSubmit(Artist artist) throws BadHanyuPinyinOutputFormatCombination {
        artistService.updateArtist(artist);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result artistDelete(@PathVariable Long id){
        artistDao.deleteArtist(id);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/add",method = RequestMethod.POST)
    @ResponseBody
    public Result artistRecommend(ArtistRecommend artistRecommend){
        artistDao.insertArtistRecommend(artistRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/list",method = RequestMethod.GET)
    public String recommendList(HttpServletRequest request) {
        List<ArtistRecommend> artistRecommendList = artistService.artistRecommendList();
        request.setAttribute("artistRecommendList",artistRecommendList);
        return "/cms/artist/recommend_list";
    }

    @RequestMapping(value="/recommend/update",method = RequestMethod.POST)
    @ResponseBody
    public Result recommendUpdate(ArtistRecommend artistRecommend){
        artistDao.updateArtistRecommend(artistRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteArtistRecommend(@PathVariable Long id){
        artistDao.deleteArtistRecommend(id);
        return Result.ok();
    }



}
