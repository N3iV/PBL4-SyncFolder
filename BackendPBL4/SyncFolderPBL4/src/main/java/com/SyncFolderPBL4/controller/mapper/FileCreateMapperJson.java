package com.SyncFolderPBL4.controller.mapper;

import com.google.gson.annotations.Expose;

public class FileCreateMapperJson {
	
	@Expose
	private int parentFolderId;
	
	@Expose
	private String folderName;

	public int getParentFolderId() {
		return parentFolderId;
	}

	public void setParentFolderId(int parentFolderId) {
		this.parentFolderId = parentFolderId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public FileCreateMapperJson(int parentFolderId, String folderName) {
		this.parentFolderId = parentFolderId;
		this.folderName = folderName;
	}

	public FileCreateMapperJson() {
		// TODO Auto-generated constructor stub
	}
}
