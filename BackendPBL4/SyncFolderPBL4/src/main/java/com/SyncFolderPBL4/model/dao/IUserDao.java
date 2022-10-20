package com.SyncFolderPBL4.model.dao;

import com.SyncFolderPBL4.model.entities.UserEntity;

public interface IUserDao extends GenericDao<UserEntity> {
	public Boolean checkEmail(String email);
}
