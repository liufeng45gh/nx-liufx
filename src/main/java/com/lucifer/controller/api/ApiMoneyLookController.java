package com.lucifer.controller.api;

import com.lucifer.dao.hfc.MemberDao;
import com.lucifer.dao.hfc.MoneyLookDao;
import com.lucifer.model.hfc.Answer;
import com.lucifer.model.hfc.Question;
import com.lucifer.model.hfc.QuestionPay;
import com.lucifer.service.hfc.MoneyLookService;
import com.lucifer.service.hfc.TradeService;
import com.lucifer.utils.*;


import com.wordnik.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MoneyLookDao moneyLookDao;

    @Resource
    private MoneyLookService moneyLookService;

    @Resource
    private MemberDao memberDao;

    @Resource
    private TradeService tradeService;

    @ApiOperation(value = "获得所有问题", notes = "获得所有问题")
    @RequestMapping(value="/question-list",method = RequestMethod.GET)
    public Result questionList(HttpServletRequest request,
                               @RequestParam(value = "page",required=false,defaultValue="1")Integer page,
                               @RequestHeader(required = false) String token){
        Map resultMap = new HashMap();
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<Question> questionList = moneyLookService.questionList(offset,pageSize,token);
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


    @ApiOperation(value = "一元钱看统一下单", notes = "一元钱看统一下单")
    @RequestMapping(value="/question/{id}/make-order",method = RequestMethod.POST)
    public Result makeOrder(@RequestHeader String token,@PathVariable Long id,HttpServletRequest request) throws Exception {
        Long userId = memberDao.getMemberIdByToken(token);
        if (null == userId) {
            return null;
        }

        String ip = IpUtils.getIpAddr(request);
        String notifyUrl =  "/question/"+id +"/make-buy/" + userId;
        return tradeService.wxMakeOrder("money-look","一元钱看","付费", RandomUtil.getRandomString(9),1,ip,notifyUrl);
    }

    @ApiOperation(value = "一元钱看付费成功", notes = "一元钱看付费成功")
    @RequestMapping(value="/question/{id}/make-buy/{userId}",method = RequestMethod.POST)
    public Result makeBuy(@PathVariable Long id,@PathVariable Long userId) throws Exception {
        QuestionPay questionPay = new QuestionPay();
        questionPay.setQuestionId(id);
        questionPay.setUserId(userId);
        return Result.ok();
    }


}
