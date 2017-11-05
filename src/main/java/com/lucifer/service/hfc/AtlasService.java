package com.lucifer.service.hfc;


import com.lucifer.dao.hfc.AtlasDao;

import com.lucifer.model.hfc.Atlas;
import com.lucifer.model.hfc.AtlasRecommend;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fx on 2017/5/3.
 */

@Component
public class AtlasService {

    @Resource
    private AtlasDao atlasDao;



//    public void saveArtist(Artist artist) throws BadHanyuPinyinOutputFormatCombination {
//        String namePy = pinYinService.toHanYuPinYin(artist.getName());
//        artist.setNamePy(namePy);
//        artistDao.insertArtist(artist);
//    }
//
//    public void updateArtist(Artist artist) throws BadHanyuPinyinOutputFormatCombination {
//        String namePy = pinYinService.toHanYuPinYin(artist.getName());
//        artist.setNamePy(namePy);
//        artistDao.updateArtist(artist);
//    }

    public List<AtlasRecommend> atlasRecommendList(){
        List<AtlasRecommend> atlasRecommendList = atlasDao.atlasRecommendList();
        for (AtlasRecommend atlasRecommend:atlasRecommendList) {
            Atlas atlas = atlasDao.getAtlas(atlasRecommend.getAtlasId());
            atlasRecommend.setAtlas(atlas);
        }
        return atlasRecommendList;
    }
}
