package com.Ann.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Ann.model.AnnService;
import com.admPril.model.AdmPrilService;
import com.admin.controller.AdminMailService;
import com.admin.model.AdminService;
import com.admin.model.AdminVO;


public class AnnServlet  extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);	
}

public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	req.setCharacterEncoding("UTF-8");
	String action = req.getParameter("action");
	System.out.println(req.getParameter("adminID"));
	
	   if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				try {
					/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
					Integer adminID = new Integer(req.getParameter("adminID"));
	System.out.println(adminID);
					String annName = req.getParameter("annName");
					String annNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					
				if (annName == null || annName.trim().length() == 0) {
						errorMsgs.put("annName","公告標題");
					} else if(!annName.trim().matches(annNameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
						errorMsgs.put("annName",",");
		            }
	System.out.println(annName);
				
					String annContent = req.getParameter("annContent").trim();
					if (annContent == null || annContent.trim().length() == 0) {
						errorMsgs.put("annContent","請輸入內容");
					}					
	System.out.println(annContent);

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/back-end/adminAnn.jsp");
						failureView.forward(req, res);
						return;
					}
					System.out.println(nowTimestamp());
					/***************************2.�}�l�s�W���***************************************/
					AnnService annSvc = new AnnService();
					annSvc.addAnn(adminID, annName, annContent, nowTimestamp());
					
					/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
					String url = "/back-end/adminAnn.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
					successView.forward(req, res);				
					
					/***************************��L�i�઺���~�B�z**********************************/
				} catch (Exception e) {
					errorMsgs.put("Exception",e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adminAnn.jsp");
					failureView.forward(req, res);
				}
			}
	   
	   //刪除
	   
	   if ("delete".equals(action)) { // 來自admin.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer annID = new Integer(req.getParameter("annID"));

				/*************************** 2.開始刪除資料 ***************************************/
				AnnService annSvc = new AnnService();
				annSvc.deleteAnn(annID);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/back-end/adminAnn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adminAnn.jsp");
				failureView.forward(req, res);
			}
	   }
}

//------------------timestsmp--------------------

	// ���o�{�b��Timestamp�ɶ�
	public static Timestamp nowTimestamp() {
		java.util.Date utildate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
		java.sql.Time sTime = new java.sql.Time(utildate.getTime());
		java.sql.Timestamp stp = new java.sql.Timestamp(utildate.getTime());
		return stp;
	}
	

}