package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.ArtistLecture;
import com.lucifer.model.hfc.ResearchReport;
import com.lucifer.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liufx on 17/1/22.
 */
@Component
public class ResearchReportDao extends IBatisBaseDao{

    public List<ResearchReport> researchReportList(String title, Integer offset, Integer count){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("researchReportList",param);
    }

    public Integer matchRecordCount(String title){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        return this.hfcSqlSession.selectOne("cmsResearchReportMatchRecordCount",param);
    }

    public Integer insertResearchReport(ResearchReport researchReport){
        researchReport.setCreatedAt(DateUtils.now());
        researchReport.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.insert("insertResearchReport",researchReport);
    }

    public ResearchReport getResearchReport(Long id){
        return this.hfcSqlSession.selectOne("getResearchReport",id);
    }

    public Integer updateResearchReport(ResearchReport researchReport){
        researchReport.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.update("updateResearchReport",researchReport);
    }

    public Integer deleteResearchReport(Long id){
        return this.hfcSqlSession.delete("deleteResearchReport",id);
    }
}
