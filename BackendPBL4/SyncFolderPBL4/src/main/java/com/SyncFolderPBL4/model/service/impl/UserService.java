package com.SyncFolderPBL4.model.service.impl;

import java.util.Date;

import com.SyncFolderPBL4.model.dao.IUserDao;
import com.SyncFolderPBL4.model.dao.impl.UserDao;
import com.SyncFolderPBL4.model.entities.UserEntity;
import com.SyncFolderPBL4.model.service.IUserService;

public class UserService implements IUserService {
	
	private IUserDao userDao;
	public UserService() {
		userDao = new UserDao(UserEntity.class);
	}
	@Override
	public UserEntity findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity findOne(UserEntity obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long save(UserEntity obj) {
		Long id = null;
		if(userDao.checkEmail(obj.getEmail()))
		{			
			obj.setCreatedDate(new Date());
			id = userDao.save(obj);
			
		}
		return id;
	}

}
