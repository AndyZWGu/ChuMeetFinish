package com.clubImg.model;

import java.util.*;
import java.sql.*;

public class ClubImgJDBCDAO implements ClubImgDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G2";
	String passwd = "testend";

	private static final String INSERT_STMT = 
			"INSERT INTO clubImg (clubImgID,clubAlbumID,memID,clubImgDate,clubImg,clubImgContent,clubImgStatus) VALUES (clubImg_seq.NEXTVAL, ?, ?, ?, ?, ?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT clubImgID,clubAlbumID,memID,to_char(clubImgDate,'yyyy-mm-dd hh:mm:ss') clubImgDate,clubImgContent,clubImgStatus FROM clubImg order by clubImgID";
		private static final String GET_ONE_STMT = 
			"SELECT clubImgID,clubAlbumID,memID,to_char(clubImgDate,'yyyy-mm-dd hh:mm:ss') clubImgDate,clubImgContent,clubImgStatus FROM clubImg where clubImgID = ?";
//		private static final String DELETE = 
//			"DELETE FROM club where clubID = ?";
		private static final String UPDATE = 
			"UPDATE clubImg set clubAlbumID=?, MemID=?, clubImgDate=?, clubImg=? ,clubImgContent=?, clubImgStatus=? where clubImgID = ?";

		
		//以下自己加
		private static final String DELETE_ONE_IMG = 
			"UPDATE clubImg set clubImgStatus=? where clubImgID = ?";
	
	@Override
	public void insert(ClubImgVO clubImgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, clubImgVO.getClubAlbumID());
			pstmt.setInt(2, clubImgVO.getMemID());
			pstmt.setTimestamp(3, clubImgVO.getClubImgDate());
			pstmt.setBytes(4, clubImgVO.getClubImg());
			pstmt.setString(5, clubImgVO.getClubImgContent());
			pstmt.setInt(6, clubImgVO.getClubImgStatus());

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
	public void update(ClubImgVO clubImgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, clubImgVO.getClubAlbumID());
			pstmt.setInt(2, clubImgVO.getMemID());
			pstmt.setTimestamp(3, clubImgVO.getClubImgDate());
			pstmt.setBytes(4, clubImgVO.getClubImg());
			pstmt.setString(5, clubImgVO.getClubImgContent());
			pstmt.setInt(6, clubImgVO.getClubImgStatus());
			pstmt.setInt(7, clubImgVO.getClubImgID());

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
	public ClubImgVO findByPrimaryKey(Integer clubImgID) {

		ClubImgVO clubImgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, clubImgID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				clubImgVO = new ClubImgVO();
				clubImgVO.setClubImgID(rs.getInt("clubImgID"));
				clubImgVO.setClubAlbumID(rs.getInt("clubAlbumID"));			
				clubImgVO.setMemID(rs.getInt("MemID"));
				clubImgVO.setClubImgDate(rs.getTimestamp("clubImgDate"));
				clubImgVO.setClubImgContent(rs.getString("clubImgContent"));
				clubImgVO.setClubImgStatus(rs.getInt("clubImgStatus"));
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
		return clubImgVO;
	}
//
	@Override
	public List<ClubImgVO> getAll() {
		List<ClubImgVO> list = new ArrayList<ClubImgVO>();
		ClubImgVO clubImgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				clubImgVO = new ClubImgVO();
				clubImgVO.setClubImgID(rs.getInt("clubImgID"));
				clubImgVO.setClubAlbumID(rs.getInt("clubAlbumID"));
				clubImgVO.setMemID(rs.getInt("memID"));
				clubImgVO.setClubImgDate(rs.getTimestamp("clubImgDate"));
				clubImgVO.setClubImgContent(rs.getString("clubImgContent"));
				clubImgVO.setClubImgStatus(rs.getInt("clubImgStatus"));
				list.add(clubImgVO); // Store the row in the list
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
	
	
	
	
	//以下自己加
	//刪除照片
	@Override
	public void deleteOneImg(ClubImgVO clubImgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_ONE_IMG);

			pstmt.setInt(1, clubImgVO.getClubImgStatus());
			pstmt.setInt(2, clubImgVO.getClubImgID());

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

	
	
	
	
	
	
	
	
	
	

	public static void main(String[] args) {

		ClubImgJDBCDAO dao = new ClubImgJDBCDAO();

		// 新增OK
//		ClubImgVO clubImgVO1 = new ClubImgVO();
//		clubImgVO1.setClubAlbumID(1);
//		clubImgVO1.setMemID(1);
////		clubImgVO1.setClubPhoto(new Double(500));
//		clubImgVO1.setClubImgDate(java.sql.Date.valueOf("2017-09-09"));
//		clubImgVO1.setClubImgContent("社團出遊啦");
//		clubImgVO1.setClubImgStatus(1);
//		dao.insert(clubImgVO1);

		// 修改OK
//		ClubImgVO clubImgVO2 = new ClubImgVO();
//		clubImgVO2.setClubAlbumID(1);
//		clubImgVO2.setMemID(1);
////		clubImgVO2.setClubPhoto(new Double(500));
//		clubImgVO2.setClubImgDate(java.sql.Date.valueOf("2017-09-11"));
//		clubImgVO2.setClubImgContent("社團出遊啦2");
//		clubImgVO2.setClubImgStatus(1);
//		clubImgVO2.setClubImgID(13);
//		dao.update(clubImgVO2);
//
		// 刪除未做
//		dao.delete(43);
//
//		// 查詢OK
//		ClubImgVO clubImgVO3 = dao.findByPrimaryKey(1);
//		System.out.print(clubImgVO3.getClubImgID() + ",");
//		System.out.print(clubImgVO3.getClubAlbumID() + ",");
//		System.out.print(clubImgVO3.getMemID() + ",");
//		System.out.print(clubImgVO3.getClubImgDate() + ",");
//		System.out.print(clubImgVO3.getClubImgContent() + ",");
//		System.out.print(clubImgVO3.getClubImgStatus());
//		System.out.println("---------------------");
//
		// 查詢OK
//		List<ClubImgVO> list = dao.getAll();
//		for (ClubImgVO aClubImg : list) {
//			System.out.print(aClubImg.getClubImgID() + ",");
//			System.out.print(aClubImg.getClubAlbumID() + ",");
//			System.out.print(aClubImg.getMemID() + ",");
//			System.out.print(aClubImg.getClubImgDate() + ",");
//			System.out.print(aClubImg.getClubImgContent() + ",");
//			System.out.print(aClubImg.getClubImgStatus());
//			System.out.println();
//		}
		
		
	}
}

