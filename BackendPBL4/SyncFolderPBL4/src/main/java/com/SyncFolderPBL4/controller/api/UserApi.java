package com.SyncFolderPBL4.controller.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SyncFolderPBL4.constant.SystemConstant;
import com.SyncFolderPBL4.model.entities.UserEntity;
import com.SyncFolderPBL4.model.service.IUserService;
import com.SyncFolderPBL4.model.service.impl.UserService;
import com.SyncFolderPBL4.utils.HttpUtils;
import com.google.gson.Gson;

@WebServlet(urlPatterns = {"/user","/user/register"})

public class UserApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Gson gson;
	private static PrintWriter out;
	private IUserService userService;
//	private String defaultPath = getServletContext().getRealPath("");
	
	public UserApi() {
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String applicationPath = getServletContext().getRealPath("");
//		System.out.println(applicationPath + SystemConstant.CONCAT_PATH);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		gson = new Gson();
		out = response.getWriter();
		UserEntity user = new UserEntity();
		if(HttpUtils.getPathURL(request.getRequestURI()).equals("register")) {
			user = gson.fromJson(HttpUtils.getjson(request.getReader()), UserEntity.class);
			Long id = userService.save(user);
			if (id == null) {
				response.setStatus(405);
				out.print(gson.toJson(HttpUtils.toJsonObject("Đăng kí thất bại")));
			} else {
				out.print(gson.toJson(userService.findOne(id)));
			}
		}
		
	}

}
