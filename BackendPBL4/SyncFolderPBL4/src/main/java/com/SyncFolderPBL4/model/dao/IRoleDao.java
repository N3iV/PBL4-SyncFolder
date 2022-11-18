package com.SyncFolderPBL4.model.dao;

import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public interface IRoleDao extends GenericDao<UserRoleFileEntity>{
	UserRoleFileEntity getRoleByFileId(int fileId);
	void deleteRoleByPath(String path, int fileId, int ownerId);
}
