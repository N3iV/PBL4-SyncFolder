package com.SyncFolderPBL4.controller.api;

import java.io.File;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

@Path("/files")
public class FileRestApi {
	@Context
	private ServletContext application;

	private static Gson gson;
	private IUserService userService;
	private IFileService fileService;

	public FileRestApi() {
		userService = new UserService();
		fileService = new FileService();
		gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
	}

	@GET
	@Path("/download/{fileId}")
	public Response downloadFile(@PathParam("fileId") int fileId) {

		FileEntity fileEntity = fileService.findOne(fileId);
		if (fileEntity != null) {
			String readPath = FileUtils.getPathProject(application.getRealPath(""))
								.concat(fileEntity.getPath());
			File file = null;
			String mimeType =null;
			String fileName = null;
			if (fileEntity.getType().getName().equals("Directory")) {
				file = FileUtils.zippingFile(fileEntity, readPath);
				mimeType = "application/zip" + SystemConstant.CHARSET;
				fileName = file.getName();

			} else if (fileEntity.getType().getName().equals("File")) {
				file = new File(readPath);
				mimeType = MediaType.APPLICATION_OCTET_STREAM + SystemConstant.CHARSET;
				fileName = fileEntity.getName();
			}
			return Response.ok(file, mimeType)
					.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
					.build();
		} else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(gson.toJson(HttpUtils.toJsonObject("File Không tồn tại")))
					.type(MediaType.APPLICATION_JSON + SystemConstant.CHARSET)
					.build();
		}
	}

}
