package com.FAQ.model;

import java.sql.*;

import java.util.*;


public class FAQJDBADAO implements FAQDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "servlet";
	String passwd = "123";

	private static final String INSERT_STMT = 
		"INSERT INTO faq(faqID,faqTitle,faqContent,faqDate) VALUES ( ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT faqID,faqTitle,faqContent,to_char(faqDate,'yyyy-mm-dd hh:mm:ss')faqDate FROM faq order by faqID";
	private static final String GET_ONE_STMT = 
		"SELECT faqID,faqTitle,faqContent,to_char(faqDate,'yyyy-mm-dd hh:mm:ss')faqDate FROM faq where faqID = ?";
	private static final String DELETE = 
		"DELETE FROM faq where faqID = ?";
	private static final String UPDATE = 
		"UPDATE faq set faqTitle=?, faqContent=?, faqDate=? where faqID = ?";

	@Override
	public void insert(FAQVO faqVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
//			faqID,faqTitle,faqContent
			pstmt.setInt(1, faqVO.getFAQID());
			pstmt.setString(2, faqVO.getFAQTitle());
			pstmt.setString(3, faqVO.getFAQContent());
			pstmt.setTimestamp(4, faqVO.getFAQDate());			
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
	public void update(FAQVO faqVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, faqVO.getFAQTitle());
			pstmt.setString(2, faqVO.getFAQContent());
			pstmt.setTimestamp(3, faqVO.getFAQDate());
			pstmt.setInt(4, faqVO.getFAQID());			
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
	public void delete(Integer faqID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, faqID);

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
	public FAQVO findByPrimaryKey(Integer faqID) {

		FAQVO faqVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, faqID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				faqVO = new FAQVO();
				faqVO.setFAQID(rs.getInt("faqID"));
				faqVO.setFAQTitle(rs.getString("faqTitle"));
				faqVO.setFAQContent(rs.getString("faqContent"));
				faqVO.setFAQDate(rs.getTimestamp("faqDate"));
				
		
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
		return faqVO;
	}

	@Override
	public List<FAQVO> getAll() {
		List<FAQVO> list = new ArrayList<FAQVO>();
		FAQVO faqVO = null;

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
				faqVO = new FAQVO();
				faqVO.setFAQID(rs.getInt("faqID"));
				faqVO.setFAQTitle(rs.getString("faqTitle"));
				faqVO.setFAQContent(rs.getString("faqContent"));
				faqVO.setFAQDate(rs.getTimestamp("faqDate"));				
				list.add(faqVO); // Store the row in the list
				

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

		FAQJDBADAO dao = new FAQJDBADAO();

		// �s�W
//		FAQVO faqVO1 = new FAQVO();
//		faqVO1.setFAQID(5);
//		faqVO1.setFAQTitle("MANAGER");
//		faqVO1.setFAQContent("456");
//		faqVO1.setFAQDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
//		
//		
//		dao.insert(faqVO1);

		// �ק�
		FAQVO faqVO2 = new FAQVO();
		faqVO2.setFAQID(5);
		faqVO2.setFAQTitle("MANAGER");
		faqVO2.setFAQContent("456");
		faqVO2.setFAQDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
		
		dao.update(faqVO2);

		// �R��
	//dao.delete(5);

		// �d��
		FAQVO faqVO3 = dao.findByPrimaryKey(2);
		System.out.print(faqVO3.getFAQID() + ",");
		System.out.print(faqVO3.getFAQTitle() + ",");
		System.out.print(faqVO3.getFAQContent() + ",");
		System.out.print(faqVO3.getFAQDate() + ",");
		
		
		System.out.println("---------------------");

		// �d��
		List<FAQVO> list = dao.getAll();
		for (FAQVO aFAQ : list) {
			System.out.print(aFAQ.getFAQID() + ",");
			System.out.print(aFAQ.getFAQTitle() + ",");
			System.out.print(aFAQ.getFAQContent() + ",");
			System.out.print(aFAQ.getFAQDate() + ",");
			
			System.out.println();
		}
	}
}

