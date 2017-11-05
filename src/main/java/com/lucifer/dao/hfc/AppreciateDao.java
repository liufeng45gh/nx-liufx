package com.lucifer.dao.hfc;

import com.lucifer.cache.AppCache;
import com.lucifer.cache.CacheProvider;
import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.Appreciate;
import com.lucifer.model.hfc.AppreciateCategory;
import com.lucifer.utils.Constant;
import com.lucifer.utils.DateUtils;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liufx on 17/1/16.
 */
@Component
public class AppreciateDao  extends IBatisBaseDao {

    @Resource
    private AppCache appCache;

    public List<AppreciateCategory> appreciateCategoryList(){
        return this.hfcSqlSession.selectList("appreciateCategoryList");
    }

    public Integer addAppreciateCategory(AppreciateCategory appreciateCategory){
        return this.hfcSqlSession.insert("addAppreciateCategory",appreciateCategory);
    }

    public Integer deleteAppreciateCategory(Long id){
        return this.hfcSqlSession.delete("deleteAppreciateCategory",id);
    }

    public AppreciateCategory getAppreciateCategory(Long id){
        return this.hfcSqlSession.selectOne("getAppreciateCategory",id);
    }

    public Integer updateAppreciateCategory(AppreciateCategory appreciateCategory){
        return this.hfcSqlSession.update("updateAppreciateCategory",appreciateCategory);
    }

    public List<Appreciate> appreciateList(String title,Long categoryId,Integer offset,Integer count){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        param.put("categoryId",categoryId);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("appreciateList",param);
    }

    public List<Appreciate> appreciateTopList(String title,Integer offset,Integer count){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("appreciateTopList",param);
    }

    public Integer matchRecordCount(String title,Long categoryId){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        param.put("categoryId",categoryId);
        return this.hfcSqlSession.selectOne("cmsAppreciateMatchRecordCount",param);
    }

    public Integer matchTopRecordCount(String title){
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("title",title);
        return this.hfcSqlSession.selectOne("cmsAppreciateTopMatchRecordCount",param);
    }

    public Integer insertAppreciate(Appreciate appreciate){
        appreciate.setCreatedAt(DateUtils.now());
        appreciate.setUpdatedAt(DateUtils.now());
        //news.setTop(0f);
        appreciate.setClickCount(0);
        return this.hfcSqlSession.insert("insertAppreciate",appreciate);
    }

    public Appreciate getAppreciate(Long id){
        String key = Constant.CACHE_KEY_GET_APPRECIATE + id;
        return appCache.find(key, new CacheProvider() {
            @Override
            public Object getData() {
                return hfcSqlSession.selectOne("getAppreciate",id);
            }
        });

    }

    public void removeAppreciateCache(Long id){
        String key = Constant.CACHE_KEY_GET_APPRECIATE + id;
        appCache.remove(key);
    }

    public Appreciate getAppreciateCounts(Long id){
        return this.hfcSqlSession.selectOne("getAppreciateCounts",id);
    }

    public Integer updateAppreciate(Appreciate appreciate){
        appreciate.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.update("updateAppreciate",appreciate);
    }

    public Integer deleteAppreciate(Long id){
        return this.hfcSqlSession.delete("deleteAppreciate",id);
    }

    public List<Appreciate> appreciateListOrderByUpdatedAt(Date updatedAt, int count){
        Map param = new HashMap();
        param.put("updatedAt", updatedAt);
        param.put("count", count);
        return this.hfcSqlSession.selectList("appreciateListOrderByUpdatedAt",param);
    }

    public Integer updateAppreciateLikeCount(Long id,Integer likeCount) {
        Map param = new HashMap();
        param.put("id", id);
        param.put("likeCount", likeCount);
        return this.hfcSqlSession.update("updateAppreciateLikeCount",param);
    }

    public Integer appreciateAddOneRead(Long id){
        return this.hfcSqlSession.update("appreciateAddOneRead",id);
    }
}
