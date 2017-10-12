package com.admPril.model;

import java.sql.*;

import java.util.*;



public class AdmPrilJDBCDAO implements AdmPrilDAO_interface{

	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String userid="servlet";
	String passwd="123";
		private static final String INSERT_STMT=
				"INSERT INTO admPril (admPrilID,adminID,admPrildate,admPrilStatus) VALUES (?,?,?,?)";
		private static final String GET_ALL_STMT=
				"SELECT admPrilID,adminID,to_char(admPrildate,'yyyy-mm-dd hh:mm:ss')admPrildate,admPrilStatus From admPril order by admPrilID";
		private static final String GET_ONE_STMT=
				"SELECT admPrilID,adminID,to_char(admPrildate,'yyyy-mm-dd hh:mm:ss')admPrildate,admPrilStatus From admPril where admPrilID=?";
		private static final String DELETE=
				"DELETE FROM admPril where admPrilID =?";
		private static final String UPDATE=
				"UPDATE admPril set adminID=?, admPrildate=? ,admPrilStatus=? where admPrilID=?"; 
		
	
	

	@Override
	public void insert(AdmPrilVO admPrilVO) {
		
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

		
			
			pstmt.setInt(1, admPrilVO.getAdmPrilID());
			pstmt.setInt(2, admPrilVO.getAdminID());
			pstmt.setTimestamp(3, admPrilVO.getAdmPrildate());
			pstmt.setInt(4, admPrilVO.getAdmPrilStatus());		
			pstmt.executeUpdate();
			
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
	public void update(AdmPrilVO admPrilVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

		
			
			pstmt.setInt(1, admPrilVO.getAdminID());
			pstmt.setTimestamp(2, admPrilVO.getAdmPrildate());
			pstmt.setInt(3, admPrilVO.getAdmPrilStatus());
			pstmt.setInt(4, admPrilVO.getAdmPrilID());
		
			pstmt.executeUpdate();
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
	public void delete(Integer admPrilID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		
			try{
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, admPrilID);

				pstmt.executeUpdate();
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
	public AdmPrilVO findByPrimaryKey(Integer admPrilID) {
		// TODO Auto-generated method stub
		AdmPrilVO admPrilVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, admPrilID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				admPrilVO = new AdmPrilVO();
				admPrilVO.setAdmPrilID(rs.getInt("admPrilID"));
				admPrilVO.setAdminID(rs.getInt("adminID"));
				admPrilVO.setAdmPrildate(rs.getTimestamp("admPrildate"));
				admPrilVO.setAdmPrilStatus(rs.getInt("admPrilStatus"));
			
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
		return admPrilVO;
	}
	@Override
	public List<AdmPrilVO> getAll() {
		// TODO Auto-generated method stub
		List<AdmPrilVO> list = new ArrayList<AdmPrilVO>();
		AdmPrilVO admPrilVO = null;

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
				admPrilVO = new AdmPrilVO();
				admPrilVO.setAdmPrilID(rs.getInt("admPrilID"));
				admPrilVO.setAdminID(rs.getInt("adminID"));
				admPrilVO.setAdmPrildate(rs.getTimestamp("admPrildate"));
				admPrilVO.setAdmPrilStatus(rs.getInt("admPrilStatus"));				
				list.add(admPrilVO); // Store the row in the list
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

		AdmPrilJDBCDAO dao = new AdmPrilJDBCDAO();

		// �s�W   
		AdmPrilVO admPrilVO1 = new AdmPrilVO();
		admPrilVO1.setAdmPrilID(3);
		admPrilVO1.setAdminID(1);
		admPrilVO1.setAdmPrildate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
		admPrilVO1.setAdmPrilStatus(1);		
		dao.insert(admPrilVO1);
		
		
		// �ק�
//		AdmPrilVO admPrilVO2 = new AdmPrilVO();
//		admPrilVO2.setAdmPrilID(3);
//		admPrilVO2.setAdminID(1);
//		admPrilVO2.setAdmPrildate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
//		admPrilVO2.setAdmPrilStatus(1);
//		
//		dao.update(admPrilVO2);
//
//		// �R��
//		dao.delete(3);

		// �d��
		AdmPrilVO admPrilVO3 = dao.findByPrimaryKey(1);
		System.out.print(admPrilVO3.getAdmPrilID() + ",");
		System.out.print(admPrilVO3.getAdminID() + ",");
		System.out.print(admPrilVO3.getAdmPrildate() + ",");
		System.out.print(admPrilVO3.getAdmPrilStatus() + ",");
		
		System.out.println("---------------------");

		// �d��
		List<AdmPrilVO> list = dao.getAll();
		for (AdmPrilVO aAdmPril : list) {
			System.out.print(aAdmPril.getAdmPrilID() + ",");
			System.out.print(aAdmPril.getAdminID() + ",");
			System.out.print(aAdmPril.getAdmPrildate() + ",");
			System.out.print(aAdmPril.getAdmPrilStatus() + ",");
			
			System.out.println();
		}
	}

	@Override
	public List<AdmPrilVO> status2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdmPrilVO> findByAdminID(Integer adminID) {
		// TODO Auto-generated method stub
		return null;
	}
}
