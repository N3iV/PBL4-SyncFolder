package com.SyncFolderPBL4.model.service;

import java.util.List;
import java.util.Map;

import com.SyncFolderPBL4.model.entities.FileEntity;

public interface IFileService extends GenericService<FileEntity> {
	Map<String,Object> getFileUsers(int id,int nodeId,int page);
	Map<String,Object> getFileUsers(int id, int page);
	Map<String, Object> getAllDirs(int page, int nodeId);
}
