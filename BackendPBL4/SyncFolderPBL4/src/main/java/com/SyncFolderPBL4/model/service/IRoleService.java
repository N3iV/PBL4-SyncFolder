package com.SyncFolderPBL4.model.service;

import com.SyncFolderPBL4.controller.mapper.PermisUserMapper;
import com.SyncFolderPBL4.model.entities.RoleID;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public interface IRoleService extends GenericService<UserRoleFileEntity> {
	boolean setRoles(PermisUserMapper permisUser);

	UserRoleFileEntity getRoleByRoleId(RoleID roleId);
}
