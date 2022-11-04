package com.SyncFolderPBL4.model.entities.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.entities.TypeEntity;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public class FileBuilder implements IFileBuilder {
	
	private int nodeId;
	
	private int ownerId;
	
	private String name;
	
	private String path;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
	
	private List<UserRoleFileEntity> roles = new ArrayList<>();
	
	private TypeEntity type;
	
	@Override
	public FileBuilder addNodeId(int nodeid) {
		this.nodeId = nodeid;
		return this;
	}

	@Override
	public FileBuilder addName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public FileBuilder addPath(String path) {
		this.path = path;
		return this;
	}

	@Override
	public FileBuilder addCreatedDate(LocalDateTime date) {
		this.createdDate = date;
		return this;
	}

	@Override
	public FileBuilder addModifiedDate(LocalDateTime date) {
		this.modifiedDate = date;
		return this;
	}

	@Override
	public FileBuilder addType(TypeEntity type) {
		this.type = type;
		return this;
	}

	@Override
	public FileBuilder addRoles(List<UserRoleFileEntity> roles) {
		this.roles = roles;
		return this;
	}

	@Override
	public FileBuilder addOwnerId(int ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	
	@Override
	public FileEntity build() {
		return new FileEntity(nodeId, ownerId, name, path, createdDate, modifiedDate,roles,type);
	}


}
