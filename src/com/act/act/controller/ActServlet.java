package com.act.act.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.act.act.model.*;
import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.*;
import com.gen.tool.*;
import com.member.model.MemberHVO;
import com.poi.model.POIVO;



public class ActServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//@@@@@@@@@@@@@@@
		//INSERT            @@@@@@@@@
		//@@@@@@@@@@@@@@@
		
        if ("insert".equals(action)) { // 來自actStart.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer actType = Integer.parseInt(req.getParameter("actType"));
				Integer actID = Integer.parseInt(req.getParameter("actID"));
				Integer memID = Integer.parseInt(req.getParameter("memID"));
				Timestamp actCreateDate = tools.strToTimestamp(req.getParameter("actCreateDate"));
				String actName = req.getParameter("actName");
				Integer actStatus = Integer.parseInt(req.getParameter("actStatus"));
				Integer actPriID = Integer.parseInt(req.getParameter("actPriID"));
				Timestamp actStartDate = tools.strToTimestamp(req.getParameter("actStartDate"));
				Timestamp actEndDate = tools.strToTimestamp(req.getParameter("actEndDate"));
				Timestamp actSignStartDate = tools.strToTimestamp(req.getParameter("actSignStartDate"));
				Timestamp actSignEndDate = tools.strToTimestamp(req.getParameter("actSignEndDate"));
				Integer actTimeTypeID = Integer.parseInt(req.getParameter("actTimeTypeID"));
				String actTimeTypeCnt = req.getParameter("actTimeTypeCnt");
				Integer actMemMax = Integer.parseInt(req.getParameter("actMemMax"));
				Integer actMemMin = Integer.parseInt(req.getParameter("actMemMin"));
				byte[] actIMG = req.getParameter("actImg").getBytes();
				String actContent = req.getParameter("actContent");
				Integer actIsHot = Integer.parseInt(req.getParameter("actIsHot"));
				Double actLong = Double.parseDouble(req.getParameter("actLong"));
				Double actLat = Double.parseDouble(req.getParameter("actLat"));
				Integer actPost = Integer.parseInt(req.getParameter("actPost"));
				String actLocName = req.getParameter("actLocName");
				String actAdr = req.getParameter("actAdr");
				String actUID = req.getParameter("actUID");
				String actShowUni = req.getParameter("actShowUni");
				String actMasterUnit = req.getParameter("actMasterUnit");
				String actWebSales = req.getParameter("actWebSales");
				String actSourceWebName = req.getParameter("actSourceWebName");
				String actOnSale = req.getParameter("actOnSale");
				String actPrice = req.getParameter("actPrice");

				String[] values=req.getParameter("pois").split(", ");
				Set<String> hs = new HashSet<String>(Arrays.asList(values));	
				Set<Integer> poiincome= new HashSet<>(hs.size());
				hs.forEach(i -> poiincome.add(Integer.parseInt(i)));

//為了回傳用的
				Act_VO actVO = new Act_VO();
				
				Set<ActMemVO> amset = new HashSet<ActMemVO>();
				Set<ActPOIVO> apset = new HashSet<ActPOIVO>();
				
				ActMemVO amVO=new ActMemVO();
						MemberHVO mvo=new MemberHVO();
						mvo.setMemID(memID);
					amVO.setMemberHVO(mvo);
					amVO.setActJoinDate(tools.nowTimestamp());
					amVO.setActMemStatus(1);

				for (Integer poiID:poiincome){
					ActPOIVO apVO=new ActPOIVO();  
					POIVO pv=new POIVO();
					pv.setPOIID(poiID);
					apVO.setPOIVO(pv);
					apset.add(apVO);
				}
				
				actVO.setActPOIs(apset);
				actVO.setActMems(amset);
				actVO.setActType(actType);
				actVO.setActID(actID);
				actVO.setMemID(memID);
				actVO.setActCreateDate(actCreateDate);
				actVO.setActName(actName);
				actVO.setActStatus(actStatus);
				actVO.setActPriID(actPriID);
				actVO.setActStartDate(actStartDate);
				actVO.setActEndDate(actEndDate);
				actVO.setActSignStartDate(actSignStartDate);
				actVO.setActSignEndDate(actSignEndDate);
				actVO.setActTimeTypeID(actTimeTypeID);
				actVO.setActTimeTypeCnt(actTimeTypeCnt);
				actVO.setActMemMax(actMemMax);
				actVO.setActMemMin(actMemMin);
				actVO.setActIMG(actIMG);
				actVO.setActContent(actContent);
				actVO.setActIsHot(actIsHot);
				actVO.setActLong(actLong);
				actVO.setActLat(actLat);
				actVO.setActPost(actPost);
				actVO.setActLocName(actLocName);
				actVO.setActAdr(actAdr);
				actVO.setActUID(actUID);
				actVO.setActShowUnit(actShowUni);
				actVO.setActMasterUnit(actMasterUnit);
				actVO.setActWebSales(actWebSales);
				actVO.setActSourceWebName(actSourceWebName);
				actVO.setActOnSale(actOnSale);
				actVO.setActPrice(actPrice);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/act/actStart.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Act_Service actSrv = new Act_Service();
				Integer actIDNo=actSrv.insert(actVO);
				
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				
				String url = "";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/emp/addEmp.jsp");
				failureView.forward(req, res);
			}
		}
        
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        //@@@@@@@@@@@@@@@@					ShowOne				@@@@@@@@@@@@@@@@@@@@@
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        
		if ("showOne".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer actID=Integer.parseInt(req.getParameter("actID"));
				System.out.println(actID);
				/***************************2.開始查詢資料*****************************************/
				Act_Service actSvc = new Act_Service();
				ActFiestaVO actfVO = actSvc.getOne(actID);
				Integer memNow=1;						 // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				if (actfVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/error");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actfVO", actfVO); // 資料庫取出的act_VO物件,存入req
				req.setAttribute("memNow", memNow); // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@

				String url = "/front-end/act/actItem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/error2");
//				failureView.forward(req, res);
//			}
		}
		
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        
		//@@@@@@@@@@@@@@@
		//Q WK WK WK WK           @@@@@@@@@
		//@@@@@@@@@@@@@@@
		
        
		if ("QueryWks".equals(action)) { // 來自actListjsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				/***************************2.開始查詢資料*****************************************/
				Act_Service act_Svc = new Act_Service();
				List<ActFiestaVO> actfVOs = act_Svc.getActByWks();

				Integer memNow=1;						 // @@@@@@@@@@@@@@@@@@@@@@@@@@@@@
				if (actfVOs.size() == 0) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/error");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actie", "QueryWks");
				req.setAttribute("actfVOs", actfVOs); // 資料庫取出的act_VO物件,存入req
				String url = "/front-end/act/actList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/error2");
//				failureView.forward(req, res);
//			}
		}
		
        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
	}
}