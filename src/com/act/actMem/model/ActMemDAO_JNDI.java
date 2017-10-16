package com.act.actMem.model;

import java.sql.*;
import java.util.*;
	//import com.act.model.ActVO;
	import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;
	import com.act.actMem.model.AmFaceVO;


	public class ActMemDAO_JNDI implements ActMem_Interface{
		private static DataSource ds = null;
		static {
			try {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}



		private static final String ACTMEM_INSERT ="insert into actmem "
				+ "(actID, memID, actMemStatus, actJoinDate) "
				+ " values (?,?,?,sysTimeStamp)";
		
		private static final String ACTMEM_DELETE ="DELETE FROM actmem where actID=? and memID=?";
		private static final String WHOSIN ="select MEMBER.MEMID as memID, MEMBER.MEMNAME as memName from ACTMEM inner join MEMBER on ACTMEM.MEMID=MEMBER.MEMID where ACTMEM.actid=? and ACTMEM.actmemStatus in (1,2)";
		private static final String WHOST ="select MEMBER.MEMID as memID, MEMBER.MEMNAME as memName from ACTMEM inner join MEMBER on ACTMEM.MEMID=MEMBER.MEMID where ACTMEM.actid=? and ACTMEM.actmemStatus=5";
		
		@Override
		public List<AmFaceVO> whosIn(Integer actID){
		List<AmFaceVO> list=new ArrayList<AmFaceVO>(); 
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(WHOSIN);

				pstmt.setInt(1, actID);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					AmFaceVO amf= new AmFaceVO();
						amf.setMemName(rs.getString("memName"));
						amf.setMemID(rs.getInt("memID"));
						list.add(amf);
						System.out.println("list.size@actMem="+list.size());
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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
		public List<AmFaceVO> whosT(Integer actID){
		List<AmFaceVO> list=new ArrayList<AmFaceVO>(); 
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(WHOST);

				pstmt.setInt(1, actID);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					AmFaceVO amf= new AmFaceVO();
						amf.setMemName(rs.getString("memName"));
						amf.setMemID(rs.getInt("memID"));
						list.add(amf);
						System.out.println("list.size@actMem="+list.size());
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
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
		public void delete(Integer actID, Integer memID) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(ACTMEM_DELETE);
				pstmt.setInt(1, actID);
				pstmt.setInt(2, memID);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
public void insert(Integer actID, Integer memID, Integer stat) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {
				System.out.println("一筆actmem資料準備開始："+actID+", "+memID+", "+stat);
				con = ds.getConnection();
				pstmt = con.prepareStatement(ACTMEM_INSERT);
					pstmt.setInt(1, actID);
					pstmt.setInt(2, memID);
					pstmt.setInt(3, stat);
				pstmt.executeUpdate();
System.out.println("一筆actmem資料更新成功："+actID+", "+memID+", "+stat);
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "+ se.getMessage());
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
