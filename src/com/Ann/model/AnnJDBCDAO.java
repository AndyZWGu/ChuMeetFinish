package com.Ann.model;

import java.sql.*;

import java.util.*;


public class AnnJDBCDAO implements AnnDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "servlet";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO ann (annID,adminID,annName,annContent,annDate) VALUES (annID_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT annID,adminID,annName,annContent,to_char(annDate,'yyyy-mm-dd hh:mm:ss')annDate FROM ann order by annID";
	private static final String GET_ONE_STMT = 
		"SELECT annID,adminID,annName,annContent,to_char(annDate,'yyyy-mm-dd hh:mm:ss')annDate FROM ann where annID = ?";
	private static final String DELETE = 
		"DELETE FROM ann where annID = ?";
	private static final String UPDATE = 
		"UPDATE ann set adminID=?,annName=?,annContent=?,annDate=?  where annID = ?";

	
	
	@Override
	public void insert(AnnVO annVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, annVO.getAdminID());
			pstmt.setString(2, annVO.getAnnName());
			pstmt.setString(3, annVO.getAnnContent());
			pstmt.setTimestamp(4, annVO.getAnnDate());
			

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
	public void update(AnnVO annVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			//adminID=?,annName=?,annContent=?,annDate=? 
			pstmt.setInt(1, annVO.getAdminID());
			pstmt.setString(2, annVO.getAnnName());
			pstmt.setString(3, annVO.getAnnContent());
			pstmt.setTimestamp(4, annVO.getAnnDate());
			pstmt.setInt(5, annVO.getAnnID());

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
	public void delete(Integer annID) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, annID);

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
	public AnnVO findByPrimaryKey(Integer annID) {
		// TODO Auto-generated method stub
		AnnVO annVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, annID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				annVO = new AnnVO();
				annVO.setAnnID(rs.getInt(1));
				annVO.setAdminID(rs.getInt(1));
				annVO.setAnnContent(rs.getString("job"));
				annVO.setAnnDate(rs.getTimestamp("hiredate"));
				
				
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
		return annVO;
	}
	@Override
	public List<AnnVO> getAll() {
		// TODO Auto-generated method stub
		List<AnnVO> list = new ArrayList<AnnVO>();
		AnnVO annVO = null;

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
				annVO = new AnnVO();
				annVO.setAnnID(rs.getInt(1));
				annVO.setAdminID(rs.getInt(1));
				annVO.setAnnName(rs.getString("annName"));
				annVO.setAnnContent(rs.getString("annContent"));
				annVO.setAnnDate(rs.getTimestamp("annDate"));	
				
				list.add(annVO); // Store the row in the list
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

		AnnJDBCDAO dao = new AnnJDBCDAO();

//		// �s�W
//		AnnVO annVO1 = new AnnVO();
//		annVO1.setAdminID(2);
//		annVO1.setAnnName("MANAGER");
//		annVO1.setAnnContent("MANAGER");
//		annVO1.setAnnDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
//		
//		dao.insert(annVO1);
//
//		// �ק�
//		AnnVO annVO2 = new AnnVO();
//		annVO2.setAnnID(2);
//		annVO2.setAdminID(1);
//		annVO2.setAnnName("MANAGER2");
//		annVO2.setAnnContent("MANAGER2");
//		annVO2.setAnnDate(java.sql.Timestamp.valueOf("2002-01-01 10:10:10"));
//		
//		
//		dao.update(annVO2);
//
//		// �R��
////		dao.delete(1);
//		//adminID=?,annName=?,annContent=?,annDate=? 
//		// �d��
//		AnnVO annVO3 = dao.findByPrimaryKey(1);
//		System.out.print(annVO3.getAnnID() + ",");
//		System.out.print(annVO3.getAdminID() + ",");
//		System.out.print(annVO3.getAnnName() + ",");
//		System.out.print(annVO3.getAnnContent() + ",");
//		System.out.print(annVO3.getAnnDate() + ",");
//	
		System.out.println("---------------------");

		// �d��
		List<AnnVO> list = dao.getAll();
		for (AnnVO aAnn : list) {
			System.out.print(aAnn.getAnnID() + ",");
			System.out.print(aAnn.getAdminID() + ",");
			System.out.print(aAnn.getAnnName() + ",");
			System.out.print(aAnn.getAnnContent() + ",");
			System.out.print(aAnn.getAnnDate() + ",");
		
			System.out.println();
		}
	}
	}