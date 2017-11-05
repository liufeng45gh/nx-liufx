package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.CarouseDao;
import com.lucifer.dao.hfc.NewsDao;
import com.lucifer.model.hfc.Carousel;
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
 * Created by liufx on 17/2/6.
 */
@Controller
@RequestMapping("/cms/news")
public class CmsNewsCarouselController {

    @Resource
    private CarouseDao carouseDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //------------------------------------------------------------------------//
    //下面是轮播图
    @RequestMapping(value = "/carousel/list", method = RequestMethod.GET)
    public String carouselList(HttpServletRequest request){
        List<Carousel> carouselList = carouseDao.newsCarouselList();
        request.setAttribute("carouselList",carouselList);
        Carousel carousel = new Carousel();
        carousel.setLogo("/cms/images/logo.png");
        request.setAttribute("entity",carousel);
        return "/cms/news/carousel_list";
    }

    @RequestMapping(value = "/carousel/add", method = RequestMethod.POST)
    public String carouseAdd(Carousel carousel){
        carouseDao.insertNewsCarousel(carousel);
        return "redirect:/cms/news/carousel/list";
    }

    @RequestMapping(value = "/carousel/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteCarousel(Long id){
        carouseDao.deleteNewsCarousel(id);
        return Result.ok();
    }

    @RequestMapping(value = "/carousel/{id}/update", method = RequestMethod.GET)
    public String updateCarousel(@PathVariable Long id, HttpServletRequest request){
        logger.info(" updateCarousel has been called");
        Carousel carousel = carouseDao.getNewsCarousel(id);
        request.setAttribute("entity",carousel);
        return "/cms/news/carousel_update";
    }

    @RequestMapping(value = "/carousel/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateCarouselSubmit(Carousel carousel){
        carouseDao.updateNewsCarousel(carousel);
        return Result.ok();
    }

}
