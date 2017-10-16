package com.mbTicket.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MbTicketJDBCDAO implements MbTicketDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G2";
	String passwd = "a123";

	private static final String INSERT_STMT = 											
		"INSERT INTO mbTicket  VALUES (mbTicket_seq.NEXTVAL, ?, ?, ?, ?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT mbTkID,reporter,actMBID,clubMBID,memNFID,memMBID,MBTkMsg,MBTkStatID,to_char(MBTkDate,'yyyy-mm-dd hh:mm:ss')MBTkDate,MBTkCat FROM mbTicket order by mbTkID";
	private static final String GET_ONE_STMT = 
		"SELECT mbTkID,reporter,actMBID,clubMBID,memNFID,memMBID,MBTkMsg,MBTkStatID,to_char(MBTkDate,'yyyy-mm-dd hh:mm:ss')MBTkDate,MBTkCat FROM mbTicket where mbTkID= ?";
	private static final String DELETE = 
		"DELETE FROM mbTicket where mbTkID = ?";
	private static final String UPDATE = 
		"UPDATE mbTicket set reporter=?,actMBID=?,clubMBID=?,memNFID=?,memMBID=?,MBTkMsg=?,MBTkStatID=?,MBTkDate=?,MBTkCat=?  where mbTkID = ?";

	
	
	@Override
	public void insert(MbTicketVO mbTicketVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, mbTicketVO.getReporter());
			pstmt.setInt(2, mbTicketVO.getActMBID());
			pstmt.setInt(3, mbTicketVO.getClubMBID());
			pstmt.setInt(4, mbTicketVO.getMemNFID());
			pstmt.setInt(5, mbTicketVO.getMemMBID());
			pstmt.setString(6, mbTicketVO.getMBTkMsg());
			pstmt.setInt(7, mbTicketVO.getMBTkStatID());
			pstmt.setTimestamp(8, mbTicketVO.getMBTkDate());
			pstmt.setInt(9, mbTicketVO.getMBTkCat());
			
		

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
	public void update(MbTicketVO mbTicketVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, mbTicketVO.getReporter());
			pstmt.setInt(2, mbTicketVO.getActMBID());
			pstmt.setInt(3, mbTicketVO.getClubMBID());
			pstmt.setInt(4, mbTicketVO.getMemNFID());
			pstmt.setInt(5, mbTicketVO.getMemMBID());
			pstmt.setString(6, mbTicketVO.getMBTkMsg());
			pstmt.setInt(7, mbTicketVO.getMBTkStatID());
			pstmt.setTimestamp(8, mbTicketVO.getMBTkDate());
			pstmt.setInt(9, mbTicketVO.getMBTkCat());
		

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
	public void delete(Integer mbTkID) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mbTkID);

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
	public MbTicketVO findByPrimaryKey(Integer mbTkID) {
		// TODO Auto-generated method stub
		MbTicketVO mbTicketVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mbTkID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memTicketVO �]�٬� Domain objects
				mbTicketVO = new MbTicketVO();
				mbTicketVO.setMbTkID(rs.getInt("mbTkID"));			
				mbTicketVO.setReporter(rs.getInt("reporter"));
				mbTicketVO.setActMBID(rs.getInt("actMBID"));
				mbTicketVO.setClubMBID(rs.getInt("clubMBID"));
				mbTicketVO.setMemNFID(rs.getInt("memNFID"));
				mbTicketVO.setMemMBID(rs.getInt("memMBID"));
				mbTicketVO.setMBTkMsg(rs.getString("MBTkMsg"));
				mbTicketVO.setMBTkStatID(rs.getInt("MBTkStatID"));
				mbTicketVO.setMBTkDate(rs.getTimestamp("MBTkDate"));
				mbTicketVO.setMBTkCat(rs.getInt("MBTkCat"));
				
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
		return mbTicketVO;
	}
		
	@Override
	public List<MbTicketVO> getAll() {
		// TODO Auto-generated method stub
		List<MbTicketVO> list = new ArrayList<MbTicketVO>();
		MbTicketVO mbTicketVO = null;

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
				mbTicketVO = new MbTicketVO();
				mbTicketVO.setMbTkID(rs.getInt("mbTkID"));
				mbTicketVO.setReporter(rs.getInt("reporter"));
				mbTicketVO.setActMBID(rs.getInt("actMBID"));
				mbTicketVO.setClubMBID(rs.getInt("clubMBID"));
				mbTicketVO.setMemNFID(rs.getInt("memNFID"));
				mbTicketVO.setMemNFID(rs.getInt("memNFID"));
				mbTicketVO.setMemMBID(rs.getInt("memMBID"));
				mbTicketVO.setMBTkStatID(rs.getInt("MBTkStatID"));
				mbTicketVO.setMBTkDate(rs.getTimestamp("MBTkDate"));
				mbTicketVO.setMBTkCat(rs.getInt("MBTkCat"));
				
				list.add(mbTicketVO); // Store the row in the list
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

		MbTicketJDBCDAO dao = new MbTicketJDBCDAO();
		
//		// �s�W
//		MbTicketVO mbTicketVO1 = new MbTicketVO();
//		mbTicketVO1.setMbTkID(1);
//		mbTicketVO1.setReporter(12);
//		mbTicketVO1.setActMBID(2);
//		mbTicketVO1.setClubMBID(1);
//		mbTicketVO1.setMemNFID(1);
//		mbTicketVO1.setMemMBID(1);
//		mbTicketVO1.setMBTkMsg("1");
//		mbTicketVO1.setMBTkStatID(2);
//		mbTicketVO1.setMBTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
//		mbTicketVO1.setMBTkCat(1);
//		dao.insert(mbTicketVO1);
//		
//		// �ק�
		MbTicketVO mbTicketVO2 = new MbTicketVO();
		mbTicketVO2.setMbTkID(3);
		mbTicketVO2.setReporter(2);
		mbTicketVO2.setActMBID(2);
		mbTicketVO2.setClubMBID(1);
		mbTicketVO2.setMemNFID(1);
		mbTicketVO2.setMemMBID(1);
		mbTicketVO2.setMBTkMsg("1");
		mbTicketVO2.setMBTkStatID(2);
		mbTicketVO2.setMBTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
		mbTicketVO2.setMBTkCat(1);
		
		
		dao.update(mbTicketVO2);
		// �R��
		dao.delete(1);
////		 
////		// �d��
//		MbTicketVO mbTicketVO3 = dao.findByPrimaryKey(1);
//		System.out.print(mbTicketVO3.getMbTkID() + ",");
//		System.out.print(mbTicketVO3.getReporter() + ",");
//		System.out.print(mbTicketVO3.getActMBID() + ",");
//		System.out.print(mbTicketVO3.getClubMBID() + ",");
//		System.out.print(mbTicketVO3.getMemNFID() + ",");
//		System.out.print(mbTicketVO3.getMemMBID() + ",");
//		System.out.print(mbTicketVO3.getMBTkMsg() + ",");
//		System.out.print(mbTicketVO3.getMBTkStatID() + ",");
//		System.out.print(mbTicketVO3.getMBTkDate() + ",");
//		System.out.print(mbTicketVO3.getMBTkCat() + ",");
//		System.out.println("---------------------");
//		
		// �d��
		List<MbTicketVO> list = dao.getAll();
		for (MbTicketVO aMbTicket : list) {
			System.out.print(aMbTicket.getMbTkID() + ",");
			System.out.print(aMbTicket.getReporter() + ",");
			System.out.print(aMbTicket.getActMBID() + ",");
			System.out.print(aMbTicket.getClubMBID() + ",");
			System.out.print(aMbTicket.getMemNFID() + ",");
			System.out.print(aMbTicket.getMemMBID() + ",");
			System.out.print(aMbTicket.getMBTkMsg() + ",");
			System.out.print(aMbTicket.getMBTkStatID() + ",");
			System.out.print(aMbTicket.getMBTkDate() + ",");
			System.out.print(aMbTicket.getMBTkCat() + ",");
			System.out.println();
		}
	}
	}



