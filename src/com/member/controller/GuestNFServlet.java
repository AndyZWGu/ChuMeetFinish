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
public class GuestNFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GuestNFServlet() {
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
		System.out.println("這裡是動態");
		HttpSession session = req.getSession();
		
		if (session.getAttribute("guestVO") != null) {
			session.removeAttribute("guestVO");
		}
		
		MemberService memSvc = new MemberService();
		Integer memID = Integer.parseInt(req.getParameter("memID"));
		MemberVO guestVO = (MemberVO) memSvc.getOneMember(memID);
		session.setAttribute("guestVO", guestVO);
		MemberVO memVO = (MemberVO) session.getAttribute("memVO");
		//好友關係
		FriendsService friSvc = new FriendsService();
		FriendsVO friVO = friSvc.getOneFriends(memVO.getMemID(), guestVO.getMemID());
		FriendsVO friVO2 = friSvc.getOneFriends(guestVO.getMemID(),memVO.getMemID());
		if(friVO==null||friVO2==null){
			session.setAttribute("memPriv", 0);
		} else if(friVO.getFriendType().contains("好友") && friVO2.getFriendType().contains("好友")){
			session.setAttribute("memPriv", 1);
		} else {
			session.setAttribute("memPriv", 0);
		}
		MemNFService nfSvc = new MemNFService();
		MemMBService mbSvc = new MemMBService();
		String memNFID = req.getParameter("memNFID");
		if (memNFID == null) {
			// MemNFService nfSvc = new MemNFService();
			// list
			List<MemNFVO> memNFList = nfSvc.getOneNFByMemID(guestVO.getMemID());
			req.setAttribute("memNFList", memNFList);
			// 轉址用
			String url = "/front-end/member/guestNF.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;			
		} else {
			System.out.println("這裡是動態細項");
			// MemNFVO memNFVO = nfSvc.getOneNF(memNFID);
			 
			// nfNameList
				MemNFVO memNFVO = nfSvc.getOneNF(Integer.valueOf(memNFID));
				req.setAttribute("memNFVO", memNFVO);
				//瀏覽數+1
				nfSvc.updateNFViews(Integer.valueOf(memNFID));
			// 留言功能如果被使用
			String action = req.getParameter("action");
			if ("comment".equals(action)) {
				System.out.println("留言");
				String comment = req.getParameter("comment");
				// String comment2 = new
				// String(comment.getBytes("ISO-8859-1"),"UTF-8");
				if (comment == null || (comment.trim()).length() == 0) {
					System.out.println("留言失敗,不得為空");
					String url = "/front-end/member/guestNF.do?memID="+memID+"&memNFID="+memNFID;
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				} else {
					// get做先後再
					// System.out.println(comment); //沒處理編碼會亂碼
					// System.out.println(comment2); //有處理
					MemMBVO addMBVO = mbSvc.addMB(Integer.valueOf(memNFID), memVO.getMemID(), comment, nowTimestamp(),1);
				}
			}
			// 找動態下方的留言
			Map<String, String[]> mbMap = new HashMap<String, String[]>();
			String[] memNFIDs = new String[2];
			memNFIDs[0] = String.valueOf(memNFID);
			mbMap.put("memNFID", memNFIDs);
			List<MemMBVO> memNFMBList = mbSvc.getAllMB(mbMap);
			req.setAttribute("memNFMBList", memNFMBList);
			// 找首頁動態下方留言者的姓名
			List<MemberVO> mbMemNameList = new ArrayList<MemberVO>();
			MemberService memSvc2 = new MemberService();
			for (MemMBVO list : memNFMBList) {
				mbMemNameList.add(memSvc2.getOneMember(list.getMemID()));
			}
			req.setAttribute("mbMemNameList", mbMemNameList);
			// 轉址用
			String url = "/front-end/member/guestNFDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}

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
