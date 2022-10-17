package com.SyncFolderPBL4.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoleID implements Serializable {
	
	private static final long serialVersionUID = 330144007988058875L;

	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "file_id")
	private int fileId;

//	public int getUserId() {
//		return userId;
//	}
//
//	public void setUserId(int userId) {
//		this.userId = userId;
//	}
//
//	public int getFileId() {
//		return fileId;
//	}
//
//	public void setFileId(int fileId) {
//		this.fileId = fileId;
//	}
//
//	public RoleID(int userId, int fileId) {
//		super();
//		this.userId = userId;
//		this.fileId = fileId;
//	}
//	public RoleID() {
//		// TODO Auto-generated constructor stub
//	}
	
}
