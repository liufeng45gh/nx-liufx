package com.lucifer.service.hfc;

import com.lucifer.dao.hfc.MemberDao;
import com.lucifer.dao.hfc.MoneyLookDao;
import com.lucifer.model.hfc.Question;
import com.lucifer.utils.StringHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liufx on 2017/12/1.
 */
@Component
public class MoneyLookService {

    @Resource
    private MoneyLookDao moneyLookDao;

    @Resource
    private MemberDao memberDao;

    public List<Question> questionList(Integer offset, Integer count,String token){
        List<Question> questionList = moneyLookDao.questionList(offset,count);
        if (StringHelper.isEmpty(token)) {
            return questionList;
        }
        Long userId = memberDao.getMemberIdByToken(token);
        for (Question question: questionList) {
            Integer isPay = moneyLookDao.isPay(question.getId(),userId);
            question.setIsPay(isPay);
        }
        return questionList;
    }
}
