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

import com.act.act.model.Act_Service;
import com.act.act.model.Act_VO;
import com.act.actMem.model.ActMemService;
import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIService;
import com.gen.tool.tools;
import com.member.model.MemberHVO;

public class ActMemServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			System.out.println(requestURL);	
			try {
				/***************************1.接收請求參數***************************************/
				Integer memID = 1; //@@@@@@@@@@@@@@@@@@@@@@@@
				Integer actID=new Integer(req.getParameter("actID"));
					ActMemVO amVO=new ActMemVO();
						MemberHVO mvo=new MemberHVO();
							mvo.setMemID(memID);
						Act_VO av=new Act_VO();
							av.setActID(actID);
					amVO.setActVO(av);
					amVO.setMemberHVO(mvo);
	
				/***************************2.開始new資料***************************************/
				ActMemService actmSvc = new ActMemService();
				actmSvc.delete(amVO);
			
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
				Integer memID = 1; //@@@@@@@@@@@@@@@@@@@@@@@@
				Integer actID=new Integer(req.getParameter("actID"));
				

					ActMemVO amVO=new ActMemVO();
						MemberHVO mvo=new MemberHVO();
						mvo.setMemID(memID);
					amVO.setMemberHVO(mvo);
					amVO.setActJoinDate(tools.nowTimestamp());
					amVO.setActMemStatus(2);
						Act_VO av=new Act_VO();
						av.setActID(actID);
					amVO.setActVO(av);

	
				/***************************2.開始new資料***************************************/
				ActMemService actmSvc = new ActMemService();
				actmSvc.insert(amVO);
			
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
		
		
		if ("insert5".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			System.out.println(requestURL);	
			try {
				/***************************1.接收請求參數***************************************/
				Integer memID = 1; //@@@@@@@@@@@@@@@@@@@@@@@@
				Integer actID=new Integer(req.getParameter("actID"));
				
				ActMemVO amVO=new ActMemVO();
				MemberHVO mvo=new MemberHVO();
					mvo.setMemID(memID);
				amVO.setMemberHVO(mvo);
				amVO.setActJoinDate(tools.nowTimestamp());
				amVO.setActMemStatus(5);
					Act_VO av=new Act_VO();
					av.setActID(actID);
				amVO.setActVO(av);

		/***************************2.開始new資料***************************************/
		ActMemService actmSvc = new ActMemService();
		actmSvc.insert(amVO);
			
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
		
		
		if ("update2".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
			System.out.println(requestURL);	
			try {
				/***************************1.接收請求參數***************************************/
				Integer memID = 1; //@@@@@@@@@@@@@@@@@@@@@@@@
				Integer actID=new Integer(req.getParameter("actID"));
			/***************************2.開始查詢資料****************************************/
				ActMemService amS = new ActMemService();
				ActMemVO amVO=amS.getOne(actID,memID);

				amVO.setActMemStatus(2);
			
					amS.update(amVO);
		
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
				Integer memID = 1; //@@@@@@@@@@@@@@@@@@@@@@@@
				Integer actID=new Integer(req.getParameter("actID"));
			/***************************2.開始查詢資料****************************************/
				ActMemService amS = new ActMemService();
				ActMemVO amVO=amS.getOne(actID,memID);

				amVO.setActMemStatus(5);
			
					amS.update(amVO);
		
				 res.sendRedirect(req.getContextPath()+"/front-end/act/act.do?actID="+actID+"&action=showOne");
			
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
	
	
	
	
	
