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

import com.club.model.ClubService;
import com.club.model.ClubVO;
import com.clubMem.model.ClubMemService;
import com.clubMem.model.ClubMemVO;
import com.member.model.*;

/**
 * Servlet implementation class LoginServlet
 */
public class GuestHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuestHomeServlet() {
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
		System.out.println("demo");
		//找會員參加的社團
	 	Integer memID= new Integer(req.getParameter("memID").trim());
		/***************************2.開始查詢資料*****************************************/
		ClubService clubSvc = new ClubService();		
		ClubMemService clubMemSvc = new ClubMemService();
		List<ClubVO> clublist = clubSvc.getAll();			
		
		List<ClubMemVO> memAllJoinClublist = clubMemSvc.getAllJoinClub(memID);				
		/***************************3.查詢完成,準備轉交(Send the Success view)*************/
		req.setAttribute("clublist", clublist); 
		req.setAttribute("memAllJoinClublist", memAllJoinClublist);	
		
		//
		//Integer memID = Integer.parseInt(req.getParameter("memID"));
		// System.out.println(memID);
		HttpSession session = req.getSession();
		if (session.getAttribute("guestVO") != null) {
			session.removeAttribute("guestVO");
		}
		MemberService memSvc = new MemberService();
		MemberVO guestVO = (MemberVO) memSvc.getOneMember(memID);
		session.setAttribute("guestVO", guestVO);
		MemberVO memVO = (MemberVO) session.getAttribute("memVO");
		//好友關係
		FriendsService friSvc = new FriendsService();
		FriendsVO friVO = friSvc.getOneFriends(memVO.getMemID(), guestVO.getMemID());
		FriendsVO friVO2 = friSvc.getOneFriends(guestVO.getMemID(),memVO.getMemID());
		if(friVO==null||friVO2==null){
			req.setAttribute("memPriv",0);
		} else if(friVO.getFriendType().contains("好友") && friVO2.getFriendType().contains("好友")){
			req.setAttribute("memPriv",1);
		} 
		if(friVO2!=null && friVO2.getFriendType().contains("申請中")){
			req.setAttribute("memPriv",2);
		} 
		// 找首頁動態
		MemNFService nfSvc = new MemNFService();
		MemMBService mbSvc = new MemMBService();
		Map<String, String[]> nfMap = new HashMap<String, String[]>();
		String[] NFMemID = new String[2];
		String[] nfStatus = new String[2];
		NFMemID[0] = guestVO.getMemID().toString();
		nfStatus[0] = "2";
		nfMap.put("memID", NFMemID);
		nfMap.put("nfStatus", nfStatus);
		List<MemNFVO> memNFList = nfSvc.getAllNF(nfMap);
		Integer memNFID = null;
		for (MemNFVO list : memNFList) {
			memNFID = list.getMemNFID();
		}
		// System.out.println(memNFID);
		// 留言功能如果被使用
		String action = req.getParameter("action");
		if ("comment".equals(action)) {
			System.out.println("留言");
			String comment2 = req.getParameter("comment");
			String comment = new String(comment2.getBytes("ISO-8859-1"),"UTF-8");
			// String comment2 = new
			// String(comment.getBytes("ISO-8859-1"),"UTF-8");
			if (comment == null || (comment.trim()).length() == 0) {
				System.out.println("留言失敗,不得為空");
				String url = "/front-end/member/guestHome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} else {
				// get做先後再
				// System.out.println(comment); //沒處理編碼會亂碼
				// System.out.println(comment2); //有處理
				MemMBVO addMBVO = mbSvc.addMBtoHome(memNFID, memVO.getMemID(), comment, nowTimestamp());
			}
		}
		// 找首頁動態下方的留言
		Map<String, String[]> mbMap = new HashMap<String, String[]>();
		String[] memNFIDs = new String[2];
		memNFIDs[0] = String.valueOf(memNFID);
		mbMap.put("memNFID", memNFIDs);
		List<MemMBVO> memMBList = mbSvc.getAllMB(mbMap);
		req.setAttribute("memMBList", memMBList);
		// 找首頁動態下方留言者的姓名
		List<MemberVO> mbMemNameList = new ArrayList<MemberVO>();
		for (MemMBVO list : memMBList) {
			mbMemNameList.add(memSvc.getOneMember(list.getMemID()));
			// System.out.println(list.getMemID());
		}
		req.setAttribute("mbMemNameList", mbMemNameList);
		
		
		/**左邊一般按鈕**/
		if ("addFriend".equals(action)) {
			System.out.println("加好友！！！");
			String friMem1 = req.getParameter("friMem1");
			String friMem2 = req.getParameter("friMem2");
			friSvc.addMember(Integer.valueOf(friMem1), Integer.valueOf(friMem2), "申請中", nowTimestamp());
		}
		if ("addFollow".equals(action)) {
			System.out.println("加追隨！！！");
		}
		if ("addMail".equals(action)) {
			System.out.println("加寄一封站內信！！！");
			String mailTitle2 = req.getParameter("mailTitle");
			String mailTitle = new String(mailTitle2.getBytes("ISO-8859-1"),"UTF-8");
			//System.out.println("郵件標題"+mailTitle);
			String mailContent2 = req.getParameter("mailContent");
			String mailContent = new String(mailContent2.getBytes("ISO-8859-1"),"UTF-8");
			//System.out.println("郵件內容"+mailContent);
			MemMailService mailSvc = new MemMailService();
			String receiver = req.getParameter("receiver");
			String author = req.getParameter("author");
			mailSvc.addMail(Integer.valueOf(receiver), Integer.valueOf(author), mailTitle, nowTimestamp(), mailContent);
			
		}
		if ("addReport".equals(action)) {
			System.out.println("檢舉！！！");
		}
		
		// 轉址用
		String url = "/front-end/member/guestHome.jsp";
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
