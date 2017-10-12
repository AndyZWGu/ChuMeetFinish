package com.Img.controller;
import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;



public class ClubAlbumImg extends HttpServlet{

			Connection con;

			public void doGet(HttpServletRequest req, HttpServletResponse res)
					throws ServletException, IOException {
				
				req.setCharacterEncoding("UTF-8");
				res.setContentType("image/gif");
				ServletOutputStream out = res.getOutputStream();

				try {
					Statement stmt = con.createStatement();
					String clubImgID = req.getParameter("clubImgID");
					
					ResultSet rs = stmt.executeQuery(
						"SELECT clubImg FROM clubImg WHERE clubImgID='"+clubImgID+"'");
					
					if (rs.next()) {
						BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("clubImg"));
						byte[] buf = new byte[4 * 1024]; // 4K buffer
						int len;
						
						while ((len = in.read(buf)) != -1) {
							out.write(buf, 0, len);//寫出
						}
						in.close();
					} else {
						//res.sendError(HttpServletResponse.SC_NOT_FOUND);
						InputStream in = getServletContext().getResourceAsStream("/Img/bear.jpg");
						byte[] buf = new byte[in.available()];
						in.read(buf);
						out.write(buf);
						in.close();
					}
					rs.close();
					stmt.close();
				} catch (Exception e) {
					//System.out.println(e);
					InputStream in = getServletContext().getResourceAsStream("/Img/bear.jpg");
					byte[] buf = new byte[in.available()];
					in.read(buf);
					out.write(buf);
					in.close();
				}
			}

			public void init() throws ServletException {
				try {
					Context ctx = new javax.naming.InitialContext();
					DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
					con = ds.getConnection();
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
	
