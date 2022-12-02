package com.SyncFolderPBL4.controller.socket.cls;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;

public class MessageReply {
	@Expose
	private String fromUser;
	@Expose
	private String message;
	@Expose
	private Map<String, Object> data = new HashMap<>();

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

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public MessageReply(String fromUser, String message, Map<String, Object> data) {
		this.fromUser = fromUser;
		this.message = message;
		this.data = data;
	}

	public MessageReply() {
	}

	@Override
	public String toString() {
		return "MessageReply [fromUser=" + fromUser + ", message=" + message + ", data=" + data + "]";
	}

}
