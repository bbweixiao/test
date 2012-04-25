package com.kaishengit.web;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kaishengit.entity.Account;
import com.kaishengit.entity.Recode;
import com.kaishengit.service.RecodeService;

public class RecodeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		Account a = (Account) session.getAttribute("session_account");
		
		RecodeService service = new RecodeService();
		List<Recode> list = service.findRecodeByAccount(a);
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/recode.jsp").forward(request, response);
		
	}
}
