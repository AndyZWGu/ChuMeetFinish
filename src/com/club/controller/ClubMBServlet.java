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
