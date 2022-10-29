package com.SyncFolderPBL4.model.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;


@Entity
@Table(name = "user_role_file")
public class UserRoleFileEntity {
	
	@EmbeddedId
	@Expose
	private RoleID roleIds;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId(value = "userId")
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserEntity user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId(value = "fileId")
	@JoinColumn(name = "file_id", referencedColumnName = "id")
	private FileEntity file;
	
	@Column
	@Expose
	private boolean readPermission;
	
	@Column
	@Expose
	private boolean updatePermission;

	public RoleID getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(RoleID roleIds) {
		this.roleIds = roleIds;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public FileEntity getFile() {
		return file;
	}

	public void setFile(FileEntity file) {
		this.file = file;
	}

	public boolean isReadPermission() {
		return readPermission;
	}

	public void setReadPermission(boolean readPermission) {
		this.readPermission = readPermission;
	}

	public boolean isUpdatePermission() {
		return updatePermission;
	}

	public void setUpdatePermission(boolean updatePermission) {
		this.updatePermission = updatePermission;
	}
	

	public UserRoleFileEntity(RoleID roleIds, UserEntity user, FileEntity file, boolean readPermission,
			boolean updatePermission) {
		this.roleIds = roleIds;
		this.user = user;
		this.file = file;
		this.readPermission = readPermission;
		this.updatePermission = updatePermission;
	}

	@Override
	public String toString() {
		return "\n UserRoleFileEntity [roleIds=" + roleIds + ", user=" + user + ", file=" + file + ", readPermission="
				+ readPermission + ", updatePermission=" + updatePermission + "]";
	}

	public UserRoleFileEntity() {
		// TODO Auto-generated constructor stub
	}
	
}
