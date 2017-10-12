package com.club.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.club.model.*;
import com.clubMB.model.ClubMBService;
import com.clubMB.model.ClubMBVO;


public class ClubMBServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("clubMBID");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入員工編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/club/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer clubMBID = null;
//				try {
//					clubMBID = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("員工編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/club/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				ClubMBService clubMBSvc = new ClubMBService();
//				ClubMBVO clubMBVO = clubMBSvc.getOneClubMB(clubMBID);
//				if (clubMBVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/club/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("clubMBVO", clubMBVO); // 資料庫取出的empVO物件,存入req
//				String url = "/club/ClubOne.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/club/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
		
		
//		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				/***************************1.接收請求參數****************************************/
//				Integer clubMBID = new Integer(req.getParameter("clubMBID"));
//				
//				/***************************2.開始查詢資料****************************************/
//				ClubMBService clubMBSvc = new ClubMBService();
//				ClubMBVO clubMBVO = clubMBSvc.getOneClubMB(clubMBID);
//								
//				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//				req.setAttribute("clubMBVO", clubMBVO);         // 資料庫取出的empVO物件,存入req
//				String url = "/club/update_club_input.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/club/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		
//		
//		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//		
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				Integer clubMBID = new Integer(req.getParameter("clubMBID").trim());
//				
//				
//				String clubMBContent = req.getParameter("clubMBContent").trim();
//
//
//				
//				
//				Integer clubID = null;
//				try {
//					clubID = new Integer(req.getParameter("clubID").trim());
//				} catch (NumberFormatException e) {
//					clubID = 1;
//					errorMsgs.add("ID請填數字.");
//				}
//				
//				
//				Integer memID = null;
//				try {
//					memID = new Integer(req.getParameter("clubTypeID").trim());
//				} catch (NumberFormatException e) {
//					memID = 1;
//					errorMsgs.add("類型請填數字.");
//				}
//				
//				
//				
//				
//				java.sql.Date clubMBDate = null;
//				try {
//					clubMBDate = java.sql.Date.valueOf(req.getParameter("clubMBDate").trim());
//				} catch (IllegalArgumentException e) {
//					clubMBDate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				
//	
//
//				
//				
//				Integer clubMBStatus = null;
//				try {
//					clubMBStatus = new Integer(req.getParameter("clubMBStatus").trim());
//				} catch (NumberFormatException e) {
//					clubMBStatus = 1;
//					errorMsgs.add("狀態請填數字.");
//				}
//				
//				
//
//		
//				
//				ClubMBVO clubMBVO = new ClubMBVO();
//				clubMBVO.setClubMBID(clubMBID);	
//				clubMBVO.setClubID(clubID);				
//				clubMBVO.setMemID(memID);
//				clubMBVO.setClubMBContent(clubMBContent);
//				clubMBVO.setClubMBDate(clubMBDate);
//				clubMBVO.setClubMBStatus(clubMBStatus);
//
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("clubMBVO", clubMBVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/club/update_club_input.jsp");
//					failureView.forward(req, res);
//					return; //程式中斷
//				}
//				
//				/***************************2.開始修改資料*****************************************/
//				ClubMBService clubSvc = new ClubMBService();
//				clubMBVO = clubSvc.updateClubMB(clubMBID,clubID,memID,clubMBContent,clubMBDate,clubMBStatus);
//				
//				/***************************3.修改完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("clubMBVO", clubMBVO); // 資料庫update成功後,正確的的empVO物件,存入req
//				String url = "/club/listOneClub.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("修改資料失敗:"+e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/club/update_club_input.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//		
//		
//		
//		
//		
//
//        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
//			
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//
//				Integer clubMBID = new Integer(req.getParameter("clubMBID").trim());
//				
//				
//				String clubMBContent = req.getParameter("clubMBContent").trim();
//
//
//				
//				
//				Integer clubID = null;
//				try {
//					clubID = new Integer(req.getParameter("clubID").trim());
//				} catch (NumberFormatException e) {
//					clubID = 1;
//					errorMsgs.add("ID請填數字.");
//				}
//				
//				
//				Integer memID = null;
//				try {
//					memID = new Integer(req.getParameter("clubTypeID").trim());
//				} catch (NumberFormatException e) {
//					memID = 1;
//					errorMsgs.add("類型請填數字.");
//				}
//				
//				
//				
//				
//				java.sql.Date clubMBDate = null;
//				try {
//					clubMBDate = java.sql.Date.valueOf(req.getParameter("clubMBDate").trim());
//				} catch (IllegalArgumentException e) {
//					clubMBDate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
//				
//	
//
//				
//				
//				Integer clubMBStatus = null;
//				try {
//					clubMBStatus = new Integer(req.getParameter("clubMBStatus").trim());
//				} catch (NumberFormatException e) {
//					clubMBStatus = 1;
//					errorMsgs.add("狀態請填數字.");
//				}
//					
//				
//	
//				ClubMBVO clubMBVO = new ClubMBVO();
//				clubMBVO.setClubMBID(clubMBID);	
//				clubMBVO.setClubID(clubID);				
//				clubMBVO.setMemID(memID);
//				clubMBVO.setClubMBContent(clubMBContent);
//				clubMBVO.setClubMBDate(clubMBDate);
//				clubMBVO.setClubMBStatus(clubMBStatus);
//				
//
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					req.setAttribute("clubMBVO", clubMBVO); // 含有輸入格式錯誤的empVO物件,也存入req
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/club/addClub.jsp");
//					failureView.forward(req, res);
//					return;
//				}
//				
//				/***************************2.開始新增資料***************************************/
//				ClubMBService clubMBSvc = new ClubMBService();
//				clubMBVO = clubMBSvc.addClubMB(clubMBID,clubID,memID,clubMBContent,clubMBDate,clubMBStatus);
//				
//				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/club/listAllClubMB.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//				successView.forward(req, res);				
//				
//				/***************************其他可能的錯誤處理**********************************/
//			} catch (Exception e) {
//				errorMsgs.add(e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/club/addClub.jsp");
//				failureView.forward(req, res);
//			}
//		}
//		
//		
//
//        
//    	if ("getOneMB_For_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			try {
//				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("clubMBID");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入社團編號才能知道留言");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/club/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				Integer clubMBID = null;
//				try {
//					clubMBID = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("社團編號格式不正確");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/club/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************2.開始查詢資料*****************************************/
//				ClubMBService clubMBSvc = new ClubMBService();
//				ClubMBVO clubMBVO = clubMBSvc.getOneClubMB(clubMBID);
//				if (clubMBVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/club/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
//				
//				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//				req.setAttribute("clubMBVO", clubMBVO); // 資料庫取出的empVO物件,存入req
//				String url = "/club/ClubOne.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
//				successView.forward(req, res);
//
//				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/club/select_page.jsp");
//				failureView.forward(req, res);
//			}
//		}
//        
//      //以下自己加
//    	//找尋某社團所有MB
     	if ("getOneMB_For_Display".equals(action)) { // 來自select_page.jsp的請求

     		String clubID = req.getParameter("clubID");	//傳進來的是字串轉成數字
				
			
				/***************************2.開始查詢資料*****************************************/
	
				ClubMBService clubMBSvc = new ClubMBService();
				ClubMBVO clubMBVO = clubMBSvc.getOneClubMB(Integer.parseInt(clubID));

				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("clubMBVO", clubMBVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/club/ClubOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
				successView.forward(req, res);


			
		}
        
        
        
	}
}
