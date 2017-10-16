package com.act.act.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.act.act.model.*;
import com.act.actMem.model.*;
import com.act.actPOI.model.*;
import com.gen.tool.*;
import com.member.model.MemberVO;
import com.poi.model.POIVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ActServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// @@@@@@@@@@@@@@@
		// INSERT @@@@@@@@@
		// @@@@@@@@@@@@@@@

		if ("insert".equals(action)) { // 來自actStart.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			byte[] actIMG = null;
			Integer actType = 1;
			HttpSession session = req.getSession();
			MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
			Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
			Timestamp actCreateDate = tools.strToTimestamp(req.getParameter("actCreateDate"));
			String actName = req.getParameter("actName");
			Integer actStatus = 1;
			System.out.println("req.getParameter('actStartDate')=" + req.getParameter("actStartDate"));
			System.out.println(tools.strHTML5ToTimestamp(req.getParameter("actEndDate").replace("T", " ")));

			Timestamp actStartDate = tools.strHTML5ToTimestamp(req.getParameter("actStartDate").replace("T", " "));
			Timestamp actEndDate = tools.strHTML5ToTimestamp(req.getParameter("actEndDate").replace("T", " "));
			System.out.println("actStartDate=" + actStartDate);
			InputStream in = req.getPart("actIMG").getInputStream();
			actIMG = new byte[in.available()];
			in.read(actIMG);
			in.close();

			String actContent = req.getParameter("actContent");
			Integer actIsHot = 0;
			Double actLong = Double.parseDouble(req.getParameter("lng"));
			Double actLat = Double.parseDouble(req.getParameter("lat"));
			Integer actPost = Integer.parseInt(req.getParameter("postal_code"));
			String actLocName = req.getParameter("name");
			String actAdr = req.getParameter("formatted_address");

			 String[] values=req.getParameter("pois").split(", ");
			 List<String> hs = new ArrayList<String>(Arrays.asList(values));
				 Set<Integer> poiincome= new HashSet<>();
				 for(int i=0; i<hs.size();i++) {
				 poiincome.add(Integer.parseInt(hs.get(i)));
			 }

			// 為了回傳用的
			ActVO actVO = new ActVO();

			actVO.setActType(actType);
			actVO.setMemID(memID);
			actVO.setActCreateDate(actCreateDate);
			actVO.setActName(actName);
			actVO.setActStatus(actStatus);
			actVO.setActStartDate(actStartDate);
			actVO.setActEndDate(actEndDate);
			actVO.setActIMG(actIMG);
			actVO.setActContent(actContent);
			actVO.setActIsHot(actIsHot);
			actVO.setActLong(actLong);
			actVO.setActLat(actLat);
			actVO.setActPost(actPost);
			actVO.setActLocName(actLocName);
			actVO.setActAdr(actAdr);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/act/actStart.jsp");
				failureView.forward(req, res);
				return;
			}
			System.out.println("insert act start...");
			/*************************** 2.開始新增資料 ***************************************/
			Act_Service actSrv = new Act_Service();
			Integer actIDNo = actSrv.insert(actVO);
			System.out.println("update: "+actIDNo+" finished");
			ActMemService ams=new ActMemService();
			ams.insert(actIDNo, memID, 1);
			ActPOIService apS=new ActPOIService();
				for(Integer POIID: poiincome){
					apS.insert(actIDNo, POIID);
					System.out.println("POI insert: "+POIID+" done.");
				}
			
			/***************************
			 * 3.新增完成,準備轉交(Send the Success view)
			 ***********/
			ActFVO actfVO=actSrv.getOne(actIDNo);

			req.setAttribute("actfVO", actfVO); // 資料庫取出的act_VO物件,存入req
			req.setAttribute("memNow", 1); // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@

			String url = "/front-end/act/actItem.jsp?actID="+actIDNo;

//			String url = "/front-end/act/act.do?action=showOne&actID=" + actIDNo;
			System.out.println(url);
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
			// } catch (Exception e) {
			// errorMsgs.put("Exception",e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/emp/addEmp.jsp");
			// failureView.forward(req, res);
			// }
		}

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		// @@@@@@@@@@@@@@@@ ShowOne @@@@@@@@@@@@@@@@@@@@@
		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		if ("showOne".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			Integer actID = Integer.parseInt(req.getParameter("actID"));
			System.out.println(actID);
			/*************************** 2.開始查詢資料 *****************************************/
			Act_Service actSvc = new Act_Service();
			ActFVO actfVO = actSvc.getOne(actID);
			Integer memNow = 1; // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			if (actfVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/actListopps.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("actfVO", actfVO); // 資料庫取出的act_VO物件,存入req
			req.setAttribute("memNow", memNow); // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@

			String url = "/front-end/act/actItem.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/error2");
			// failureView.forward(req, res);
			// }
		}

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

		// @@@@@@@@@@@@@@@
		// Q WK WK WK WK @@@@@@@@@
		// @@@@@@@@@@@@@@@

		if ("QueryWks".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			/*************************** 2.開始查詢資料 *****************************************/
			Act_Service act_Svc = new Act_Service();
			List<ActFVO> list = act_Svc.getActByWks();

			Integer memNow = 1; // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			if (list.size() == 0) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/actListopps.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("actie", "QueryWks");
			req.setAttribute("list", list); // 資料庫取出的act_VO物件,存入req
			String url = "/front-end/act/actList.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/error2");
			// failureView.forward(req, res);
			// }
		}

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		if ("QueryPOI".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			Integer poiID = Integer.parseInt(req.getParameter("poiID"));
			System.out.println("get POIID=" + poiID + ", Start Query;");
			/*************************** 2.開始查詢資料 *****************************************/
			Act_Service act_Svc = new Act_Service();
			List<ActFVO> list = act_Svc.getActByPOIID(poiID);

			Integer memNow = 1; // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			if (list.size() == 0) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/actListopps.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			System.out.println("get POIID=" + poiID + ", Query end. Result:" + list.size());
			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("actie", "QueryPOI");
			req.setAttribute("poiID", poiID);
			req.setAttribute("list", list); // 資料庫取出的act_VO物件,存入req
			String url = "/front-end/act/actList.jsp?actie=QueryPOI";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/error2");
			// failureView.forward(req, res);
			// }
		}

		// @@@@@@@@@@@@ get Mine 1 2 @@@@@@@@@@@@@@
		if ("getMyAct12".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			HttpSession session = req.getSession();
			MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
			Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
			// memID= Integer.parseInt(req.getParameter("memID"));
			/*************************** 2.開始查詢資料 *****************************************/
			Act_Service act_Svc = new Act_Service();
			List<ActFVO> list = act_Svc.getMemActs12(memID);

			Integer memNow = 1; // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			if (list.size() == 0) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/actListopps.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("actie", "ohMy12");
			req.setAttribute("list", list); // 資料庫取出的act_VO物件,存入req
			String url = "/front-end/act/actList.jsp?actie=QueryPOI";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/error2");
			// failureView.forward(req, res);
			// }
		}

		// @@@@@@@@@@@@ get Mine 1 @@@@@@@@@@@@@@
		if ("getMyAct1".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			HttpSession session = req.getSession();
			MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
			Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
			// memID= Integer.parseInt(req.getParameter("memID"));
			/*************************** 2.開始查詢資料 *****************************************/
			Act_Service act_Svc = new Act_Service();
			List<ActFVO> list = act_Svc.getMemActs(memID, 1);

			Integer memNow = 1; // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			if (list.size() == 0) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/actListopps.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("actie", "ohMy1");
			req.setAttribute("list", list); // 資料庫取出的act_VO物件,存入req
			String url = "/front-end/act/actList.jsp?actie=QueryPOI";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/error2");
			// failureView.forward(req, res);
			// }
		}

		// @@@@@@@@@@@@ get Mine 2 @@@@@@@@@@@@@@
		if ("getMyAct2".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			HttpSession session = req.getSession();
			MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
			Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
			// memID= Integer.parseInt(req.getParameter("memID"));
			/*************************** 2.開始查詢資料 *****************************************/
			Act_Service act_Svc = new Act_Service();
			List<ActFVO> list = act_Svc.getMemActs(memID, 2);
			Integer memNow = 1; // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			if (list.size() == 0) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/actListopps.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("actie", "ohMy2");
			req.setAttribute("list", list); // 資料庫取出的act_VO物件,存入req

			String url = "/front-end/act/actList.jsp?actie=getMyAct2";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/error2");
			// failureView.forward(req, res);
			// }
		}

		// @@@@@@@@@@@@ get Mine 5 @@@@@@@@@@@@@@
		if ("getMyAct5".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// try {
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			HttpSession session = req.getSession();
			MemberVO memVO =	 (MemberVO)session.getAttribute("memVO");
			Integer memID = memVO.getMemID(); //@@@@@@@@@@@@@@@@@@@@@@@@
			// memID= Integer.parseInt(req.getParameter("memID"));
			/*************************** 2.開始查詢資料 *****************************************/
			Act_Service act_Svc = new Act_Service();
			List<ActFVO> list = act_Svc.getMemActs(memID, 5);

			Integer memNow = 1; // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
			if (list.size() == 0) {
				errorMsgs.add("查無資料");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/actListopps.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("actie", "ohMy2");
			req.setAttribute("list", list); // 資料庫取出的act_VO物件,存入req

			String url = "/front-end/act/actList.jsp?actie=getMyAct2";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 *************************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得資料:" + e.getMessage());
			// RequestDispatcher failureView = req
			// .getRequestDispatcher("/error2");
			// failureView.forward(req, res);
			// }
		}
	}
}