package com.vshell.web.mapper;

import java.util.List;

import com.vshell.web.common.dao.MyMapper;
import com.vshell.web.model.AuthRoleOperation;

public interface AuthRoleOperationMapper extends MyMapper<AuthRoleOperation> {

	void batchInsert(List<AuthRoleOperation> list);

	void delRoleOpers(List<AuthRoleOperation> list);
}