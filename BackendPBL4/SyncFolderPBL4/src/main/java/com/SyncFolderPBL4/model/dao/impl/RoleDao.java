package com.SyncFolderPBL4.model.dao.impl;

import com.SyncFolderPBL4.model.dao.IRoleDao;
import com.SyncFolderPBL4.model.entities.RoleID;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public class RoleDao extends AbstractDao<UserRoleFileEntity> implements IRoleDao {

	public RoleDao(Class<UserRoleFileEntity> clazz) {
		super(clazz);
	}
	@Override
	public Integer save(UserRoleFileEntity obj) {
		return ((RoleID)session.save(obj)).getUserId();
	}

}
