package com.SyncFolderPBL4.model.service;

import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public interface IRoleService extends GenericService<UserRoleFileEntity> {
	boolean setRole(UserRoleFileEntity role);
}
