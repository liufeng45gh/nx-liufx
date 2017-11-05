package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.MemberActivity;
import com.lucifer.model.hfc.MemberActivityCategory;
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
public class MemberActivityDao extends IBatisBaseDao {

    public List<MemberActivityCategory> memberActivityCategoryList(){
        return this.hfcSqlSession.selectList("memberActivityCategoryList");
    }

    public Integer addMemberActivityCategory(MemberActivityCategory memberActivityCategory){

        return this.hfcSqlSession.insert("addMemberActivityCategory",memberActivityCategory);
    }

    public Integer deleteMemberActivityCategory(Long id){
        return this.hfcSqlSession.delete("deleteMemberActivityCategory",id);
    }

    public MemberActivityCategory getMemberActivityCategory(Long id){
        return this.hfcSqlSession.selectOne("getMemberActivityCategory",id);
    }

    public Integer updateMemberActivityCategory(MemberActivityCategory memberActivityCategory){
        return this.hfcSqlSession.update("updateMemberActivityCategory",memberActivityCategory);
    }


    public List<MemberActivity> memberActivityList(Long categoryId,String title, Integer offset, Integer count){

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("categoryId",categoryId);
        param.put("title",title);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("memberActivityList",param);

    }

    public Integer matchRecordCount(String title){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        return this.hfcSqlSession.selectOne("cmsMemberActivityMatchRecordCount",param);
    }

    public Integer insertMemberActivity(MemberActivity memberActivity){
        memberActivity.setCreatedAt(DateUtils.now());
        memberActivity.setUpdatedAt(DateUtils.now());
        //news.setTop(0f);
        //culturalFinance.setClickCount(0);
        return this.hfcSqlSession.insert("insertMemberActivity",memberActivity);

    }

    public MemberActivity getMemberActivity(Long id){
        return this.hfcSqlSession.selectOne("getMemberActivity",id);
    }

    public Integer updateMemberActivity(MemberActivity memberActivity){
        return this.hfcSqlSession.update("updateMemberActivity",memberActivity);
    }

    public Integer deleteMemberActivity(Long id){
        return this.hfcSqlSession.delete("deleteMemberActivity",id);
    }
}
