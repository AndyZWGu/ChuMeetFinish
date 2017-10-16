package com.act.act.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.act.act.model.Act_Service;
import com.act.actMem.model.ActMemService;
import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIService;
import com.act.model.ActMBService;
import com.act.model.ActMBVO;
import com.gen.tool.tools;
import com.member.model.*;


public class ActMBServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insertMB".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
//			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
//			System.out.println("URL="+requestURL);	
//			try {
				/***************************1.接收請求參數***************************************/
				HttpSession session = req.getSession();
				MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
				Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
				Integer actID=Integer.parseInt(req.getParameter("actID"));
				String actMBContent=req.getParameter("actMBContent");

				/***************************2.開始new資料***************************************/
				ActMBService ambS = new ActMBService();
				System.out.println("actID="+actID+", memID="+memID+actMBContent);
				 ambS.insert(actID, memID, actMBContent);
			
				/***************************3.new完成,準備轉接***********/
				
				 res.sendRedirect(req.getContextPath()+"/front-end/act/act.do?actID="+actID+"&action=showOne");
		

				/***************************其他可能的錯誤處理**********************************/
//						} catch (Exception e) {
//							errorMsgs.add(e.getMessage());
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/emp/addEmp.jsp");
//							failureView.forward(req, res);
//						}
					}	//end of if
	
	if ("deleteMB".equals(action)) { 

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
//		String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
//		System.out.println("URL="+requestURL);	
//		try {
			/***************************1.接收請求參數***************************************/
			HttpSession session = req.getSession();
			MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
			Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
			Integer actMBID=Integer.parseInt(req.getParameter("actMBID"));
			Integer actID=Integer.parseInt(req.getParameter("actID"));
			/***************************2.開始new資料***************************************/
			ActMBService ambS = new ActMBService();

			 ambS.delete(actMBID);
		
			/***************************3.new完成,準備轉接***********/
			
			 res.sendRedirect(req.getContextPath()+"/front-end/act/act.do?actID="+actID+"&action=showOne");
	}

			/***************************其他可能的錯誤處理**********************************/
//					} catch (Exception e) {
//						errorMsgs.add(e.getMessage());
//						RequestDispatcher failureView = req
//								.getRequestDispatcher("/emp/addEmp.jsp");
//						failureView.forward(req, res);
//					}
				}	//end of if	
	
		
		
			
	
}
	
	
	
