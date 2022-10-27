package com.SyncFolderPBL4.model.service;

import java.util.List;
import java.util.Map;

import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.entities.UserEntity;

public interface IUserService extends GenericService<UserEntity> {
	Integer createUser(UserEntity user, String dirPath);
	
}
