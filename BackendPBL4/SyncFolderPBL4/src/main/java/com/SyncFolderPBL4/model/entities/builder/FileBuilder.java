package com.SyncFolderPBL4.model.entities.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.entities.TypeEntity;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;

public class FileBuilder implements IFileBuilder {
	
	private int nodeId;
	
	private String name;
	
	private String path;
	
	private Date createdDate;
	
	private Date modifiedDate;
	
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
	public FileBuilder addCreatedDate(Date date) {
		this.createdDate = date;
		return this;
	}

	@Override
	public FileBuilder addModifiedDate(Date date) {
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
	public FileEntity build() {
		return new FileEntity(nodeId, name, path, createdDate, modifiedDate,roles,type);
	}

}
