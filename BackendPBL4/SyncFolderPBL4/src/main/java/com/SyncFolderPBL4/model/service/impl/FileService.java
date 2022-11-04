package com.SyncFolderPBL4.model.service.impl;

import java.util.HashMap;
import java.util.Map;

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
import com.SyncFolderPBL4.model.entities.TypeEntity;
import com.SyncFolderPBL4.model.entities.UserEntity;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;
import com.SyncFolderPBL4.model.service.IFileService;
import com.SyncFolderPBL4.utils.HibernateUtils;

public class FileService implements IFileService {

	private IUserDao userDao;
	private ITypeDao typeDao;
	private IFileDao fileDao;
	private IRoleDao roleDao;

	public FileService() {
		userDao = new UserDao(UserEntity.class);
		typeDao = new TypeDao(TypeEntity.class);
		fileDao = new FileDao(FileEntity.class);
		roleDao = new RoleDao(UserRoleFileEntity.class);
	}

	@Override
	public FileEntity findOne(Integer fileId) {
		FileEntity result = null;
		HibernateUtils.startTrans(fileDao);

		result = fileDao.findOneById(fileId);

		HibernateUtils.commitTrans();
		return result;
	}

	@Override
	public FileEntity findOne(FileEntity obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer save(FileEntity obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getFileUsers(int id, int nodeId, int page) {
		Map<String, Object> result = new HashMap<>();
		HibernateUtils.startTrans(fileDao);

		Long maxItem = fileDao.countFilesByOwnerIdAndNodeId(id, nodeId);
		int numPage = (int) (Math.ceil((double) maxItem / SystemConstant.MAX_PAGE_SIZE));

		result.put("files", fileDao.getFilesByOwnerIdAndNodeId(id, nodeId, page));
		result.put("page", page);
		result.put("numberOfPage", numPage);

		HibernateUtils.commitTrans();
		return result;
	}

	@Override
	public Map<String, Object> getFileUsers(int id, int page) {
		return getFileUsers(id, 1, page);
	}

	@Override
	public Map<String, Object> getAllDirs(int page, int nodeId) {
		Map<String, Object> rs = new HashMap<>();

		HibernateUtils.startTrans(fileDao);
		Long maxItem = fileDao.countDir(nodeId);
		int numPage = (int) Math.ceil((double) maxItem / SystemConstant.MAX_PAGE_SIZE);
		
		rs.put("dirs", fileDao.getAllDirs(page, nodeId));
		rs.put("page", page);
		rs.put("numberOfPage", numPage);
		HibernateUtils.commitTrans();
		
		return rs;
	}

	

}
