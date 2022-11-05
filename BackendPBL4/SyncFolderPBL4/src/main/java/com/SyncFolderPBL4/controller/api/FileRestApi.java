package com.SyncFolderPBL4.controller.api;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.SyncFolderPBL4.config.LocalDateTimeAdapter;
import com.SyncFolderPBL4.constant.SystemConstant;
import com.SyncFolderPBL4.model.entities.FileEntity;
import com.SyncFolderPBL4.model.service.IFileService;
import com.SyncFolderPBL4.model.service.IUserService;
import com.SyncFolderPBL4.model.service.impl.FileService;
import com.SyncFolderPBL4.model.service.impl.UserService;
import com.SyncFolderPBL4.utils.FileUtils;
import com.SyncFolderPBL4.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/folders")
public class FileRestApi {
	@Context
	private ServletContext application;

	private static Gson gson;
	private IUserService userService;
	private IFileService fileService;

	public FileRestApi() {
		userService = new UserService();
		fileService = new FileService();
		gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.setPrettyPrinting()
				.create();
	}
	
	@POST
	@Path("/{folderId}")
	@Produces(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
	public Response createFile(@PathParam("folderId") int folderId,
							   FileEntity fileRequest) {
		String readPath = FileUtils.getPathProject(application.getRealPath(""));
		FileEntity file = fileService.createFolder(folderId, fileRequest.getName(), readPath);
		
		return Response
				.ok(gson.toJson(fileService.getAllFilesDESC(file.getOwnerId(),1, file.getNodeId())))
				.build();
	}
	
	
	@GET
	@Path("/{folderId}/download")
	public Response downloadFolder(@PathParam("folderId") int folderId) {

		FileEntity fileEntity = fileService.findOne(folderId);
		if (fileEntity != null && fileEntity.getType().getName().equals("Directory")) {
			String readPath = FileUtils.getPathProject(application.getRealPath(""))
								.concat(fileEntity.getPath());
			File file = FileUtils.zippingFile(fileEntity, readPath);
			return Response.ok(file, "application/zip" + SystemConstant.CHARSET)
					.header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
					.build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(gson.toJson(HttpUtils.toJsonObject("Folder không tồn tại")))
					.type(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
					.build();
		}
	}
	
	@GET
	@Path("/file/{fileId}/download")
	public Response downloadFile(@PathParam("fileId") int fileId) {

		FileEntity fileEntity = fileService.findOne(fileId);
		if (fileEntity != null && fileEntity.getType().getName().equals("File")) {
			String readPath = FileUtils.getPathProject(application.getRealPath(""))
								.concat(fileEntity.getPath());
			File file = new File(readPath);
			return Response.ok(file, MediaType.APPLICATION_OCTET_STREAM + SystemConstant.CHARSET)
					.header("Content-Disposition", "attachment; filename=\"" + fileEntity.getName() + "\"")
					.build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(gson.toJson(HttpUtils.toJsonObject("File không tồn tại")))
					.type(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
					.build();
		}
	}
	
	@POST
	@Path("/file/{folderId}/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadFile(@FormDataParam("uploadFile") InputStream is,
							   @FormDataParam("uploadFile") FormDataContentDisposition fdcd,
							   @PathParam("folderId") int folderId)
	{
		String readPath = FileUtils.getPathProject(application.getRealPath(""));
		FileEntity file = fileService.uploadFile(folderId, is, fdcd, readPath);
		return Response
				.ok(gson.toJson(fileService.getAllFilesDESC(file.getOwnerId(),1, file.getNodeId())))
				.build();
	}

}
