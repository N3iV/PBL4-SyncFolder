package com.SyncFolderPBL4.model.service;

import com.SyncFolderPBL4.model.entities.UserEntity;

public interface IUserService extends GenericService<UserEntity> {
	Integer createUser(UserEntity user, String dirPath);
	
}
