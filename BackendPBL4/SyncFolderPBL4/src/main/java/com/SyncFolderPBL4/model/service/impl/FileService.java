package com.SyncFolderPBL4.model.service.impl;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

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
import com.SyncFolderPBL4.model.service.IFileService;
import com.SyncFolderPBL4.utils.FileUtils;
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
	public Map<String, Object> getFileUsers(int ownerid, int nodeId, String path, int page) {
		Map<String, Object> result = new HashMap<>();
		HibernateUtils.startTrans(fileDao);

		Long maxItem = fileDao.countFilesByOwnerIdAndNodeId(ownerid, nodeId);
		int numPage = (int) (Math.ceil((double) maxItem / SystemConstant.MAX_PAGE_SIZE));
		
		path = path.replace(File.separator, "%").concat("%");
		result.put("files", fileDao.getFilesByOwnerIdAndNodeId(ownerid, nodeId,path, page));
		result.put("page", page);
		result.put("numberOfPage", numPage);

		HibernateUtils.commitTrans();
		return result;
	}

//	@Override
//	public Map<String, Object> getFileUsers(int id, int page) {
////		return getFileUsers(id, 1, page);
//	}

	

	@Override
	public Map<String, Object> createFolder(int fileParentId, String fileName, String dirPath) {
		Map<String,Object> result = new HashMap<>();
		
		HibernateUtils.startTrans(fileDao,typeDao,userDao,roleDao);
		FileEntity fileCreate = saveFile(fileParentId, fileName, typeDao.findOneById(1));
		UserEntity user = userDao.findOneById(fileCreate.getOwnerId());
		UserRoleFileEntity role = new UserRoleFileEntity(new RoleID(user.getId(),fileCreate.getId()),
															user,
															fileCreate,
															true,true);
		roleDao.save(role);
		FileUtils.createNewDir(dirPath + File.separator + fileCreate.getPath());
		
		// Pagination 
		Long maxItem = fileDao.countFiles(fileCreate.getOwnerId(), fileCreate.getNodeId());
		int numPage = (int) (Math.ceil((double) maxItem / SystemConstant.MAX_PAGE_SIZE));
		int page = numPage;
		result.put("files", fileDao.getAllFiles(page, fileCreate.getOwnerId(), fileCreate.getNodeId()));
		result.put("page", page);
		result.put("numberOfPage", numPage);
		
		HibernateUtils.commitTrans();
		
		
		return result;
	}

	@Override
	public Map<String, Object> uploadFile(int fileParentId, InputStream is, FormDataContentDisposition fdcd, String dirPath) {
		Map<String,Object> result = new HashMap<>();
		HibernateUtils.startTrans(fileDao,typeDao,userDao,roleDao);
		
		FileEntity fileEntityUpload = saveFile(fileParentId, fdcd.getFileName(), typeDao.findOneById(2));
		File fileUpload = FileUtils.createNewFile(dirPath + File.separator + fileEntityUpload.getPath());
		FileUtils.writeFile(fileUpload, is);
		// Pagination 
		Long maxItem = fileDao.countFiles(fileEntityUpload.getOwnerId(), fileEntityUpload.getNodeId());
		int numPage = (int) (Math.ceil((double) maxItem / SystemConstant.MAX_PAGE_SIZE));
		int page = numPage;
		result.put("files", fileDao.getAllFiles(page, fileEntityUpload.getOwnerId(), fileEntityUpload.getNodeId()));
		result.put("page", page);
		result.put("numberOfPage", numPage);
				
		HibernateUtils.commitTrans();

		
		return result;
	}

	@Override
	public FileEntity saveFile(int fileParentId, String fileName, TypeEntity type) {
		FileEntity parentFile = fileDao.findOneById(fileParentId);
		
		FileEntity fileEntityUpload = new FileBuilder()
								.addName(fileName)
								.addNodeId(parentFile.getNodeId() + 1)
								.addOwnerId(parentFile.getOwnerId())
								.addPath(parentFile.getPath() + File.separator + fileName)
								.addType(type)
								.addCreatedDate(LocalDateTime.now())
								.addModifiedDate(LocalDateTime.now())
								.build();
		fileEntityUpload = fileDao.findOneById(fileDao.save(fileEntityUpload));
		return fileEntityUpload;
	}

	
	
	@Override
	public void deleteFile(UserRoleFileEntity role) {

		HibernateUtils.startTrans(roleDao, fileDao);
		if(role.getFile().getType().getName().equals("File")) {
			roleDao.delete(role);
			fileDao.delete(role.getFile());
		} else {
			int ownerId = role.getFile().getOwnerId();
			String path = role.getFile().getPath().replace(File.separator, "%").concat("%");
			roleDao.deleteRoleByPath(path, ownerId);
			fileDao.deleteFileByPath(path, ownerId);
		}
		
		HibernateUtils.commitTrans();
	}

	

	

	

}
