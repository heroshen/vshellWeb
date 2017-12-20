package com.vshell.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vshell.web.common.annotation.Authority;
import com.vshell.web.common.annotation.ControllerLog;
import com.vshell.web.common.pojo.AjaxResult;
import com.vshell.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录Controller
 */
@Controller
@RequestMapping("/admin/")
public class LoginController extends BaseController {
	
	@Autowired
	private LoginService loginService;

	/**
	 * 登录
	 * @param response
	 * @param userName
	 * @param password
	 * @return
	 */
	@ControllerLog("登录")
	@RequestMapping("login")
	@ResponseBody
	public AjaxResult login(HttpServletRequest request, HttpServletResponse response) {
		return loginService.login(request, response);
	}

	/**
	 * 登录成功进入主界面
	 * @param map
	 * @return
	 */
	@Authority(opCode = "0001", opName = "系统主界面")
	@RequestMapping("main")
	public String main() {
		return "common/main";
	}

	/**
	 * 退出
	 * @param response
	 * @param request
	 * @return
	 */
	@ControllerLog("退出")
	@RequestMapping("logout")
	@ResponseBody
	public AjaxResult logout(HttpServletResponse response, HttpServletRequest request) {
		return loginService.logout(response, request);
	}
}
