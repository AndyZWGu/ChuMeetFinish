package com.admin.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.admPril.model.*;
import com.admin.model.AdminService;
import com.admin.model.AdminVO;

/**
 * Servlet implementation class BackLogin
 */

public class BackLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected boolean BackLogin(String adminMail,String adminPW) {
		AdminService adminSvc = new AdminService();
		List<AdminVO> adminList = adminSvc.getAll();
		//判斷帳號是否正確,判斷帳號正確時密碼是否正確
		Boolean isAdminMail = false;
		Boolean isAdminPw = false;
		//判斷帳號存不存在
		for (AdminVO adminVO : adminList) {
			String serverAdminMail = adminVO.getAdminMail().toString();
			if(adminMail.equals(serverAdminMail))
				isAdminMail=true;
			
		}
		//判斷密碼存不存在
			if(isAdminMail){
				AdminVO adminVO = adminSvc.getAdminByAdminMail(adminMail);
				if(adminPW.equals(adminVO.getAdminPW()))
					isAdminPw=true;
			}
//			System.out.println("帳號為"+adminMail);
//			System.out.println("密碼為"+adminPW);
//			System.out.println("帳號存不存在"+isAdminMail);
//			System.out.println("密碼正不正確"+isAdminPw);
		if(isAdminMail&&isAdminPw)
			return true;
		else
			return false;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMags", errorMsgs);

		String adminMail = req.getParameter("adminMail");
		String adminPW = req.getParameter("adminPW");
		
		if (adminMail == null || (adminMail.trim()).length() == 0) {
			errorMsgs.add("帳號不得為空");
		}
		if (adminPW == null || (adminPW.trim()).length() == 0) {
			errorMsgs.add("密碼不得為空");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("backLogin.jsp");
			failureView.forward(req, res);
			return;
		}
		if (!BackLogin(adminMail, adminPW)) {
			errorMsgs.add("錯誤");
			RequestDispatcher failureView = req.getRequestDispatcher("backLogin.jsp");
			failureView.forward(req, res);
			return;
		} else {
			AdminService adminService = new AdminService();
			AdminVO adminVO = adminService.getAdminByAdminMail(adminMail);
			HttpSession session = req.getSession();
			session.setAttribute("adminName", adminVO.getAdminName());
			session.setAttribute("adminMail", adminMail);
			session.setAttribute("adminVO", adminVO);
			AdmPrilService admPrilSvc = new AdmPrilService();
			List<AdmPrilVO> admPrilList = new ArrayList<AdmPrilVO>();
			admPrilList = admPrilSvc.getAdmPrilList(adminVO.getAdminID());
			session.setAttribute("admPrilList", admPrilList);

			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
			res.sendRedirect(req.getContextPath() + "/back-end/index.jsp");

		}
	}

}
