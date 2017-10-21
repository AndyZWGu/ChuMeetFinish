package com.clubBackground.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.clubTicket.model.*;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ClubTKServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		doPost(req, res); // 雖然用超連結傳進來,轉交傳給doPost處理
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println("進來");
		
		//刪除
				if ("deleteOneClubTk".equals(action)) {
System.out.println("deleteOneClubTk1");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				Integer clubTkID = Integer.parseInt(req.getParameter("clubTkID").trim());
				Integer clubTkStatID =0;
	
System.out.println("deleteOneClubTk2");		

				ClubTicketVO clubTkVO = new ClubTicketVO();	

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("clubTKVO", clubTkVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/club/ClubUpdate.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				
System.out.println("deleteOneClubTk3");	
				ClubTicketService clubTkSvc = new ClubTicketService();
				clubTkVO = clubTkSvc.changeOneClubTkStatID(clubTkID, clubTkStatID);
						
		
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("clubTkVO", clubTkVO); // 資料庫update成功後,正確的的empVO物件,存入req
System.out.println("deleteOneClubTk4");		
				String url = "/back-end/actClubTicket.jsp";
System.out.println("deleteOneClubTk5");	
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/club/ClubUpdate.jsp");
				failureView.forward(req, res);
			}
		}


				
				
				// 尚未處理
				if ("not".equals(action)) {
System.out.println("deleteOneClubTk1");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				Integer clubTkID = Integer.parseInt(req.getParameter("clubTkID").trim());
				Integer clubTkStatID =1;
	
System.out.println("deleteOneClubTk2");		
				ClubTicketVO clubTkVO = new ClubTicketVO();	
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("clubTKVO", clubTkVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/club/ClubUpdate.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				
System.out.println("deleteOneClubTk3");	
				ClubTicketService clubTkSvc = new ClubTicketService();
				clubTkVO = clubTkSvc.changeOneClubTkStatID(clubTkID, clubTkStatID);
						
		
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("clubTkVO", clubTkVO); // 資料庫update成功後,正確的的empVO物件,存入req
System.out.println("deleteOneClubTk4");		
				String url = "/back-end/actClubTicket.jsp";
System.out.println("deleteOneClubTk5");	
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/club/ClubUpdate.jsp");
				failureView.forward(req, res);
			}
		}		
				
				
				
				// 正在處理
				if ("on".equals(action)) {
System.out.println("deleteOneClubTk1");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				Integer clubTkID = Integer.parseInt(req.getParameter("clubTkID").trim());
				Integer clubTkStatID =2;
	
System.out.println("deleteOneClubTk2");		
				ClubTicketVO clubTkVO = new ClubTicketVO();	
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("clubTKVO", clubTkVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/club/ClubUpdate.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				
System.out.println("deleteOneClubTk3");	
				ClubTicketService clubTkSvc = new ClubTicketService();
				clubTkVO = clubTkSvc.changeOneClubTkStatID(clubTkID, clubTkStatID);
						
		
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("clubTkVO", clubTkVO); // 資料庫update成功後,正確的的empVO物件,存入req
System.out.println("deleteOneClubTk4");		
				String url = "/back-end/actClubTicket.jsp";
System.out.println("deleteOneClubTk5");	
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/club/ClubUpdate.jsp");
				failureView.forward(req, res);
			}
		}				
				
				
				
				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
