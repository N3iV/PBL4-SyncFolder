package com.SyncFolderPBL4.model.dao.impl;

import com.SyncFolderPBL4.model.dao.IUserDao;
import com.SyncFolderPBL4.model.entities.UserEntity;

public class UserDao extends AbstractDao<UserEntity> implements IUserDao {

	public UserDao(Class<UserEntity> clazz) {
		super(clazz);
		
	}
	@Override
	public Boolean checkEmail(String email) {
		UserEntity user = setListParamsInHQL(session.createNamedQuery(UserEntity.Q_GET_EMAIL, UserEntity.class), 
							email)
							.uniqueResult();
		return (user == null) ? true : false;
	}
	@Override
	public UserEntity findOneByEmailPassword(String email, String password) {
		UserEntity user = setListParamsInHQL(session.createNamedQuery(UserEntity.Q_FIND_ONE_BY_EMAIL_PASSWORD, UserEntity.class), 
				email,password)
				.uniqueResult();
		return user;
	}
}
