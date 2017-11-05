package com.lucifer.controller.web;

import com.lucifer.model.hfc.Member;
import com.lucifer.service.hfc.MemberLoginService;
import com.lucifer.service.hfc.MemberService;
import com.lucifer.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by fx on 2017/3/6.
 */
//@Controller
public class WebLoginController {

    @Resource
    private MemberLoginService memberLoginService;

    @Resource
    private MemberService memberService;

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String loginInput(){
        return "/web/login";
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public Result loginByPhone(Member member) throws Exception {
        return memberLoginService.loginByPhone(member);
       // return Result.ok();
    }

    @RequestMapping(value="/token-user",method = RequestMethod.GET)
    @ResponseBody
    public Result getMemberByToken(@RequestParam String token){
        Member member = memberService.getMemberByToken(token);
        if (null != member) {
            return  Result.ok(member);
        }
        return Result.fail();
    }
}
