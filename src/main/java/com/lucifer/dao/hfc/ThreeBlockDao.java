package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.Carousel;
import com.lucifer.model.hfc.ThreeBlock;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fx on 2017/5/2.
 */
@Component
public class ThreeBlockDao extends IBatisBaseDao {


    public List<ThreeBlock> threeBlockList(){
        return this.hfcSqlSession.selectList("threeBlockList");
    }

    public Integer insertThreeBlock(ThreeBlock threeBlock){
        return this.hfcSqlSession.insert("insertThreeBlock",threeBlock);
    }

    public Integer deleteThreeBlock(Long id){
        return this.hfcSqlSession.delete("deleteThreeBlock",id);
    }

    public ThreeBlock getThreeBlock(Long id){
        return this.hfcSqlSession.selectOne("getThreeBlock",id);
    }

    public Integer updateThreeBlock(ThreeBlock threeBlock){
        return this.hfcSqlSession.update("updateThreeBlock",threeBlock);
    }






}
