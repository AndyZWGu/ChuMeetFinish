package com.clubTicket.model;

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

public class ClubTicketDAO implements ClubTicketDAO_interface{
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
			"INSERT INTO clubTicket  VALUES (clubTicket_seq.NEXTVAL, ?, ?, ?, ?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT clubTkID,reporter,clubID,clubTkMsg,clubTkStatID,to_char(clubTkDate,'yyyy-mm-dd hh:mm:ss')clubTkDate,clubTkCat FROM clubTicket order by clubTkID";
		private static final String GET_ONE_STMT = 
			"SELECT clubTkID,reporter,clubID,clubTkMsg,clubTkStatID,to_char(clubTkDate,'yyyy-mm-dd hh:mm:ss')clubTkDate,clubTkCat FROM clubTicket where clubTkID= ?";
		private static final String DELETE = 
			"DELETE FROM clubTicket where memTkID = ?";
		private static final String UPDATE = 
			"UPDATE clubTicket set reporter=?,clubID=?,clubTkMsg=?,clubTkStatID=?,clubTkDate=?,clubTkCat=?  where clubTkID = ?";

		
		
		@Override
		public void insert(ClubTicketVO clubTicketVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, clubTicketVO.getReporter());
				pstmt.setInt(2, clubTicketVO.getClubID());
				pstmt.setString(3, clubTicketVO.getClubTkMsg());
				pstmt.setInt(4, clubTicketVO.getClubTkStatID());
				pstmt.setTimestamp(5, clubTicketVO.getClubTkDate());
				pstmt.setInt(6, clubTicketVO.getClubTkCat());
				
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
		public void update(ClubTicketVO clubTicketVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
		
				pstmt.setInt(1, clubTicketVO.getReporter());
				pstmt.setInt(2, clubTicketVO.getClubID());
				pstmt.setString(3, clubTicketVO.getClubTkMsg());
				pstmt.setInt(4, clubTicketVO.getClubTkStatID());
				pstmt.setTimestamp(5, clubTicketVO.getClubTkDate());
				pstmt.setInt(6, clubTicketVO.getClubTkCat());
				pstmt.setInt(7, clubTicketVO.getClubTkID());
		//		"UPDATE memTicket set reporter=?,memTkMsg=?,memTkStatID=?,memTkDate=?,memTkCat=?  where memTkID = 

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
		public void delete(Integer clubTkID) {
			// TODO Auto-generated method stub
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, clubTkID);

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
		public ClubTicketVO findByPrimaryKey(Integer clubTkID) {
			// TODO Auto-generated method stub
			ClubTicketVO clubTicketVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, clubTkID);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// memTicketVO �]�٬� Domain objects
					clubTicketVO = new ClubTicketVO();
					clubTicketVO.setClubTkID(rs.getInt("clubTkID"));			
					clubTicketVO.setReporter(rs.getInt("reporter"));
					clubTicketVO.setClubID(rs.getInt("clubID"));
					clubTicketVO.setClubTkMsg(rs.getString("clubTkMsg"));
					clubTicketVO.setClubTkStatID(rs.getInt("clubTkStatID"));
					clubTicketVO.setClubTkDate(rs.getTimestamp("clubTkDate"));
					clubTicketVO.setClubTkCat(rs.getInt("clubTkCat"));
					
				}

				// Handle any driver errors
			}  catch (SQLException se) {
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
			return clubTicketVO;
		}
		@Override
		public List<ClubTicketVO> getAll() {
			// TODO Auto-generated method stub
			List<ClubTicketVO> list = new ArrayList<ClubTicketVO>();
			ClubTicketVO clubTicketVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					clubTicketVO = new ClubTicketVO();
					clubTicketVO.setClubTkID(rs.getInt("clubTkID"));
					clubTicketVO.setReporter(rs.getInt("reporter"));
					clubTicketVO.setClubID(rs.getInt("clubID"));
					clubTicketVO.setClubTkMsg(rs.getString("clubTkMsg"));
					clubTicketVO.setClubTkStatID(rs.getInt("clubTkStatID"));
					clubTicketVO.setClubTkDate(rs.getTimestamp("clubTkDate"));
					clubTicketVO.setClubTkCat(rs.getInt("clubTkCat"));
			
					list.add(clubTicketVO); // Store the row in the list
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

		public static void main(String[] args) {

			ClubTicketJDBCDAO dao = new ClubTicketJDBCDAO();
			
//			// �s�W
			ClubTicketVO clubTicketVO1 = new ClubTicketVO();
			clubTicketVO1.setClubTkID(3);
			clubTicketVO1.setReporter(2);
			clubTicketVO1.setClubID(2);
			clubTicketVO1.setClubTkMsg("MANAGER");
			clubTicketVO1.setClubTkStatID(1);
			clubTicketVO1.setClubTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
			clubTicketVO1.setClubTkCat(1);
			dao.insert(clubTicketVO1);
		
//			// �ק�
//			ClubTicketVO clubTicketVO2 = new ClubTicketVO();
//			clubTicketVO2.setClubTkID(3);
//			clubTicketVO2.setReporter(2);
//			clubTicketVO2.setClubID(2);
//			clubTicketVO2.setClubTkMsg("MANAGER");
//			clubTicketVO2.setClubTkStatID(1);
//			clubTicketVO2.setClubTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
//			clubTicketVO2.setClubTkCat(1);
//			
//			
//			dao.update(clubTicketVO2);
			// �R��
			dao.delete(1);
////			 
////			// �d��
//			ClubTicketVO clubTicketVO3 = dao.findByPrimaryKey(1);
//			System.out.print(clubTicketVO3.getClubTkID() + ",");
//			System.out.print(clubTicketVO3.getReporter() + ",");
//			System.out.print(clubTicketVO3.getClubID() + ",");
//			System.out.print(clubTicketVO3.getClubTkMsg() + ",");
//			System.out.print(clubTicketVO3.getClubTkStatID() + ",");
//			System.out.print(clubTicketVO3.getClubTkDate() + ",");
//			System.out.print(clubTicketVO3.getClubTkCat() + ",");
//			System.out.println("---------------------");

					// �d��
			List<ClubTicketVO> list = dao.getAll();
			for (ClubTicketVO aClubTicket : list) {
				System.out.print(aClubTicket.getClubTkID() + ",");
				System.out.print(aClubTicket.getReporter() + ",");
				System.out.print(aClubTicket.getClubID() + ",");
				System.out.print(aClubTicket.getClubTkMsg() + ",");
				System.out.print(aClubTicket.getClubTkStatID() + ",");
				System.out.print(aClubTicket.getClubTkDate() + ",");
				System.out.print(aClubTicket.getClubTkCat() + ",");
				System.out.println();
			}
		}
		}
		
		



