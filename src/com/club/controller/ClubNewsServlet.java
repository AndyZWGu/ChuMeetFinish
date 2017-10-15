package com.club.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.club.model.ClubService;
import com.club.model.ClubVO;
import com.clubAlbum.model.ClubAlbumVO;
import com.clubMB.model.ClubMBService;
import com.clubMB.model.ClubMBVO;
import com.clubMem.model.ClubMemService;
import com.clubMem.model.ClubMemVO;
import com.clubNews.model.ClubNewsService;
import com.clubNews.model.ClubNewsVO;

public class ClubNewsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println("AA");	
     	
//新增一個公告
	      if ("addClubNews".equals(action)) { // 來自addEmp.jsp的請求  
				
				List<String> errorMsgs = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			
					Integer clubID = new Integer(req.getParameter("clubID").trim());
					Integer  memID = Integer.parseInt(req.getParameter("memID"));
					String clubNewsTitle = req.getParameter("clubNewsTitle").trim();
					String clubNewsContent = req.getParameter("clubNewsContent").trim();
					Timestamp clubNewsDate = new Timestamp(System.currentTimeMillis());
					Integer clubNewsStatus = 1;
					//暫時先不管					
					Integer actID = 1;
					
					ClubNewsVO clubNewsVO = new ClubNewsVO();
					clubNewsVO.setClubID(clubID);
					clubNewsVO.setMemID(memID);
					clubNewsVO.setClubNewsContent(clubNewsContent);
					clubNewsVO.setClubNewsDate(clubNewsDate);
					clubNewsVO.setClubNewsStatus(clubNewsStatus);

					
System.out.println("addClubNews的"+clubID);	
System.out.println("addClubNews的"+memID);
System.out.println("addClubNews的"+clubNewsTitle);
System.out.println("addClubNews的"+clubNewsContent);
System.out.println("addClubNews的"+clubNewsDate);
System.out.println("addClubNews的"+clubNewsStatus);
System.out.println("addClubNews的"+actID);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("clubNewsVO", clubNewsVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/emp/addEmp.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
//
					ClubService clubSvc = new ClubService();
					ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
					
					ClubNewsService clubNewsSvc = new ClubNewsService();
					clubNewsVO = clubNewsSvc.addClubNews(clubID,memID,clubNewsDate,clubNewsTitle, clubNewsContent,actID,clubNewsStatus);
						
					List<ClubNewsVO> clubNewslist = clubSvc.getClubNewsByClubID(clubID);
					
					
					
					ClubMemService clubMemSvc = new ClubMemService();
					ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID,memID);
					List<ClubAlbumVO> clubAlbumlist = clubSvc.getClubAlbumsByClubID(clubID);
					List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);					
					List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(clubID);
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
					
					req.setAttribute("clubNewslist", clubNewslist); // 資料庫取出的empVO物件,存入req
					req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req
					req.setAttribute("clubMemlist", clubMemlist); // 資料庫取出的empVO物件,存入req
					req.setAttribute("clubMBlist", clubMBlist); 
					req.setAttribute("clubNewslist", clubNewslist);
					req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
					req.setAttribute("clubAlbumlist", clubAlbumlist); // 資料庫取出的empVO物件,存入req
					
					String url = "/front-end/club/ClubNews.jsp";
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
	      
	      
	      
	      
//clubNewsUpdate變更頁面
		 if ("clubNewsUpdate".equals(action)){
System.out.println("clubNewsUpdate");			 
			 	Integer clubID = Integer.parseInt(req.getParameter("clubID"));	//傳進來的是字串轉成數字
			 	Integer clubNewsID = Integer.parseInt(req.getParameter("clubNewsID"));	
			 	Integer  memID = Integer.parseInt(req.getParameter("memID"));
				/***************************2.開始查詢資料*****************************************/

				//社團資料
				ClubService clubSvc = new ClubService();
				ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
				
				ClubNewsService clubNewsSvc = new ClubNewsService();
				ClubNewsVO clubNewsVO = clubNewsSvc.getOneClubNews(clubNewsID);
				//拿到一個社團成員
				ClubMemService clubMemSvc = new ClubMemService();
				ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID,memID);

				List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);					
				List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(clubID);
				List<ClubNewsVO> clubNewslist =clubSvc.getClubNewsByClubID(clubID); 
				List<ClubAlbumVO> clubAlbumlist = clubSvc.getClubAlbumsByClubID(clubID);
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/

				req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req	
				req.setAttribute("clubNewsVO", clubNewsVO); // 資料庫取出的empVO物件,存入req	
				req.setAttribute("clubAlbumlist", clubAlbumlist); // 資料庫取出的empVO物件,存入req
				req.setAttribute("clubNewslist", clubNewslist); 
				req.setAttribute("clubMemlist", clubMemlist); 
				req.setAttribute("clubMBlist", clubMBlist); 
				req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料

				String url = "/front-end/club/ClubNewsUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
				successView.forward(req, res);		
		 		}	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
	      
//clubNews拿到變更社團NEWS完跳回原頁面
		  if ("getOneNews_For_Update".equals(action)){
			   Integer  clubNewsID = Integer.parseInt(req.getParameter("clubNewsID"));	
				Integer  memID = Integer.parseInt(req.getParameter("memID"));
				Integer  clubID = Integer.parseInt(req.getParameter("clubID"));
				Timestamp clubNewsDate = new Timestamp(System.currentTimeMillis()); 
				String clubNewsTitle = req.getParameter("clubNewsTitle").trim();		
				String clubNewsContent = req.getParameter("clubNewsContent").trim();
				Integer actID=1;

			
				Integer clubNewsStatus = 1;	
			
				ClubNewsVO clubNewsVO = new ClubNewsVO();
				clubNewsVO.setClubNewsID(clubNewsID);
				clubNewsVO.setClubID(clubID);	
				clubNewsVO.setMemID(memID);
				clubNewsVO.setClubNewsTitle(clubNewsTitle);
				clubNewsVO.setClubNewsContent(clubNewsContent);				
				clubNewsVO.setActID(actID);
				clubNewsVO.setClubNewsStatus(clubNewsStatus);
				
System.out.println("clubNews拿到變更社團NEWS完跳回原頁面clubNewsID"+clubNewsID);
System.out.println("clubNews拿到變更社團NEWS完跳回原頁面clubID"+clubID);
System.out.println("clubNews拿到變更社團NEWS完跳回原頁面memID"+memID);
System.out.println("clubNews拿到變更社團NEWS完跳回原頁面clubNewsTitle"+clubNewsTitle);
System.out.println("clubNews拿到變更社團NEWS完跳回原頁面clubNewsContent"+clubNewsContent);
System.out.println("clubNews拿到變更社團NEWS完跳回原頁面actID"+actID);
		
				
				/***************************2.開始修改資料*****************************************/
				ClubService clubSvc = new ClubService();
				ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
						
				ClubNewsService clubNewsSvc = new ClubNewsService();
		
				clubNewsVO = clubNewsSvc.updateClubNews(clubNewsID,clubID,memID,clubNewsDate,clubNewsTitle,clubNewsContent,actID,clubNewsStatus);
		
				List<ClubNewsVO> clubNewslist = clubSvc.getClubNewsByClubID(clubID);
				
				ClubMemService clubMemSvc = new ClubMemService();
				ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID,memID);

				List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);					
				List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(clubID);
				List<ClubAlbumVO> clubAlbumlist = clubSvc.getClubAlbumsByClubID(clubID);
	
						/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req	
				req.setAttribute("clubNewsVO", clubNewsVO); // 資料庫取出的empVO物件,存入req	
				req.setAttribute("clubAlbumlist", clubAlbumlist); // 資料庫取出的empVO物件,存入req
				req.setAttribute("clubNewslist", clubNewslist); 
				req.setAttribute("clubMemlist", clubMemlist); 
				req.setAttribute("clubMBlist", clubMBlist); 
				req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
						
						String url = "/front-end/club/ClubNews.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
						successView.forward(req, res);		
				 		}
	      
	      
	      
//clubNews刪除,變更社團NEWS完跳回原頁面
		  if ("deleteOneNews".equals(action)){	
				Integer  clubNewsID = Integer.parseInt(req.getParameter("clubNewsID"));
				Integer  clubID = Integer.parseInt(req.getParameter("clubID"));
				Integer  memID = Integer.parseInt(req.getParameter("memID"));

				Integer clubNewsStatus = 0;	
				
				ClubNewsVO clubNewsVO = new ClubNewsVO();
				clubNewsVO.setClubNewsID(clubNewsID);
				clubNewsVO.setClubID(clubID);	
				clubNewsVO.setMemID(memID);


				
				/***************************2.開始修改資料*****************************************/
				ClubService clubSvc = new ClubService();
				ClubVO clubVO = clubSvc.findByPrimaryKey(clubID);
				
				ClubNewsService clubNewsSvc = new ClubNewsService();
		
				clubNewsVO = clubNewsSvc.deleteOneNews(clubNewsID,clubNewsStatus);
		
				List<ClubNewsVO> clubNewslist = clubSvc.getClubNewsByClubID(clubID);
				
				ClubMemService clubMemSvc = new ClubMemService();
				ClubMemVO clubMemVO = clubMemSvc.getOneClubMem(clubID,memID);
				List<ClubMBVO> clubMBlist =clubSvc.getClubMBByClubID(clubID);					
				List<ClubMemVO> clubMemlist = clubSvc.getClubMemByClubID(clubID);
				List<ClubAlbumVO> clubAlbumlist = clubSvc.getClubAlbumsByClubID(clubID);
						/***************************3.修改完成,準備轉交(Send the Success view)*************/
						
						req.setAttribute("clubVO", clubVO); // 資料庫取出的empVO物件,存入req	
						req.setAttribute("clubNewsVO", clubNewsVO); // 資料庫取出的empVO物件,存入req	
						req.setAttribute("clubAlbumlist", clubAlbumlist); // 資料庫取出的empVO物件,存入req
						req.setAttribute("clubNewslist", clubNewslist); 
						req.setAttribute("clubMemlist", clubMemlist); 
						req.setAttribute("clubMBlist", clubMBlist); 
						req.setAttribute("clubMemVO", clubMemVO); // 單一社員資料
						
						
						String url = "/front-end/club/ClubNews.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneClub.jsp
						successView.forward(req, res);		
				 		}
        
        
        
	}
}
