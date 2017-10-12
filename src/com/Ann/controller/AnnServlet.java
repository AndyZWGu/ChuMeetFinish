package com.Ann.controller;

import java.io.IOException;
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
	
	
	   if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
				Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);

				try {
					/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
					String adminID = req.getParameter("adminID");
					String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					
					if (adminID == null || adminID.trim().length() == 0) {
						errorMsgs.put("adminID","管理員編號: 請勿空白");
					} else if(!adminID.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
						errorMsgs.put("adminID","管理員編號: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		            }
					
					String annName = req.getParameter("annName").trim();
					if (annName == null || annName.trim().length() == 0) {
						errorMsgs.put("annName","職位請勿空白");
					}
					
					java.sql.Date hiredate = null;
					try {
						hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
					} catch (IllegalArgumentException e) {
						errorMsgs.put("hiredate","請輸入日期");
					}
					
					Double sal = null;
					try {
						sal = new Double(req.getParameter("sal").trim());
					} catch (NumberFormatException e) {
						errorMsgs.put("sal","薪水請填數字");
					}
					
					Double comm = null;
					try {
						comm = new Double(req.getParameter("comm").trim());
					} catch (NumberFormatException e) {
						errorMsgs.put("comm","獎金請填數字");
					}
					
					Integer deptno = new Integer(req.getParameter("deptno").trim());

					// Send the use back to the form, if there were errors
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("/emp/addEmp.jsp");
						failureView.forward(req, res);
						return;
					}
					
					/***************************2.開始新增資料***************************************/
					//EmpService empSvc = new EmpService();
					//empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);
					
					/***************************3.新增完成,準備轉交(Send the Success view)***********/
					String url = "/emp/listAllEmp.jsp";
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

}
}