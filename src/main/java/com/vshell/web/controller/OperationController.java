package com.vshell.web.controller;

import com.vshell.web.common.annotation.Authority;
import com.vshell.web.common.annotation.ControllerLog;
import com.vshell.web.model.AuthOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vshell.web.common.pojo.PageAjax;
import com.vshell.web.service.OperationService;

@Controller
@RequestMapping("/admin/oper/")
public class OperationController extends BaseController {

	@Autowired
	private OperationService opService;

	@Authority(opCode = "0103", opName = "权限管理界面")
	@RequestMapping("mainPage")
	public String mainPage(){
		return "auth/oper/main";
	}

	@ControllerLog("查询权限列表")
    @RequestMapping("queryPage")
    @ResponseBody
    @Authority(opCode = "0103", opName = "查询权限列表")
    public PageAjax<AuthOperation> queryPage(PageAjax<AuthOperation> page, AuthOperation oper) {
        return opService.queryPage(page, oper);
    }
}
