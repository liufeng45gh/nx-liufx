package com.lucifer.controller.web;

import com.lucifer.cache.AppCache;
import com.lucifer.cache.CacheProvider;
import com.lucifer.config.ServerConfig;
import com.lucifer.dao.hfc.CarouseDao;
import com.lucifer.model.hfc.Carousel;
import com.lucifer.model.hfc.IndexRecommend;
import com.lucifer.service.hfc.AppreciateSearchService;
import com.lucifer.service.hfc.ArtistSearchService;
import com.lucifer.service.hfc.IndexService;
import com.lucifer.service.hfc.NewsSearchService;
import com.lucifer.utils.*;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/2/19.
 */
//@Controller
public class WebIndexController {

    @Resource
    private CarouseDao carouseDao;

    @Resource
    private IndexService indexService;

    @Resource
    private NewsSearchService newsSearchService;


    @Resource
    private ArtistSearchService artistSearchService;

    @Resource
    private AppreciateSearchService appreciateSearchService;

    @Resource
    private ServerConfig serverConfig;

    @Resource
    private AppCache appCache;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/",method = RequestMethod.GET)
    @ResponseBody
    public String newsIndex(HttpServletRequest request,HttpServletResponse response){

        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-Language","zh-CN");
        response.setHeader("Date", DateUtils.now().toString());
        //response.setHeader("Content-Type","text/html;charset=UTF-8");
        String html =  appCache.find(Constant.CACHE_KEY_INDEX_HTML, new CacheProvider() {
            @Override
            public Object getData() {
                return null;
            }
        });
        return html;
    }


    @RequestMapping(value="/index-no-cache",method = RequestMethod.GET)
    public String indexWithNoCache(HttpServletRequest request){
        List<Carousel> carouselList = carouseDao.carouselList();
        request.setAttribute("carouselList",carouselList);

        List<IndexRecommend> newsRecommendList = indexService.newsIndexRecommendListLimit6();
        request.setAttribute("newsRecommendList",newsRecommendList);

//        List<IndexRecommend> artistRecommendList = indexService.artistIndexRecommendListLimit16();
//        request.setAttribute("artistRecommendList",artistRecommendList);

        List<IndexRecommend> financeRecommendList = indexService.financeIndexRecommendListLimit3();
        request.setAttribute("financeRecommendList",financeRecommendList);

        List<IndexRecommend> researchRecommendList = indexService.researchIndexRecommendListLimit3();
        request.setAttribute("researchRecommendList",researchRecommendList);

        List<IndexRecommend> memberActivityRecommendList = indexService.memberActivityIndexRecommendListLimit4();
        request.setAttribute("memberActivityRecommendList",memberActivityRecommendList);

        List<IndexRecommend> atlasRecommendList = indexService.atlasIndexRecommendListLimit12();
        request.setAttribute("atlasRecommendList",atlasRecommendList);

        return "/web/index";
    }

    @Scheduled(cron = "0/10 * * * * ?")
    private void resetIndexCache() {
        logger.debug("resetIndexCache is {}",serverConfig.resetCache);
        String url = "http://localhost:"+ serverConfig.port+"/index-no-cache";
        if (!serverConfig.resetCache) {
            return;
        }
        logger.debug("start reset");
        try {
            String html = HttpClientUtils.get(url);
            if (!StringHelper.isEmpty(html)) {
                appCache.set(Constant.CACHE_KEY_INDEX_HTML,html);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/search",method = RequestMethod.GET)
    public String search(HttpServletRequest request,
                         @RequestParam(value = "title",required=false,defaultValue="") String title,
                         @RequestParam(value = "page",required=false,defaultValue="1")Integer page) throws IOException, JSONException {

        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        PageInfoWriter pageInfoNews = newsSearchService.searchList(title,page,pageSize);
        logger.info("pageInfoNews.getDataList().size(): {}",pageInfoNews.getDataList().size());
        //List<News> newsList = newsDao.cmsNewsList(title,offset,pageSize);
        request.setAttribute("newsList",pageInfoNews.getDataList());
        request.setAttribute("newsNumberFound",pageInfoNews.getAllRecordCount());


        PageInfoWriter pageInfoArtist = artistSearchService.searchList(title,page,pageSize);
        logger.info("pageInfoArtist.getDataList().size(): {}",pageInfoArtist.getDataList().size());
        //List<Artist> artistList = artistDao.artistList(name,offset,pageSize);
        request.setAttribute("artistList",pageInfoArtist.getDataList());
        request.setAttribute("artistNumberFound",pageInfoArtist.getAllRecordCount());


//        PageInfoWriter pageInfoAppreciate = appreciateSearchService.searchList(title,page,pageSize);
//        logger.info("pageInfoAppreciate.getDataList().size(): {}",pageInfoAppreciate.getDataList().size());



        return "/web/search";

    }
}
