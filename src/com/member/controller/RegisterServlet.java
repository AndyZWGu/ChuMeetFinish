package com.member.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.member.model.*;
import com.mysql.jdbc.Blob;

@MultipartConfig
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		if ("register".equals(action) || session.getAttribute("memVO")==null) { // 來自register.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String memEmail = req.getParameter("memEmail");
				String memPw = req.getParameter("memPw");
				String memName = req.getParameter("memName");
				String memPhone = req.getParameter("memPhone");
				String memLocBorn = req.getParameter("memLocBorn");
				String memLocLive = req.getParameter("memLocLive");
				String memBD = req.getParameter("memBD");
				System.out.println(memBD);
				Integer memGender = Integer.parseInt(req.getParameter("memGender"));
				String memInt = req.getParameter("memInt");
				String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memEmail == null || (memEmail.trim()).length() == 0) {
					errorMsgs.add("請輸入會員帳號");
				}
				if (memPw == null || (memPw.trim()).length() == 0) {
					errorMsgs.add("請輸入會員密碼");
				} else if (!memPw.trim().matches(adminNameReg)) {
					errorMsgs.add("密碼:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("register.jsp");
					failureView.forward(req, res);
					return;
				}
				String formatMemEmail = null;
				String formatMemPw = null;
				try {
					formatMemEmail = new String();
					formatMemPw = new String();
				} catch (Exception e) {
					errorMsgs.add("輸入格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("register.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memEmailVO = memSvc.getMemberByMemEmail(memEmail);
				if (memEmailVO != null) {
					errorMsgs.add("帳號已被使用,請重新輸入新的帳號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("register.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view)*************
				**************************** 正式註冊一筆新資料 *************************************/
				// 時間用(生日)
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            Date parsedDate = dateFormat.parse(memBD);
	            java.sql.Timestamp timestampMemBD = new java.sql.Timestamp(parsedDate.getTime());
	            // 時間用(加入時間)
				java.sql.Timestamp today = nowTimestamp();
				// 大頭照用
				byte[] byteAvatar = getAvatarByPart(req);
				// 註冊呼叫Service調用Dao
				memSvc.addMember(memEmail, memPw, 0, 1, 0, 50, memName, memGender, timestampMemBD, memPhone,
						byteAvatar, today, 1, memLocBorn, memLocLive, memInt, 0.0, 0.0, 2, 1);
				// 自動登入
				MemberVO memVO = memSvc.getMemberByMemEmail(memEmail);
				System.out.println(memVO.getMemID());
				session.setAttribute("memVO", memVO);
				session.setAttribute("account", memVO.getMemEmail());
				//自動首頁發表第一篇動態作為收集留言之用途
				MemNFService nfSvc = new MemNFService();
				nfSvc.addNFtoHome(memVO.getMemID(),"歡迎加入揪咪大家庭","本動態作為第一次加入並且限定會員首頁顯示及留言之作用", null, today, 0);//隱藏Status=2作為辨識用
				//回首頁
				res.sendRedirect("/ChuMeetWebsiteFinish/front-end/index.jsp");
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				req.setAttribute("errorMsgs", errorMsgs);
				RequestDispatcher failureView = req.getRequestDispatcher("register.jsp");
				failureView.forward(req, res);
			}
		} else {
			RequestDispatcher failureView = req.getRequestDispatcher("front-end/member/register.jsp");
			failureView.forward(req, res);
		}
	}
	/*************************** 其他工具方法 *************************************/
	// 取得現在時間(java.sql.Date型態)
	  public static Timestamp nowTimestamp(){
		  java.util.Date utildate=new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(utildate.getTime());
			java.sql.Time sTime=new java.sql.Time(utildate.getTime());
			java.sql.Timestamp stp=new java.sql.Timestamp(utildate.getTime());
	       return stp;
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
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
	// 處理回傳null的bug
	private static String getValue(Part part) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
		StringBuilder value = new StringBuilder();
		char[] buffer = new char[1024];
		for (int length = 0; (length = reader.read(buffer)) > 0;) {
			value.append(buffer, 0, length);
		}
		return value.toString();
	}

}
