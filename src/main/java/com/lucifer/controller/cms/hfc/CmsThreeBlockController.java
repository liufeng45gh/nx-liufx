package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.ThreeBlockDao;
import com.lucifer.model.hfc.Carousel;
import com.lucifer.model.hfc.ThreeBlock;
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
 * Created by fx on 2017/5/2.
 */

//@Controller
//@RequestMapping("/cms/index")
public class CmsThreeBlockController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private ThreeBlockDao threeBlockDao;

    //------------------------------------------------------------------------//
    //下面是轮播图
    @RequestMapping(value = "/three-block/list", method = RequestMethod.GET)
    public String carouselList(HttpServletRequest request){
        List<ThreeBlock> threeBlockList = threeBlockDao.threeBlockList();
        request.setAttribute("threeBlockList",threeBlockList);
        ThreeBlock carousel = new ThreeBlock();
        carousel.setLogo("/cms/images/logo.png");
        request.setAttribute("entity",carousel);
        return "/cms/index/three_block_list";
    }

    @RequestMapping(value = "/three-block/add", method = RequestMethod.POST)
    public String threeBlockAdd(ThreeBlock threeBlock){
        threeBlockDao.insertThreeBlock(threeBlock);
        return "redirect:/cms/index/three-block/list";
    }

    @RequestMapping(value = "/three-block/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteCarousel(Long id){
        threeBlockDao.deleteThreeBlock(id);
        return Result.ok();
    }

    @RequestMapping(value = "/three-block/{id}/update", method = RequestMethod.GET)
    public String updateCarousel(@PathVariable Long id, HttpServletRequest request){
        logger.info(" updateCarousel has been called");
        ThreeBlock carousel = threeBlockDao.getThreeBlock(id);
        request.setAttribute("entity",carousel);
        return "/cms/index/three_block_update";
    }

    @RequestMapping(value = "/three-block/update", method = RequestMethod.POST)
    @ResponseBody
    public Result updateCarouselSubmit(ThreeBlock threeBlock){
        threeBlockDao.updateThreeBlock(threeBlock);
        return Result.ok();
    }
}
