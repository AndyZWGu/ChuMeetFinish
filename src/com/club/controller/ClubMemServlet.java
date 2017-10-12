package com.club.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.club.model.*;
import com.clubMB.model.ClubMBService;
import com.clubMB.model.ClubMBVO;
import com.clubMem.model.ClubMemService;
import com.clubMem.model.ClubMemVO;
import com.clubNews.model.ClubNewsService;
import com.clubNews.model.ClubNewsVO;





public class ClubMemServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doPost(req, res);  //雖然用超連結傳進來,轉交傳給doPost處理
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action為"+action);
		
		
		
//		if ("toClubAlbum".equals(action)){
//		String url = "/club/ClubAlbum.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
//		successView.forward(req, res);
		
		
				 //導向社團成員
		  if ("toClubMem".equals(action)){
				String clubID = req.getParameter("clubID");	//傳進來的是字串轉成數字
			
				/***************************2.開始查詢資料*****************************************/
				ClubService clubSvc = new ClubService();
				List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(Integer.parseInt(clubID));
				
				ClubVO clubVO = clubSvc.findByPrimaryKey(Integer.parseInt(clubID));
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("clubMemlist", clubMemlist); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/club/ClubAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
				successView.forward(req, res);
					return;
		 		}
		 
		 
		 
		 
			//會員加入社團跳轉到單一社團頁面
	      if ("joinClub".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			  	 
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer clubID = new Integer(req.getParameter("clubID").trim());
					Integer memID =1;
					
					
					//判斷
					ClubMemService clubMemSvc = new ClubMemService();
					ClubMemVO clubMemVO1 = clubMemSvc.getOneClubMem(clubID,memID);
//如果資料庫已有資料
							if(clubMemVO1!=null){
		System.out.println("if");
							ClubMemVO clubMemVO = new ClubMemVO();	
							clubMemVO.setClubMemStatus(1);	
							ClubService clubSvc = new ClubService();
							ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
							Integer clubMemType =1;
							Timestamp clubMemJoinDate = new Timestamp(System.currentTimeMillis());
							Integer clubMemStatus =1;
							clubMemVO= clubMemSvc.updateClubMem(clubID,memID,clubMemType,clubMemJoinDate,clubMemStatus);	

							/***************************2.開始修改資料*****************************************/
		
							clubMemVO = clubMemSvc.updateClubMem(clubID,memID,clubMemType, clubMemJoinDate,clubMemStatus);
				
									/***************************3.修改完成,準備轉交(Send the Success view)*************/
									List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);
									List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(clubID); 
									List<ClubMemVO> clubMemlist =clubSvc.getClubMemByClubID(clubID); 
									req.setAttribute("clubMBlist", clubMBlist); 
									req.setAttribute("clubNewslist", clubNewslist);
									req.setAttribute("clubMemlist", clubMemlist); 	
							
									req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
									req.setAttribute("clubMemVO", clubMemVO); // 資料庫取出的empVO物件,存入req
									String url = "/front-end/club/ClubOne.jsp";
				
									RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
									successView.forward(req, res);	

							}
							
							
					
							else{
		System.out.println("else");
							Integer clubMemType = 1;
							Timestamp clubMemJoinDate = new Timestamp(System.currentTimeMillis());
							Integer clubMemStatus = 1;
							ClubMemVO clubMemVO = new ClubMemVO();
							clubMemVO.setClubID(clubID);
							clubMemVO.setMemID(memID);
							clubMemVO.setClubMemType(clubMemType);
							clubMemVO.setClubMemJoinDate(clubMemJoinDate);	
							clubMemVO.setClubMemStatus(clubMemStatus);			
					    
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								req.setAttribute("clubMemVO", clubMemVO); // 含有輸入格式錯誤的empVO物件,也存入req
								RequestDispatcher failureView = req
										.getRequestDispatcher("/emp/addEmp.jsp");
								failureView.forward(req, res);
								return;
							}
				
					
					
					/***************************2.開始新增資料***************************************/
					ClubService clubSvc = new ClubService();
					ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
					ClubMemService clubMemSvc1 = new ClubMemService();
					clubMemVO= clubMemSvc1.addClubMem(clubID,memID,clubMemType,clubMemJoinDate,clubMemStatus);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);
					List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(clubID); 
					List<ClubMemVO> clubMemlist =clubSvc.getClubMemByClubID(clubID); 
					req.setAttribute("clubMBlist", clubMBlist); 
					req.setAttribute("clubNewslist", clubNewslist);
					req.setAttribute("clubMemlist", clubMemlist); 	
					
					
					
					req.setAttribute("clubMemVO", clubMemVO); // 資料庫取出的empVO物件,存入req
					req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
						
					String url = "/front-end/club/ClubOne.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
								}
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/club/addClub.jsp");
					failureView.forward(req, res);
					return;
				}
			}
	      
	      
	      
			//會員退出社團跳轉到單一社團頁面
	      if ("quitClub".equals(action)) { // 來自addEmp.jsp的請求  
	  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
		  	 
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer clubID = new Integer(req.getParameter("clubID").trim());
//會員
					Integer memID = new Integer(req.getParameter("memID").trim());
					System.out.println("這裡是退出社團接到的memID為"+memID);
					Integer clubMemType = 1;
					Timestamp clubMemJoinDate = new Timestamp(System.currentTimeMillis());
					Integer clubMemStatus = 0;			
					ClubMemVO clubMemVO = new ClubMemVO();
					clubMemVO.setClubID(clubID);
					clubMemVO.setMemID(memID);
					clubMemVO.setClubMemType(clubMemType);
					clubMemVO.setClubMemJoinDate(clubMemJoinDate);	
					clubMemVO.setClubMemStatus(clubMemStatus);	
				
			    
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("clubMemVO", clubMemVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
						failureView.forward(req, res);
						return;
					}
				
					/***************************2.開始新增資料***************************************/
			
					ClubService clubSvc = new ClubService();
					ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
	
					
					ClubMemService clubMemSvc = new ClubMemService();
System.out.println(clubID);	
System.out.println(memID);	
System.out.println(clubMemType);
System.out.println(clubMemJoinDate);	
System.out.println(clubMemStatus);	
					clubMemSvc.updateClubMem(clubID,memID,clubMemType,clubMemJoinDate,clubMemStatus);
					

					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);
					List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(clubID); 
					List<ClubMemVO> clubMemlist =clubSvc.getClubMemByClubID(clubID); 
					req.setAttribute("clubMBlist", clubMBlist); 
					req.setAttribute("clubNewslist", clubNewslist);
					req.setAttribute("clubMemlist", clubMemlist); 	
					

					req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
					req.setAttribute("clubMemVO", clubMemVO); // 資料庫取出的empVO物件,存入req					
					String url = "/front-end/club/ClubAll.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/club/addClub.jsp");
					failureView.forward(req, res);
					return;
				}
			}
	      
	      
	      
	      
	      
//			 //導向個人加入的社團列表
//			 if ("listAllJoinClub".equals(action)){
//				 	Integer clubID = Integer.parseInt(req.getParameter("memID"));	//傳進來的是字串轉成數字
//				 	Integer memID= new Integer(req.getParameter("memID").trim());
//					/***************************2.開始查詢資料*****************************************/
//					ClubService clubSvc = new ClubService();
//					ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
//					List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(clubID);
//					
			

	
	//這邊有會員ID
//					ClubMemService clubMemSvc = new ClubMemService();
//					
//					List<ClubMemVO> memAllJoinClublist = clubMemSvc.getAllJoinClub(memID);
//					
//					ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID,memID);
//					List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);
//					List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(clubID); 
//				
//					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//					req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
//					req.setAttribute("clubMemlist", clubMemlist); // 資料庫取出的empVO物件,存入req
//					req.setAttribute("clubMBlist", clubMBlist); 
//					req.setAttribute("clubNewslist", clubNewslist);
//					req.setAttribute("memAllJoinClublist", memAllJoinClublist);
//					req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
//					if(clubMemVO==null){
//						clubMemVO=clubMemSvc.getOneClubMem(0,0);// 社團成員資料庫建一個0,0的無值選項
//					};
//					if(clubMemVO!=null){
//					req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
//					};
//					
//					String url = "/club/MemAllClub.jsp";
//					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
//					successView.forward(req, res);
//					
//			 		}
					
					
					
					//上面重改寫成正確
			 //導向個人加入的社團列表
			 if ("listAllJoinClub".equals(action)){
				 	Integer memID= new Integer(req.getParameter("memID").trim());
					/***************************2.開始查詢資料*****************************************/
					ClubService clubSvc = new ClubService();		
					ClubMemService clubMemSvc = new ClubMemService();
					List<ClubVO> clublist = clubSvc.getAll();			
					
					List<ClubMemVO> memAllJoinClublist = clubMemSvc.getAllJoinClub(memID);				
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("clublist", clublist); // 資料庫取出的empVO物件,存入req
					req.setAttribute("memAllJoinClublist", memAllJoinClublist);	
					String url = "/front-end/club/MemAllClub.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
					successView.forward(req, res);
					return;
			 		}
	      
	     
	      


		 
		 
	}
}


		