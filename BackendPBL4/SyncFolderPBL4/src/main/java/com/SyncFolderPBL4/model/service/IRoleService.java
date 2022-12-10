package com.SyncFolderPBL4.model.service;

import java.util.Map;

import com.SyncFolderPBL4.controller.mapper.PermisUserMapper;
import com.SyncFolderPBL4.model.entities.RoleID;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public interface IRoleService extends GenericService<UserRoleFileEntity> {
	
	boolean setRolesNotTransaction(PermisUserMapper permisUser);
	
	boolean setRoles(PermisUserMapper permisUser);

	UserRoleFileEntity getRoleByRoleId(RoleID roleId);
	
	UserRoleFileEntity getRoleByRoleIdNotTransaction(RoleID roleId);

	UserRoleFileEntity getRoleFromParent(RoleID roleId);
	
	UserRoleFileEntity getParentRole(RoleID roleId);
	
}
