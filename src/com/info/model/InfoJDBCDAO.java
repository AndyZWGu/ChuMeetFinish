package com.info.model;

import java.util.*;
import java.sql.*;

public class InfoJDBCDAO implements InfoDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "servlet";
	String passwd = "123";

	private static final String INSERT_STMT = 
		"INSERT INTO info (infoID,infoName,infoContent) VALUES ( ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT infoID,infoName,infoContent FROM info order by infoID";
	private static final String GET_ONE_STMT = 
		"SELECT infoID,infoName,infoContent FROM info where infoID = ?";
	private static final String DELETE = 
		"DELETE FROM info where infoID = ?";
	private static final String UPDATE = 
		"UPDATE info set  infoName=?, infoContent=? where infoID =?";

	@Override
	public void insert(InfoVO infoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, infoVO.getInfoID());
			pstmt.setString(2, infoVO.getInfoName());
			pstmt.setString(3, infoVO.getInfoContent());
			

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
	public void update(InfoVO infoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(3, infoVO.getInfoID());
			pstmt.setString(1, infoVO.getInfoName());
			pstmt.setString(2, infoVO.getInfoContent());
			

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
	public void delete(Integer infoID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, infoID);

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
	public InfoVO findByPrimaryKey(Integer infoID) {

		InfoVO infoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, infoID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				infoVO = new InfoVO();
				infoVO.setInfoID(rs.getInt("infoID"));
				infoVO.setInfoName(rs.getString("infoName"));
				infoVO.setInfoContent(rs.getString("infoContent"));
				
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
		return infoVO;
	}

	@Override
	public List<InfoVO> getAll() {
		List<InfoVO> list = new ArrayList<InfoVO>();
		InfoVO infoVO = null;

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
				infoVO = new InfoVO();
				infoVO.setInfoID(rs.getInt("infoID"));
				infoVO.setInfoName(rs.getString("infoName"));
				infoVO.setInfoContent(rs.getString("infoContent"));
				
				list.add(infoVO); // Store the row in the list
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

		InfoJDBCDAO dao = new InfoJDBCDAO();

	//	 �s�W
		InfoVO infoVO1 = new InfoVO();
		infoVO1.setInfoID(5);
		infoVO1.setInfoName("123");
		infoVO1.setInfoContent("456");
		
		dao.insert(infoVO1);

		// �ק�
		InfoVO infoVO2 = new InfoVO();
		infoVO2.setInfoID(4);
		infoVO2.setInfoName("123");
		infoVO2.setInfoContent("21");
		dao.update(infoVO2);

		//dao.delete(5);

		// �d��
		InfoVO infoVO3 = dao.findByPrimaryKey(1);
		System.out.print(infoVO3.getInfoID() + ",");
		System.out.print(infoVO3.getInfoName() + ",");
		System.out.print(infoVO3.getInfoContent() + ",");
		
		System.out.println("---------------------");

		// �d��
		List<InfoVO> list = dao.getAll();
		for (InfoVO aInfo : list) {
			System.out.print(aInfo.getInfoID() + ",");
			System.out.print(aInfo.getInfoName() + ",");
			System.out.print(aInfo.getInfoContent() + ",");
			System.out.println();
		}
	}
}