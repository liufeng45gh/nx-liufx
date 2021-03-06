package com.lucifer.controller.api;


import com.lucifer.model.User;
import com.lucifer.service.UserLoginService;
import com.lucifer.utils.Result;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户登录
 * @author liufengxuan
 *
 */

@Api(basePath = "/api/account", value = "登录", description = "登录controller", produces = "application/json")
@RestController
@RequestMapping(value = "/api/account")
public class AipLoginController {

	@Resource
	private UserLoginService userLoginService;


	

	
	/**
	 * 登录
	 * @param  @RequestBody user  example
	 * 账号登陆 {phone:"18610814074",password:"xxxxxxx"}
	 * 微博登陆{weiboId:"xxxxxxx",accessToken:"xxxxxxxxxx"}
	 * 微信登陆{weixinId:"xxxxxxx",accessToken:"xxxxxxxxxx"}
	 * @return  {ok:true,data:"token-value",message:"xxxxx"}
	 * @throws Exception 
	 */
	@ApiOperation(value = "用户登录",  notes = "user login")
	@RequestMapping(value="/logins",method=RequestMethod.POST)
	@ResponseBody

	public Result login(
			@ApiParam(name = "request-body",value = "只需要传入 phone 和 password 字段,like this: " +
					"{phone:\"18610814074\",password:\"xxxxxxx\"}")
			@RequestBody User user) throws Exception{
		Result result = userLoginService.login(user);
		return result;
	}
	
	/**
	 * 登出
	 * @param  @CookieValue String token 
	 * @return @ResponseBody {ok:true,data:"token-value",message:"xxxxx"}
	 */
	@ApiOperation(value = "用户登出")
	@RequestMapping(value="/logins/{user_id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Result logout(@CookieValue String token){
		userLoginService.logout(token);
		return Result.ok();
	}
	
	/**
	 * 登出
	 * @param  @CookieValue String token 
	 * @return @ResponseBody {ok:true,data:"token-value",message:"xxxxx"}
	 */
	@ApiOperation(value = "用户登出")
	@RequestMapping(value="/logout/{user_id}",method=RequestMethod.POST)
	@ResponseBody
	public Result logoutPost(@CookieValue String token){
		userLoginService.logout(token);
		return Result.ok();
	}
	

}
