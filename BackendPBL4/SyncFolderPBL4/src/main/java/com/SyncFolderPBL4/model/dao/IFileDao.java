package com.SyncFolderPBL4.model.dao;

import java.util.List;

import com.SyncFolderPBL4.model.entities.FileEntity;

public interface IFileDao extends GenericDao<FileEntity> {
	List<FileEntity> getFilesByOwnerIdAndNodeId(int ownerId, int nodeId, int page);

	Long countFilesByOwnerIdAndNodeId(int ownerId, int nodeId);

	List<FileEntity> getAllFiles(int page, int ownerId, int nodeId);

	Long countFiles(int ownerId, int nodeId);

	FileEntity findOneByOwnerIdAndNodeId(int ownerId, int nodeId);
	
	Long countSharedFiles(int userId);
	
	List<FileEntity> getSharedFiles(int userId, int page);
	
	void deleteFileByPath(String path, int ownerId);
	
	
}
