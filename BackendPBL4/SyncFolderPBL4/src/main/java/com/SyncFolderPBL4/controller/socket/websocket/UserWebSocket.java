package com.SyncFolderPBL4.controller.socket.websocket;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;

import javax.servlet.ServletContext;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.core.Context;

import com.SyncFolderPBL4.config.LocalDateTimeAdapter;
import com.SyncFolderPBL4.controller.socket.cls.MessageFunction;
import com.SyncFolderPBL4.controller.socket.cls.MessageReply;
import com.SyncFolderPBL4.controller.socket.endecoder.MessageFunctionDecoder;
import com.SyncFolderPBL4.controller.socket.endecoder.MessageFunctionEncoder;
import com.SyncFolderPBL4.controller.socket.endecoder.MessageReplyEncoder;
import com.SyncFolderPBL4.model.entities.RoleID;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;
import com.SyncFolderPBL4.model.service.IFileService;
import com.SyncFolderPBL4.model.service.IRoleService;
import com.SyncFolderPBL4.model.service.IUserService;
import com.SyncFolderPBL4.model.service.impl.FileService;
import com.SyncFolderPBL4.model.service.impl.RoleService;
import com.SyncFolderPBL4.model.service.impl.UserService;
import com.SyncFolderPBL4.utils.FileUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ServerEndpoint(value = "/sync/{ownerId}/{userId}", decoders = MessageFunctionDecoder.class, encoders = {
		MessageFunctionEncoder.class, MessageReplyEncoder.class })
public class UserWebSocket {

	private Session session;
	private static Set<UserWebSocket> chatEndpoints = new CopyOnWriteArraySet<>();
	private static HashMap<String, Integer> users = new HashMap<>();
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
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
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
		switch (messageFunc.getFunc()) {
		case "delete":
			RoleID roleId = gson.fromJson(messageFunc.getContentMsg(), RoleID.class);
			roleId.setUserId(users.get(session.getId()));
			UserRoleFileEntity userRole = roleService.getRoleByRoleId(roleId);
			if(userRole == null) {
				message = "File không tồn tại hoặc bạn không có quyền với file này";
				break;
			}
			if (userRole.getFile().getType().getName().equals("File")) {
				message = deleteFileStrategy(userRole, t -> t.getFile().getType().getName().equals("File"),
						"File không tồn tại");
			} else {
				if (userRole.getFile().getNodeId() == 0)
					message = "Không thể xóa thư mục gốc";
				else {
					message = deleteFileStrategy(userRole, t -> t.getFile().getType().getName().equals("Directory"),
							"Folder không tồn tại");
				}
			}
			break;
		default:
			break;
		}
		MessageReply msgRep = new MessageReply("Server", message);
		senderResponse(msgRep);
	}

	@OnClose
	public void onClose(Session session) throws IOException, EncodeException {

		chatEndpoints.remove(this);
//		MessageJoin message = new MessageJoin();
//		message.setFrom(users.get(session.getId()));
//		message.setContent("Disconnected!");
//		broadcast(message);
	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		System.out.println(throwable.getMessage());
	}

	private void broadcast(MessageReply messageRep) throws IOException, EncodeException {

		chatEndpoints.forEach(endpoint -> {
			try {
				if (endpoint != this)
					endpoint.session.getBasicRemote().sendObject(messageRep);
			} catch (IOException | EncodeException e) {
				e.printStackTrace();
			}

		});
	}

	private void senderResponse(MessageReply messageRep) throws IOException, EncodeException {
		this.session.getBasicRemote().sendObject(messageRep);
	}

	public String deleteFileStrategy(UserRoleFileEntity userRole, Predicate<UserRoleFileEntity> compareHelper,
			String msgFolderNotExist) throws IOException, EncodeException {
		String contentRep = "";
		if (!userRole.isUpdatePermission()) {
			return "Không cho phép xóa";
		}
		if (compareHelper.test(userRole)) {
			fileService.deleteFile(userRole);
			String readPath = pathApp.concat(userRole.getFile().getPath());
			FileUtils.deleteFile(readPath, userRole.getFile().getType().getName());
			contentRep = this.username + " đã xóa thành công " + userRole.getFile().getType().getName() + " "
					+ userRole.getFile().getName();
			MessageReply msgRep = new MessageReply(this.username, contentRep);
			broadcast(msgRep);
			return "Xóa thành công";
		} else {
			return msgFolderNotExist;
		}
	}
}