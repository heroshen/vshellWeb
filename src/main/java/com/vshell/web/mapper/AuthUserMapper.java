package com.vshell.web.mapper;

import java.util.List;

import com.vshell.web.common.dao.MyMapper;
import com.vshell.web.model.AuthUser;
import org.apache.ibatis.annotations.Param;

public interface AuthUserMapper extends MyMapper<AuthUser> {

	AuthUser queryByUsername(@Param("username") String username);

	List<AuthUser> queryList(@Param("user")AuthUser user);

	List<Integer> queryRoleUids(@Param("roleid")int roleid);
}