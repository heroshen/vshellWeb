package com.vshell.web.service;

import java.util.ArrayList;
import java.util.List;

import com.vshell.web.mapper.AuthRoleMapper;
import com.vshell.web.mapper.AuthRoleOperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vshell.web.common.annotation.ServiceLog;
import com.vshell.web.common.pojo.AjaxResult;
import com.vshell.web.common.utils.AppUtil;
import com.vshell.web.model.AuthRole;
import com.vshell.web.model.AuthRoleOperation;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class RoleService extends AbstratService<AuthRole> {

	@Autowired
	private AuthRoleMapper roleMapper;
	@Autowired
	private AuthRoleOperationMapper roleOperMapper;
	
	public AuthRole queryRoleById(int roleid){
		return roleMapper.queryRoleById(roleid);
	}

	@ServiceLog("新增角色")
	public AjaxResult saveRole(AuthRole role) {
		String result = null;
		AuthRole $role = roleMapper.queryByRolename(role.getRolename());
		if (null == $role) {
			save(role);
		} else {
			result = "角色名已存在";
		}
		return AppUtil.returnObj(result);
	}

	@ServiceLog("更新角色")
	public AjaxResult updateRole(AuthRole role) {
		String result = null;
		AuthRole $role = roleMapper.queryByRolename(role.getRolename());
		if (null != $role && $role.getRoleid() != role.getRoleid()) {
			result = "角色名已存在";
		} else {
			updateByID(role);
		}
		return AppUtil.returnObj(result);
	}

	public List<AuthRole> queryNotAdmin() {
		Example example = new Example(AuthRole.class);
		Criteria criteria = example.createCriteria();
		criteria.andNotEqualTo("rolename", "admin");
		return roleMapper.selectByExample(example);
	}

	@ServiceLog("绑定角色权限")
	public AjaxResult bindOpers(int roleid, int[] opids) {
		List<AuthRoleOperation> list = new ArrayList<AuthRoleOperation>();
		AuthRoleOperation roleOperation = null;
		for(int opid: opids){
			roleOperation = new AuthRoleOperation();
			roleOperation.setRoleid(roleid);
			roleOperation.setOpid(opid);
			list.add(roleOperation);
		}
		//通用mapper的批量插入竟然不行
//		roleOperMapper.insertList(list);
		roleOperMapper.batchInsert(list);
		return AppUtil.returnObj(null);
	}

	@ServiceLog("解除角色权限")
	public AjaxResult unbindOpers(int roleid, int[] opids){
		List<AuthRoleOperation> list = new ArrayList<AuthRoleOperation>();
		AuthRoleOperation roleOperation = null;
		for(int opid: opids){
			roleOperation = new AuthRoleOperation();
			roleOperation.setRoleid(roleid);
			roleOperation.setOpid(opid);
			list.add(roleOperation);
		}
		roleOperMapper.delRoleOpers(list);
		return AppUtil.returnObj(null);
	}

}
