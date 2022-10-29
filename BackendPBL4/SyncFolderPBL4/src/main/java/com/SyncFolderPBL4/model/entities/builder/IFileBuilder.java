package com.SyncFolderPBL4.model.entities.builder;

import java.util.Date;
import java.util.List;

import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.entities.TypeEntity;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public interface IFileBuilder {
	FileBuilder addNodeId(int nodeid);
	FileBuilder addOwnerId(int ownerId);
	FileBuilder addName(String name);
	FileBuilder addPath(String path);
	FileBuilder addCreatedDate(Date date);
	FileBuilder addModifiedDate(Date date);
	FileBuilder addType(TypeEntity type);
	FileBuilder addRoles(List<UserRoleFileEntity> roles);
	FileEntity build();
	
}
