package com.SyncFolderPBL4.controller.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;

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
import com.SyncFolderPBL4.constant.SystemConstant;
import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.entities.UserEntity;
import com.SyncFolderPBL4.model.service.IFileService;
import com.SyncFolderPBL4.model.service.IUserService;
import com.SyncFolderPBL4.model.service.impl.FileService;
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

	public UserRestApi() {
		userService = new UserService();
		fileService = new FileService();
		gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.setPrettyPrinting()
				.create();
	}

	@GET
	@Path("/{ownerId}/folders/file/{fileId}")
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response readFiles(@PathParam("ownerId") int ownerId, 
								@PathParam("fileId") int fileId)

	{
		if (fileId == 0) {
			return HttpUtils.errorResponse(Response.Status.BAD_REQUEST, "Sai định dạng", gson);
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
			return HttpUtils.errorResponse(Response.Status.NOT_FOUND, "File Không tồn tại", gson);
		}
	}

	@GET
	@Path("/{ownerId}/folders/{folderId}")
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response getUserFiles(@PathParam("ownerId") int ownerId, 
									@PathParam("folderId") int folderId,
								@DefaultValue("1") @QueryParam("page") int page) {
		FileEntity fileEntity = fileService.findOne(folderId);
		if (fileEntity != null && fileEntity.getType().getName().equals("Directory")) {
			return Response
					.ok(gson.toJson(fileService.getFileUsers(ownerId, fileEntity.getNodeId() + 1)))
					.build();

		} else {
			return HttpUtils.errorResponse(Response.Status.NOT_FOUND, "Folder Không tồn tại", gson);
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

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response loginUser(UserEntity user) {
		UserEntity userFind = userService.findOne(user);
		if (userFind == null) {
			return 	HttpUtils.errorResponse(Response.Status.UNAUTHORIZED, "Đăng nhập thất bại", gson);
		} else {
			return Response
					.ok(gson.toJson(userFind))
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
			return 	HttpUtils.errorResponse(Response.Status.UNAUTHORIZED, "Đăng kí thất bại", gson);
		} else {
			return Response
					.ok(gson.toJson(userService.findOne(id)))
					.build();
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
