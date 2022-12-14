package com.SyncFolderPBL4.controller.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.SyncFolderPBL4.config.LocalDateTimeAdapter;
import com.SyncFolderPBL4.config.UserRoleFileAdapter;
import com.SyncFolderPBL4.constant.SystemConstant;
import com.SyncFolderPBL4.controller.mapper.PermisUserMapper;
import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.entities.RoleID;
import com.SyncFolderPBL4.model.entities.UserEntity;
import com.SyncFolderPBL4.model.entities.UserRoleFileEntity;
import com.SyncFolderPBL4.model.service.IFileService;
import com.SyncFolderPBL4.model.service.IRoleService;
import com.SyncFolderPBL4.model.service.IUserService;
import com.SyncFolderPBL4.model.service.impl.FileService;
import com.SyncFolderPBL4.model.service.impl.RoleService;
import com.SyncFolderPBL4.model.service.impl.UserService;
import com.SyncFolderPBL4.utils.FileUtils;
import com.SyncFolderPBL4.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/users")
public class UserRestApi {

	@Context
	private ServletContext application;

	private static Gson gson;
	private IUserService userService;
	private IFileService fileService;
	private IRoleService roleService;

	public UserRestApi() {
		userService = new UserService();
		fileService = new FileService();
		roleService = new RoleService();
		gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(UserRoleFileEntity.class, new UserRoleFileAdapter())
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.setPrettyPrinting()
				.create();
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response getAllUser( @PathParam("userId") int userId,
			@DefaultValue("1") @QueryParam("page") int page)
	{
		return Response
				.ok(gson.toJson(userService.getAllUser(userId, page)))
				.build();
	}

	@GET
	@Path("/{ownerId}/folders/file/{fileId}")
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response readFiles(@PathParam("ownerId") int ownerId, 
								@PathParam("fileId") int fileId)

	{
		if (fileId == 0) {
			return HttpUtils.messageResponse(Response.Status.BAD_REQUEST, "Sai định dạng", gson);
		}
		FileEntity fileEntity = fileService.findOne(fileId);
		if (fileEntity != null && fileEntity.getType().getName().equals("File")) {
			String readPath = FileUtils
					.getPathProject(application.getRealPath(""))
					.concat(fileEntity.getPath());
			File file = new File(readPath);
			String extension = fileEntity.getName()
										.substring(fileEntity.getName()
										.lastIndexOf("."));
			return writeFileResponse(file, extension);
		} else {
			return HttpUtils.messageResponse(Response.Status.NOT_FOUND, "File Không tồn tại", gson);
		}
	}

	@GET
	@Path("/{userId}/folders/{folderId}")
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response getUserFiles(@PathParam("userId") int userId, 
									@PathParam("folderId") int folderId,
								@DefaultValue("1") @QueryParam("page") int page) {
		int ownerId = 0;
		FileEntity fileEntity = fileService.findOne(folderId);
		// check permission
		
		if (fileEntity != null && fileEntity.getType().getName().equals("Directory")) {
			if (roleService.getParentRole(new RoleID(userId, folderId)) != null)
			{
				ownerId = fileEntity.getOwnerId();
				return Response
						.ok(gson.toJson(fileService.getFileUsers(ownerId, fileEntity.getNodeId() + 1,fileEntity.getPath(), page)))
						.build();
			} else {
				return HttpUtils.messageResponse(Response.Status.UNAUTHORIZED, "Bạn không có quyền này", gson);
			}

		} else {
			return HttpUtils.messageResponse(Response.Status.NOT_FOUND, "Folder Không tồn tại", gson);
		}
	}
	@GET
	@Path("/{ownerId}/folders")
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response getUserFolders(@PathParam("ownerId") int ownerId) {
		return Response
				.ok(gson.toJson(userService.findUserFolder(ownerId)))
				.build();
	}
	
	@GET
	@Path("/{userId}/folders/share")
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response getUserShareFiles(@PathParam("userId") int userId, 
									  @DefaultValue("1") @QueryParam("page") int page) {
		return Response
				.ok(gson.toJson(userService.getSharedFiles(userId, page)))
				.build();
	}
	
	@GET
	@Path("/{userId}/folders/search")
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response searchFile(@PathParam("userId") int userId, FileEntity file)
	{
		return Response
					.ok(gson.toJson(fileService.searchFile(userId,file.getName())))
					.build();
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response loginUser(UserEntity user) {
		UserEntity userFind = userService.findOne(user);
		if (userFind == null) {
			return 	HttpUtils.messageResponse(Response.Status.UNAUTHORIZED, "Đăng nhập thất bại", gson);
		} else {
			FileEntity fileOfUser = userService.findUserFolder(userFind.getId());
			Map<String, Object> responseMap = new HashMap<>();
			responseMap.put("user", userFind);
			responseMap.put("file", fileOfUser);
			return Response
					.ok(gson.toJson(responseMap))
					.build();
		}
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response registerUser(UserEntity user) {
		String storePath = FileUtils
							.getPathProject(application.getRealPath(""))
							.concat(SystemConstant.CONCAT_PATH);
		Integer id = userService.createUser(user, storePath);
		if (id == null) {
			return 	HttpUtils.messageResponse(Response.Status.UNAUTHORIZED, "Đăng kí thất bại", gson);
		} else {
			return Response
					.ok(gson.toJson(userService.findOne(id)))
					.build();
		}
	}
	
	@POST
	@Path("/folders/permission")
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response setPermissionFile(PermisUserMapper permisUser)
	{
		if (permisUser.isUpdatePermission() == true)
		{
			permisUser.setReadPermission(true);
		}
		if (roleService.setRoles(permisUser)) {
			return HttpUtils.messageResponse(Response.Status.ACCEPTED, "Chia sẻ quyền thành công", gson);
		} else 
		{			
			return HttpUtils.messageResponse(Response.Status.NOT_FOUND, "Chia sẻ quyền thất bại", gson);
		}
	}
	
	private Response writeFileResponse(File file, String extension) {
		StreamingOutput so = (output -> {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream bos = new BufferedOutputStream(output);
			int s;
			while ((s = bis.read()) != -1) {
				bos.write(s);
			}
			bis.close();
			bos.close();
		});
		switch (extension) {
		case ".txt":
			return Response
					.ok(so, MediaType.TEXT_PLAIN + SystemConstant.CHARSET)
					.build();
		case ".pdf":
			return Response
					.ok(so, "application/pdf" + SystemConstant.CHARSET)
					.build();
		default:
			return null;
		}

	}
}
