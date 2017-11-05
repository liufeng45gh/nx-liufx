package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.Artist;
import com.lucifer.model.hfc.ArtistRecommend;
import com.lucifer.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liufx on 17/1/16.
 */
@Component
public class ArtistDao extends IBatisBaseDao {

    public List<Artist> artistList(String name, Integer offset, Integer count){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("name",name);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("artistList",param);
    }

    public Integer matchRecordCount(String name){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("name",name);
        return this.hfcSqlSession.selectOne("cmsArtistMatchRecordCount",param);
    }

    public Integer insertArtist(Artist artist){
        artist.setCreatedAt(DateUtils.now());
        artist.setUpdatedAt(DateUtils.now());
        //artist.setTop(0f);
        //news.setClickCount(0);
        return this.hfcSqlSession.insert("insertArtist",artist);
    }

    public Artist getArtist(Long id){
        return this.hfcSqlSession.selectOne("getArtist",id);
    }

    public Integer updateArtist(Artist artist){
        artist.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.update("updateArtist",artist);
    }

    public Integer deleteArtist(Long id){
        return this.hfcSqlSession.delete("deleteArtist",id);
    }

    public Integer insertArtistRecommend(ArtistRecommend artistRecommend){
        artistRecommend.setTop(0f);
        artistRecommend.setCreatedAt(DateUtils.now());
        artistRecommend.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.insert("insertArtistRecommend",artistRecommend);
    }

    public List<ArtistRecommend> artistRecommendList(){
        return this.hfcSqlSession.selectList("artistRecommendList");
    }

    public Integer updateArtistRecommend(ArtistRecommend artistRecommend){
        return this.hfcSqlSession.update("updateArtistRecommend",artistRecommend);
    }

    public Integer deleteArtistRecommend(Long id) {
        return this.hfcSqlSession.delete("deleteArtistRecommend",id);
    }

    public List<Artist> artistListOrderByUpdatedAt(Date updatedAt, int count){
        Map param = new HashMap();
        param.put("updatedAt", updatedAt);
        param.put("count", count);
        return this.hfcSqlSession.selectList("artistListOrderByUpdatedAt",param);
    }
}
