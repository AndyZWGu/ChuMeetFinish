package com.club.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.club.model.*;
import com.clubMB.model.ClubMBService;
import com.clubMB.model.ClubMBVO;
import com.clubMem.model.ClubMemService;
import com.clubMem.model.ClubMemVO;
import com.clubNews.model.ClubNewsVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ClubAllServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		doPost(req, res);  //雖然用超連結傳進來,轉交傳給doPost處理
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

   
				//跳轉到單一社團頁面
				 if ("toClubOne".equals(action)){
				String clubID = req.getParameter("clubID");	//傳進來的是字串轉成數字
			
				/***************************2.開始查詢資料*****************************************/
				//社團資料
				ClubService clubSvc = new ClubService();
				ClubVO clubVO = clubSvc.findByPrimaryKey(Integer.parseInt(clubID));

				//留言	
				List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(Integer.parseInt(clubID));
				List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(Integer.parseInt(clubID)); 
				List<ClubMemVO> clubMemlist =clubSvc.getClubMemByClubID(Integer.parseInt(clubID)); 
				
//這邊有會員ID
				ClubMemService clubMemSvc = new ClubMemService();
				String memID="1";
				ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(Integer.parseInt(clubID),Integer.parseInt(memID));

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/

				req.setAttribute("clubVO", clubVO); 		
				req.setAttribute("clubMBlist", clubMBlist); 
				req.setAttribute("clubNewslist", clubNewslist);
				req.setAttribute("clubMemlist", clubMemlist); 	
				if(clubMemVO==null){
					clubMemVO=clubMemSvc.getOneClubMem(0,0);// 社團成員資料庫建一個0,0的無值選項
				};
				if(clubMemVO!=null){
				req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
				};

System.out.println(clubID);
				String url = "/front-end/club/ClubOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
				successView.forward(req, res);		
		 		}

		 
//				 //前往新增社團還沒寫(還有會員參數部分)
//				 if ("addClub".equals(action)){
//					 //String memID = req.getParameter("memID");
//					 String clubID = req.getParameter("clubID");//傳進來的是字串轉成數字
//					 
//					 
//						/***************************2.開始查詢資料*****************************************/
//						//社團資料
//						ClubService clubSvc = new ClubService();
//						ClubVO clubVO = clubSvc.findByPrimaryKey(Integer.parseInt(clubID));
//		
//						/***************************3.查詢完成,準備轉交(Send the Success view)*************/
//						req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req			
//								 
//						String url = "/club/ClubUpdate.jsp";
//						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
//						successView.forward(req, res);
//						
//				 	}
		 
//未自增主鍵
//				//新增社團資料輸入完傳回	
//				 if ("getAdd".equals(action)) { // 來自addEmp.jsp的請求  
//						
//						List<String> errorMsgs = new LinkedList<String>();
//						// Store this set in the request scope, in case we need to
//						// send the ErrorPage view.
//						req.setAttribute("errorMsgs", errorMsgs);
//
//						try {
//							/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//
//							String clubName = req.getParameter("clubName").trim();
//							
//							String clubContent = req.getParameter("clubContent").trim();
//													
//							Integer clubmemID = null;
//							try {
//								clubmemID = 2;
//							} catch (NumberFormatException e) {
//								clubmemID = 1;
//								errorMsgs.add("ID請填數字.");
//							}
//				
//							Integer clubTypeID = null;
//							clubTypeID = new Integer(req.getParameter("clubTypeID").trim());
//
//
//							Timestamp clubStartDate = new Timestamp(System.currentTimeMillis()); 
//
//							Integer clubStatus = 1;
//
//							Double clubLong =5.0;
//
//							Double clubLat = 4.0;
//							
//
//							     InputStream  in= req.getPart("clubPic").getInputStream(); 
//
//								 ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//								 
//								 int length;
//								 byte[] clubPhoto = new byte[in.available()];
//								 
//								 while ((length = in.read(clubPhoto)) != -1) {
//								   buffer.write(clubPhoto, 0, length);
//								 }
//								 clubPhoto=buffer.toByteArray();
//
//	
////新增社團部分
//							ClubVO clubVO = new ClubVO();
//							clubVO.setClubmemID(clubmemID);				
//							clubVO.setClubName(clubName);
//							clubVO.setClubTypeID(clubTypeID);
//							clubVO.setClubContent(clubContent);
//							clubVO.setClubPhoto(clubPhoto);
//							clubVO.setClubStartDate(clubStartDate);
//							clubVO.setClubStatus(clubStatus);
//							clubVO.setClubLong(clubLong);
//							clubVO.setClubLat(clubLat);
//							
//												
//							
//
//							// Send the use back to the form, if there were errors
//							if (!errorMsgs.isEmpty()) {
//								req.setAttribute("clubVO", clubVO); // 含有輸入格式錯誤的empVO物件,也存入req
//								RequestDispatcher failureView = req
//										.getRequestDispatcher("/club/addClub.jsp");
//								failureView.forward(req, res);
//								return;
//							}
//							
//							/***************************2.開始新增資料***************************************/
//							//新增社團部分
//							ClubService clubSvc = new ClubService();
//
//							clubVO = clubSvc.addClub(clubmemID, clubName, clubTypeID, clubContent, clubPhoto ,clubStartDate, clubStatus, clubLong, clubLat);
//
//							//新增社長部分
//
////							ClubMemService clubMemSvc = new ClubMemService();
////							clubMemVO= clubMemSvc.addClubMem(clubID,memID,clubMemType,clubMemJoinDate,clubMemStatus);
//							
//							/***************************3.新增完成,準備轉交(Send the Success view)***********/
//							String url = "/club/ClubAll.jsp";
//							RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//							successView.forward(req, res);				
//							
//							/***************************其他可能的錯誤處理**********************************/
//						} catch (Exception e) {
//							errorMsgs.add(e.getMessage());
//							RequestDispatcher failureView = req
//									.getRequestDispatcher("/club/addClub.jsp");
//							failureView.forward(req, res);
//						}
//					}
				 
				 
				 
				 
				 
				 
//		
//					//新增社團自增主鍵拿到資料後傳給建立社員為社長	
				 if ("getAdd".equals(action)) { // 來自addEmp.jsp的請求  
						
						List<String> errorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("errorMsgs", errorMsgs);

						try {
							/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

							String clubName = req.getParameter("clubName").trim();
							
							String clubContent = req.getParameter("clubContent").trim();
													
							Integer clubmemID = null;
							try {
								clubmemID = 1;
							} catch (NumberFormatException e) {
								clubmemID = 1;
								errorMsgs.add("ID請填數字.");
							}
				
							Integer clubTypeID = null;
							clubTypeID = new Integer(req.getParameter("clubTypeID").trim());


							Timestamp clubStartDate = new Timestamp(System.currentTimeMillis()); 

							Integer clubStatus = 1;

							Double clubLong =5.0;

							Double clubLat = 4.0;
					

							     InputStream  in= req.getPart("clubPic").getInputStream(); 

								 ByteArrayOutputStream buffer = new ByteArrayOutputStream();
								 
								 int length;
								 byte[] clubPhoto = new byte[in.available()];
								 
								 while ((length = in.read(clubPhoto)) != -1) {
								   buffer.write(clubPhoto, 0, length);
								 }
								 clubPhoto=buffer.toByteArray();

	
//新增社團部分
							ClubVO clubVO = new ClubVO();
							clubVO.setClubmemID(clubmemID);				
							clubVO.setClubName(clubName);
							clubVO.setClubTypeID(clubTypeID);
							clubVO.setClubContent(clubContent);
							clubVO.setClubPhoto(clubPhoto);
							clubVO.setClubStartDate(clubStartDate);
							clubVO.setClubStatus(clubStatus);
							clubVO.setClubLong(clubLong);
							clubVO.setClubLat(clubLat);

							
						////新增為社團成員部分							
//						Integer clubID = new Integer(req.getParameter("clubID").trim());
						Integer memID =1;
						Integer clubMemType = 3;
						Timestamp clubMemJoinDate = new Timestamp(System.currentTimeMillis());
						Integer clubMemStatus = 1;
						ClubMemVO clubMemVO = new ClubMemVO();
//						clubMemVO.setClubID(clubID);
						clubMemVO.setMemID(memID);
						clubMemVO.setClubMemType(clubMemType);
						clubMemVO.setClubMemJoinDate(clubMemJoinDate);	
						clubMemVO.setClubMemStatus(clubMemStatus);		
							
							
							
							// Send the use back to the form, if there were errors
							if (!errorMsgs.isEmpty()) {
								req.setAttribute("clubVO", clubVO); // 含有輸入格式錯誤的empVO物件,也存入req
								RequestDispatcher failureView = req
										.getRequestDispatcher("/club/addClub.jsp");
								failureView.forward(req, res);
								return;
							}
							
							/***************************2.開始新增資料***************************************/
							//新增社團部分
							ClubService clubSvc = new ClubService();
							clubVO = clubSvc.addClub(clubmemID, clubName, clubTypeID, clubContent, clubPhoto ,clubStartDate, clubStatus, clubLong, clubLat);
						
							//新增社長部分
							ClubMemService clubMemSvc = new ClubMemService();
							clubMemVO= clubMemSvc.addClubMem(clubVO.getClubID(),memID,clubMemType,clubMemJoinDate,clubMemStatus);						
							/***************************3.新增完成,準備轉交(Send the Success view)***********/
							List<ClubVO> list =clubSvc.getAll();
							req.setAttribute("clublist", list); 
							String url = "/front-end/club/ClubAll.jsp";
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
	


	
		



