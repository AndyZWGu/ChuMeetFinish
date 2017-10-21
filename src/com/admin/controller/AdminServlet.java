package com.admin.controller;

import java.io.*;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.admin.model.*;

import com.admPril.model.*;

public class AdminServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);	
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 新增員工資料
		if ("insert".equals(action)) {
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("erroeMsgs", errorMsgs);

			try {
				String adminName = req.getParameter("adminName");
				String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminName == null || adminName.trim().length() == 0) {
					errorMsgs.put("adminName", "姓名:請勿空白");
				} else if (!adminName.trim().matches(adminNameReg)) {
					errorMsgs.put("adminName", "姓名:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				String adminMail = req.getParameter("adminMail").trim();
				if (adminMail == null || adminMail.trim().length() == 0) {
					errorMsgs.put("adminMail", "帳號勿空白");
				}

				String adminEmail = req.getParameter("adminEmail").trim();
				if (adminEmail == null || adminEmail.trim().length() == 0) {
					errorMsgs.put("adminEmail", "信箱勿空白");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admPril.jsp");
					failureView.forward(req, res);
					return;
				}
				System.out.println(adminName);
				System.out.println(adminMail);
				System.out.println(adminEmail);

				// 寄信＋密碼
				Random rand = new Random();
				String pw = encrypt(rand.toString());
				System.out.println(pw);
				AdminMailService mailService = new AdminMailService();
				// UUID
				mailService.sendMail("ChuMeetService@gmail.com", "揪咪管理員ChuMeet 密碼確認信",
						"<img src=\"https://i.imgur.com/IN3wmJe.png\"><h2>親愛的 " + adminName
								+ " 您好:</h2><br><p>歡迎您成為揪咪ChuMeet管理員。密碼為</p><br><strong>" + pw
								+ "</strong><br><p>ChuMeet將會提供您更多的服務資訊與內容。 </p><br><h5>ChuMeet歡迎您的加入! ChuMeet服務團隊</h5><br><h4>如有任何問題歡迎來信ChuMeet客服信箱: chuMeetService@gmail.com</h4>");
				// 開始新增資料
				AdminService adminSvc = new AdminService();
				adminSvc.addAdmin(adminName, adminMail, pw, adminEmail, nowTimestamp(), 1);

				AdminVO adminVO = adminSvc.getAdminByAdminName(adminName);
				System.out.println(adminVO.getAdminName());
				// 權限
				AdmPrilService admPrilSvc = new AdmPrilService();
				String adminPrivCheckbox = req.getParameter("adminPrivCheckbox");
				if ("on".equals(adminPrivCheckbox))
					admPrilSvc.addAdmPril(1, adminVO.getAdminID(), nowTimestamp(), 1);
				String infoPrivCheckbox = req.getParameter("infoPrivCheckbox");
				if ("on".equals(infoPrivCheckbox))
					admPrilSvc.addAdmPril(2, adminVO.getAdminID(), nowTimestamp(), 1);
				String appPrivCheckbox = req.getParameter("appPrivCheckbox");
				if ("on".equals(appPrivCheckbox))
					admPrilSvc.addAdmPril(3, adminVO.getAdminID(), nowTimestamp(), 1);
				String memberPrivCheckbox = req.getParameter("memberPrivCheckbox");
				if ("on".equals(memberPrivCheckbox))
					admPrilSvc.addAdmPril(4, adminVO.getAdminID(), nowTimestamp(), 1);
				String achPrivCheckbox = req.getParameter("achPrivCheckbox");
				if ("on".equals(achPrivCheckbox))
					admPrilSvc.addAdmPril(5, adminVO.getAdminID(), nowTimestamp(), 1);
				String reportPrivCheckbox = req.getParameter("reportPrivCheckbox");
				if ("on".equals(reportPrivCheckbox))
					admPrilSvc.addAdmPril(6, adminVO.getAdminID(), nowTimestamp(), 1);
				String poiPrivCheckbox = req.getParameter("poiPrivCheckbox");
				if ("on".equals(poiPrivCheckbox))
					admPrilSvc.addAdmPril(7, adminVO.getAdminID(), nowTimestamp(), 1);
				String actPrivCheckbox = req.getParameter("actPrivCheckbox");
				if ("on".equals(actPrivCheckbox))
					admPrilSvc.addAdmPril(8, adminVO.getAdminID(), nowTimestamp(), 1);
				String clubPrivCheckbox = req.getParameter("clubPrivCheckbox");
				if ("on".equals(clubPrivCheckbox))
					admPrilSvc.addAdmPril(9, adminVO.getAdminID(), nowTimestamp(), 1);

				// 新增完成轉交
				String url = "/back-end/admin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admPril.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer adminID = new Integer(req.getParameter("adminID"));

				/*************************** 2.開始查詢資料 ****************************************/
				AdminService adminSvc = new AdminService();
				AdminVO adminVO = adminSvc.getOneAdmin(adminID);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("adminVO", adminVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/updateAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				Integer adminID = null;
				String adminName = null;
				String adminMail = null;
				String adminPW = null;
				String adminEmail = null;

					adminID = new Integer(req.getParameter("adminID").trim());
					adminName = req.getParameter("adminName").trim();
					adminMail = req.getParameter("adminMail").trim();
					adminPW = req.getParameter("adminPW").trim();
					adminEmail = req.getParameter("adminEmail").trim();


				String adminNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (adminName == null || adminName.trim().length() == 0) {
					errorMsgs.put("adminName", "姓名:請勿空白");
				}
				if (!adminName.trim().matches(adminNameReg)) {
					errorMsgs.put("adminName", "姓名:只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				if (adminMail == null || adminMail.trim().length() == 0) {
					errorMsgs.put("adminMail", "帳號勿空白");
				}
				if (adminEmail == null || adminEmail.trim().length() == 0) {
					errorMsgs.put("adminEmail", "信箱勿空白");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/updateAdmin.jsp");
					failureView.forward(req, res);
					return;
				}

				AdminVO adminVO = new AdminVO();
				adminVO.setAdminID(adminID);
				adminVO.setAdminName(adminName);
				adminVO.setAdminMail(adminMail);
				adminVO.setAdminPW(adminPW);
				adminVO.setAdminEmail(adminEmail);
				adminVO.setAdminDate(nowTimestamp());
				adminVO.setAdminStatus(1);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adminVO", adminVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/updateAdmin.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				AdminService adminSvc = new AdminService();
				adminSvc.updateAdmin(adminVO);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				String url = "/back-end/index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.put("修改資料失敗:" , e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/updateAdmin.jsp");
				failureView.forward(req, res);
			}
		}

		if ("logout".equals(action)) {
			HttpSession session = req.getSession();
			session.removeAttribute("adminVO");
			session.removeAttribute("admPrilList");
			res.sendRedirect(req.getContextPath() + "/back-end/backLogin.jsp");
		}
		
		
		if ("delete".equals(action)) { // 來自admin.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer adminID = new Integer(req.getParameter("adminID"));

				/*************************** 2.開始刪除資料 ***************************************/
				AdminService adminSvc = new AdminService();
				adminSvc.deleteAdmin(adminID);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/back-end/admin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin.jsp");
				failureView.forward(req, res);
			}
		}
		if ("ststus1".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer adminID = new Integer(req.getParameter("adminID"));
				// 開始查詢

				AdminService adminSvc = new AdminService();
				adminSvc.status1(adminID);

				// 查詢完成,轉交

				String url = "/back-end/admin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/admin.jsp");
				failureView.forward(req, res);
			}
		}
	}

	// ------------------timestsmp--------------------

	// 取得現在的Timestamp時間
	public static Timestamp nowTimestamp() {
		java.util.Date utildate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
		java.sql.Time sTime = new java.sql.Time(utildate.getTime());
		java.sql.Timestamp stp = new java.sql.Timestamp(utildate.getTime());
		return stp;
	}
	
	//加密
	public static String encrypt(String s){   
		  MessageDigest sha = null;
		  
		  try{
		   sha = MessageDigest.getInstance("SHA-256");   
		   sha.update(s.getBytes());   
		  }catch(Exception e){
		   e.printStackTrace();
		   return "";
		  }

		  return byte2hex(sha.digest());   
		  
		 }
	//hash
	private static String byte2hex(byte[] b){
	     String hs="";
	     String stmp="";
	     for (int n=0;n<b.length;n++){
	      stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
	      if (stmp.length()==1) hs=hs+"0"+stmp;
	      else hs=hs+stmp;
	     }
	     return hs.toUpperCase();
	    }
}
