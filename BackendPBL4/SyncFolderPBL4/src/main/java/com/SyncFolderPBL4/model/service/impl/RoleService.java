package com.SyncFolderPBL4.model.service.impl;

import java.io.File;
import java.util.Arrays;

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
import com.SyncFolderPBL4.utils.StringUtils;

public class RoleService implements IRoleService {
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
	public boolean setRolesNotTransaction(PermisUserMapper permisUser) {
		FileEntity file = fileDao.findOneById(permisUser.getFileId());
		if (file == null) {
			HibernateUtils.commitTrans();
			return false;
		}
		for (Integer userId : permisUser.getUserIds()) {
			UserEntity user = userDao.findOneById(userId);
			if (user == null) {
				HibernateUtils.commitTrans();
				return false;
			}
			UserRoleFileEntity roleResult = new UserRoleFileEntity(new RoleID(userId, permisUser.getFileId()), user,
					file, permisUser.isReadPermission(), permisUser.isUpdatePermission());
			roleDao.getSession().saveOrUpdate(roleResult);
		}
		return true;
	}

	@Override
	public boolean setRoles(PermisUserMapper permisUser) {
		HibernateUtils.checkTransactionAlreadyActive();
		HibernateUtils.startTrans(userDao, fileDao, roleDao);

		boolean checkRole = this.setRolesNotTransaction(permisUser);

		HibernateUtils.commitTrans();
		return checkRole;
	}

	@Override
	public UserRoleFileEntity getRoleByRoleIdNotTransaction(RoleID roleId) {
		UserRoleFileEntity userRoleFile = roleDao.getRoleByRoleId(roleId);
		if (userRoleFile != null)
			System.out.println(userRoleFile.getFile());
		return userRoleFile;
	}

	@Override
	public UserRoleFileEntity getRoleByRoleId(RoleID roleId) {
		HibernateUtils.checkTransactionAlreadyActive();
		HibernateUtils.startTrans(roleDao);

		UserRoleFileEntity userRoleFile = this.getRoleByRoleIdNotTransaction(roleId);

		HibernateUtils.commitTrans();

		return userRoleFile;
	}

	@Override
	public UserRoleFileEntity getRoleFromParent(RoleID roleId) {
		HibernateUtils.checkTransactionAlreadyActive();
		UserRoleFileEntity result = null;

		HibernateUtils.startTrans(userDao, roleDao, fileDao);
		FileEntity file = fileDao.findOneById(roleId.getFileId());
		int round = file.getNodeId();
		String path = file.getPath();
		for (int i = round; i > 0; i--) {
			path = StringUtils.cutLastElementPath(path);
			UserRoleFileEntity parentRole = roleDao.getRoleByUserIdAndPath(roleId.getUserId(),
					path.replace(File.separator, "%"));
			if (parentRole != null) {
				PermisUserMapper uMapper = new PermisUserMapper(Arrays.asList(roleId.getUserId()),
																roleId.getFileId(),
																parentRole.isReadPermission(),
																parentRole.isUpdatePermission());
				if (this.setRolesNotTransaction(uMapper) == true)
					result = this.getRoleByRoleIdNotTransaction(roleId);
				HibernateUtils.commitTrans();
				return result;
			}
		}

		HibernateUtils.commitTrans();
		return result;
	}

	@Override
	public UserRoleFileEntity getParentRole(RoleID roleId) {
		HibernateUtils.checkTransactionAlreadyActive();
		UserRoleFileEntity result = null;

		HibernateUtils.startTrans(userDao, roleDao, fileDao);
		FileEntity file = fileDao.findOneById(roleId.getFileId());
		int round = file.getNodeId();
		String path = file.getPath();
		for (int i = round; i >= 0; i--) {
			UserRoleFileEntity parentRole = roleDao.getRoleByUserIdAndPath(roleId.getUserId(),
					path.replace(File.separator, "%"));
			if (parentRole != null) {
				result = parentRole;
			}
			path = StringUtils.cutLastElementPath(path);
		}
		if(result != null)
		{
			System.out.println(result.getFile());
		}
		HibernateUtils.commitTrans();
		return result;
	}

}
