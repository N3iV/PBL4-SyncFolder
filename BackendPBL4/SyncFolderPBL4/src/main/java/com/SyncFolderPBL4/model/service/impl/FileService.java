package com.SyncFolderPBL4.model.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

public class FileService implements IFileService{
	
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
	public Map<String,Object> getFileUsers(int id, int nodeId, int page) {
		List<FileEntity> filesResult = new ArrayList<>();
		Map<String,Object> result = new HashMap<>();
		HibernateUtils.startTrans(roleDao);
		
		BigInteger maxItem = roleDao.countFilesByUserIdAndNodeId(id, nodeId);
		int numPage = (int)(Math.ceil((double)maxItem.intValue()/ SystemConstant.MAX_PAGE_SIZE));
		
		List<UserRoleFileEntity> roles = roleDao.getFilesByUserIdAndNodeId(id, nodeId, page);
		
		filesResult = roles.stream()
							.map(UserRoleFileEntity::getFile)
							.collect(Collectors.toList());
		result.put("files", filesResult);
		result.put("page", page);
		result.put("numberOfPage", numPage);
		
		HibernateUtils.commitTrans();
		return result;
	}

	@Override
	public Map<String,Object> getFileUsers(int id,int page) {
		return getFileUsers(id,1,page);
	}
	
}
