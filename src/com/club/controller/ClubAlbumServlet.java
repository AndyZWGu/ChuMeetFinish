package com.club.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.club.model.*;
import com.clubAlbum.model.ClubAlbumService;
import com.clubAlbum.model.ClubAlbumVO;
import com.clubImg.model.ClubImgVO;
import com.clubMB.model.ClubMBService;
import com.clubMB.model.ClubMBVO;
import com.clubMem.model.ClubMemVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ClubAlbumServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
				//跳轉到觀看IMG頁面
				 if ("toClubImg".equals(action)){
				String clubID = req.getParameter("clubID");	//傳進來的是字串轉成數字
				String clubAlbumID = req.getParameter("clubAlbumID");
					
				/***************************2.開始查詢資料*****************************************/
				ClubService clubSvc = new ClubService();
				ClubVO clubVO = clubSvc.findByPrimaryKey(Integer.parseInt(clubID));
				ClubAlbumService clubAlbumSvc = new ClubAlbumService();
				List<ClubImgVO> clubImglist = clubAlbumSvc.getClubImgByClubAlbumID(Integer.parseInt(clubAlbumID));
				ClubAlbumVO clubAlbumVO = clubAlbumSvc.getOneClubAlbum(Integer.parseInt(clubAlbumID));
	
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("clubImglist", clubImglist); // 資料庫取出的empVO物件,存入req
				req.setAttribute("clubAlbumVO", clubAlbumVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/club/ClubImg.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
				successView.forward(req, res);
	
		 		}
		 
		 
				 //跳轉到增加相簿頁面
				 if ("toClubAlbumAdd".equals(action)){
				String clubID = req.getParameter("clubID");	//傳進來的是字串轉成數字
		
			
				/***************************2.開始查詢資料*****************************************/
				ClubService clubSvc = new ClubService();
				ClubVO clubVO = clubSvc.findByPrimaryKey(Integer.parseInt(clubID));

		
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/club/ClubAlbumAdd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
				successView.forward(req, res);
				
		 		}
		 
		 
		 
		 
		 
		 
		  if ("getAlbumAdd".equals(action)) { // 來自addEmp.jsp的請求  

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
	
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

					String clubAlbumName= req.getParameter("clubAlbumName").trim();					
				
					Integer memID = null;
					try {
						memID = 1;
					} catch (NumberFormatException e) {
						memID = 1;
						errorMsgs.add("ID請填數字.");
					}

					String clubID1=req.getParameter("clubID").trim();
					Integer clubID =  Integer.parseInt(clubID1);
	
		 			Timestamp clubAlbumDate = new Timestamp(System.currentTimeMillis()); 

					Integer clubAlbumStatus = 1;
					
					ClubAlbumVO clubAlbumVO = new ClubAlbumVO();
					clubAlbumVO.setClubID(clubID);				
					clubAlbumVO.setMemID(memID);
					clubAlbumVO.setClubAlbumName(clubAlbumName);
					clubAlbumVO.setClubAlbumDate(clubAlbumDate);
					clubAlbumVO.setClubAlbumStatus(clubAlbumStatus);
					System.out.println(clubAlbumDate);
	
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("clubAlbumVO", clubAlbumVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/club/ClubAlbum.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/

					ClubAlbumService clubAlbumSvc = new ClubAlbumService();
					clubAlbumVO = clubAlbumSvc.addClubAlbum(clubID,memID,clubAlbumDate,clubAlbumName,clubAlbumStatus);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/front-end/club/clubOne.do?action=toClubAlbum&clubID="+clubID;
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/club/addClub.jsp");
					failureView.forward(req, res);
				}
			}
			

	}
}
