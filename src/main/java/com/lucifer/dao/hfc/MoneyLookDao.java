package com.lucifer.dao.hfc;

import com.lucifer.dao.IBatisBaseDao;
import com.lucifer.model.hfc.Answer;
import com.lucifer.model.hfc.Atlas;
import com.lucifer.model.hfc.Question;
import com.lucifer.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/5.
 */
@Component
public class MoneyLookDao extends IBatisBaseDao {

    public List<Question> questionList(Integer offset, Integer count){
        Map<String,Object> param = new HashMap<String,Object>();
        //param.put("name",name);
        param.put("offset",offset);
        param.put("count",count);
        return this.hfcSqlSession.selectList("questionList",param);
    }

    public Integer matchRecordCount(){
        Map<String,Object> param = new HashMap<String,Object>();
        //param.put("name",name);
        return this.hfcSqlSession.selectOne("cmsQuestionMatchRecordCount",param);
    }

    public Integer insertQuestion(Question question){
        question.setCreatedAt(DateUtils.now());
        question.setUpdatedAt(DateUtils.now());
        //artist.setTop(0f);
        //news.setClickCount(0);
        return this.hfcSqlSession.insert("insertQuestion",question);
    }

    public Question getQuestion(Long id){
        return this.hfcSqlSession.selectOne("getQuestion",id);
    }

        public Integer updateQuestion(Question question){
        question.setUpdatedAt(DateUtils.now());
        return this.hfcSqlSession.update("updateQuestion",question);
    }

    public Integer deleteQuestion(Long id){
        return this.hfcSqlSession.delete("deleteQuestion",id);
    }

    public List<Answer> answerList(Long id){
        return this.hfcSqlSession.selectList("answerList",id);
    }

    public Integer insertAnswer(Answer answer){
        return this.hfcSqlSession.insert("insertAnswer",answer);
    }

    public Integer deleteAnswer(Long id){
        return this.hfcSqlSession.delete("deleteAnswer",id);
    }


}
