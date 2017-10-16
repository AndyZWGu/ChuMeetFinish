package com.memTicket.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class MemTicketJDBCDAO implements MemTicketDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G2";
	String passwd = "a123";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void update(MemTicketVO memTicketVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
	public void delete(Integer memTkID) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memTkID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

		MemTicketJDBCDAO dao = new MemTicketJDBCDAO();
		//memTkID,repopter,memTkMsg,memTkStatID,memTkDate,memTkCat
//		// �s�W
//		MemTicketVO memTicketVO1 = new MemTicketVO();
//		memTicketVO1.setMemTkID(3);
//		memTicketVO1.setReporter(2);
//		memTicketVO1.setMemID(2);
//		memTicketVO1.setMemTkMsg("MANAGER");
//		memTicketVO1.setMemTkStatID(1);
//		memTicketVO1.setMemTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
//		memTicketVO1.setMemTkCat(1);
//		dao.insert(memTicketVO1);

//		// �ק�
		MemTicketVO memTicketVO2 = new MemTicketVO();
		memTicketVO2.setMemTkID(3);
		memTicketVO2.setReporter(2);
		memTicketVO2.setMemID(2);
		memTicketVO2.setMemTkMsg("MANAGER");
		memTicketVO2.setMemTkStatID(1);
		memTicketVO2.setMemTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
		memTicketVO2.setMemTkCat(1);
		
		
		dao.update(memTicketVO2);
		// �R��
		dao.delete(1);
////		 
////		// �d��
//		MemTicketVO memTicketVO3 = dao.findByPrimaryKey(1);
//		System.out.print(memTicketVO3.getMemTkID() + ",");
//		System.out.print(memTicketVO3.getReporter() + ",");
//		System.out.print(memTicketVO3.getMemID() + ",");
//		System.out.print(memTicketVO3.getMemTkMsg() + ",");
//		System.out.print(memTicketVO3.getMemTkStatID() + ",");
//		System.out.print(memTicketVO3.getMemTkDate() + ",");
//		System.out.print(memTicketVO3.getMemTkCat() + ",");
//		System.out.println("---------------------");

		// �d��
		List<MemTicketVO> list = dao.getAll();
		for (MemTicketVO aMemTicket : list) {
			System.out.print(aMemTicket.getMemTkID() + ",");
			System.out.print(aMemTicket.getReporter() + ",");
			System.out.print(aMemTicket.getMemID() + ",");
			System.out.print(aMemTicket.getMemTkMsg() + ",");
			System.out.print(aMemTicket.getMemTkStatID() + ",");
			System.out.print(aMemTicket.getMemTkDate() + ",");
			System.out.print(aMemTicket.getMemTkCat() + ",");
			System.out.println();
		}
	}
	}