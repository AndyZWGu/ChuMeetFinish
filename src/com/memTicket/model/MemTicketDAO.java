package com.memTicket.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemTicketDAO implements MemTicketDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
	}
	private static final String INSERT_STMT = 											
			"INSERT INTO memTicket (memTkID,reporter,memID,memTkMsg,memTkStatID,memTkDate,memTkCat) VALUES (memTicket_seq.NEXTVAL, ?, ?, ?, ?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT memTkID,reporter,memID,memTkMsg,memTkStatID,to_char(memTkDate,'yyyy-mm-dd hh:mm:ss')memTkDate,memTkCat FROM memTicket order by memTkID";
		private static final String GET_ONE_STMT = 
			"SELECT memTkID,reporter,memID,memTkMsg,memTkStatID,to_char(memTkDate,'yyyy-mm-dd hh:mm:ss')memTkDate FROM memTicket where memTkID= ?";
		private static final String DELETE = 
			"DELETE FROM memTicket where memTkID = ?";
		private static final String UPDATE = 
			"UPDATE memTicket set reporter=?,memID=?,memTkMsg=?,memTkStatID=?,memTkDate=?,memTkCat=?  where memTkID = ?";

		
		
		@Override
		public void insert(MemTicketVO memTicketVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, memTicketVO.getReporter());
				pstmt.setInt(2, memTicketVO.getMemID());
				pstmt.setString(3, memTicketVO.getMemTkMsg());
				pstmt.setInt(4, memTicketVO.getMemTkStatID());
				pstmt.setTimestamp(5, memTicketVO.getMemTkDate());
				pstmt.setInt(6, memTicketVO.getMemTkCat());
				
	//"INSERT INTO memTicket (memTkID,reporter,memTkMsg,memTkStatID,memTkDate,memTkCat) VALUES (memTicket_seq.NEXTVAL, ?, ?, ?, ?,?)";
				pstmt.executeUpdate();

				// Handle any driver errors
			}  catch (SQLException se) {
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
		public void update(MemTicketVO memTicketVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
			
				pstmt.setInt(1, memTicketVO.getReporter());
				pstmt.setInt(2, memTicketVO.getMemID());
				pstmt.setString(3, memTicketVO.getMemTkMsg());
				pstmt.setInt(4, memTicketVO.getMemTkStatID());
				pstmt.setTimestamp(5, memTicketVO.getMemTkDate());
				pstmt.setInt(6, memTicketVO.getMemTkCat());
				pstmt.setInt(7, memTicketVO.getMemTkID());
		//		"UPDATE memTicket set reporter=?,memTkMsg=?,memTkStatID=?,memTkDate=?,memTkCat=?  where memTkID = 

				pstmt.executeUpdate();

				// Handle any driver errors
			}  catch (SQLException se) {
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
		public void delete(Integer memTkID) {
			// TODO Auto-generated method stub
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, memTkID);

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
		public MemTicketVO findByPrimaryKey(Integer memTkID) {
			// TODO Auto-generated method stub
			MemTicketVO memTicketVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, memTkID);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// memTicketVO �]�٬� Domain objects
					memTicketVO = new MemTicketVO();
					memTicketVO.setMemTkID(rs.getInt("memTkID"));			
					memTicketVO.setReporter(rs.getInt("reporter"));
					memTicketVO.setMemID(rs.getInt("memID"));
					memTicketVO.setMemTkMsg(rs.getString("memTkMsg"));
					memTicketVO.setMemTkStatID(rs.getInt("memTkStatID"));
					memTicketVO.setMemTkDate(rs.getTimestamp("memTkDate"));
					memTicketVO.setMemTkCat(rs.getInt("memTkCat"));
					//memTkID,reporter,memTkMsg,memTkStatID,memTkDate,memTkCat
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
			return memTicketVO;
		}
		@Override
		public List<MemTicketVO> getAll() {
			// TODO Auto-generated method stub
			List<MemTicketVO> list = new ArrayList<MemTicketVO>();
			MemTicketVO memTicketVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					memTicketVO = new MemTicketVO();
					memTicketVO.setMemTkID(rs.getInt("memTkID"));
					memTicketVO.setReporter(rs.getInt("reporter"));
					memTicketVO.setMemID(rs.getInt("memID"));
					memTicketVO.setMemTkMsg(rs.getString("memTkMsg"));
					memTicketVO.setMemTkStatID(rs.getInt("memTkStatID"));
					memTicketVO.setMemTkDate(rs.getTimestamp("memTkDate"));
					memTicketVO.setMemTkCat(rs.getInt("memTkCat"));
					
					list.add(memTicketVO); // Store the row in the list
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

}
