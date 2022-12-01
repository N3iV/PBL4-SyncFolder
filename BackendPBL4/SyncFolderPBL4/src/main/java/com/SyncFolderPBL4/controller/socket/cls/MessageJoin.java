package com.SyncFolderPBL4.controller.socket.cls;

public class MessageJoin {
	private int ownerId;
	private int userId;

	public MessageJoin() {
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public MessageJoin(int ownerId, int userId) {
		super();
		this.ownerId = ownerId;
		this.userId = userId;
	}

}
