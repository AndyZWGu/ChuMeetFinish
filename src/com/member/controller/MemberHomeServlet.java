package com.member.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes.Name;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.*;

/**
 * Servlet implementation class LoginServlet
 */
@MultipartConfig
public class MemberHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	int friendsNum = 0;
	int followNum = 0;
	int memMBNum = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberHomeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		if (session.getAttribute("memVO") == null) {
			res.sendRedirect("ChuMeetWebsite/front-end/index.jsp");
		}
		MemberVO memVO = (MemberVO) session.getAttribute("memVO");
		// sidebar判斷與Content執行區域使用
		String RequestURI = req.getRequestURI();
		String membertURI = RequestURI.substring(RequestURI.lastIndexOf("/"), RequestURI.length());
		if ("/memberHome.do".equals(membertURI.toString())) {
			session.setAttribute("checkedSidbar", "memHome");
		}
		if ("/memberCalendar.do".equals(membertURI.toString())) {
			session.setAttribute("checkedSidbar", "memCalendar");
		}
		if ("/memberMail.do".equals(membertURI.toString())) {
			// 若查看郵件細項
			String memMailID = req.getParameter("memMailID");
			if (memMailID != null) {
				session.setAttribute("checkedSidbar", "memMailDetail");
			} else {
				session.setAttribute("checkedSidbar", "memMail");
			}
		}
		if ("/memberNF.do".equals(membertURI.toString())) {
			// 若查看動態細項
			String memNFID = req.getParameter("memNFID");
			if (memNFID != null) {
				session.setAttribute("checkedSidbar", "memNFDetail");
			} else {
				session.setAttribute("checkedSidbar", "memNF");
			}
		}
		if ("/memberCommunity.do".equals(membertURI.toString())) {
			session.setAttribute("checkedSidbar", "memCommunity");
		}
		if ("/memberLv.do".equals(membertURI.toString())) {
			session.setAttribute("checkedSidbar", "memLv");
		}
		if ("/memberAch.do".equals(membertURI.toString())) {
			session.setAttribute("checkedSidbar", "memAch");
		}
		if ("/memberReward.do".equals(membertURI.toString())) {
			session.setAttribute("checkedSidbar", "memReward");
		}
		if ("/memberStar.do".equals(membertURI.toString())) {
			session.setAttribute("checkedSidbar", "memStar");
		}
		if ("/memberInfo.do".equals(membertURI.toString())) {
			session.setAttribute("checkedSidbar", "memInfo");
			// 若收到驗證功能
			String verify = req.getParameter("verify");
			System.out.println(verify);
			if ("email".equals(verify)) {
				// String to = "say8807101@gmail.com";
				String to = memVO.getMemEmail(); // 正式使用時改這版
				String subject = "揪咪ChuMeet E-mail 信箱驗證信";
				String ch_name = memVO.getMemName();
				String passRandom = memVO.getMemPw();
				String messageText = "<img src=\"https://i.imgur.com/IN3wmJe.png\"><h2>親愛的 " + ch_name
						+ " 您好:</h2><br><p>歡迎您使用揪咪ChuMeet信箱驗證功能。</p><p>驗證地址：http://localhost:8081/ChuMeetWebsite/front-end/member/verifyEmail.do?memID="
						+ memVO.getMemID()
						+ "</p><p>驗證完成後，ChuMeet將會提供您更多的服務資訊與內容。 </p><br><h5>ChuMeet歡迎您的加入! ChuMeet服務團隊</h5><br><h4>如有任何問題歡迎來信ChuMeet客服信箱: service@chumeet.com</h4>";
				MailService mailService = new MailService();
				mailService.sendMail(to, subject, messageText);
			}
			if (verify == "phone") {

			}
		}
		if ("/memberSetting.do".equals(membertURI.toString())) {
			session.setAttribute("checkedSidbar", "memSetting");
		}
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		if (session.getAttribute("memVO") == null) {
			res.sendRedirect("ChuMeetWebsite/front-end/index.jsp");
		}
		/**************************************************************
		 ******************** 左邊Sidebar使用(每次)***********************
		 *************************************************************/
		MemberVO memVO = (MemberVO) session.getAttribute("memVO");
		// System.out.println("當前會員編號"+memVO.getMemID()+"此程式在MemberHomeServlet.java");
		// 好友列表,好友數,跟隨數,平價數...
		FriendsService friSvc = new FriendsService();
		List<FriendsVO> friendsList = friSvc.getAllFriends(memVO.getMemID());
		friendsNum = 0;
		followNum = 0;
		for (FriendsVO list : friendsList) {
			if (list.getFriendType().contains("追蹤")) {
				followNum++;
			}
			if (list.getFriendType().contains("好友")) {
				followNum++;
				friendsNum++;
			}
		}
		// 會員名稱,稱號,跟隨,好友數,評分數,揪點,驗證,動態數,文章數,註冊時間
		// session.setAttribute("achName", achName);
		req.setAttribute("followNum", followNum);
		req.setAttribute("friNum", friendsNum);
		// session.setAttribute("memStar", memStar);
		if (memVO.getMemberType() == 0) {
			req.setAttribute("isMail", "未驗證");
			req.setAttribute("isPhone", "未驗證");
		} else if (memVO.getMemberType() == 1) {
			req.setAttribute("isMail", "驗證");
			req.setAttribute("isPhone", "未驗證");
		} else if (memVO.getMemberType() == 2) {
			req.setAttribute("isMail", "驗證");
			req.setAttribute("isPhone", "驗證");
		}
		MemNFService nfSvc = new MemNFService();
		StringBuffer nfCount = new StringBuffer().append(nfSvc.getCountByMemID(memVO.getMemID()));
		req.setAttribute("memNFNum", nfCount.toString());
		MemMBService mbSvc = new MemMBService();
		StringBuffer mbCount = new StringBuffer().append(mbSvc.getCountByMemID(memVO.getMemID()));
		req.setAttribute("memMBNum", mbCount.toString());
		req.setAttribute("memJoinDate", timestampToString(memVO.getMemJoinDate()).toString());
		/**************************************************************
		 ******************** 右邊Content使用****************************
		 *************************************************************/

		/******************** 首頁 ********************/
		if (session.getAttribute("checkedSidbar") == "memHome") {
			// 找首頁動態
			Map<String, String[]> nfMap = new HashMap<String, String[]>();
			String[] NFMemID = new String[2];
			String[] nfStatus = new String[2];
			NFMemID[0] = memVO.getMemID().toString();
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
				String comment = req.getParameter("comment");
				// String comment2 = new
				// String(comment.getBytes("ISO-8859-1"),"UTF-8");
				if (comment == null || (comment.trim()).length() == 0) {
					System.out.println("留言失敗,不得為空");
					String url = "/front-end/member/memberHome.jsp";
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
			MemberService memSvc = new MemberService();
			for (MemMBVO list : memMBList) {
				mbMemNameList.add(memSvc.getOneMember(list.getMemID()));
				// System.out.println(list.getMemID());
			}
			req.setAttribute("mbMemNameList", mbMemNameList);
			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 行事曆 ********************/
		if (session.getAttribute("checkedSidbar") == "memCalendar") {
			System.out.println("這裡是行事曆");

			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 收件夾 ********************/
		if (session.getAttribute("checkedSidbar") == "memMail") {
			System.out.println("這裡是收件夾");
			// Map
			Map<String, String[]> mailMap = new HashMap<String, String[]>();
			String[] MailMemIDs = new String[2];
			MailMemIDs[0] = memVO.getMemID().toString();
			mailMap.put("receiver", MailMemIDs);
			// list
			MemMailService mailSvc = new MemMailService();
			List<MemMailVO> memMailList = mailSvc.getAll(mailMap);
			req.setAttribute("memMailList", memMailList);
			// mamNameList
			List<MemberVO> mailMemNameList = new ArrayList<MemberVO>();
			MemberService memSvc = new MemberService();
			for (MemMailVO list : memMailList) {
				mailMemNameList.add(memSvc.getOneMember(list.getAuthor()));
			}
			req.setAttribute("mailMemNameList", mailMemNameList);
			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 收件夾細項 ********************/
		if (session.getAttribute("checkedSidbar") == "memMailDetail") {
			System.out.println("這裡是收件夾細項");
			// mailVO
			MemMailService mailSvc = new MemMailService();
			Integer memMailID = Integer.parseInt(req.getParameter("memMailID"));
			// System.out.println("memMailID="+memMailID);
			MemMailVO memMailVO = mailSvc.getOneMail(memMailID);
			req.setAttribute("memMailVO", memMailVO);
			MemberService memSvc = new MemberService();
			// mailName
			String mailName = memSvc.getOneMember(memMailVO.getAuthor()).getMemName();
			req.setAttribute("mailName", mailName);
			// req.setAttribute("memMailList", memMailList);
			// 郵件留言功能如果被使用
			String action = req.getParameter("action");
			if ("comment".equals(action)) {
				String comment = req.getParameter("comment");
				// String comment2 = new
				// String(comment.getBytes("ISO-8859-1"),"UTF-8");
				if (comment == null || (comment.trim()).length() == 0) {
					System.out.println("留言失敗,不得為空");
					String url = "/front-end/member/memberHome.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				} else {
					// get做先後再
					// System.out.println(comment); //沒處理編碼會亂碼
					// System.out.println(comment2); //有處理
					if (!memMailVO.getMailTitle().contains("回覆:")) {
						memMailVO.setMailTitle("回覆:" + memMailVO.getMailTitle());
					}
					mailSvc.addMail(memMailVO.getAuthor(), memMailVO.getReceiver(), memMailVO.getMailTitle(),
							nowTimestamp(), comment);
				}
			}
			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 動態 ********************/
		if (session.getAttribute("checkedSidbar") == "memNF") {
			System.out.println("這裡是動態");
			// 發布動態功能如果被使用
			String action = req.getParameter("action");
			System.out.println(action);
			String action2 = req.getParameter("comment");
			System.out.println(action2);
			String action3 = req.getParameter("nfTitle");
			System.out.println(action3);
			if ("newNF".equals(action)) {
				System.out.println("發布一則新動態");
				String comment = req.getParameter("comment");
				String nfTitle = req.getParameter("nfTitle");
				// String comment2 = new
				// String(comment.getBytes("ISO-8859-1"),"UTF-8");
				if (comment == null || (comment.trim()).length() == 0 || nfTitle == null
						|| (nfTitle.trim()).length() == 0) {
					System.out.println("留言失敗,不得為空");
					String url = "/front-end/member/memberHome.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				} else {
					// get做先後再
					// System.out.println(comment); //沒處理編碼會亂碼
					// System.out.println(comment2); //有處理
					// 大頭照用
					byte[] byteAvatar = getAvatarByPart(req);
					nfSvc.addNF(memVO.getMemID(), nfTitle, comment, byteAvatar, nowTimestamp(), 0, 1);
				}
			}
			// MemNFService nfSvc = new MemNFService();
			// list
			List<MemNFVO> memNFList = nfSvc.getOneNFByMemID(memVO.getMemID());
			req.setAttribute("memNFList", memNFList);
			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 動態細項 ********************/
		if (session.getAttribute("checkedSidbar") == "memNFDetail") {
			System.out.println("這裡是動態細項");
			// MemNFVO memNFVO = nfSvc.getOneNF(memNFID);
			// req.setAttribute("memNFVO", memNFVO);
			// nfNameList
			Integer memNFID = null;
			if (req.getParameter("memNFID") != null) {
				memNFID = Integer.parseInt(req.getParameter("memNFID"));
				MemNFVO memNFVO = nfSvc.getOneNF(memNFID);
				req.setAttribute("memNFVO", memNFVO);
			}
			// 留言功能如果被使用
			String action = req.getParameter("action");
			if ("comment".equals(action)) {
				System.out.println("留言");
				String comment = req.getParameter("comment");
				// String comment2 = new
				// String(comment.getBytes("ISO-8859-1"),"UTF-8");
				if (comment == null || (comment.trim()).length() == 0) {
					System.out.println("留言失敗,不得為空");
					String url = "/front-end/member/memberHome.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
					return;
				} else {
					// get做先後再
					// System.out.println(comment); //沒處理編碼會亂碼
					// System.out.println(comment2); //有處理
					MemMBVO addMBVO = mbSvc.addMB(memNFID, memVO.getMemID(), comment, nowTimestamp(), 1);
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
			MemberService memSvc = new MemberService();
			for (MemMBVO list : memNFMBList) {
				mbMemNameList.add(memSvc.getOneMember(list.getMemID()));
			}
			req.setAttribute("mbMemNameList", mbMemNameList);
			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 社群管理 ********************/
		if (session.getAttribute("checkedSidbar") == "memCommunity") {
			System.out.println("這裡是社群管理");

			// Map
			Map<String, String[]> friMap = new HashMap<String, String[]>();
			String[] MailMemIDs = new String[2];
			MailMemIDs[0] = memVO.getMemID().toString();
			friMap.put("friMem1", MailMemIDs);
			// list
			List<FriendsVO> memFriList = friSvc.getAll(friMap);
			req.setAttribute("memFriList", memFriList);
			// mamNameList
			List<MemberVO> friMemNameList = new ArrayList<MemberVO>();
			MemberService memSvc = new MemberService();
			for (FriendsVO list : memFriList) {
				friMemNameList.add(memSvc.getOneMember(list.getFriMem2()));
			}
			req.setAttribute("friMemNameList", friMemNameList);

			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 等級 ********************/
		if (session.getAttribute("checkedSidbar") == "memLv") {
			System.out.println("這裡是等級");
			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 成就 ********************/
		if (session.getAttribute("checkedSidbar") == "memAch") {
			System.out.println("這裡是成就");
			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 獎賞 ********************/
		if (session.getAttribute("checkedSidbar") == "memReward") {
			System.out.println("這裡是獎賞");
			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 評價 ********************/
		if (session.getAttribute("checkedSidbar") == "memStar") {
			System.out.println("這裡是評價");
			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 基本資訊 ********************/
		if (session.getAttribute("checkedSidbar") == "memInfo") {
			System.out.println("這裡是基本資訊");
			// 修改功能被使用
			String action = req.getParameter("action");
			if ("changeInfo".equals(action)) {
				System.out.println("執行修改基本資訊");
				MemberService memSvc = new MemberService();
				String memPw = req.getParameter("memPw");
				String memName = req.getParameter("memName");
				String memLocBorn = req.getParameter("memLocBorn");
				if ("當前未設定".equals(memLocBorn)) {
					memLocBorn = null;
				}
				String memLocLive = req.getParameter("memLocLive");
				if ("當前未設定".equals(memLocLive)) {
					memLocBorn = null;
				}
				MemberVO memInfoVO = memSvc.updateInfoMember(memVO, memPw, memName, memLocBorn, memLocLive);
				session.setAttribute("memVO", memInfoVO);
			}

			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		/******************** 個人頁面設定 ********************/
		if (session.getAttribute("checkedSidbar") == "memSetting") {
			System.out.println("這裡是個人頁面設定");
			// 修改用
			String action = req.getParameter("action");
			if ("changeSetting".equals(action)) {
				String memInt = req.getParameter("memInt");
				String memPriv = req.getParameter("memPriv");
				int i = 2;
				MemberService memSvc = new MemberService();
				if ("公開".equals(memPriv)) {
					i = 2;
				}
				if ("僅好友".equals(memPriv)) {
					i = 1;
				}
				if ("不公開".equals(memPriv)) {
					i = 0;
				}
				MemberVO memSettingVO = memSvc.updateSettingMember(memVO, memInt, i);
				session.setAttribute("memVO", memSettingVO);
				System.out.println(memVO.getMemPriv());
			}
			// 轉址用
			String url = "/front-end/member/memberHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		// 拿照片
		// base64用
		// List<String> list = new ArrayList<String>();
		// List<byte[]> list = new ArrayList<byte[]>();
		// MemberService memSvc = new MemberService();
		// list = memSvc.getAvatar(memVO.getMemID());
		// req.setAttribute("pictureList", list);
		// 基本作法
		// 放在另外一隻透過doGet加參數獲取圖片

		// req.setCharacterEncoding("UTF-8");
		// String action = req.getParameter("action");
		// String action = "login";

		// if ("login".equals(action)) { // 來自login.jsp的請求
		//
		// List<String> errorMsgs = new LinkedList<String>();
		// // Store this set in the request scope, in case we need to
		// // send the ErrorPage view.
		// req.setAttribute("errorMsgs", errorMsgs);
		//
		// try {
		// /***************************1.接收請求參數 -
		// 輸入格式的錯誤處理**********************/
		// String memEmail = req.getParameter("memEmail");
		// String memPw = req.getParameter("memPw");
		//// String memEmail = "adm@gmail";
		//// String memPw = "Master";
		// if (memEmail == null || (memEmail.trim()).length() == 0) {
		// errorMsgs.add("請輸入會員帳號");
		// }
		// if (memPw == null || (memPw.trim()).length() == 0) {
		// errorMsgs.add("請輸入會員密碼");
		// }
		// // Send the use back to the form, if there were errors
		// if (!errorMsgs.isEmpty()) {
		// RequestDispatcher failureView = req
		// .getRequestDispatcher("/member/login.jsp");
		// failureView.forward(req, res);
		// return;//程式中斷
		// }
		//
		// String formatMemEmail = null;
		// String formatMemPw = null;
		// try {
		// formatMemEmail = new String();
		// formatMemPw = new String();
		// } catch (Exception e) {
		// errorMsgs.add("輸入格式不正確");
		// }
		// // Send the use back to the form, if there were errors
		// if (!errorMsgs.isEmpty()) {
		// RequestDispatcher failureView = req
		// .getRequestDispatcher("/emp/select_page2.jsp");
		// failureView.forward(req, res);
		// return;//程式中斷
		// }
		//
		// /***************************2.開始查詢資料*****************************************/
		// MemberService memSvc = new MemberService();
		// MemberVO memVO = memSvc.getMemberByMemEmail(memEmail);
		// if (memVO == null) {
		// errorMsgs.add("查無帳號或密碼,請重新登入");
		// }
		// MemberVO memVO2 = memSvc.getMemberByMemPw(memPw);
		// if (memVO2 == null) {
		// errorMsgs.clear();
		// errorMsgs.add("您輸入的密碼有誤,請確認");
		// }
		// // Send the use back to the form, if there were errors
		// if (!errorMsgs.isEmpty()) {
		// RequestDispatcher failureView = req
		// .getRequestDispatcher("/member/login.jsp");
		// failureView.forward(req, res);
		// return;//程式中斷
		// }
		//
		// /***************************3.查詢完成,準備轉交(Send the Success
		// view)*************/
		// req.setAttribute("memVO", memVO); // 資料庫取出的empVO物件,存入req
		// String url = "/member/memHome.jsp";
		// RequestDispatcher successView = req.getRequestDispatcher(url); //
		// 成功轉交 listOneEmp.jsp
		// successView.forward(req, res);
		//
		// /***************************其他可能的錯誤處理*************************************/
		// } catch (Exception e) {
		// errorMsgs.add("無法取得資料:" + e.getMessage());
		// req.setAttribute("errorMsgs", errorMsgs);
		// RequestDispatcher failureView = req
		// .getRequestDispatcher("/member/login.jsp");
		// failureView.forward(req, res);
		// }
		// }else{
		// RequestDispatcher failureView = req
		// .getRequestDispatcher("/member/login.jsp");
		// failureView.forward(req, res);
		// }

		/**************************************************************
		 * *******************我的動態*******************************
		 *************************************************************/
		// if ("memNF".equals(action)) { // 來自memHome.jsp的請求
		//
		// }
		/**************************************************************
		 * *******************我的等級積分*******************************
		 *************************************************************/
		// if ("memLevel".equals(action)) { // 來自memHome.jsp的請求
		//
		// }
		/**************************************************************
		 * *******************我的基本資訊*******************************
		 *************************************************************/
		// if ("memInfo".equals(action)) { // 來自memHome.jsp的請求
		//
		// }
		/**************************************************************
		 * *******************我的成就*******************************
		 *************************************************************/
		// if ("memAch".equals(action)) { // 來自memHome.jsp的請求
		//
		// }
		/**************************************************************
		 * *******************我的評價*******************************
		 *************************************************************/
		// if ("memOpinion".equals(action)) { // 來自memHome.jsp的請求
		//
		// }
		/**************************************************************
		 * *******************我的個人頁面設定*******************************
		 *************************************************************/
		// if ("memSeeting".equals(action)) { // 來自memHome.jsp的請求
		//
		// }
		/**************************************************************
		 * *******************會員動態*******************************
		 *************************************************************/
		// if ("memLevel".equals(action)) { // 來自memHome.jsp的請求
		//
		// }
		/**************************************************************
		 * *******************會員動態*******************************
		 *************************************************************/
		// if ("memLevel".equals(action)) { // 來自memHome.jsp的請求
		//
		// }
		// doPost
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

	// Timestamp轉String
	public static String timestampToString(Timestamp timestamp) {
		if (timestamp == null) {
			return "null";
		} else {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 定义格式，不显示毫秒
			// Timestamp now = new
			// Timestamp(System.currentTimeMillis());//获取系统当前时间
			String str = df.format(timestamp);
			// System.out.println(str);
			return str;
		}
	}

	// 取得上傳照片
	public byte[] getAvatarByPart(HttpServletRequest req) throws IllegalStateException, IOException, ServletException {
		byte[] byteAvatar = null;
		// Servlet3.0新增part介面方面檔案上傳
		Collection<Part> filePart2 = req.getParts();
		System.out.println("FilePart's Size=" + filePart2.size());
		for (Part part : filePart2) {
			if (getFileNameFromPart(part) != null && part.getContentType() != null) {
				// 寫入功能
				// 額外測試 InputStream 與 byte[] (幫將來model的VO預作準備)
				InputStream in = part.getInputStream();// 位元組串流
				byteAvatar = new byte[in.available()];// 長度
				in.read(byteAvatar);// 串流寫入byte[]
				in.close();
			}
		}
		return byteAvatar;
	}

	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		// System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		// System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
