package com.admin.model;

import java.sql.*;

import java.util.*;



public class AdminJDBCDAO implements AdminDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "servlet";
	String passwd = "123456";

	private static final String INSERT_STMT =
			"INSERT INTO admin (adminID,adminName,adminMail,adminPW,adminEmail,adminDate,adminStatus) VALUES (adminID_SEQ.NEXTVAL,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
			"SELECT adminID,adminName,adminMail,adminPW,adminEmail,to_char(adminDate,'yyyy-mm-dd hh:mm:ss')adminDate,adminStatus FROM admin order by adminID";
	private static final String GET_ONE_STMT=
			"SELECT adminID,adminName,adminMail,adminPW,adminEmail,to_char(adminDate,'yyyy-mm-dd hh:mm:ss')adminDate,adminStatus FROM admin where adminID=?";
	private static final String DELETE =
			"DELETE FROM admin where adminID=?";
	private static final String UPDATE =
			"UPDATE admin set adminName=?, adminMail=?,adminPW=?,adminEmail=?,adminDate=?,adminStatus=? where adminID=?";
	
	

	@Override
	public void update(AdminVO adminVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con =DriverManager.getConnection(url,userid,passwd);
			pstmt =con.prepareStatement(UPDATE);
	
			pstmt.setString(1, adminVO.getAdminName());
			pstmt.setString(2, adminVO.getAdminMail());
			pstmt.setString(3, adminVO.getAdminPW());
			pstmt.setString(4, adminVO.getAdminEmail());
			pstmt.setTimestamp(5, adminVO.getAdminDate());
			pstmt.setInt(6, adminVO.getAdminStatus());
			pstmt.setInt(7, adminVO.getAdminID());
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
	public void delete(Integer adminID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt=con.prepareStatement(DELETE);
			
			pstmt.setInt(1, adminID);
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt !=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con !=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public AdminVO findByPrimaryKey(Integer adminID) {
		// TODO Auto-generated method stub
		AdminVO adminVO=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			Class.forName(driver);
			con=DriverManager.getConnection(url,userid,passwd);
			pstmt =con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, adminID);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				adminVO=new AdminVO();
				adminVO.setAdminID(rs.getInt("adminID"));
				adminVO.setAdminName(rs.getString("adminName"));
				adminVO.setAdminMail(rs.getString("adminMail"));
				adminVO.setAdminPW(rs.getString("adminPW"));
				adminVO.setAdminEmail(rs.getString("adminEmail"));
				adminVO.setAdminDate(rs.getTimestamp("adminDate"));
				adminVO.setAdminStatus(rs.getInt("adminStatus"));
			}
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(rs!=null){
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
		return adminVO;
	}


	@Override
	public List<AdminVO> getAll() {
		// TODO Auto-generated method stub
		List<AdminVO> list=new ArrayList<AdminVO>();
		AdminVO adminVO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
		
			while(rs.next()){
				adminVO=new AdminVO();
				adminVO.setAdminID(rs.getInt("adminID"));
				adminVO.setAdminName(rs.getString("adminName"));
				adminVO.setAdminMail(rs.getString("adminMail"));
				adminVO.setAdminPW(rs.getString("adminPW"));
				adminVO.setAdminEmail(rs.getString("adminEmail"));
				adminVO.setAdminDate(rs.getTimestamp("adminDate"));
				adminVO.setAdminStatus(rs.getInt("adminStatus"));
				list.add(adminVO);
			}
			}catch(ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
			}finally{
				if(rs!=null){
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

		AdminJDBCDAO dao = new AdminJDBCDAO();

//		 嚙編嚙磕
//		AdminVO adminVO1 = new AdminVO();
//		adminVO1.setAdminID(4);
//		adminVO1.setAdminName("123");
//		adminVO1.setAdminMail("MANAGER");
//		adminVO1.setAdminPW("1233");
//		adminVO1.setAdminEmail("50000");
//		adminVO1.setAdminDate(java.sql.Timestamp.valueOf("2002-01-01 10:10:10"));
//		adminVO1.setAdminStatus(1);
//		
//		dao.insert(adminVO1);

		
//		adminID=? ,adminName=?, adminMail=?,adminPW=?,adminEmail=?,adminDate=?,adminStatus=?
//		// 嚙論改蕭
//		AdminVO adminVO2 = new AdminVO();
//		adminVO2.setAdminID(13);
//		adminVO2.setAdminName("123");
//		adminVO2.setAdminMail("MANAGER");
//		adminVO2.setAdminPW("123");
//		adminVO2.setAdminEmail("50000");
//		adminVO2.setAdminDate(java.sql.Timestamp.valueOf("2002-01-01 10:10:10"));
//		adminVO2.setAdminStatus(1);
//		dao.update(adminVO2);

		// 嚙磋嚙踝蕭
	//		dao.delete(13);
//
//		// 嚙範嚙踝蕭
		AdminVO adminVO3 = dao.findByPrimaryKey(2);
		System.out.print(adminVO3.getAdminID()+ ",");
		System.out.print(adminVO3.getAdminName() + ",");
		System.out.print(adminVO3.getAdminMail() + ",");
		System.out.print(adminVO3.getAdminPW() + ",");
		System.out.print(adminVO3.getAdminEmail() + ",");
		System.out.print(adminVO3.getAdminDate() + ",");
		System.out.print(adminVO3.getAdminStatus() + ",");
		
		System.out.println("---------------------");

		// 嚙範嚙踝蕭
//		List<AdminVO> list = dao.getAll();
//		for (AdminVO aAdmin : list) {
//			System.out.print(aAdmin.getAdminID() + ",");
//			System.out.print(aAdmin.getAdminName() + ",");
//			System.out.print(aAdmin.getAdminMail() + ",");
//			System.out.print(aAdmin.getAdminPW() + ",");
//			System.out.print(aAdmin.getAdminEmail() + ",");
//			System.out.print(aAdmin.getAdminDate() + ",");
//			System.out.print(aAdmin.getAdminStatus() + ",");
//	
//			System.out.println();
//		}
	}


	@Override
	public void insert(AdminVO adminVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try{
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			
			pstmt.setString(1, adminVO.getAdminName());
			pstmt.setString(2, adminVO.getAdminMail());
			pstmt.setString(3, adminVO.getAdminPW());
			pstmt.setString(4, adminVO.getAdminEmail());
			pstmt.setTimestamp(5, adminVO.getAdminDate());
			pstmt.setInt(6, adminVO.getAdminStatus());
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver."
					+e.getMessage());
		}catch (SQLException se){
			throw new RuntimeException("A database error occured."
			+ se.getMessage());
		}finally{
			if(pstmt !=null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con !=null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
		}
		}
	}


	@Override
	public AdminVO findByAdminName(String adminName) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void status1(Integer AdminID) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<AdminVO> statusadmin() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AdminVO findByAdminMail(String adminMail) {
		// TODO Auto-generated method stub
		return null;
	}



	
	
}
