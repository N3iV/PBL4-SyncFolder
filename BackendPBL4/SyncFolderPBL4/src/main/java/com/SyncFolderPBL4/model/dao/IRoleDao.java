package com.SyncFolderPBL4.model.dao;

import com.SyncFolderPBL4.model.entities.RoleID;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public interface IRoleDao extends GenericDao<UserRoleFileEntity> {
	
	UserRoleFileEntity getRoleByRoleId(RoleID roleId);

	void deleteRoleByPath(String path, int ownerId);

	UserRoleFileEntity getParentRole(int userId, String path);
}
