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
				Integer clubID = Integer.parseInt(req.getParameter("clubID").trim());
				
				Integer memID = Integer.parseInt(req.getParameter("memID").trim());
				/***************************2.開始查詢資料*****************************************/
				ClubService clubSvc = new ClubService();
				List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(clubID);
				
				ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
				
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
					Integer clubID = Integer.parseInt(req.getParameter("clubID").trim());
					
					Integer memID = Integer.parseInt(req.getParameter("memID").trim());
					
					//判斷
					ClubMemService clubMemSvc = new ClubMemService();
					ClubMemVO clubMemVO1 = clubMemSvc.getOneClubMem(clubID,memID);
//如果資料庫已有資料
							if(clubMemVO1!=null){
		System.out.println("會員加入社團if");
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
		System.out.println("會員加入社團else");
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
System.out.println("會員退出社團跳轉到單一社團頁面");	  
				
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
System.out.println("退出社團接到的clubID:"+clubID);	
System.out.println("退出社團接到的memID:"+memID);	
System.out.println("退出社團接到的clubMemType:"+clubMemType);
System.out.println("退出社團接到的clubMemJoinDate:"+clubMemJoinDate);	
System.out.println("退出社團接到的clubMemStatus:"+clubMemStatus);	
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
	      
			 
			 
			 
			 
			 
			 
			 
				// 變更社團成員為一般成員或幹部
				if ("UpdateClubMemStatusToLeader".equals(action) 
						|| "UpdateClubMemStatusToMember".equals(action)) { // 來自addEmp.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/***********************
						 * 1.接收請求參數 - 輸入格式的錯誤處理
						 *************************/
						Integer clubID = Integer.parseInt(req.getParameter("clubID")); // 傳進來的是字串轉成數字
		System.out.println(clubID);
						// 社團成員被選取的要變更的ID
						Integer clubMemID = Integer.parseInt(req.getParameter("clubMemID")); // 傳進來的是字串轉成數字
		System.out.println(clubMemID);
						// 會員ID
						Integer memID = Integer.parseInt(req.getParameter("memID")); // 傳進來的是字串轉成數字
		System.out.println(memID);

						Integer clubMemType = 0;
						if ("UpdateClubMemStatusToLeader".equals(action)) {
							clubMemType = 2;
							System.out.println("UpdateClubMemStatusToLeader");
						}

						if ("UpdateClubMemStatusToBuilder".equals(action)) {
							clubMemType = 3;
							System.out.println("UpdateClubMemStatusToBuilder");
						}

						if ("UpdateClubMemStatusToMember".equals(action)) {
							clubMemType = 1;
							System.out.println("UpdateClubMemStatusToMember");
						}

						ClubMemVO clubMemUpdateTypeVO = new ClubMemVO();
						clubMemUpdateTypeVO.setClubMemType(clubMemType);
						clubMemUpdateTypeVO.setClubID(clubID);
						clubMemUpdateTypeVO.setMemID(clubMemID);
						
						
		System.out.println(clubMemType);	
		System.out.println(clubID);
		System.out.println(clubMemID);				
						
						ClubMemService clubMemSvc = new ClubMemService();
						clubMemUpdateTypeVO = clubMemSvc.updateClubMemType(clubID, clubMemID,clubMemType);
		System.out.println("A");				
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("clubMemUpdateTypeVO", clubMemUpdateTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
							failureView.forward(req, res);
							return;
						}

						/*************************** 2.開始新增資料 ***************************************/

						ClubService clubSvc = new ClubService();
						ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);

						ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID, memID);
		System.out.println("B");
						/***************************
						 * 3.新增完成,準備轉交(Send the Success view)
						 ***********/
						List<ClubMBVO> clubMBlist = clubSvc.getClubMBByClubID(clubID);
						List<ClubNewsVO> clubNewslist = clubSvc.getClubNewsByClubID(clubID);
						List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(clubID);
						req.setAttribute("clubMBlist", clubMBlist);
						req.setAttribute("clubNewslist", clubNewslist);
						req.setAttribute("clubMemlist", clubMemlist);
						req.setAttribute("clubMemVO", clubMemVO);
						req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
						req.setAttribute("clubMemUpdateTypeVO", clubMemUpdateTypeVO); // 資料庫取出的empVO物件,存入req
						String url = "/front-end/club/ClubAllMem.jsp";
		System.out.println("C");
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
		System.out.println("D");
						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/club/addClub.jsp");
						failureView.forward(req, res);
						return;
					}
				}
				
				



				// 變更社團成員提升為社長
				if ("UpdateClubMemStatusToBuilder".equals(action)) { // 來自addEmp.jsp的請求
		System.out.println("1UpdateClubMemStatusToLeader");
					List<String> errorMsgs = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						/***********************
						 * 1.接收請求參數 - 輸入格式的錯誤處理
						 *************************/
						Integer clubID = Integer.parseInt(req.getParameter("clubID")); // 傳進來的是字串轉成數字
		System.out.println("被變更的clubID"+clubID);
						// 社團成員被選取的要變更的ID
						Integer clubMemID = Integer.parseInt(req.getParameter("clubMemID")); // 傳進來的是字串轉成數字
		System.out.println("被變更的clubMemID"+clubMemID);
						// 會員ID
						Integer memID = Integer.parseInt(req.getParameter("memID")); // 傳進來的是字串轉成數字
		System.out.println("被變更的memID"+memID);
						Integer clubMemType = 3;
		System.out.println("被變更的clubMemType"+clubMemType);

						ClubMemVO clubMemUpdateTypeVO = new ClubMemVO();
						clubMemUpdateTypeVO.setClubMemType(clubMemType);
						clubMemUpdateTypeVO.setClubID(clubID);
						clubMemUpdateTypeVO.setMemID(clubMemID);			
						
						ClubMemService clubMemSvc = new ClubMemService();
						clubMemUpdateTypeVO = clubMemSvc.updateClubMemType(clubID, clubMemID,clubMemType);
		System.out.println("變更將要變更得人變成社長");	
		
		
		
		//變更clubVO.clubmemID	
		
		ClubVO clubChangeClubMemVO = new ClubVO();
		clubChangeClubMemVO.setClubID(clubID);	
		clubChangeClubMemVO.setClubmemID(clubMemID);	
		ClubService clubSvc = new ClubService();
		clubChangeClubMemVO = clubSvc.clubChangeClubMem(clubID, clubMemID);
		
		
			
						
//			把原本社長變回幹部	
						clubMemType = 2;
		System.out.println(clubMemType);	
		System.out.println(clubID);	
		System.out.println(memID);	
						ClubMemVO clubMemUpdateOldBuilderTypeVO = new ClubMemVO();
						clubMemUpdateOldBuilderTypeVO.setClubMemType(clubMemType);
						clubMemUpdateOldBuilderTypeVO.setClubID(clubID);
						clubMemUpdateOldBuilderTypeVO.setMemID(memID);			

						clubMemUpdateOldBuilderTypeVO = clubMemSvc.updateClubMemType(clubID, memID,clubMemType);
		System.out.println("將原本社長變回幹部");					
						// Send the use back to the form, if there were errors
						if (!errorMsgs.isEmpty()) {
							req.setAttribute("clubMemUpdateTypeVO", clubMemUpdateTypeVO); // 含有輸入格式錯誤的empVO物件,也存入req
							RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
							failureView.forward(req, res);
							return;
						}
						



						/*************************** 2.開始新增資料 ***************************************/

						
						ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
						ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID, memID);

						
					
						
						
						
						

						/***************************
						 * 3.新增完成,準備轉交(Send the Success view)
						 ***********/
						List<ClubMBVO> clubMBlist = clubSvc.getClubMBByClubID(clubID);
						List<ClubNewsVO> clubNewslist = clubSvc.getClubNewsByClubID(clubID);
						List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(clubID);
						req.setAttribute("clubMBlist", clubMBlist);
						req.setAttribute("clubNewslist", clubNewslist);
						req.setAttribute("clubMemlist", clubMemlist);
						req.setAttribute("clubMemVO", clubMemVO);
						req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
						req.setAttribute("clubMemUpdateTypeVO", clubMemUpdateTypeVO); // 資料庫取出的empVO物件,存入req
						String url = "/front-end/club/ClubAllMem.jsp";

						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp

						successView.forward(req, res);

						/*************************** 其他可能的錯誤處理 **********************************/
					} catch (Exception e) {
						errorMsgs.add(e.getMessage());
						RequestDispatcher failureView = req.getRequestDispatcher("/club/addClub.jsp");
						failureView.forward(req, res);
						return;
					}
				}
			 
			 
					 
			 
			 
			 
	     
				//會員被剃除跳轉到社員頁面
			      if ("DeleteClubMember".equals(action)) { // 來自addEmp.jsp的請求  
		System.out.println("會員被剃除跳轉到社員頁面");	  
						
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);
				  	 
						try {
							/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
							Integer clubID = new Integer(req.getParameter("clubID").trim());

							Integer clubMemID = new Integer(req.getParameter("clubMemID").trim());

							Integer memID = new Integer(req.getParameter("memID").trim());
							
							Integer clubMemStatus = 0;			
							ClubMemVO clubDeleteMemVO = new ClubMemVO();
							clubDeleteMemVO.setClubID(clubID);
							clubDeleteMemVO.setMemID(clubMemID);
							clubDeleteMemVO.setClubMemStatus(clubMemStatus);	
						
					    
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								req.setAttribute("clubDeleteMemVO", clubDeleteMemVO); // 含有輸入格式錯誤的empVO物件,也存入req
								RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
								failureView.forward(req, res);
								return;
							}
						
							/***************************2.開始新增資料***************************************/
					
							ClubService clubSvc = new ClubService();
							ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
			
							
							ClubMemService clubMemSvc = new ClubMemService();
		System.out.println("退出社團接到的clubID:"+clubID);	
		System.out.println("退出社團接到的clubMemID:"+clubMemID);	
		
		System.out.println("退出社團接到的clubMemStatus:"+clubMemStatus);	
							clubMemSvc.changeMemStatus(clubID, clubMemID,clubMemStatus);
		System.out.println("退出社團成功");	
						
							ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID, memID);
							/***************************3.新增完成,準備轉交(Send the Success view)***********/
							List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);
							List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(clubID); 
							List<ClubMemVO> clubMemlist =clubSvc.getClubMemByClubID(clubID); 
							req.setAttribute("clubMBlist", clubMBlist); 
							req.setAttribute("clubNewslist", clubNewslist);
							req.setAttribute("clubMemlist", clubMemlist); 	
			System.out.println("準備轉交");		

							req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
							req.setAttribute("clubMemVO", clubMemVO); // 資料庫取出的empVO物件,存入req					
							String url = "/front-end/club/ClubAllMem.jsp";
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
			      
			      
			      
			      
			      
			      
			      if ("ExitClubMember".equals(action)) { // 來自addEmp.jsp的請求  
		System.out.println("會員被剃除跳轉到社員頁面");	  
						
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);
				  	 
						try {
							/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
							Integer clubID = new Integer(req.getParameter("clubID").trim());

							Integer clubMemID = new Integer(req.getParameter("clubMemID").trim());

							Integer memID = new Integer(req.getParameter("memID").trim());
							
							Integer clubMemStatus = 0;			
							ClubMemVO clubDeleteMemVO = new ClubMemVO();
							clubDeleteMemVO.setClubID(clubID);
							clubDeleteMemVO.setMemID(clubMemID);
							clubDeleteMemVO.setClubMemStatus(clubMemStatus);	
						
					    
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								req.setAttribute("clubDeleteMemVO", clubDeleteMemVO); // 含有輸入格式錯誤的empVO物件,也存入req
								RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
								failureView.forward(req, res);
								return;
							}
						
							/***************************2.開始新增資料***************************************/
					
							ClubService clubSvc = new ClubService();
							ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
			
							
							ClubMemService clubMemSvc = new ClubMemService();
		System.out.println("退出社團接到的clubID:"+clubID);	
		System.out.println("退出社團接到的clubMemID:"+clubMemID);	
		
		System.out.println("退出社團接到的clubMemStatus:"+clubMemStatus);	
							clubMemSvc.changeMemStatus(clubID, clubMemID,clubMemStatus);
		System.out.println("退出社團成功");	
						
							ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID, memID);
							/***************************3.新增完成,準備轉交(Send the Success view)***********/
							List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);
							List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(clubID); 
							List<ClubMemVO> clubMemlist =clubSvc.getClubMemByClubID(clubID); 
							req.setAttribute("clubMBlist", clubMBlist); 
							req.setAttribute("clubNewslist", clubNewslist);
							req.setAttribute("clubMemlist", clubMemlist); 	
			System.out.println("準備轉交");		

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
			      
			      
			      
			      


		 
		 
	}
}


		