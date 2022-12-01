package com.SyncFolderPBL4.controller.socket.cls;

public class MessageFunction {
	private String func;
	private String contentMsg;
	public MessageFunction() {
		// TODO Auto-generated constructor stub
	}
	public MessageFunction(String func, String contentMsg) {
		this.func = func;
		this.contentMsg = contentMsg;
	}
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	public String getContentMsg() {
		return contentMsg;
	}
	public void setContentMsg(String contentMsg) {
		this.contentMsg = contentMsg;
	}
	@Override
	public String toString() {
		return "MessageFunction [func=" + func + ", contentMsg=" + contentMsg + "]";
	}
	
	
	
	

}
