package com.SyncFolderPBL4.model.service;

import java.util.List;
import java.util.Map;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.entities.TypeEntity;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public interface IFileService extends GenericService<FileEntity> {

	Map<String, Object> getFileUsers(int id, int nodeId, String path, int page);

//	Map<String, Object> getFileUsers(int id, String path, int page);

	Map<String, Object> createFolder(int fileParentId, String fileName, String dirPath);

	Map<String, Object> uploadFile(int fileParentId, FormDataBodyPart body, String dirPath);

	FileEntity saveFile(int fileParentId, String fileName, TypeEntity type);

	void deleteFile(UserRoleFileEntity role);

	List<FileEntity> searchFile(int userId, String nameFile);

}
