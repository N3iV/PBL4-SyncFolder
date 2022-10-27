package com.SyncFolderPBL4.model.dao;

import java.math.BigInteger;
import java.util.List;

import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public interface IRoleDao extends GenericDao<UserRoleFileEntity>{
	List<UserRoleFileEntity> getFilesByUserIdAndNodeId(int id, int nodeId,int page);
	BigInteger countFilesByUserIdAndNodeId(int id, int nodeId);
	
}
