package com.lucifer.controller.web;

import com.lucifer.model.hfc.Member;
import com.lucifer.service.hfc.MemberService;
import com.lucifer.service.hfc.QiniuCloudService;
import com.lucifer.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by liufx on 17/3/10.
 */
//@Controller
//@RequestMapping("/u-center")
public class WebUCenterController {

    @Resource
    private MemberService memberService;

    @Autowired
    QiniuCloudService qiniuCloudService;

    @RequestMapping(value="/index",method = RequestMethod.GET)
    public String upInfoInput(HttpServletRequest request, @CookieValue(required = false) String token) {
        Member member = memberService.getMemberByToken(token);
        request.setAttribute("member",member);
        return "/web/u-center/up-info";
    }

    @RequestMapping(value="/info/update",method = RequestMethod.POST)
    @ResponseBody
    public Result upInfoSubmit(HttpServletRequest request,@CookieValue(required = false) String token,Member member) throws IOException {
        return memberService.updateMember(token,member);
    }

    @RequestMapping(value="/head-edit",method = RequestMethod.GET)
    public String headEditInput() {
        return "/web/u-center/head-edit";
    }

    @RequestMapping(value="/head-edit",method = RequestMethod.POST)
    @ResponseBody
    public Result uploadHead(@CookieValue(required = false) String token,@RequestParam("file") MultipartFile file) throws IOException {
        //Member member = memberService.getMemberByToken(token);
        String uploadUrl = qiniuCloudService.simpleUploadWithoutKey(file);
        //member.setAvatar(uploadUrl);
        memberService.updateMemberAvatar(token,uploadUrl);
        return Result.ok();
    }

    @RequestMapping(value="/up-pass",method = RequestMethod.GET)
    public String upPassInput() {
        return "/web/u-center/up-pass";
    }

    @RequestMapping(value="/pass/update",method = RequestMethod.POST)
    @ResponseBody
    public Result upPassSubmit(@CookieValue(required = false) String token,String oldPass,String newPass,String repeatPass) {
        return memberService.updatePassWord(token,oldPass,newPass,repeatPass);
    }
}
