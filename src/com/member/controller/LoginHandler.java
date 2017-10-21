package com.member.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Loginhandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		// 【取得使用者 帳號(account) 密碼(password)】
		String memEmail = req.getParameter("memEmail");
		String memPw = req.getParameter("memPw");

		// 資料格式驗證
		if (memEmail == null || (memEmail.trim()).length() == 0) {
			errorMsgs.add("請輸入會員帳號");
		}
		if (memPw == null || (memPw.trim()).length() == 0) {
			errorMsgs.add("請輸入會員密碼");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("register.jsp");
			failureView.forward(req, res);
			return;
		}

		// 【檢查該帳號 , 密碼是否有效】
		if (!allowUser(memEmail, memPw)) { // 【帳號 , 密碼無效時】
			errorMsgs.add("查無帳號或密碼錯誤,請重新登入");
			RequestDispatcher failureView = req.getRequestDispatcher("login.jsp");
			failureView.forward(req, res);
			return;
		} else { // 【帳號 , 密碼有效時, 才做以下工作】
			MemberService memSvc = new MemberService();
			MemberVO memVO = memSvc.getMemberByMemEmail(memEmail);
			//上站數+1
			memSvc.updateLoginNumMember(memVO);
			HttpSession session = req.getSession();
			session.setAttribute("account", memEmail); // *工作1:
														// 才在session內做已經登入過的標識
			session.setAttribute("memVO", memVO);
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁
															// (-->如有來源網頁:則重導至來源網頁)(改)(登完回首頁)
//					res.sendRedirect(location);
					res.sendRedirect(req.getContextPath() + "/front-end/index.jsp");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			res.sendRedirect(req.getContextPath() + "/front-end/member/memberHome.do"); // *工作3:
																				// (-->如無來源網頁:則重導至index.jsp)
		}
	}
	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】
	protected boolean allowUser(String memEmail, String memPw) {
		MemberService memSvc = new MemberService();
		MemberVO memEmailVO = memSvc.getMemberByMemEmail(memEmail);
		if (memEmailVO != null && memEmailVO.getMemEmail().equals(memEmail) && memEmailVO.getMemPw().equals(memPw)) {
			return true;
		} else
			return false;
	}
	
}