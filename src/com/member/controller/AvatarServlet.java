package com.member.controller;


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
public class AvatarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvatarServlet() {
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
	      String memID = req.getParameter("memID");
	      String memID2 = new String(memID.getBytes("ISO-8859-1"),"UTF-8");
//	      Integer memID3 = Integer.parseInt(req.getParameter("memID"));
//	      MemberService memSvc = new MemberService();
//	      ResultSet rs = memSvc.getAvatar(memID3);
				ResultSet rs = stmt.executeQuery(
					"SELECT memAvatar FROM member WHERE memID='"+memID2+"'");

				if (rs.next()) {
//					System.out.println("true");
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("memAvatar"));
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
