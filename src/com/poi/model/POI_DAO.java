package com.poi.model;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.act.act.model.Act_interface;
import com.act.actPOI.model.ActPOIVO;
import com.gen.tool.tools;
public class POI_DAO implements POI_interface{
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

	private static final String GET_ALL="select * from POI";

		@Override
		public List<POIVO> getAll() {
			List<POIVO> list = new ArrayList<POIVO>();

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					POIVO pVO= new POIVO();
					System.out.println("rs.getInt(POIID)="+rs.getInt("POIID"));
					pVO.setPOIID(rs.getInt("POIID"));
					pVO.setPOINameC(rs.getString("POINAMEC"));
					list.add(pVO);
				};

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}

			return list;
		}

	

	}

