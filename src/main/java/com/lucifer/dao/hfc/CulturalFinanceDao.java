package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.CulturalFinance;
import com.lucifer.model.hfc.CulturalFinanceCategory;
import com.lucifer.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liufx on 17/1/16.
 */
@Component
public class CulturalFinanceDao extends IBatisBaseDao {

    public List<CulturalFinanceCategory> culturalFinanceCategoryList(){
          return this.hfcSqlSession.selectList("culturalFinanceCategoryList");
    }

    public Integer addCulturalFinanceCategory(CulturalFinanceCategory culturalFinanceCategory){

        return this.hfcSqlSession.insert("addCulturalFinanceCategory",culturalFinanceCategory);
    }

    public Integer deleteCulturalFinanceCategory(Long id){
        return this.hfcSqlSession.delete("deleteCulturalFinanceCategory",id);
    }

    public CulturalFinanceCategory getCulturalFinanceCategory(Long id){
        return this.hfcSqlSession.selectOne("getCulturalFinanceCategory",id);
    }

    public Integer updateCulturalFinanceCategory(CulturalFinanceCategory culturalFinanceCategory){
        return this.hfcSqlSession.update("updateCulturalFinanceCategory",culturalFinanceCategory);
    }


    public List<CulturalFinance> culturalFinanceList(Long categoryId,String title,Integer offset,Integer count){

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("categoryId",categoryId);
        param.put("title",title);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("culturalFinanceList",param);

    }

    public Integer matchRecordCount(String title){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        return this.hfcSqlSession.selectOne("cmsCulturalFinanceMatchRecordCount",param);
    }

    public Integer insertCulturalFinance(CulturalFinance culturalFinance){
        culturalFinance.setCreatedAt(DateUtils.now());
        culturalFinance.setUpdatedAt(DateUtils.now());
        //news.setTop(0f);
        //culturalFinance.setClickCount(0);
        return this.hfcSqlSession.insert("insertCulturalFinance",culturalFinance);

    }

    public CulturalFinance getCulturalFinance(Long id){
        return this.hfcSqlSession.selectOne("getCulturalFinance",id);
    }

    public Integer updateCulturalFinance(CulturalFinance culturalFinance){
        return this.hfcSqlSession.update("updateCulturalFinance",culturalFinance);
    }

    public Integer deleteCulturalFinance(Long id){
        return this.hfcSqlSession.delete("deleteCulturalFinance",id);
    }
}
