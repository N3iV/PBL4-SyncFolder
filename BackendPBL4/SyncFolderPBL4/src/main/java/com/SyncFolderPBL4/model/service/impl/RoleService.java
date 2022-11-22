package com.SyncFolderPBL4.model.service.impl;

import java.util.function.Predicate;

import com.SyncFolderPBL4.controller.mapper.PermisUserMapper;
import com.SyncFolderPBL4.model.dao.IFileDao;
import com.SyncFolderPBL4.model.dao.IRoleDao;
import com.SyncFolderPBL4.model.dao.ITypeDao;
import com.SyncFolderPBL4.model.dao.IUserDao;
import com.SyncFolderPBL4.model.dao.impl.FileDao;
import com.SyncFolderPBL4.model.dao.impl.RoleDao;
import com.SyncFolderPBL4.model.dao.impl.TypeDao;
import com.SyncFolderPBL4.model.dao.impl.UserDao;
import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.entities.RoleID;
import com.SyncFolderPBL4.model.entities.TypeEntity;
import com.SyncFolderPBL4.model.entities.UserEntity;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;
import com.SyncFolderPBL4.model.service.IRoleService;
import com.SyncFolderPBL4.utils.HibernateUtils;

public class RoleService implements IRoleService{
	private IUserDao userDao;
	private ITypeDao typeDao;
	private IFileDao fileDao;
	private IRoleDao roleDao;

	public RoleService() {
		userDao = new UserDao(UserEntity.class);
		typeDao = new TypeDao(TypeEntity.class);
		fileDao = new FileDao(FileEntity.class);
		roleDao = new RoleDao(UserRoleFileEntity.class);
	}
	@Override
	public UserRoleFileEntity findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRoleFileEntity findOne(UserRoleFileEntity obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(UserRoleFileEntity obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setRoles(PermisUserMapper permisUser) {
		HibernateUtils.startTrans(userDao,fileDao,roleDao);
		FileEntity file = fileDao.findOneById(permisUser.getFileId());
		if(file == null) {
			HibernateUtils.commitTrans();
			return false;
		}
		for(Integer userId : permisUser.getUserIds())
		{			
			UserEntity user = userDao.findOneById(userId);
			if(user == null) {
				HibernateUtils.commitTrans();
				return false;
			}
			UserRoleFileEntity roleResult = new UserRoleFileEntity(new RoleID(userId,permisUser.getFileId()),
					user,
					file,
					permisUser.isReadPermission(),
					permisUser.isUpdatePermission());
			roleDao.getSession().saveOrUpdate(roleResult);
		}
		
		HibernateUtils.commitTrans();
		return true;
	}

}
