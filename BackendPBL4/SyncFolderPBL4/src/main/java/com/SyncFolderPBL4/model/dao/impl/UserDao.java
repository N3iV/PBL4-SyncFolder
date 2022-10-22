package com.SyncFolderPBL4.model.dao.impl;

import com.SyncFolderPBL4.model.dao.IUserDao;
import com.SyncFolderPBL4.model.entities.UserEntity;

public class UserDao extends AbstractDao<UserEntity> implements IUserDao {

	public UserDao(Class<UserEntity> clazz) {
		super(clazz);
		
	}
	@Override
	public Boolean checkEmail(String email) {
		UserEntity user = setListParamsInHQL(session.createQuery("from UserEntity u where u.email = ?0",UserEntity.class), 
							email)
							.uniqueResult();
		return (user == null) ? true : false;
	}
}
