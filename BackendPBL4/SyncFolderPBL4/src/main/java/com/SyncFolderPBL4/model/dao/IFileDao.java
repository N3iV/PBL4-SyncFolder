package com.SyncFolderPBL4.model.dao;

import java.util.List;

import com.SyncFolderPBL4.model.entities.FileEntity;

public interface IFileDao extends GenericDao<FileEntity> {
	List<FileEntity> getFilesByOwnerIdAndNodeId(int ownerId, int nodeId,int page);
	Long countFilesByOwnerIdAndNodeId(int ownerId, int nodeId);
	List<FileEntity> getAllDirs(int page, int nodeId);
	Long countDir(int nodeId);
}
