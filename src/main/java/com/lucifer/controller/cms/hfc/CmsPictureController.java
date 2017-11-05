package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.PictureDao;
import com.lucifer.model.hfc.Picture;
import com.lucifer.service.hfc.PictureService;
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
 * Created by fx on 2017/5/4.
 */
//@Controller
//@RequestMapping("/cms/atlas")
public class CmsPictureController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PictureDao pictureDao;

    @Resource
    private PictureService pictureService;

    @RequestMapping(value="/{atlasId}/picture",method = RequestMethod.GET)
    public String list(HttpServletRequest request, @PathVariable Long atlasId){
        List<Picture> pictureList = pictureDao.pictureList(atlasId);
        request.setAttribute("pictureList",pictureList);
        Picture picture = new Picture();
        picture.setAtlasId(atlasId);
        request.setAttribute("entity",picture);
        return "/cms/atlas/picture";
    }

    @RequestMapping(value="/picture/save",method = RequestMethod.POST)
    @ResponseBody
    public Result savePicture(Picture picture){
        pictureService.savePicture(picture);
        return Result.ok();

    }

    @RequestMapping(value="/picture/{id}/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deletePicture(@PathVariable Long id){
        this.pictureDao.deletePicture(id);
        return Result.ok();
    }

    @RequestMapping(value="/picture/{id}/update",method = RequestMethod.GET)
    public String updatePictureInput(HttpServletRequest request,@PathVariable Long id){
        Picture picture = pictureDao.getPicture(id);
        List<Picture> pictureList = pictureDao.pictureList(picture.getAtlasId());
        request.setAttribute("pictureList",pictureList);
        request.setAttribute("entity",picture);
        return "/cms/atlas/picture";
    }

}
