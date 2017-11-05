package com.lucifer.controller.cms.hfc;

import com.lucifer.model.SearchParam;
import com.lucifer.model.User;
import com.lucifer.model.hfc.Member;
import com.lucifer.service.hfc.MemberService;
import com.lucifer.utils.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liufx on 17/1/16.
 */
@Controller
@RequestMapping("/cms/member")
public class CmsMemberController {

    @Resource
    private MemberService memberService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String list(HttpServletRequest request, SearchParam param){

        logger.info("page :"+param.getPage());
        Integer perPageCount = 20;
        @SuppressWarnings("rawtypes")


        List<Member> memberList=memberService.memberCmsSearch(param);
        Integer matchRecordCount=memberService.memberCmsSearchCount(param);
        Integer totalPageCount= PageUtil.getTotalPageCount(matchRecordCount, perPageCount);
        //request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("memberList", memberList);
        PageUtil pageUtil = new PageUtil(request);
        String pageDiv = pageUtil.willPaginate(totalPageCount,  "pages_bar",new String []{"page","msg"});
        request.setAttribute("pageDiv",pageDiv);
        request.setAttribute("param",param);
        return "/cms/member/list";

    }
}
