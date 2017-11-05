package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.ArtistInterview;
import com.lucifer.model.hfc.ArtistLecture;
import com.lucifer.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liufx on 17/1/22.
 */
@Component
public class ArtistLectureDao extends IBatisBaseDao{


    public List<ArtistLecture> lectureList(String title, Integer offset, Integer count){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("lectureList",param);
    }

    public Integer matchRecordCount(String title){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        return this.hfcSqlSession.selectOne("cmsArtistLectureMatchRecordCount",param);
    }

    public Integer insertArtistLecture(ArtistLecture artistLecture){
        artistLecture.setCreatedAt(DateUtils.now());
        artistLecture.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.insert("insertArtistLecture",artistLecture);
    }

    public ArtistLecture getArtistLecture(Long id){
        return this.hfcSqlSession.selectOne("getArtistLecture",id);
    }

    public Integer updateArtistLecture(ArtistLecture artistLecture){
        artistLecture.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.update("updateArtistLecture",artistLecture);
    }

    public Integer deleteArtistLecture(Long id){
        return this.hfcSqlSession.delete("deleteArtistLecture",id);
    }
}
