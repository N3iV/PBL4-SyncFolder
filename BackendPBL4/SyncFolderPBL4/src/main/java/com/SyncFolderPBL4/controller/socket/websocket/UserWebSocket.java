package com.SyncFolderPBL4.controller.socket.websocket;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.SyncFolderPBL4.config.LocalDateTimeAdapter;
import com.SyncFolderPBL4.constant.SystemConstant;
import com.SyncFolderPBL4.controller.socket.cls.MessageFunction;
import com.SyncFolderPBL4.controller.socket.cls.MessageReply;
import com.SyncFolderPBL4.controller.socket.endecoder.MessageFunctionDecoder;
import com.SyncFolderPBL4.controller.socket.endecoder.MessageReplyEncoder;
import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.entities.RoleID;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;
import com.SyncFolderPBL4.model.service.IFileService;
import com.SyncFolderPBL4.model.service.IRoleService;
import com.SyncFolderPBL4.model.service.IUserService;
import com.SyncFolderPBL4.model.service.impl.FileService;
import com.SyncFolderPBL4.model.service.impl.RoleService;
import com.SyncFolderPBL4.model.service.impl.UserService;
import com.SyncFolderPBL4.utils.FileUtils;
import com.SyncFolderPBL4.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ServerEndpoint(value = "/sync/{ownerId}/{userId}", decoders = MessageFunctionDecoder.class, encoders = MessageReplyEncoder.class)
public class UserWebSocket {

	private Session session;
	private static Set<UserWebSocket> chatEndpoints = new CopyOnWriteArraySet<>();
	private static Map<String, Integer> users = new HashMap<>();
	private int roomOwnerId;
	private String username;
	private static String pathApp = System.getProperty("user.dir") + "\\";
	private static Gson gson;
	private IUserService userService;
	private IFileService fileService;
	private IRoleService roleService;


	public UserWebSocket() {
		userService = new UserService();
		fileService = new FileService();
		roleService = new RoleService();
		gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.setPrettyPrinting()
				.create();
	}

	@OnOpen
	public void onOpen(Session session, @PathParam("ownerId") String ownerId, @PathParam("userId") String userId)
			throws IOException, EncodeException {

		this.session = session;
		chatEndpoints.add(this);
		users.put(session.getId(), Integer.parseInt(userId));
		roomOwnerId = Integer.parseInt(ownerId);
		username = userService.findOne(users.get(session.getId())).getUsername();
	}

	@OnMessage
	public void onMessage(Session session, MessageFunction messageFunc) throws IOException, EncodeException {
		String message = null;
		MessageReply msgRep = null;
		switch (messageFunc.getFunc()) {
		// delete file/folder
		case "delete":
			RoleID roleId = gson.fromJson(messageFunc.getContentMsg(), RoleID.class);
			roleId.setUserId(users.get(session.getId()));
			UserRoleFileEntity userRole = roleService.getRoleByRoleId(roleId);
			if (userRole == null) {
				userRole = roleService.getRoleFromParent(roleId);
				if(userRole == null)
				{					
					message = "File không tồn tại hoặc bạn không có quyền này";
					senderResponse(new MessageReply(SystemConstant.SERVER_NAME, message,null));
					return;
				}
			}
			deleteFileStrategy(userRole, roleId.getFileId());

			break;
		// create folder feature
//		case "create":
//			FileCreateMapperJson fileInfoInput = gson.fromJson(messageFunc.getContentMsg(), FileCreateMapperJson.class);
//			data = fileService.createFolder(fileInfoInput.getParentFolderId(), fileInfoInput.getFolderName(), pathApp);
//			String contentRep = this.username + " đã tạo thành công folder " + fileInfoInput.getFolderName();
//			msgRep = new MessageReply(this.username, contentRep, data);
//			broadcast(msgRep);
		}
	}

	@OnClose
	public void onClose(Session session) throws IOException, EncodeException {

		chatEndpoints.remove(this);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println(throwable.getMessage());
	}



	public void deleteFileStrategy(UserRoleFileEntity userRole, int fileId) throws IOException, EncodeException {
		String contentRep = "";
		String path = null;
		if (!userRole.isUpdatePermission()) {
			senderResponse(new MessageReply(SystemConstant.SERVER_NAME, "Không cho phép xóa",null));
		}
		if (userRole.getFile().getNodeId() == 0) {
			senderResponse(new MessageReply(SystemConstant.SERVER_NAME, "Không được phép xóa thư mục gốc",null));
		}
		// Check response List user
		Map<Integer, String> tableSendMsg = checkPermissionAllUserInRoomForResponse(fileId);
		
		// Delete file
		fileService.deleteFile(userRole);

		path = StringUtils.cutLastElementPath(userRole.getFile().getPath());
		Map<String, Object> data = fileService.getFileUsers(roomOwnerId, userRole.getFile().getNodeId(), path, 1);

		String readPath = pathApp.concat(userRole.getFile().getPath());
		FileUtils.deleteFile(readPath, userRole.getFile().getType().getName());
		contentRep = this.username + " đã xóa thành công " + userRole.getFile().getType().getName() + " "
				+ userRole.getFile().getName();
		MessageReply msgRep = new MessageReply(this.username, contentRep, data);
		
		// broad cast
		broadcastIfCondition(msgRep,tableSendMsg);
	}
	// ===================================== send ====================================

	private void broadcastIfCondition(MessageReply messageRep, Map<Integer, String> tableSendMsg) throws IOException, EncodeException {
					
			chatEndpoints.forEach(endpoint -> {
				try {
					if (tableSendMsg.containsKey(users.get(endpoint.session.getId())))
					{
						// Send full data
						if( tableSendMsg.get(users.get(endpoint.session.getId())).equals("Send full data"))
						{				
							// check sender
							if(endpoint == this)
							{
								endpoint.session.getBasicRemote().sendObject(new MessageReply(SystemConstant.SERVER_NAME,
																								"Xóa thành công",
																								messageRep.getData())
																			);
							}else 
							{								
								endpoint.session.getBasicRemote().sendObject(messageRep);
							}
						//send empty data
						} else 
						{
							// create empty list files in map
							Map<String, Object> emptyResponseMap = new HashMap<>();
							emptyResponseMap.putAll(emptyResponseMap);
							MessageReply msgEmptyData = new MessageReply(messageRep.getFromUserId(),
																		messageRep.getMessage(),
																		emptyResponseMap);
							msgEmptyData.getData().put("files", new ArrayList<>());
							if (endpoint == this)
							{
								endpoint.session.getBasicRemote().sendObject(new MessageReply(SystemConstant.SERVER_NAME,
																								"Xóa thành công",
																								msgEmptyData.getData())
																			);
							} else
							{								
								endpoint.session.getBasicRemote().sendObject(msgEmptyData);
							}
						}
					}
				} catch (IOException | EncodeException e) {
					e.printStackTrace();
				}

			});
	}
	

	private void senderResponse(MessageReply messageRep) throws IOException, EncodeException {
		this.session.getBasicRemote().sendObject(messageRep);
	}
	
	// ===================================== utils ===================================
	
	Map<Integer, String> checkPermissionAllUserInRoomForResponse(int fileId)
	{
		Map<Integer, String> tableSendMsg = new HashMap<>();
		for(Entry<String, Integer> entry : users.entrySet())
		{
			RoleID roleId = new RoleID(entry.getValue(),fileId);
			UserRoleFileEntity roleOfUser = roleService.getParentRole(roleId);
			UserRoleFileEntity roleCurrentFile = roleService.getRoleByRoleId(roleId);
			if(roleOfUser!= null)
			{
				if(roleCurrentFile == null)
				{
					tableSendMsg.put(entry.getValue(), "Send full data");
				} else {
					if(roleCurrentFile.equals(roleOfUser) && !entry.getValue().equals(roomOwnerId))
					{
						tableSendMsg.put(entry.getValue(), "Send empty data");
					}else {					
						tableSendMsg.put(entry.getValue(), "Send full data");
					}
				}	
				
			} 
		}
		return tableSendMsg;
	}
}