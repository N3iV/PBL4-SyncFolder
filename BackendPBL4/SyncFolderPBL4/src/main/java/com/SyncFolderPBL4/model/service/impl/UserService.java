package com.SyncFolderPBL4.model.service.impl;

import java.io.File;
import java.util.Date;

import com.SyncFolderPBL4.constant.SystemConstant;
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
import com.SyncFolderPBL4.model.entities.builder.FileBuilder;
import com.SyncFolderPBL4.model.service.IUserService;
import com.SyncFolderPBL4.utils.FileUtils;
import com.SyncFolderPBL4.utils.HibernateUtils;

public class UserService implements IUserService {

	private IUserDao userDao;
	private ITypeDao typeDao;
	private IFileDao fileDao;
	private IRoleDao roleDao;

	public UserService() {
		userDao = new UserDao(UserEntity.class);
		typeDao = new TypeDao(TypeEntity.class);
		fileDao = new FileDao(FileEntity.class);
		roleDao = new RoleDao(UserRoleFileEntity.class);
	}

	@Override
	public UserEntity findOne(Integer id) {
		HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
		HibernateUtils.setListSession(userDao);
		UserEntity user = userDao.findOneById(id);

		HibernateUtils.getSessionFactory().getCurrentSession().getTransaction().commit();
		return user;
	}

	@Override
	public UserEntity findOne(UserEntity obj) {
		return null;
	}

	@Override
	public Integer save(UserEntity obj) {
		return null;
	}

	@Override
	public Integer createUser(UserEntity user, String dirPath) {
		Integer idUser = null;
		HibernateUtils.getSessionFactory().getCurrentSession().beginTransaction();
		HibernateUtils.setListSession(userDao,typeDao,fileDao,roleDao);
		
		if (userDao.checkEmail(user.getEmail())) {
			user.setCreatedDate(new Date());
			user.setModifiedDate(new Date());
			idUser = userDao.save(user);
			if (idUser != null) {
				String userRootPath = dirPath + File.separator
						+ user.getEmail().substring(0, user.getEmail().indexOf("@"));
				
				File userDir = FileUtils.createNewDir(userRootPath);
				
				FileEntity file = new FileBuilder().addNodeId(0)
						.addName(user.getEmail().substring(0, user.getEmail().indexOf("@")))
						.addPath(userRootPath.substring(userRootPath.indexOf(SystemConstant.CONCAT_PATH)))
						.addCreatedDate(new Date())
						.addModifiedDate(new Date())
						.addType(typeDao.findOneById(1))
						.build();
				Integer idFile = fileDao.save(file);
				UserRoleFileEntity role = new UserRoleFileEntity(new RoleID(idUser,idFile),
																userDao.findOneById(idUser),
																fileDao.findOneById(idFile),
																true,true);
				Integer idRole = roleDao.save(role);
			}

		}
		HibernateUtils.getSessionFactory().getCurrentSession().getTransaction().commit();
		return idUser;
	}

}
