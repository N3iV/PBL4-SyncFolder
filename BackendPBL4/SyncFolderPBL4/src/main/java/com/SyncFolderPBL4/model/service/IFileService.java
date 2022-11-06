package com.SyncFolderPBL4.model.service;

import java.io.InputStream;
import java.util.Map;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.entities.TypeEntity;

public interface IFileService extends GenericService<FileEntity> {
	
	Map<String,Object> getFileUsers(int id,int nodeId,int page);
	Map<String,Object> getFileUsers(int id, int page);
	Map<String,Object> createFolder(int fileParentId, String fileName, String dirPath);
	Map<String,Object> uploadFile(int fileParentId, InputStream is, FormDataContentDisposition fdcd, String dirPath);
	FileEntity saveFile(int fileParentId, String fileName, TypeEntity type);
}
