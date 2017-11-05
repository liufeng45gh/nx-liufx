package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.CulturalFinance;
import com.lucifer.model.hfc.CulturalFinanceCategory;
import com.lucifer.model.hfc.Summit;
import com.lucifer.model.hfc.SummitCategory;
import com.lucifer.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liufx on 17/1/16.
 */
@Component
public class SummitDao extends IBatisBaseDao {

    public List<SummitCategory> summitCategoryList(){
        return this.hfcSqlSession.selectList("summitCategoryList");
    }

    public Integer addSummitCategory(SummitCategory summitCategory){

        return this.hfcSqlSession.insert("addSummitCategory",summitCategory);
    }

    public Integer deleteSummitCategory(Long id){
        return this.hfcSqlSession.delete("deleteSummitCategory",id);
    }

    public SummitCategory getSummitCategory(Long id){
        return this.hfcSqlSession.selectOne("getSummitCategory",id);
    }

    public Integer updateSummitCategory(SummitCategory summitCategory){
        return this.hfcSqlSession.update("updateSummitCategory",summitCategory);
    }


    public List<Summit> summitList(String title, Integer offset, Integer count){

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("summitList",param);

    }

    public Integer matchRecordCount(String title){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        return this.hfcSqlSession.selectOne("cmsSummitMatchRecordCount",param);
    }

    public Integer insertSummit(Summit summit){
        summit.setCreatedAt(DateUtils.now());
        summit.setUpdatedAt(DateUtils.now());
        //news.setTop(0f);
        //culturalFinance.setClickCount(0);
        return this.hfcSqlSession.insert("insertSummit",summit);

    }

    public Summit getSummit(Long id){
        return this.hfcSqlSession.selectOne("getSummit",id);
    }

    public Integer updateSummit(Summit summit){
        return this.hfcSqlSession.update("updateSummit",summit);
    }

    public Integer deleteSummit(Long id){
        return this.hfcSqlSession.delete("deleteSummit",id);
    }
}
