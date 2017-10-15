package com.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.*;

public class MemberNFSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberNFSearchServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			if (req.getParameter("memNFSearchList") == null) {
				MemNFService nfSvc = new MemNFService();
				List<MemNFVO> memNFSearchList = nfSvc.getAll();
				// Send the use back to the form, if there were errors
				req.setAttribute("memNFSearchList", memNFSearchList);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/memberNFSearch.jsp");
				failureView.forward(req, res);
				return;
			}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("memNFNameSearch".equals(action)) {
			Map<String, String[]> map = req.getParameterMap();
			HttpSession session = req.getSession();
			session.setAttribute("map", map);
			// 撈資料
			MemNFService nfSvc = new MemNFService();
			List<MemNFVO> memSearchList = nfSvc.getAllNF(map);
			// 綁
			req.removeAttribute("memNFSearchList");
			req.setAttribute("memNFSearchList", memSearchList);
			// 導回去
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/memberNFSearch.jsp");
			failureView.forward(req, res);
			return;
		}
		if ("compositeNFSearch".equals(action)) {
			Map<String, String[]> map = req.getParameterMap();
			System.out.println(map);
			HttpSession session = req.getSession();
			session.setAttribute("map", map);
			// 撈資料
			MemNFService nfSvc = new MemNFService();
			List<MemNFVO> memNFSearchList = nfSvc.getAllNF(map);
			// 綁
			req.removeAttribute("memNFSearchList");
			req.setAttribute("memNFSearchList", memNFSearchList);
			// 導回去
			RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/memberNFSearch.jsp");
			failureView.forward(req, res);
			return;
		}

	}

}
