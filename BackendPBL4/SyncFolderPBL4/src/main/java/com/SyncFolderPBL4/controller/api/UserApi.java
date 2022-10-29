package com.SyncFolderPBL4.controller.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@WebServlet(urlPatterns = { "/user", "/user/register", "/user/login", "/user/*" })

public class UserApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Gson gson;
	private static PrintWriter out;
	private IUserService userService;
	private IFileService fileService;
	public UserApi() {
		userService = new UserService();
		fileService = new FileService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setPrettyPrinting()
				.create();
		
		System.out.println();
		
		Integer ownerId = Integer.parseInt(request.getPathInfo().split("/")[1]);
		Integer fileId = (request.getParameter("fileId") != null) ? Integer.parseInt(request.getParameter("fileId"))
				: null;
		Integer page  = Integer.parseInt(request.getParameter("page"));
		if(fileId == null)
		{
			out = response.getWriter();
			out.print(gson.toJson(fileService.getFileUsers(ownerId, page)));
			return;
		}
		
		FileEntity fileEntity = fileService.findOne(fileId);
		if (fileEntity != null) {
			if (fileEntity.getType().getName().equals("Directory")) {
				
				out = response.getWriter();
				out.print(gson.toJson(fileService.getFileUsers(ownerId, fileEntity.getNodeId() + 1)));
				
			} else if (fileEntity.getType().getName().equals("File")) {
				
				String readPath = getServletContext().getRealPath("").concat(fileEntity.getPath());
				File file = new File(readPath);
				String extension = fileEntity.getName().substring(fileEntity.getName().lastIndexOf("."));
				writeFileResponse(file, response, extension);
				
			}
		} else {
			response.setStatus(404);
			out = response.getWriter();
			out.print(gson.toJson(HttpUtils.toJsonObject("File Không tồn tại")));
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		gson = new GsonBuilder().
				excludeFieldsWithoutExposeAnnotation().
				setPrettyPrinting()
				.create();
		out = response.getWriter();
		String storePath = FileUtils.getPathProject(getServletContext().getRealPath("")).concat(SystemConstant.CONCAT_PATH);
		UserEntity user = new UserEntity();
		if (HttpUtils.getPathURL(request.getRequestURI()).equals("register")) {
			user = gson.fromJson(HttpUtils.getjson(request.getReader()), UserEntity.class);
			Integer id = userService.createUser(user,storePath);
			if (id == null) {
				response.setStatus(405);
				out.print(gson.toJson(HttpUtils.toJsonObject("Đăng kí thất bại")));
			} else {
				out.print(gson.toJson(userService.findOne(id)));
			}
		} else if ((HttpUtils.getPathURL(request.getRequestURI()).equals("login"))) {
			user = gson.fromJson(HttpUtils.getjson(request.getReader()), UserEntity.class);
			UserEntity userFind = userService.findOne(user);
			if (userFind == null) {
				response.setStatus(405);
				out.print(gson.toJson(HttpUtils.toJsonObject("Đăng nhập thất bại")));
			} else {
				out.print(gson.toJson(HttpUtils.toJsonObject("Đăng nhập thành công")));
			}
		}

	}

	private static void writeFileResponse(final File file, final HttpServletResponse response, String extension)
			throws ServletException, IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		switch (extension) {
		case ".txt":
			response.setContentType("text/plain");
			break;
		case ".pdf":
			response.setContentType("application/pdf");
			break;
		}
		response.setContentLength((int) file.length());
		int s;
		while ((s = bis.read()) != -1) {
			bos.write(s);
		}
		bis.close();
		bos.close();
	}
	
}
