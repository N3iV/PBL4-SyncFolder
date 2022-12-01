package com.SyncFolderPBL4.controller.socket.cls;

public class MessageReply {
	private String fromUser;
	private String message;
	public String getFromUserId() {
		return fromUser;
	}
	public void setFromUserId(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageReply(String fromUser, String message) {
		this.fromUser = fromUser;
		this.message = message;
	}
	public MessageReply() {
	}
}
