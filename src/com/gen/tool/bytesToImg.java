package com.gen.tool;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

public class bytesToImg extends HttpServlet {

		Connection con;

		public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
			
			req.setCharacterEncoding("UTF-8");
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();

			try {
				Statement stmt = con.createStatement();
				String colName=req.getParameter("colName");				
				String table=req.getParameter("table");
				String pk=req.getParameter("pk");
				String hello=req.getParameter("imgFrom");
				
				String stat="SELECT "+colName+" FROM " +table +" WHERE "+pk+"="+hello;
//				System.out.println(stat);
				
				ResultSet rs = stmt.executeQuery(stat);

				

				if (rs.next()) {
					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream(colName));
					byte[] buf = new byte[4 * 1024]; // 4K buffer
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
					in.close();
				} else {
//					res.sendError(HttpServletResponse.SC_NOT_FOUND);
					InputStream in=getServletContext().getResourceAsStream("NoData/nopic.jpg");
					byte[] buffer=new byte[in.available()];
					in.read(buffer);
					out.write(buffer);
					in.close();
				}
				rs.close();
				stmt.close();
			} catch (Exception e) {
					InputStream in=getServletContext().getResourceAsStream("NoData/nopic.jpg");
					byte[] buffer=new byte[in.available()];
					in.read(buffer);
					out.write(buffer);
					in.close();
					
			}
		}

		public void init() throws ServletException {
			try {
				try {
					Context ctx= new javax.naming.InitialContext();
					DataSource ds=(DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
					con=ds.getConnection();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				throw new UnavailableException("Couldn't get db connection");
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
