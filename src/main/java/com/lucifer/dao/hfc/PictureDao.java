package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.Picture;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fx on 2017/5/4.
 */
@Component
public class PictureDao extends IBatisBaseDao {

    public List<Picture> pictureList(Long atlasId){
        return this.hfcSqlSession.selectList("pictureList",atlasId);
    }

    public Picture getPicture(Long id){
        return this.hfcSqlSession.selectOne("getPicture",id);
    }

    public void insertPicture(Picture picture){
         this.hfcSqlSession.insert("insertPicture",picture);
    }

    public void updatePicture(Picture picture){
        this.hfcSqlSession.update("updatePicture",picture);
    }

    public void deletePicture(Long id){
        this.hfcSqlSession.delete("deletePicture",id);
    }
}
