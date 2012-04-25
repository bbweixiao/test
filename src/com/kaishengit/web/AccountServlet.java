package com.kaishengit.web;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Account;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.service.AccountService;

public class AccountServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String method = request.getParameter("m");
		if("get".equals(method)) {
			if("POST".equals(request.getMethod())) {
				getMoney(request,response);
			} else {
				request.getRequestDispatcher("/WEB-INF/views/getmoney.jsp").forward(request, response);
			}
		} else if("save".equals(method)) {
			if("POST".equals(request.getMethod())) {
				saveMoney(request,response);
			} else {
				request.getRequestDispatcher("/WEB-INF/views/savemoney.jsp").forward(request, response);
			}
		}  else if("tran".equals(method)) {
			if("POST".equals(request.getMethod())) {
				tranMoney(request,response);
			} else {
				request.getRequestDispatcher("/WEB-INF/views/tranmoney.jsp").forward(request, response);
			}
		} else {
			response.sendError(404,"Ò³Ãæ²»´æÔÚ");
		}
		
		
	}

	private void tranMoney(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String id = request.getParameter("id");
		String money = request.getParameter("money");
		
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("session_account");
		
		AccountService service = new AccountService();
		try {
			service.tranMoney(account,id,Float.parseFloat(money));
			response.sendRedirect("account.jspx?m=tran&state=10000");
		} catch (ServiceException e) {
			String msg = e.getMessage();
			session.setAttribute("msg", msg);
			response.sendRedirect("account.jspx?m=tran");
		}
		
		
		
	}

	private void saveMoney(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String money = request.getParameter("money");
		
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("session_account");
		
		AccountService service = new AccountService();
		try {
			service.save(account,Float.parseFloat(money));
			response.sendRedirect("account.jspx?m=save&state=10000");
		} catch (ServiceException e) {
			String msg = e.getMessage();
			session.setAttribute("msg", msg);
			response.sendRedirect("account.jspx?m=save");
		}
		
		
		
	}

	private void getMoney(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String money = request.getParameter("money");
		
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("session_account");
		
		AccountService service = new AccountService();
		try {
			service.getMoney(account,Float.parseFloat(money));
			
			response.sendRedirect("account.jspx?m=get&state=10000");
		} catch (ServiceException e) {
			//
			String msg = e.getMessage();
			session.setAttribute("msg", msg);
			response.sendRedirect("account.jspx?m=get");
		}
		
	}
}
