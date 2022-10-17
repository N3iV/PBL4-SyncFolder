package com.SyncFolderPBL4.model.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


@Entity
@Table(name = "user_role_file")
public class UserRoleFileEntity {
	
	@EmbeddedId
	private RoleID roleIds;
	
	@ManyToOne
	@MapsId(value = "userId")
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_role"))
	private UserEntity user;
	
	@ManyToOne
	@MapsId(value = "fileId")
	@JoinColumn(name = "file_id", foreignKey = @ForeignKey(name = "fk_file_role"))
	private FileEntity file;
	
	@Column
	private boolean readPermission;
	
	@Column
	private boolean updatePermission;

//	public RoleID getRoleIds() {
//		return roleIds;
//	}
//
//	public void setRoleIds(RoleID roleIds) {
//		this.roleIds = roleIds;
//	}
//
//	public UserEntity getUser() {
//		return user;
//	}
//
//	public void setUser(UserEntity user) {
//		this.user = user;
//	}
//
//	public FileEntity getFile() {
//		return file;
//	}
//
//	public void setFile(FileEntity file) {
//		this.file = file;
//	}
//
//	public boolean isReadPermission() {
//		return readPermission;
//	}
//
//	public void setReadPermission(boolean readPermission) {
//		this.readPermission = readPermission;
//	}
//
//	public boolean isUpdatePermission() {
//		return updatePermission;
//	}
//
//	public void setUpdatePermission(boolean updatePermission) {
//		this.updatePermission = updatePermission;
//	}
//
//	public UserRoleFileEntity(RoleID roleIds, UserEntity user, FileEntity file, boolean readPermission,
//			boolean updatePermission) {
//		super();
//		this.roleIds = roleIds;
//		this.user = user;
//		this.file = file;
//		this.readPermission = readPermission;
//		this.updatePermission = updatePermission;
//	}
//	
//	public UserRoleFileEntity() {
//		// TODO Auto-generated constructor stub
//	}
	
}
