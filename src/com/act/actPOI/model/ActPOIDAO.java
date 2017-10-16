package com.act.actPOI.model;
	import java.sql.*;
	import java.util.*;
	//import com.act.model.ActVO;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;


	public class ActPOIDAO implements ActPOIDAO_interface{
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		private static final String INSERT_STMT = "insert into actPOI values (?,?)";
		private static final String DELETE_STMT = "DELETE FROM actPOI where actID = ?";
		private static final String SELECT_POI_BY_ACTID_STMT = "select * from actPOI where actID=?";


		
		@Override
		public List<Integer> getPOIByActID(Integer actID) {
			List<Integer> POIlist = new ArrayList<Integer>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(SELECT_POI_BY_ACTID_STMT);
				pstmt.setInt(1, actID);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					POIlist.add(rs.getInt("POIID"));
				}

				// Handle any SQL errors
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
			return POIlist;
		}
		
		@Override
		public void insert(Integer actID, Integer POIID) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, actID);
				pstmt.setInt(2, POIID);

				pstmt.executeUpdate();

				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " 	+ se.getMessage());
				// Clean up JDBC resources
			} finally {
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
			
		}
		
		@Override
		public void delete(Integer actID) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setInt(1, actID);

				pstmt.executeUpdate();
				System.out.println("actPOI delete compelete");
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " 	+ se.getMessage());
				// Clean up JDBC resources
			} finally {
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
		}
		

	
}
