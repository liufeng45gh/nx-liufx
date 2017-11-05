package com.lucifer.controller.cms.hfc;


import com.lucifer.dao.hfc.AtlasDao;
import com.lucifer.model.hfc.Atlas;
import com.lucifer.model.hfc.AtlasRecommend;
import com.lucifer.service.hfc.AtlasService;
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
 * Created by fx on 2017/5/3.
 */

//@Controller
//@RequestMapping("/cms/atlas")
public class CmsAtlasController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AtlasDao atlasDao;

    @Resource
    private AtlasService atlasService;


    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String artistList(HttpServletRequest request,
                             @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<Atlas> atlasList = atlasDao.atlasList(offset,pageSize);
        request.setAttribute("atlasList",atlasList);
        Integer matchRecordCount=atlasDao.matchRecordCount();
        Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);
        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        //request.setAttribute("name",name);
        return "/cms/atlas/list";
    }

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String atlasAddInput(HttpServletRequest request){
        Atlas atlas = new Atlas();
        atlas.setLogo("/images/artist_default_avatar.jpg");
        request.setAttribute("atlas",atlas);
        return "/cms/atlas/add";
    }

    @RequestMapping(value="/add",method = RequestMethod.POST)
    @ResponseBody
    public Result atlasAddSubmit(Atlas atlas) throws BadHanyuPinyinOutputFormatCombination {
        atlasDao.insertAtlas(atlas);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/update",method = RequestMethod.GET)
    public String atlasUpdateInput(HttpServletRequest request,@PathVariable Long id){
        Atlas atlas = atlasDao.getAtlas(id);
        request.setAttribute("atlas",atlas);

        return "/cms/atlas/update";
    }
    @RequestMapping(value="/update",method = RequestMethod.POST)
    public Result atlasUpdateSubmit(Atlas atlas) throws BadHanyuPinyinOutputFormatCombination {
        atlasDao.updateAtlas(atlas);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result atlasDelete(@PathVariable Long id){
        atlasDao.deleteAtlas(id);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/add",method = RequestMethod.POST)
    @ResponseBody
    public Result addAtlasRecommend(AtlasRecommend atlasRecommend){
        atlasDao.insertAtlasRecommend(atlasRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/list",method = RequestMethod.GET)
    public String recommendList(HttpServletRequest request) {
        List<AtlasRecommend> atlasRecommendList = atlasService.atlasRecommendList();
        request.setAttribute("atlasRecommendList",atlasRecommendList);
        return "/cms/atlas/recommend_list";
    }

    @RequestMapping(value="/recommend/update",method = RequestMethod.POST)
    @ResponseBody
    public Result recommendUpdate(AtlasRecommend atlasRecommend){
        atlasDao.updateAtlasRecommend(atlasRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteAtlasRecommend(@PathVariable Long id){
        atlasDao.deleteAtlasRecommend(id);
        return Result.ok();
    }

}
