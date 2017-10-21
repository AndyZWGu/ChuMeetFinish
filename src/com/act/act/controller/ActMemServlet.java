package com.act.act.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.act.actMem.model.ActMemService;
import com.member.model.MemberVO;

public class ActMemServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
		if ("insert5".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			System.out.println(requestURL);	
			try {
				/***************************1.接收請求參數***************************************/
				HttpSession session = req.getSession();
				MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
				Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
				Integer actID=new Integer(req.getParameter("actID"));
				System.out.println("yeah1");
				/***************************2.開始new資料***************************************/
				ActMemService actmSvc = new ActMemService();
				System.out.println("yeah2");
				System.out.println("@actMem.insert5 actID="+actID+", memid="+memID);
				actmSvc.insert(actID, memID, 5);
				/***************************3.new完成,準備轉接***********/
				System.out.println("yeah3");
				res.sendRedirect(req.getContextPath()+"/front-end/act/act.do?actID="+actID+"&action=showOne");
				/***************************其他可能的錯誤處理**********************************/
						} catch (Exception e) {
							errorMsgs.add(e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/x_x.jsp");
							failureView.forward(req, res);
						}
					}	//end of if
		
		
		if ("update2".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			System.out.println(requestURL);	
			try {
			/***************************1.接收請求參數***************************************/
					HttpSession session = req.getSession();
					MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
					Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
					Integer actID=new Integer(req.getParameter("actID"));
		
			/***************************2.開始new資料***************************************/
					ActMemService actmSvc = new ActMemService();
					actmSvc.delete(actID, memID);
					actmSvc.insert(actID, memID, 2);
					res.sendRedirect(req.getContextPath()+"/front-end/act/act.do?actID="+actID+"&action=showOne");
					/***************************其他可能的錯誤處理************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				}
			}
		
		
		if ("update5".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			System.out.println(requestURL);	
			try {
				/***************************1.接收請求參數***************************************/
				HttpSession session = req.getSession();
				MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
				Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
				Integer actID=new Integer(req.getParameter("actID"));
	
		/***************************2.開始new資料***************************************/
				ActMemService actmSvc = new ActMemService();
				actmSvc.delete(actID, memID);
				actmSvc.insert(actID, memID, 5);
				
/***************************3.new完成,準備轉接***********/
				
				 res.sendRedirect(req.getContextPath()+"/front-end/act/act.do?actID="+actID+"&action=showOne");
				

				/***************************其他可能的錯誤處理**********************************/
						} catch (Exception e) {
							errorMsgs.add(e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/emp/addEmp.jsp");
							failureView.forward(req, res);
						}
					}	//end of if
		
		
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			System.out.println(requestURL);	
			try {
				/***************************1.接收請求參數***************************************/
				HttpSession session = req.getSession();
				MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
				Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
				Integer actID=new Integer(req.getParameter("actID"));
	
				/***************************2.開始new資料***************************************/
				ActMemService actmSvc = new ActMemService();
				actmSvc.delete(actID, memID);
			
				/***************************3.new完成,準備轉接***********/
				
				 res.sendRedirect(req.getContextPath()+"/front-end/act/act.do?actID="+actID+"&action=showOne");
				

				/***************************其他可能的錯誤處理**********************************/
						} catch (Exception e) {
							errorMsgs.add(e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/emp/addEmp.jsp");
							failureView.forward(req, res);
						}
					}	//end of if

		if ("insert2".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			System.out.println(requestURL);	
			try {
				/***************************1.接收請求參數***************************************/
				HttpSession session = req.getSession();
				MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
				Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
				Integer actID=new Integer(req.getParameter("actID"));
				Integer stat=2;
	
				/***************************2.開始new資料***************************************/
				ActMemService actmSvc = new ActMemService();
				actmSvc.insert(actID, memID, stat);
			
				/***************************3.new完成,準備轉接***********/
				
				 res.sendRedirect(req.getContextPath()+"/front-end/act/act.do?actID="+actID+"&action=showOne");
				

				/***************************其他可能的錯誤處理**********************************/
						} catch (Exception e) {
							errorMsgs.add(e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/emp/addEmp.jsp");
							failureView.forward(req, res);
						}
					}	//end of if
		
		if ("insert".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			System.out.println(requestURL);	
			try {
				/***************************1.接收請求參數***************************************/
				HttpSession session = req.getSession();
				MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
				Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
				Integer actID=new Integer(req.getParameter("actID"));
				Integer stat=new Integer(req.getParameter("insertStat"));
	
				/***************************2.開始new資料***************************************/
				ActMemService actmSvc = new ActMemService();
				actmSvc.insert(actID, memID, stat);
			
				/***************************3.new完成,準備轉接***********/
				
				 res.sendRedirect(req.getContextPath()+"/front-end/act/act.do?actID="+actID+"&action=showOne");
				

				/***************************其他可能的錯誤處理**********************************/
						} catch (Exception e) {
							errorMsgs.add(e.getMessage());
							RequestDispatcher failureView = req
									.getRequestDispatcher("/emp/addEmp.jsp");
							failureView.forward(req, res);
						}
					}	//end of if
		
	}
}
	
	
	
	
	
