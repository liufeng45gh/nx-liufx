package com.lucifer.controller.web;

import com.lucifer.service.hfc.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by liufx on 17/3/29.
 */

//@Controller
//@RequestMapping("/about")
public class WebAboutController {

    @Resource
    private NewsService newsService;

    @RequestMapping(value="/company-intro",method = RequestMethod.GET)
    public String companyIntro(HttpServletRequest request){
        newsService.loadNewsRightData(request);

        return "/web/news/company-intro";
    }

    @RequestMapping(value="management-idea",method = RequestMethod.GET)
    public String managementIdea(HttpServletRequest request){
        newsService.loadNewsRightData(request);

        return "/web/news/management-idea";
    }

    @RequestMapping(value="company-structure",method = RequestMethod.GET)
    public String companyStructure(HttpServletRequest request){
        newsService.loadNewsRightData(request);

        return "/web/news/company-structure";
    }
}
