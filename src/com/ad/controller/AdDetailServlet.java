package com.ad.controller;

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

import com.ad.model.*;

/**
 * Servlet implementation class LoginServlet
 */
public class AdDetailServlet extends HttpServlet {
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdDetailServlet() {
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
		System.out.println("這裡是廣告");

		AdService adSvc = new AdService();
		if(req.getParameter("adID")!=null){
			Integer adID = Integer.parseInt(req.getParameter("adID"));
			AdVO adVO = (AdVO)adSvc.getOneAd(adID);
			req.setAttribute("adVO",adVO);
		} else {
			Integer adID = 1;
			AdVO adVO = (AdVO)adSvc.getOneAd(adID);
			req.setAttribute("adVO",adVO);
			
		}
			// 杞夊潃鐢�
			String url = "/front-end/info/ad.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;

	}
	
	/*************************** 鍏朵粬宸ュ叿鏂规硶 *************************************/
	// 鍙栧緱鐝惧湪鏅傞枔(java.sql.Date鍨嬫厠)
	public static Timestamp nowTimestamp() {
		java.util.Date utildate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utildate.getTime());
		java.sql.Time sTime = new java.sql.Time(utildate.getTime());
		java.sql.Timestamp stp = new java.sql.Timestamp(utildate.getTime());
		return stp;
	}

}
