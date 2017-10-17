package com.club.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.club.model.*;
import com.clubAlbum.model.ClubAlbumVO;
import com.clubMB.model.ClubMBService;
import com.clubMB.model.ClubMBVO;
import com.clubMem.model.ClubMemService;
import com.clubMem.model.ClubMemVO;
import com.clubNews.model.ClubNewsVO;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ClubOneServlet extends HttpServlet {
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		doPost(req, res);  //雖然用超連結傳進來,轉交傳給doPost處理
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		
		 if ("toClubAlbum".equals(action)){
System.out.println("toClubAlbum");				 
		Integer clubID = Integer.parseInt(req.getParameter("clubID"));	
		Integer  memID = Integer.parseInt(req.getParameter("memID"));	
				/***************************2.開始查詢資料*****************************************/
				ClubService clubSvc = new ClubService();
				ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
System.out.println("memID:"+memID);		
				
				ClubMemService clubMemSvc = new ClubMemService();
				ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID,memID);
				List<ClubAlbumVO> clubAlbumlist = clubSvc.getClubAlbumsByClubID(clubID);
				List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);
				List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(clubID); 
				List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(clubID);
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
				req.setAttribute("clubMemlist", clubMemlist); // 資料庫取出的empVO物件,存入req
				req.setAttribute("clubMBlist", clubMBlist); 
				req.setAttribute("clubNewslist", clubNewslist);
				req.setAttribute("clubAlbumlist", clubAlbumlist); // 資料庫取出的empVO物件,存入req
System.out.println("clubID:"+clubID);	

				String url = "/front-end/club/ClubAlbum.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
				successView.forward(req, res);
System.out.println();	
   		}
		 
		 //導向社團成員
		 if ("toClubMem".equals(action)){
		String clubID = req.getParameter("clubID");	//傳進來的是字串轉成數字
		Integer  memID = Integer.parseInt(req.getParameter("memID"));	
				/***************************2.開始查詢資料*****************************************/
				ClubService clubSvc = new ClubService();
				List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(Integer.parseInt(clubID));
				
				ClubVO clubVO = clubSvc.findByPrimaryKey(Integer.parseInt(clubID));
				

				ClubMemService clubMemSvc = new ClubMemService();
				
				ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(Integer.parseInt(clubID),memID);
				List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(Integer.parseInt(clubID));
				List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(Integer.parseInt(clubID)); 
			
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("clubMemlist", clubMemlist); // 資料庫取出的empVO物件,存入req
				req.setAttribute("clubMBlist", clubMBlist); 
				req.setAttribute("clubNewslist", clubNewslist);
				req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
				if(clubMemVO==null){
					clubMemVO=clubMemSvc.getOneClubMem(0,0);// 社團成員資料庫建一個0,0的無值選項
				};
				if(clubMemVO!=null){
				req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
				};
				
				String url = "/front-end/club/ClubAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
				successView.forward(req, res);
				
		 		}
		 
		 
		 //導回社團首頁
		 if ("toClubOne".equals(action)){

				String clubID = req.getParameter("clubID");	//傳進來的是字串轉成數字
				Integer  memID = Integer.parseInt(req.getParameter("memID"));
				/***************************2.開始查詢資料*****************************************/
				ClubService clubSvc = new ClubService();
				ClubVO clubVO = clubSvc.findByPrimaryKey(Integer.parseInt(clubID));
System.out.println("導回首業拿到的clubID"+clubID);				
				//失敗				
				ClubMBService clubMBSvc = new ClubMBService();
				ClubMBVO clubMBVO = clubMBSvc.getOneClubMB(Integer.parseInt(clubID));
				List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(Integer.parseInt(clubID)); 
				 List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(Integer.parseInt(clubID));
				 
				 
				//這邊有會員ID
					ClubMemService clubMemSvc = new ClubMemService();
			
					ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(Integer.parseInt(clubID),memID);
					
					List<ClubMemVO> clubMemlist =clubSvc.getClubMemByClubID(Integer.parseInt(clubID));
					
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("clubVO", clubVO); 
				req.setAttribute("clubMBlist", clubMBlist); 
				req.setAttribute("clubNewslist", clubNewslist); 
				req.setAttribute("clubMemlist", clubMemlist);
				
				req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
				
				HttpSession session = req.getSession();
				session.setAttribute("clubMemlist", clubMemlist);
				session.setAttribute("clubMemVO", clubMemVO);
				//單一社團成員
				if(clubMemVO==null){
					clubMemVO=clubMemSvc.getOneClubMem(0,0);// 社團成員資料庫建一個0,0的無值選項
				};
				if(clubMemVO!=null){
				req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
				};
				
				
				String url = "/front-end/club/ClubOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
				successView.forward(req, res);
				
				
				
				
					
		 		}
		 


 
		 if ("toClubNews".equals(action)){
		String clubID = req.getParameter("clubID");	//傳進來的是字串轉成數字
		Integer  memID = Integer.parseInt(req.getParameter("memID"));
			
				/***************************2.開始查詢資料*****************************************/
				ClubService clubSvc = new ClubService();
				ClubVO clubVO = clubSvc.findByPrimaryKey(Integer.parseInt(clubID));
				List<ClubNewsVO> clubNewslist = clubSvc.getClubNewsByClubID(Integer.parseInt(clubID));
				ClubMemService clubMemSvc = new ClubMemService();
				ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(Integer.parseInt(clubID),memID);
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
				req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
				req.setAttribute("clubNewslist", clubNewslist); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/club/ClubNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
				successView.forward(req, res);
	
		 		}
		 
		 
			//新增留言跳轉到單一社團頁面
	      if ("addOneClubMB".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					Integer  clubID = Integer.parseInt(req.getParameter("clubID"));
					Integer  memID = Integer.parseInt(req.getParameter("memID"));	//傳進來的是字串轉成數字
					String clubMBContent = req.getParameter("oneClubMB").trim();
					Timestamp clubMBDate = new Timestamp(System.currentTimeMillis());
					Integer clubMBStatus = 1;
					
					ClubMBVO clubMBVO = new ClubMBVO();
					clubMBVO.setClubID(clubID);
					clubMBVO.setMemID(memID);
					clubMBVO.setClubMBContent(clubMBContent);
					clubMBVO.setClubMBDate(clubMBDate);
					clubMBVO.setClubMBStatus(clubMBStatus);
									

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("clubMBVO", clubMBVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/emp/addEmp.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					ClubService clubSvc = new ClubService();
					List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(clubID);
					
					ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
					

					ClubMemService clubMemSvc = new ClubMemService();			
					ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID,memID);
					
					ClubMBService clubMBSvc = new ClubMBService();
					clubMBVO = clubMBSvc.addClubMB(clubID,memID,clubMBContent,clubMBDate, clubMBStatus);
					
					
					List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);
					List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(clubID); 
				
			
					

					
					//留言							
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
					
					req.setAttribute("clubMBlist", clubMBlist); // 資料庫取出的empVO物件,存入req
	
					req.setAttribute("clubNewslist", clubNewslist);
					
					req.setAttribute("clubMemlist", clubMemlist); 
					
					if(clubMemVO==null){
						clubMemVO=clubMemSvc.getOneClubMem(0,0);// 社團成員資料庫建一個0,0的無值選項
					};
					if(clubMemVO!=null){
					req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
					};				
			
					
					String url = "/front-end/club/ClubOne.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
					
					successView.forward(req, res);






					//以下是給加入退出社團的mem用的
//					ClubMemService clubMemSvc = new ClubMemService();

//					ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID,memID);
					if(clubMemVO==null){
						clubMemVO=clubMemSvc.getOneClubMem(0,0);// 社團成員資料庫建一個0,0的無值選項
					};
					if(clubMemVO!=null){
					req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
					};
					
					/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					errorMsgs.add(e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/club/addClub.jsp");
					failureView.forward(req, res);
				}
			}
		 
		 
		 

		 
		 
			
		 
		 
		 
		 
		 
			//clubID跳轉變更社團頁面
		 if ("updateClub".equals(action)){
		Integer  clubID = Integer.parseInt(req.getParameter("clubID"));
	
		Integer  memID = Integer.parseInt(req.getParameter("memID"));	
System.out.println("跳轉變更社團頁面clubID:"+clubID);	
System.out.println("跳轉變更社團頁面memID:"+memID);	
				/***************************2.開始查詢資料*****************************************/
				//社團資料
				ClubService clubSvc = new ClubService();
				ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);

				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/club/ClubUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
				successView.forward(req, res);		
		 		}
		 


		 
		 
		 //拿到修改社團的form,導向社團單一頁面
			if ("getOne_For_Update".equals(action)) { // 來自update_emp_input.jsp的請求
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
			
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/

					Integer clubID = new Integer(req.getParameter("clubID").trim());
					Integer  memID = Integer.parseInt(req.getParameter("memID"));
					
					String clubName = req.getParameter("clubName").trim();

					String clubContent = req.getParameter("clubContent").trim();
			

					Integer clubTypeID = null;
					try {
						clubTypeID = new Integer(req.getParameter("clubTypeID").trim());
					} catch (NumberFormatException e) {
						clubTypeID = 1;
						errorMsgs.add("類型請填數字.");
					}
						

					
					byte[] clubPhoto=null;
					//有上傳新照片再去setClubPhoto(clubPhoto);
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
						ClubVO clubVO = new ClubVO();
						clubVO.setClubID(clubID);	
						clubVO.setClubName(clubName);
						clubVO.setClubTypeID(clubTypeID);
						clubVO.setClubContent(clubContent);
						clubVO.setClubPhoto(clubPhoto);
					

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("clubVO", clubVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/club/ClubUpdate.jsp");
						failureView.forward(req, res);
						return; //程式中斷
					}
					
					/***************************2.開始修改資料*****************************************/

					ClubService clubSvc = new ClubService();

System.out.println("修改社團的formclub:"+clubID);
System.out.println("修改社團的formclubName:"+clubName);
System.out.println("修改社團的formclubTypeID:"+clubTypeID);
System.out.println("修改社團的formclubContent:"+clubContent);

					if(req.getPart("clubPic").getSize()!=0){
						clubVO = clubSvc.ClubChange(clubID,clubName,clubTypeID,clubContent,clubPhoto);
					} else {
						clubVO = clubSvc.ClubChangeIfNotPhoto(clubID,clubName,clubTypeID,clubContent);
					}
	
					
					ClubMBService clubMBSvc = new ClubMBService();
					ClubMBVO clubMBVO = clubMBSvc.getOneClubMB(clubID);
					List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(clubID); 
					 List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);
					 List<ClubMemVO> clubMemlist =clubSvc.getClubMemByClubID(clubID); 
					 
					 
//這邊有會員ID
						ClubMemService clubMemSvc = new ClubMemService();
					
						ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID,memID);
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
 
					req.setAttribute("clubMBlist", clubMBlist); 
					req.setAttribute("clubNewslist", clubNewslist); 
					req.setAttribute("clubMemlist", clubMemlist); 
					req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
					
					req.setAttribute("clubVO", clubVO); // 資料庫update成功後,正確的的empVO物件,存入req
			
					String url = "/front-end/club/ClubOne.jsp";

					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);

					/***************************其他可能的錯誤處理*************************************/
				} catch (Exception e) {
					errorMsgs.add("修改資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/club/ClubUpdate.jsp");
					failureView.forward(req, res);
				}
			}
			
			

	
	}
}
	

	
		
		
