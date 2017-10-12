package com.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberService;
import com.member.model.MemberVO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		String action = "login";
		
		if ("login".equals(action)) { // 來自login.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String memEmail = req.getParameter("memEmail");
				String memPw = req.getParameter("memPw");
//				String memEmail = "adm@gmail";
//				String memPw = "Master";
				if (memEmail == null || (memEmail.trim()).length() == 0) {
					errorMsgs.add("請輸入會員帳號");
				}
				if (memPw == null || (memPw.trim()).length() == 0) {
					errorMsgs.add("請輸入會員密碼");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("login.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String formatMemEmail = null;
				String formatMemPw = null;
				try {
					formatMemEmail = new String();
					formatMemPw = new String();
				} catch (Exception e) {
					errorMsgs.add("輸入格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("login.jsp");
					failureView.forward(req, res);
					return;//
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getMemberByMemEmail(memEmail);
				if (memVO == null) {
					errorMsgs.add("查無帳號或密碼,請重新登入");
				}
				MemberVO memVO2 = memSvc.getMemberByMemPw(memPw);
				if (memVO2 == null) {
					errorMsgs.clear();
					errorMsgs.add("您輸入的密碼有誤,請確認");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				//測試用
//				req.setAttribute("memVO", memVO); 
//				String url = "/member/memHome.jsp"; 
//				RequestDispatcher successView = req.getRequestDispatcher(url); 
//				successView.forward(req, res);
				
				//正式處理Session
				HttpSession session = req.getSession();
				session.setAttribute("memVO", memVO);
				res.sendRedirect("/ChuMeetWebsite/front-end/index.jsp");

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req
						.getRequestDispatcher("login.jsp");
				failureView.forward(req, res);
			}
		}else{
			RequestDispatcher failureView = req
					.getRequestDispatcher("front-end/member/login.jsp");
			failureView.forward(req, res);
		}
		
	}

}
