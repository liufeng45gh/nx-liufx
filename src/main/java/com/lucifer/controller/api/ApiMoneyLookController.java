package com.lucifer.controller.api;

import com.lucifer.dao.hfc.MoneyLookDao;
import com.lucifer.model.hfc.Answer;
import com.lucifer.model.hfc.Question;
import com.lucifer.utils.Constant;


import com.lucifer.utils.PageInfoWriter;
import com.lucifer.utils.Result;

import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/5.
 */
@RestController
@RequestMapping("/api/money-look")
public class ApiMoneyLookController {

    @Resource
    private MoneyLookDao moneyLookDao;

    @ApiOperation(value = "获得所有问题", notes = "获得所有问题")
    @RequestMapping(value="/question-list",method = RequestMethod.GET)
    public Result questionList(HttpServletRequest request,
                               @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Map resultMap = new HashMap();
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<Question> questionList = moneyLookDao.questionList(offset,pageSize);
        request.setAttribute("questionList",questionList);
        Integer matchRecordCount=moneyLookDao.matchRecordCount();
        PageInfoWriter pageInfoWriter = PageInfoWriter.create(page,pageSize,matchRecordCount);
        pageInfoWriter.writeToGateway(resultMap);
        resultMap.put("dataList",questionList);

        return Result.ok(resultMap);
    }

    @ApiOperation(value = "获得一个问题对象", notes = "获得一个问题对象")
    @RequestMapping(value="/question/{id}",method = RequestMethod.GET)
    public Result getQuestion(@PathVariable Long id){
        Question question = moneyLookDao.getQuestion(id);
        //request.setAttribute("entity",question);
        return Result.ok(question);
    }

    @ApiOperation(value = "获得一个问题的所有回复", notes = "获得一个问题的所有回复")
    @RequestMapping(value="/{id}/question-answer-list",method = RequestMethod.GET)
    public Result answerList(@PathVariable Long id){
        //Question question = moneyLookDao.getQuestion(id);
        //request.setAttribute("question",question);
        List<Answer> answerList = moneyLookDao.answerList(id);
        //request.setAttribute("answerList",answerList);
        return Result.ok(answerList);
    }
}
