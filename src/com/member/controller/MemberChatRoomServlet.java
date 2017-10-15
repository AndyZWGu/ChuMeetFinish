package com.member.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.*;

/**
 * Servlet implementation class LoginServlet
 */
public class MemberChatRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberChatRoomServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("這裡是聊天室");
		HttpSession session = req.getSession();
		session.setAttribute("checkedSidbar", "memChatRoom");
		MemberVO memVO = (MemberVO) session.getAttribute("memVO");
		FriendsService friSvc = new FriendsService();
		List<FriendsVO> memFriList = friSvc.getAllFriends(memVO.getMemID());
		//好友
		List<FriendsVO> isFriList = new ArrayList<FriendsVO>();
		List<MemberVO> isFriMemNameList = new ArrayList<MemberVO>();
		MemberService memSvc = new MemberService();
		for(FriendsVO list : memFriList){
			if(list.getFriendType().contains("好友")){
				isFriList.add(list);
				isFriMemNameList.add(memSvc.getOneMember(list.getFriMem2()));
			}
			System.out.println(list.getFriendType());
		}
		req.setAttribute("memFriList", memFriList);
		req.setAttribute("isFriList", isFriList);
		req.setAttribute("isFriMemNameList", isFriMemNameList);
		
		String guestID = req.getParameter("guestID");
		String action = req.getParameter("action");
		if("changeRoom".equals(action)){
			req.setAttribute("guestID", guestID);
			if(guestID!=null){
				MemberVO guestVO = new MemberVO();
				guestVO = memSvc.getOneMember(Integer.parseInt(guestID));
				req.setAttribute("guestName", guestVO.getMemName());
			}
		}
		if(guestID==null){
			req.setAttribute("guestID", "");
		}
		
			// 轉址用
			String url = "/front-end/member/memberChatRoomContent.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;			
		 
	}
	
	/*************************** 其他工具方法 *************************************/
	// 取得現在時間(java.sql.Date型態)
	public static Timestamp nowTimestamp() {
		java.util.Date utildate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
		java.sql.Time sTime = new java.sql.Time(utildate.getTime());
		java.sql.Timestamp stp = new java.sql.Timestamp(utildate.getTime());
		return stp;
	}

}
