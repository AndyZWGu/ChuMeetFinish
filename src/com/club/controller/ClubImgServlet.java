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
import com.clubImg.model.ClubImgService;
import com.clubImg.model.ClubImgVO;
import com.clubMB.model.ClubMBService;
import com.clubMB.model.ClubMBVO;
import com.clubNews.model.ClubNewsService;
import com.clubNews.model.ClubNewsVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ClubImgServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
			
		
		 //跳轉到增加相片頁面
		 if ("toClubImgAdd".equals(action)){
		String clubID = req.getParameter("clubID");	//傳進來的是字串轉成數字
		String clubAlbumID = req.getParameter("clubAlbumID");	//傳進來的是字串轉成數字
	
		/***************************2.開始查詢資料*****************************************/
		ClubService clubSvc = new ClubService();
		ClubVO clubVO = clubSvc.findByPrimaryKey(Integer.parseInt(clubID));
		 
		ClubAlbumService clubAlbumSvc = new ClubAlbumService();
		ClubAlbumVO clubAlbumVO = clubAlbumSvc.getOneClubAlbum(Integer.parseInt(clubAlbumID));
		/***************************3.查詢完成,準備轉交(Send the Success view)*************/
		req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
		req.setAttribute("clubAlbumVO", clubAlbumVO); // 資料庫取出的empVO物件,存入req
		String url = "/front-end/club/ClubImgAdd.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
		successView.forward(req, res);
		
		}
		
		
		//拿到新增照片跳轉回觀看照片頁面
		  if ("getImgAdd".equals(action)) { // 來自addEmp.jsp的請求  

				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
	
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					
					String clubAlbumID1=req.getParameter("clubAlbumID").trim();
					Integer clubAlbumID =  Integer.parseInt(clubAlbumID1);
			
					Integer memID = null;
					try {
						memID = 1;
					} catch (NumberFormatException e) {
						memID = 1;
						errorMsgs.add("ID請填數字.");
					}
					
							
		 			Timestamp clubImgDate = new Timestamp(System.currentTimeMillis()); 
				
					String clubImgContent= req.getParameter("clubImgContent").trim();	
			
					Integer clubImgStatus = 1;

					String clubID1=req.getParameter("clubID").trim();
					Integer clubID =  Integer.parseInt(clubID1);
		
					
					
					
				     InputStream  in= req.getPart("clubImg").getInputStream(); 

					 ByteArrayOutputStream buffer = new ByteArrayOutputStream();
					 
					 int length;
					 byte[] clubImg = new byte[in.available()];
					 
					 while ((length = in.read(clubImg)) != -1) {
					   buffer.write(clubImg, 0, length);
					 }
					 clubImg=buffer.toByteArray();
					
					

					ClubImgVO clubImgVO = new ClubImgVO();
					clubImgVO.setClubAlbumID(clubAlbumID);				
					clubImgVO.setMemID(memID);
					clubImgVO.setClubImgDate(clubImgDate);
					clubImgVO.setClubImg(clubImg);
					clubImgVO.setClubImgContent(clubImgContent);
					clubImgVO.setClubImgStatus(clubImgStatus);
		
	
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("clubImgVO", clubImgVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/club/ClubAlbum.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
	
					ClubImgService clubImgSvc = new ClubImgService();
					clubImgVO = clubImgSvc.addClubImg(clubAlbumID,memID,clubImgDate,clubImg,clubImgContent,clubImgStatus);
			
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/front-end/club/clubAlbum.do?action=toClubImg&clubAlbumID="+clubAlbumID+"&clubID="+clubID;
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
		  
		  
		  
		  
		  
		  
		//clubImg拿到變更社團IMG完跳回原頁面
		  if ("getOneImg_For_Update".equals(action)){
			  

				String clubID1 = req.getParameter("clubID");
				String clubImgID1 = req.getParameter("clubImgID");
				String clubAlbumID1 = req.getParameter("clubAlbumID");		
				String memID1 = req.getParameter("memID");
				Timestamp clubImgDate = new Timestamp(System.currentTimeMillis()); 
				String clubImgContent = req.getParameter("clubImgContent").trim();

			     InputStream  in= req.getPart("clubImg").getInputStream(); 
				 ByteArrayOutputStream buffer = new ByteArrayOutputStream();	 
				 int length;
				 byte[] clubImg = new byte[in.available()];	 
				 while ((length = in.read(clubImg)) != -1) {
				   buffer.write(clubImg, 0, length);
				 }
				 clubImg=buffer.toByteArray();
				 
				 
	

				Integer clubID = Integer.parseInt(clubID1);
				Integer memID = Integer.parseInt(memID1);
				Integer clubImgID = Integer.parseInt(clubImgID1);
				Integer clubAlbumID = Integer.parseInt(clubAlbumID1);
				Integer clubImgStatus = 1;	
			
				ClubImgVO clubImgVO = new ClubImgVO();
				clubImgVO.setClubImgID(clubImgID);	
				clubImgVO.setClubAlbumID(clubAlbumID);
				clubImgVO.setMemID(memID);
				clubImgVO.setClubImgDate(clubImgDate);
				clubImgVO.setClubImg(clubImg);
				clubImgVO.setClubImgContent(clubImgContent);				

				clubImgVO.setClubImgStatus(clubImgStatus);
System.out.println("B");			
		
		
				
				/***************************2.開始修改資料*****************************************/
System.out.println(clubImgID);
						
				ClubImgService clubImgSvc = new ClubImgService();	
				clubImgVO = clubImgSvc.updateClubImg(clubImgID,clubAlbumID,memID,clubImgDate,clubImg,clubImgContent,clubImgStatus);
		
				ClubService clubSvc = new ClubService();
				ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
				ClubAlbumService clubAlbumSvc = new ClubAlbumService();
				List<ClubImgVO> clubImglist = clubAlbumSvc.getClubImgByClubAlbumID(clubAlbumID);
				ClubAlbumVO clubAlbumVO = clubAlbumSvc.getOneClubAlbum(clubAlbumID);
	
						/***************************3.修改完成,準備轉交(Send the Success view)*************/
						req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
						req.setAttribute("clubImglist", clubImglist); // 資料庫取出的empVO物件,存入req
						req.setAttribute("clubAlbumVO", clubAlbumVO); // 資料庫取出的empVO物件,存入req
						String url = "/front-end/club/ClubImg.jsp";
	
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
						successView.forward(req, res);		
				 		}
		  
		  
		  
 
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		
		  
		  
		  
		  
			 //跳轉到變更相片頁面
			 if ("toClubImgUpdate".equals(action)){
			String clubID = req.getParameter("clubID");	//傳進來的是字串轉成數字
			String clubAlbumID = req.getParameter("clubAlbumID");	//傳進來的是字串轉成數字
			String clubImgID = req.getParameter("clubImgID");	//傳進來的是字串轉成數字
 			
			/***************************2.開始查詢資料*****************************************/
			ClubService clubSvc = new ClubService();
			ClubVO clubVO = clubSvc.findByPrimaryKey(Integer.parseInt(clubID));
			 
			ClubAlbumService clubAlbumSvc = new ClubAlbumService();
			ClubAlbumVO clubAlbumVO = clubAlbumSvc.getOneClubAlbum(Integer.parseInt(clubAlbumID));
			
			ClubImgService clubImgSvc = new ClubImgService();
			ClubImgVO clubImgVO = clubImgSvc.getOneClubImg(Integer.parseInt(clubImgID));

			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
			req.setAttribute("clubAlbumVO", clubAlbumVO); // 資料庫取出的empVO物件,存入req
			req.setAttribute("clubImgVO", clubImgVO); // 資料庫取出的empVO物件,存入req
			String url = "/front-end/club/ClubImgUpdate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
			successView.forward(req, res);
			
			}
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  
			//刪除照片跳轉回觀看照片頁面
		  if ("deleteOneImg".equals(action)) { // 來自addEmp.jsp的請求  
	
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);
	
				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String clubAlbumID1=req.getParameter("clubAlbumID").trim();
					Integer clubAlbumID =  Integer.parseInt(clubAlbumID1);
					
					String clubImgID1=req.getParameter("clubImgID").trim();
					Integer clubImgID =  Integer.parseInt(clubImgID1);
			
					String clubID1=req.getParameter("clubID").trim();
					Integer clubID =  Integer.parseInt(clubID1);
			
					Integer clubImgStatus =0;  
					
					ClubImgVO clubImgVO = new ClubImgVO();
					clubImgVO.setClubImgID(clubImgID);
					clubImgVO.setClubImgStatus(clubImgStatus);
		
	
					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("clubImgVO", clubImgVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/club/ClubAlbum.jsp");
						failureView.forward(req, res);
						return;
					}
					
			
					/***************************2.開始修改資料*****************************************/
					ClubService clubSvc = new ClubService();
					ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
				
					ClubImgService clubImgSvc = new ClubImgService();	
					clubImgVO = clubImgSvc.deleteOneImg(clubImgID,clubImgStatus);			
					/***************************3.修改完成,準備轉交(Send the Success view)*************/
					req.setAttribute("clubVO", clubVO); // 資料庫update成功後,正確的的empVO物件,存入req
					String url = "/front-end/club/clubAlbum.do?action=toClubImg&clubAlbumID="+clubAlbumID+"&clubID="+clubID;
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
