package com.lucifer.controller.web;


import com.lucifer.service.hfc.HfcAccountService;
import com.lucifer.utils.Constant;
import com.lucifer.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by fx on 2017/3/4.
 */
//@Controller
public class WebAccountController {

    @Resource
    private HfcAccountService hfcAccountService;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/register",method = RequestMethod.GET)
    public String register(){
        return "/web/register";
    }

    @RequestMapping(value="/account/send-check-code.json",method = RequestMethod.POST)
    @ResponseBody
    public Result sedCheckCode(String phone, String imgCode,HttpServletRequest request) throws IOException {
        String captchaCode = (String)request.getSession().getAttribute(Constant.KAPTCHA_SESSION_KEY);
        if (captchaCode == null || !captchaCode.equals(imgCode)) {
            return Result.fail("图片验证码错误");
        }
        return hfcAccountService.sendPhoneMsg(phone);
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    @ResponseBody
    public Result register(String phone,String phoneCode,String password) throws Exception {
        return hfcAccountService.register(phone,phoneCode,password);
    }


}
