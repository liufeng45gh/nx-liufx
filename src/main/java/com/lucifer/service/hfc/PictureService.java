package com.lucifer.service.hfc;

import com.lucifer.dao.hfc.PictureDao;
import com.lucifer.model.hfc.Picture;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by fx on 2017/5/4.
 */
@Component
public class PictureService {

    @Resource
    private PictureDao pictureDao;

    public void savePicture(Picture picture){
        if (picture.getId() == null) {
            this.pictureDao.insertPicture(picture);
        } else {
            this.pictureDao.updatePicture(picture);
        }
    }
}
