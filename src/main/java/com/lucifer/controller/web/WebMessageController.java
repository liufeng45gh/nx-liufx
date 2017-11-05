package com.lucifer.controller.web;

import com.lucifer.model.hfc.Message;
import com.lucifer.service.hfc.MessageService;
import com.lucifer.utils.Constant;
import com.lucifer.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liufx on 17/3/18.
 */
//@Controller
//@RequestMapping("/u-center")
public class WebMessageController {

    @Resource
    private MessageService messageService;

    @RequestMapping(value="/message",method = RequestMethod.GET)
    public String messageIndex(HttpServletRequest request, @CookieValue(required = false) String token,
                               @RequestParam(value = "page",required=false,defaultValue="1")Integer page){
        Integer pageSize = Constant.PAGESIZE;
        Integer offset = (page-1) * pageSize;
        List<Message> messageList = messageService.messageList(token,offset,pageSize);
        request.setAttribute("messageList",messageList);
        return "/web/u-center/message";
    }

    @RequestMapping(value="/message-count",method = RequestMethod.GET)
    @ResponseBody
    public Result newMessageCount(@CookieValue(required = false) String token){
        Integer count =  messageService.newMessageCount(token);
        return Result.ok(count);
    }

    @RequestMapping(value="/clear-message-count",method = RequestMethod.POST)
    @ResponseBody
    public Result clearMessageCount(@CookieValue(required = false) String token){
        messageService.clearMessageCount(token);
        return Result.ok();
    }
}
