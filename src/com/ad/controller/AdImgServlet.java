package com.ad.controller;


import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;


import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.member.model.*;

/**
 * Servlet implementation class AvatarServlet
 */
public class AdImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	    req.setCharacterEncoding("UTF-8");
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();

			try {
				Statement stmt = con.createStatement();
	      String adID = req.getParameter("adID");
	      String adID2 = new String(adID.getBytes("ISO-8859-1"),"UTF-8");
//	      Integer memID3 = Integer.parseInt(req.getParameter("memID"));
//	      MemberService memSvc = new MemberService();
//	      ResultSet rs = memSvc.getAvatar(memID3);
				ResultSet rs = stmt.executeQuery(
					"SELECT adImg FROM ad WHERE adID='"+adID+"'");

				if (rs.next()) {
//					System.out.println("true");
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("adImg"));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
					//res.sendError(HttpServletResponse.SC_NOT_FOUND);
					InputStream in = getServletContext().getResourceAsStream("/HTML/src/wtf.jpg");
					byte[] buf = new byte[in.available()];
					in.read(buf);
					out.write(buf);
					in.close();
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
				//System.out.println(e);
				InputStream in = getServletContext().getResourceAsStream("/HTML/src/wtf.jpg");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
	}

	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
			con = (Connection) ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
