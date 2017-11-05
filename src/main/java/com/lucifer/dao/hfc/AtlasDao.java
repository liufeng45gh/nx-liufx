package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.Artist;
import com.lucifer.model.hfc.ArtistRecommend;
import com.lucifer.model.hfc.Atlas;
import com.lucifer.model.hfc.AtlasRecommend;
import com.lucifer.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fx on 2017/5/3.
 */
@Component
public class AtlasDao extends IBatisBaseDao {

    public List<Atlas> atlasList( Integer offset, Integer count){
        Map<String,Object> param = new HashMap<String,Object>();
        //param.put("name",name);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("atlasList",param);
    }

    public Integer matchRecordCount(){
        Map<String,Object> param = new HashMap<String,Object>();
        //param.put("name",name);
        return this.hfcSqlSession.selectOne("cmsAtlasMatchRecordCount",param);
    }

    public Integer insertAtlas(Atlas atlas){
        atlas.setCreatedAt(DateUtils.now());
        atlas.setUpdatedAt(DateUtils.now());
        //artist.setTop(0f);
        //news.setClickCount(0);
        return this.hfcSqlSession.insert("insertAtlas",atlas);
    }

    public Atlas getAtlas(Long id){
        return this.hfcSqlSession.selectOne("getAtlas",id);
    }

    public Integer updateAtlas(Atlas atlas){
        atlas.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.update("updateAtlas",atlas);
    }

    public Integer deleteAtlas(Long id){
        return this.hfcSqlSession.delete("deleteAtlas",id);
    }

    public Integer insertAtlasRecommend(AtlasRecommend atlasRecommend){
        atlasRecommend.setTop(0f);
        atlasRecommend.setCreatedAt(DateUtils.now());
        atlasRecommend.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.insert("insertAtlasRecommend",atlasRecommend);
    }

    public List<AtlasRecommend> atlasRecommendList(){
        return this.hfcSqlSession.selectList("atlasRecommendList");
    }

    public Integer updateAtlasRecommend(AtlasRecommend atlasRecommend){
        return this.hfcSqlSession.update("updateAtlasRecommend",atlasRecommend);
    }

    public Integer deleteAtlasRecommend(Long id) {
        return this.hfcSqlSession.delete("deleteAtlasRecommend",id);
    }

//    public List<Artist> artistListOrderByUpdatedAt(Date updatedAt, int count){
//        Map param = new HashMap();
//        param.put("updatedAt", updatedAt);
//        param.put("count", count);
//        return this.hfcSqlSession.selectList("artistListOrderByUpdatedAt",param);
//    }
}
