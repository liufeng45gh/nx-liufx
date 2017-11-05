package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.ArtistInterview;
import com.lucifer.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/21.
 */
@Component
public class ArtistInterviewDao extends IBatisBaseDao{

    public List<ArtistInterview> interviewList(String title,Integer offset,Integer count){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("interviewList",param);
    }

    public Integer matchRecordCount(String title){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        return this.hfcSqlSession.selectOne("cmsArtistInterviewMatchRecordCount",param);
    }

    public Integer insertArtistInterview(ArtistInterview artistInterview){
        artistInterview.setCreatedAt(DateUtils.now());
        artistInterview.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.insert("insertArtistInterview",artistInterview);
    }

    public ArtistInterview getArtistInterview(Long id){
        return this.hfcSqlSession.selectOne("getArtistInterview",id);
    }

    public Integer updateArtistInterview(ArtistInterview artistInterview){
        artistInterview.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.update("updateArtistInterview",artistInterview);
    }

    public Integer deleteArtistInterview(Long id){
        return this.hfcSqlSession.delete("deleteArtistInterview",id);
    }
}
