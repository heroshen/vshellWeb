package com.vshell.web.mapper;

import org.apache.ibatis.annotations.Param;

import com.vshell.web.common.dao.MyMapper;
import com.vshell.web.model.AuthRole;

public interface AuthRoleMapper extends MyMapper<AuthRole> {

	AuthRole queryRoleById(@Param("roleid")Integer roleid);
	
	AuthRole queryByRolename(@Param("rolename") String rolename);
}