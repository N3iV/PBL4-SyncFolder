package com.SyncFolderPBL4.model.service;

import java.util.Map;

import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.entities.UserEntity;

public interface IUserService extends GenericService<UserEntity> {
	Integer createUser(UserEntity user, String dirPath);

	FileEntity findUserFolder(int ownerId);

	Map<String, Object> getAllUser(int userId, int page);

	Map<String,Object> getSharedFiles(int userId, int page);

}
