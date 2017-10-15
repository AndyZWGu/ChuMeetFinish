package com.act.actPOI.model;
	import java.sql.*;
	import java.util.*;
	//import com.act.model.ActVO;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;

import com.act.act.model.Act_VO;

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
		private static final String SELECT_POI_BY_ACTID_STMT = "select POINAMEC from actPOI a join POI p on a.POIID=p.POIID where a.actID=?";
		private static final String SELECT_ACT_BY_POIID_STMT = "select act.actID as actID, act.actPOST as actPOST, act.actContent as actContent, act.actStartDate as actStartDate from actPOI join act on actPOI.ACTID=act.ACTID where actPOI.POIID=? and actStatus";

		
		@Override
		public List<String> getPOIByActID(Integer actID) {
			List<String> POIlist = new ArrayList<String>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(SELECT_POI_BY_ACTID_STMT);
				pstmt.setInt(1, actID);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					POIlist.add(rs.getString("POINAMEC"));
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
		public List<Act_VO> getActByPOIID(Integer POIID) {
			List<Act_VO> list = new ArrayList<Act_VO>();
			Act_VO Act_VO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(SELECT_ACT_BY_POIID_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					Act_VO = new Act_VO();
					Act_VO.setActID(rs.getInt("actID"));
					Act_VO.setMemID(rs.getInt("memID"));
					Act_VO.setActCreateDate(rs.getTimestamp("actCreateDate"));
					Act_VO.setActName(rs.getString("actName"));
					Act_VO.setActStatus(rs.getInt("actStatus"));
					Act_VO.setActPriID(rs.getInt("actPriID"));
					Act_VO.setActStartDate(rs.getTimestamp("actStartDate"));
					Act_VO.setActEndDate(rs.getTimestamp("actEndDate"));
					Act_VO.setActSignStartDate(rs.getTimestamp("actSignStartDate"));
					Act_VO.setActSignEndDate(rs.getTimestamp("actSignEndDate"));
					Act_VO.setActTimeTypeID(rs.getInt("actTimeTypeID"));
					Act_VO.setActTimeTypeCnt(rs.getString("actTimeTypeCnt"));
					Act_VO.setActMemMax(rs.getInt("actMemMax"));
					Act_VO.setActMemMin(rs.getInt("actMemMin"));
					Act_VO.setActIMG(rs.getBytes("actIMG"));
					Act_VO.setActContent(rs.getString("actContent"));
					Act_VO.setActIsHot(rs.getInt("actIsHot"));
					Act_VO.setActLong(rs.getDouble("actLong"));
					Act_VO.setActLat(rs.getDouble("actLat"));
					Act_VO.setActPost(rs.getInt("actPost"));
					Act_VO.setActLocName(rs.getString("actLocName"));
					Act_VO.setActAdr(rs.getString("actAdr"));
				};

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
			return list;
		}
		
		
		@Override
		public void insert(ActPOIVO actPOIVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				//pstmt.setInt(1, actPOIVO.getActID());
				//pstmt.setInt(2, actPOIVO.getPOIID());

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
		
		

		@Override
		public List<ActPOIVO> mutate(Integer actID, List<Integer> poilist) {
			// TODO Auto-generated method stub
			List<ActPOIVO> list = new ArrayList<ActPOIVO>();
			ActPOIVO actPOIVO = null;

			Connection con = null;
			try {
				con.setAutoCommit(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_STMT);
				pstmt.setInt(1, actID);
				pstmt.executeUpdate();
				
				pstmt = con.prepareStatement(INSERT_STMT);
				//TODO size()
				
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
			return list;
		}	

	
}
