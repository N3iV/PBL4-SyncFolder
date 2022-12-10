package com.SyncFolderPBL4.controller.mapper;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class PermisUserMapper {
	@Expose
	private List<Integer> userIds = new ArrayList<>();
	@Expose
	private Integer fileId;
	@Expose
	private boolean readPermission;
	@Expose
	private boolean updatePermission;
	public List<Integer> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
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
	public PermisUserMapper(List<Integer> userIds, Integer fileId, boolean readPermission, boolean updatePermission) {
		this.userIds = userIds;
		this.fileId = fileId;
		this.readPermission = readPermission;
		this.updatePermission = updatePermission;
	}
	public PermisUserMapper() {
	}
	@Override
	public String toString() {
		return "PermisUserMapper [userIds=" + userIds + ", fileId=" + fileId + ", readPermission=" + readPermission
				+ ", updatePermission=" + updatePermission + "]";
	}
	
}
