package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.Appreciate;
import com.lucifer.model.hfc.AppreciateKnowledge;
import com.lucifer.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fx on 2017/1/30.
 */
@Component
public class AppreciateKnowledgeDao extends IBatisBaseDao {

    public List<AppreciateKnowledge> appreciateKnowledgeList(String title, Integer offset, Integer count){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("appreciateKnowledgeList",param);
    }

    public Integer matchRecordCount(String title){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        return this.hfcSqlSession.selectOne("cmsAppreciateKnowledgeMatchRecordCount",param);
    }

    public Integer insertAppreciateKnowledge(AppreciateKnowledge appreciateKnowledge){
        appreciateKnowledge.setCreatedAt(DateUtils.now());
        appreciateKnowledge.setUpdatedAt(DateUtils.now());
        //news.setTop(0f);
        //appreciateKnowledge.setClickCount(0);
        return this.hfcSqlSession.insert("insertAppreciateKnowledge",appreciateKnowledge);
    }

    public AppreciateKnowledge getAppreciateKnowledge(Long id){
        return this.hfcSqlSession.selectOne("getAppreciateKnowledge",id);
    }

    public Integer updateAppreciateKnowledge(AppreciateKnowledge appreciateKnowledge){
        appreciateKnowledge.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.update("updateAppreciateKnowledge",appreciateKnowledge);
    }

    public Integer deleteAppreciateKnowledge(Long id){
        return this.hfcSqlSession.delete("deleteAppreciateKnowledge",id);
    }
}
