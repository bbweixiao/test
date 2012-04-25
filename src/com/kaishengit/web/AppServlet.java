package com.kaishengit.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Account;
import com.kaishengit.service.AccountService;
import com.kaishengit.util.StringUtil;

public class AppServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method = request.getParameter("m");
		if(StringUtil.isEmpty(method)) {
			request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		} else if("login".equals(method)) {
			if("POST".equals(request.getMethod())) {
				login(request,response);
			} else {
				response.sendError(405);
			}
		}
		
	}


	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("name");
		String password = request.getParameter("password");
		
		AccountService service = new AccountService();
		Account account = service.login(username,password);
		
		if(account == null) {
			response.sendRedirect("index.jspx?state=10001");
		} else {
			if(account.isEnable()) {
				HttpSession session = request.getSession();
				session.setAttribute("session_account", account);
			
				//request.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(request, response);
				response.sendRedirect("main.jspx");
			} else {
				response.sendRedirect("index.jspx?state=10002");
			}
		}
	}
	

}
