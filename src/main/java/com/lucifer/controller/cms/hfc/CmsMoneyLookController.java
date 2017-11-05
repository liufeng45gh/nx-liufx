package com.lucifer.controller.cms.hfc;

import com.lucifer.dao.hfc.MoneyLookDao;
import com.lucifer.model.hfc.Answer;
import com.lucifer.model.hfc.Atlas;
import com.lucifer.model.hfc.Question;
import com.lucifer.utils.Constant;
import com.lucifer.utils.PageUtil;
import com.lucifer.utils.Result;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/11/5.
 */
@Controller
@RequestMapping("/cms/money-look")
public class CmsMoneyLookController {

    @Resource
    private MoneyLookDao moneyLookDao;

    @RequestMapping(value="/question-list",method = RequestMethod.GET)
    public String questionList(HttpServletRequest request,
                             @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<Question> questionList = moneyLookDao.questionList(offset,pageSize);
        request.setAttribute("questionList",questionList);
        Integer matchRecordCount=moneyLookDao.matchRecordCount();
        Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, pageSize);
        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        //request.setAttribute("name",name);
        return "/cms/money-look/question-list";
    }

    @RequestMapping(value="/question-add",method = RequestMethod.GET)
    public String questionListAddInput(HttpServletRequest request){
        Question question = new Question();

        request.setAttribute("entity",question);
        return "/cms/money-look/question-add";
    }

    @RequestMapping(value="/question-add",method = RequestMethod.POST)
    @ResponseBody
    public Result questionAddSubmit(Question question) throws BadHanyuPinyinOutputFormatCombination {
        moneyLookDao.insertQuestion(question);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/question-update",method = RequestMethod.GET)
    public String questionUpdateInput(HttpServletRequest request,@PathVariable Long id){
        Question question = moneyLookDao.getQuestion(id);
        request.setAttribute("entity",question);

        return "/cms/money-look/question-update";
    }
    @RequestMapping(value="/question-update",method = RequestMethod.POST)
    public Result updateSubmit(Question question) throws BadHanyuPinyinOutputFormatCombination {
        moneyLookDao.updateQuestion(question);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/question-delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@PathVariable Long id){
        moneyLookDao.deleteQuestion(id);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/question-answer-list",method = RequestMethod.GET)
    public String answerList(HttpServletRequest request,@PathVariable Long id){
        Question question = moneyLookDao.getQuestion(id);
        request.setAttribute("question",question);
        List<Answer> answerList = moneyLookDao.answerList(id);
        request.setAttribute("answerList",answerList);
        return "/cms/money-look/question-answer-list";
    }

    @RequestMapping(value="/{questionId}/answer-add",method = RequestMethod.POST)
    @ResponseBody
    public Result answerAdd(HttpServletRequest request,@PathVariable Long questionId,Answer answer){
        answer.setQuestionId(questionId);
        moneyLookDao.insertAnswer(answer);
        return Result.ok();
    }

    @RequestMapping(value="/{id}/answer-delete",method = RequestMethod.POST)
    @ResponseBody
    public Result deleteAsnwer(@PathVariable Long id){
        moneyLookDao.deleteAnswer(id);
        return Result.ok();
    }
}
