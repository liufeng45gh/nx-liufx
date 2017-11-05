package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.CarouseDao;
import com.lucifer.dao.hfc.IndexDao;
import com.lucifer.model.hfc.Carousel;
import com.lucifer.model.hfc.IndexRecommend;
import com.lucifer.model.hfc.NewsRecommend;
import com.lucifer.service.hfc.IndexService;
import com.lucifer.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by fx on 2017/1/31.
 */
@Controller
@RequestMapping("/cms/index")
public class CmsIndexController {

    @Resource
    private CarouseDao carouseDao;

    @Resource
    private IndexDao indexDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //------------------------------------------------------------------------//
    //下面是轮播图
    @RequestMapping(value = "/carousel/list", method = RequestMethod.GET)
    public String carouselList(HttpServletRequest request){
        List<Carousel> carouselList = carouseDao.carouselList();
        request.setAttribute("carouselList",carouselList);
        Carousel carousel = new Carousel();
        carousel.setLogo("/cms/images/logo.png");
        request.setAttribute("entity",carousel);
        return "/cms/index/carousel_list";
    }

    @RequestMapping(value = "/carousel/add", method = RequestMethod.POST)
    public String carouseAdd(Carousel carousel){
        carouseDao.insertCarousel(carousel);
        return "redirect:/cms/index/carousel/list";
    }

    @RequestMapping(value = "/carousel/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteCarousel(Long id){
        carouseDao.deleteCarousel(id);
        return Result.ok();
    }

    @RequestMapping(value = "/carousel/{id}/update", method = RequestMethod.GET)
    public String updateCarousel(@PathVariable  Long id,HttpServletRequest request){
        logger.info(" updateCarousel has been called");
        Carousel carousel = carouseDao.getCarousel(id);
        request.setAttribute("entity",carousel);
        return "/cms/index/carousel_update";
    }

    @RequestMapping(value = "/carousel/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateCarouselSubmit(Carousel carousel){
        carouseDao.updateCarousel(carousel);
        return Result.ok();
    }

    @Resource
    private IndexService indexService;

    //-------------------------------------------------------------------------------------------------//
    //下面是推荐新闻


    @RequestMapping(value="/recommend/news-list",method = RequestMethod.GET)
    public String recommendNewsList(HttpServletRequest request){
        List<IndexRecommend> indexRecommendList = indexService.newsIndexRecommendList();
        request.setAttribute("indexRecommendList",indexRecommendList);
        return "/cms/index/news_list";
    }

    @RequestMapping(value="/recommend/news-add",method = RequestMethod.POST)
    @ResponseBody
    public Result addNewsIndexRecommendSubmit(IndexRecommend indexRecommend){
        indexDao.addNewsIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/news-update",method = RequestMethod.GET)
    public String updateNewsIndexRecommend(@PathVariable Long id,HttpServletRequest request){
        IndexRecommend indexRecommend = indexDao.getNewsIndexRecommend(id);
        request.setAttribute("entity",indexRecommend);
        return "/cms/index/news_update";
    }

    @RequestMapping(value="/recommend/news-update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateNewsIndexRecommendSubmit(IndexRecommend indexRecommend){
        this.indexDao.updateNewsIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/news-delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteNewsIndexRecommend(@PathVariable Long id){
        indexDao.deleteNewsIndexRecommend(id);
        return Result.ok();
    }

    //------------------------------------------------------------------------------------------------------------------//
    //下面是艺术家

    @RequestMapping(value="/recommend/artist-list",method = RequestMethod.GET)
    public String recommendArtistList(HttpServletRequest request){
        List<IndexRecommend> indexRecommendList = indexService.artistIndexRecommendList();
        request.setAttribute("indexRecommendList",indexRecommendList);
        return "/cms/index/artist_list";
    }

    @RequestMapping(value="/recommend/artist-add",method = RequestMethod.POST)
    @ResponseBody
    public Result addArtistIndexRecommendSubmit(IndexRecommend indexRecommend){
        indexDao.addArtistIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/artist-update",method = RequestMethod.GET)
    public String updateArtistIndexRecommend(@PathVariable Long id,HttpServletRequest request){
        IndexRecommend indexRecommend = indexDao.getArtistIndexRecommend(id);
        request.setAttribute("entity",indexRecommend);
        return "/cms/index/artist_update";
    }

    @RequestMapping(value="/recommend/artist-update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateArtistIndexRecommendSubmit(IndexRecommend indexRecommend){
        this.indexDao.updateArtistIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/artist-delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteArtistIndexRecommend(@PathVariable Long id){
        indexDao.deleteArtistIndexRecommend(id);
        return Result.ok();
    }

    //---------------------------------------------------------------------------------------------------------------------//
    //下面是文化金融

    @RequestMapping(value="/recommend/finance-list",method = RequestMethod.GET)
    public String recommendCulturalFinanceList(HttpServletRequest request){
        List<IndexRecommend> indexRecommendList = indexService.financeIndexRecommendList();
        request.setAttribute("indexRecommendList",indexRecommendList);
        return "/cms/index/finance_list";
    }

    @RequestMapping(value="/recommend/finance-add",method = RequestMethod.POST)
    @ResponseBody
    public Result addCulturalFinanceIndexRecommendSubmit(IndexRecommend indexRecommend){
        indexDao.addFinanceIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/finance-update",method = RequestMethod.GET)
    public String updateCulturalFinanceIndexRecommend(@PathVariable Long id,HttpServletRequest request){
        IndexRecommend indexRecommend = indexDao.getFinanceIndexRecommend(id);
        request.setAttribute("entity",indexRecommend);
        return "/cms/index/finance_update";
    }

    @RequestMapping(value="/recommend/finance-update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateCulturalFinanceIndexRecommendSubmit(IndexRecommend indexRecommend){
        this.indexDao.updateFinanceIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/finance-delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteCulturalFinanceIndexRecommend(@PathVariable Long id){
        indexDao.deleteFinanceIndexRecommend(id);
        return Result.ok();
    }

    //-------------------------------------------------------------------------------------------------//
    //下面是研究报告

    @RequestMapping(value="/recommend/research-list",method = RequestMethod.GET)
    public String researchCulturalFinanceList(HttpServletRequest request){
        List<IndexRecommend> indexRecommendList = indexService.researchIndexRecommendList();
        request.setAttribute("indexRecommendList",indexRecommendList);
        return "/cms/index/research_list";
    }

    @RequestMapping(value="/recommend/research-add",method = RequestMethod.POST)
    @ResponseBody
    public Result addResearchIndexRecommendSubmit(IndexRecommend indexRecommend){
        indexDao.addResearchIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/research-update",method = RequestMethod.GET)
    public String updateResearchIndexRecommend(@PathVariable Long id,HttpServletRequest request){
        IndexRecommend indexRecommend = indexDao.getResearchIndexRecommend(id);
        request.setAttribute("entity",indexRecommend);
        return "/cms/index/research_update";
    }

    @RequestMapping(value="/recommend/research-update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateResearchIndexRecommendSubmit(IndexRecommend indexRecommend){
        this.indexDao.updateResearchIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/research-delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteResearchIndexRecommend(@PathVariable Long id){
        indexDao.deleteResearchIndexRecommend(id);
        return Result.ok();
    }

    //--------------------------------------------------------------------------------------------------------//
    //下面是峰会

    @RequestMapping(value="/recommend/summit-list",method = RequestMethod.GET)
    public String recommendSummitList(HttpServletRequest request){
        List<IndexRecommend> indexRecommendList = indexService.summitIndexRecommendList();
        request.setAttribute("indexRecommendList",indexRecommendList);
        return "/cms/index/summit_list";
    }

    @RequestMapping(value="/recommend/summit-add",method = RequestMethod.POST)
    @ResponseBody
    public Result addSummitIndexRecommendSubmit(IndexRecommend indexRecommend){
        indexDao.addSummitIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/summit-update",method = RequestMethod.GET)
    public String updateSummitIndexRecommend(@PathVariable Long id,HttpServletRequest request){
        IndexRecommend indexRecommend = indexDao.getSummitIndexRecommend(id);
        request.setAttribute("entity",indexRecommend);
        return "/cms/index/summit_update";
    }

    @RequestMapping(value="/recommend/summit-update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateSummitIndexRecommendSubmit(IndexRecommend indexRecommend){
        this.indexDao.updateSummitIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/summit-delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteSummitIndexRecommend(@PathVariable Long id){
        indexDao.deleteSummitIndexRecommend(id);
        return Result.ok();
    }

    //下面是会员活动

    @RequestMapping(value="/recommend/member-activity-list",method = RequestMethod.GET)
    public String recommendMemberActivityList(HttpServletRequest request){
        List<IndexRecommend> indexRecommendList = indexService.memberActivityIndexRecommendList();
        request.setAttribute("indexRecommendList",indexRecommendList);
        return "/cms/index/member_activity_list";
    }

    @RequestMapping(value="/recommend/member-activity-add",method = RequestMethod.POST)
    @ResponseBody
    public Result addMemberActivityIndexRecommendSubmit(IndexRecommend indexRecommend){
        indexDao.addMemberActivityIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/member-activity-update",method = RequestMethod.GET)
    public String updateMemberActivityIndexRecommend(@PathVariable Long id,HttpServletRequest request){
        IndexRecommend indexRecommend = indexDao.getMemberActivityIndexRecommend(id);
        request.setAttribute("entity",indexRecommend);
        return "/cms/index/member_activity_update";
    }

    @RequestMapping(value="/recommend/member-activity-update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateMemberActivityIndexRecommendSubmit(IndexRecommend indexRecommend){
        this.indexDao.updateMemberActivityIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/member-activity-delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteMemberActivityIndexRecommend(@PathVariable Long id){
        indexDao.deleteMemberActivityIndexRecommend(id);
        return Result.ok();
    }


    //------------------------------------------------------------------------------------------------------------------//
    //下面是海富映像

    @RequestMapping(value="/recommend/atlas-list",method = RequestMethod.GET)
    public String recommendAtlasList(HttpServletRequest request){
        List<IndexRecommend> indexRecommendList = indexService.atlasIndexRecommendList();
        request.setAttribute("indexRecommendList",indexRecommendList);
        return "/cms/index/atlas_list";
    }

    @RequestMapping(value="/recommend/atlas-add",method = RequestMethod.POST)
    @ResponseBody
    public Result addAtlasIndexRecommendSubmit(IndexRecommend indexRecommend){
        indexDao.addAtlasIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/atlas-update",method = RequestMethod.GET)
    public String updateAtlasIndexRecommend(@PathVariable Long id,HttpServletRequest request){
        IndexRecommend indexRecommend = indexDao.getAtlasIndexRecommend(id);
        request.setAttribute("entity",indexRecommend);
        return "/cms/index/atlas_update";
    }

    @RequestMapping(value="/recommend/atlas-update",method = RequestMethod.POST)
    @ResponseBody
    public Result updateAtlasIndexRecommendSubmit(IndexRecommend indexRecommend){
        this.indexDao.updateAtlasIndexRecommend(indexRecommend);
        return Result.ok();
    }

    @RequestMapping(value="/recommend/{id}/atlas-delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteAtlasIndexRecommend(@PathVariable Long id){
        indexDao.deleteAtlasIndexRecommend(id);
        return Result.ok();
    }


    //搜索引擎导入
    @RequestMapping(value="/search/re-import",method = RequestMethod.GET)
    public String searchReImport(HttpServletRequest request){
        return "/cms/index/search_re_import";
    }

}
