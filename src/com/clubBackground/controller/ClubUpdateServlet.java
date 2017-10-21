package com.clubBackground.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.admin.controller.AdminMailService;
import com.club.model.*;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ClubUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		doPost(req, res); // 雖然用超連結傳進來,轉交傳給doPost處理
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
System.out.println("進來");
		if ("toUpdateClub".equals(action)) {
System.out.println("toUpdateClub");
			Integer clubID = Integer.parseInt(req.getParameter("clubID"));
System.out.println("toUpdateClub的clubID="+clubID);
			/*************************** 2.開始查詢資料 *****************************************/
			ClubService clubSvc = new ClubService();
			ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/club/clubUpdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交																		// listOneClub.jsp
			successView.forward(req, res);
		}

		// 拿到修改社團的form,導向社團單一頁面
		if ("oneForUpdate".equals(action)) { // 來自update_emp_input.jsp的請求
System.out.println("oneForUpdate");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				Integer clubID = Integer.parseInt(req.getParameter("clubID").trim());
				Integer clubmemID = Integer.parseInt(req.getParameter("clubmemID").trim());

				String clubName = req.getParameter("clubName").trim();

				String clubContent = req.getParameter("clubContent").trim();
				
System.out.println("1修改社團的");				
				byte[] clubPhoto=null;
				//有上傳新照片再去setClubPhoto(clubPhoto);
				
System.out.println("2修改社團的");					
				if(req.getPart("clubPic").getSize()!=0){
			     InputStream  in= req.getPart("clubPic").getInputStream();
			    	 	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				 	int length;
				 	clubPhoto = new byte[in.available()];
				 	while ((length = in.read(clubPhoto)) != -1) {
					 buffer.write(clubPhoto, 0, length);
				 	}
				 	clubPhoto=buffer.toByteArray();
				}
				
System.out.println("3修改社團的");		
				
				
				
				ClubVO clubVO = new ClubVO();
				
				Integer clubTypeID=clubVO.getClubTypeID();
				Integer clubStatus=clubVO.getClubStatus();
				Double clubLong=clubVO.getClubLong();
				Timestamp clubStartDate=clubVO.getClubStartDate();
				Double clubLat=clubVO.getClubLat();
System.out.println("修改社團的");			
				clubVO.setClubmemID(clubmemID);
				clubVO.setClubName(clubName);
				clubVO.setClubContent(clubContent);
				clubVO.setClubTypeID(clubTypeID);
				clubVO.setClubStatus(clubStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("clubVO", clubVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/club/ClubUpdate.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/

				ClubService clubSvc = new ClubService();
				clubVO = clubSvc.updateClub(clubID, clubmemID, clubName, clubTypeID, clubContent, clubPhoto, clubStartDate, clubStatus, clubLong, clubLat);
						
				System.out.println("修改社團的formclub:" + clubmemID);
				System.out.println("修改社團的formclubName:" + clubName);
				System.out.println("修改社團的formclubContent:" + clubContent);
		
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("clubVO", clubVO); // 資料庫update成功後,正確的的empVO物件,存入req
	
				String url = "/back-end/adminClub.jsp";
	
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/club/ClubUpdate.jsp");
				failureView.forward(req, res);
			}
		}

		
		
		
		
	
		if ("pause".equals(action)) { // 來自update_emp_input.jsp的請求
System.out.println("pause");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				Integer clubID = Integer.parseInt(req.getParameter("clubID").trim());
			

				ClubVO clubVO = new ClubVO();
				Integer clubStatus=0;
				clubVO.setClubStatus(clubStatus);
				//寄信
				AdminMailService mailService = new AdminMailService();
				mailService.sendMail("@gmail.com", "揪咪管理員ChuMeet通知,檢舉確認信",
						"<img src=\"https://i.imgur.com/IN3wmJe.png\"><h2>親愛的  印帥  您好:</h2><br><p>您的社團:鬼氏企業,已遭檢舉!</p><br><strong></strong><br><p>經查證,揪咪ChuMeet管理員已將該社團停權。</p><br><h5>ChuMeet服務團隊</h5><br><h4>如有任何問題歡迎來信ChuMeet客服信箱: chuMeetService@gmail.com</h4>");
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("clubVO", clubVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/club/ClubUpdate.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/

				ClubService clubSvc = new ClubService();
			
				clubVO = clubSvc.deleteClub(clubID, clubStatus);
						
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("clubVO", clubVO); // 資料庫update成功後,正確的的empVO物件,存入req
		
				String url = "/back-end/adminClub.jsp";
	
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/club/ClubUpdate.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		
		
		
		
		if ("ok".equals(action)) { // 來自update_emp_input.jsp的請求
System.out.println("ok");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				Integer clubID = Integer.parseInt(req.getParameter("clubID").trim());
				

				ClubVO clubVO = new ClubVO();
				Integer clubStatus=1;
				clubVO.setClubStatus(clubStatus);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("clubVO", clubVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/club/ClubUpdate.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/

				ClubService clubSvc = new ClubService();
				clubVO = clubSvc.deleteClub(clubID, clubStatus);
						
				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("clubVO", clubVO); // 資料庫update成功後,正確的的empVO物件,存入req
	
				String url = "/back-end/adminClub.jsp";
	
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
