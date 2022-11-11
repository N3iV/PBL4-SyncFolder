package com.SyncFolderPBL4.model.dao;

import java.util.List;

import com.SyncFolderPBL4.model.entities.UserEntity;

public interface IUserDao extends GenericDao<UserEntity> {
	Boolean checkEmail(String email);
	UserEntity findOneByEmailPassword(String email, String password);
	Long countAllUser(int userId);
	List<UserEntity> getAllUser(int userId, int page);
}
