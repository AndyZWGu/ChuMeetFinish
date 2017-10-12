package com.clubAlbum.model;

import java.util.*;

import com.club.model.ClubJDBCDAO;
import com.club.model.ClubVO;
import com.clubImg.model.ClubImgVO;
import com.clubMem.model.ClubMemVO;

import java.sql.*;

public class ClubAlbumJDBCDAO implements ClubAlbumDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "testend";
	String passwd = "testend";

	private static final String INSERT_STMT = "INSERT INTO clubAlbum (clubAlbumID,clubID,memID,clubAlbumDate,clubAlbumName,clubAlbumStatus) VALUES (clubAlbum_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT clubAlbumID,clubID,memID,to_char(clubAlbumDate,'yyyy-mm-dd hh:mm:ss') clubAlbumDate,clubAlbumName,clubAlbumStatus FROM clubAlbum order by clubAlbumID";
	private static final String GET_ONE_STMT = "SELECT clubAlbumID,clubID,memID,to_char(clubAlbumDate,''yyyy-mm-dd hh:mm:ss') clubAlbumDate,clubAlbumName,clubAlbumStatus FROM clubAlbum where clubAlbumID = ?";
	//private static final String DELETE = "DELETE FROM clubNews where clubNewsID = ?";
	private static final String UPDATE = "UPDATE clubAlbum set  clubID=?, memID=?, clubAlbumDate=?, clubAlbumName=?, clubAlbumStatus=? where clubAlbumID = ?";

	
	private static final String GET_ClubImg_ByClubAlbumID_STMT = 
			"SELECT clubImgID,clubAlbumID,memID,to_char(clubImgDate,'yyyy-mm-dd hh:mm:ss') clubImgDate,clubImgContent,clubImgStatus FROM clubImg where clubAlbumID=? order by clubImgID";
	
	
	@Override
	public void insert(ClubAlbumVO clubAlbumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, clubAlbumVO.getClubID());
			pstmt.setInt(2, clubAlbumVO.getMemID());
			pstmt.setTimestamp(3, clubAlbumVO.getClubAlbumDate());
			pstmt.setString(4, clubAlbumVO.getClubAlbumName());
			pstmt.setInt(5, clubAlbumVO.getClubAlbumStatus());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(ClubAlbumVO clubAlbumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, clubAlbumVO.getClubID());
			pstmt.setInt(2, clubAlbumVO.getMemID());
			pstmt.setTimestamp(3, clubAlbumVO.getClubAlbumDate());
			pstmt.setString(4, clubAlbumVO.getClubAlbumName());
			pstmt.setInt(5, clubAlbumVO.getClubAlbumStatus());
			pstmt.setInt(6, clubAlbumVO.getClubAlbumID());


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
//
//	@Override
//	public void delete(Integer clubNewsID) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, clubNewsID);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//
//	}
//
	@Override
	public ClubAlbumVO findByPrimaryKey(Integer clubAlbumID) {
		ClubAlbumVO clubAlbumVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, clubAlbumID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				clubAlbumVO = new ClubAlbumVO();
				clubAlbumVO.setClubAlbumID(rs.getInt("clubAlbumID"));
				clubAlbumVO.setClubID(rs.getInt("clubID"));			
				clubAlbumVO.setMemID(rs.getInt("memID"));
				clubAlbumVO.setClubAlbumDate(rs.getTimestamp("clubAlbumDate"));
				clubAlbumVO.setClubAlbumName(rs.getString("clubAlbumName"));
				clubAlbumVO.setClubAlbumStatus(rs.getInt("clubAlbumStatus"));
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
		return clubAlbumVO;
	}
//
	@Override
	public List<ClubAlbumVO> getAll() {
		List<ClubAlbumVO> list = new ArrayList<ClubAlbumVO>();
		ClubAlbumVO clubAlbumVO = null;

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
				clubAlbumVO = new ClubAlbumVO();
				clubAlbumVO.setClubAlbumID(rs.getInt("clubAlbumID"));
				clubAlbumVO.setClubID(rs.getInt("clubID"));
				clubAlbumVO.setMemID(rs.getInt("memID"));
				clubAlbumVO.setClubAlbumName(rs.getString("clubAlbumName"));
				clubAlbumVO.setClubAlbumDate(rs.getTimestamp("clubAlbumDate"));
				clubAlbumVO.setClubAlbumStatus(rs.getInt("clubAlbumStatus"));
				list.add(clubAlbumVO); // Store the row in the list
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
	
	
	
	//自己加
	@Override
	public List<ClubImgVO> getClubImgByClubAlbumID(Integer clubAlbumID) {
		List<ClubImgVO> list = new ArrayList<ClubImgVO>();
		ClubImgVO clubImgVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ClubImg_ByClubAlbumID_STMT);
			pstmt.setInt(1, clubAlbumID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				clubImgVO = new ClubImgVO();
				clubImgVO.setClubImgID(rs.getInt("clubImgID"));
				clubImgVO.setClubAlbumID(rs.getInt("clubAlbumID"));
				clubImgVO.setMemID(rs.getInt("memID"));
				clubImgVO.setClubImgDate(rs.getTimestamp("clubImgDate"));
				clubImgVO.setClubImgContent(rs.getString("clubImgContent"));
				clubImgVO.setClubImgStatus(rs.getInt("clubImgStatus"));
				
				list.add(clubImgVO); // Store the row in the vector
			}
	
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

		ClubAlbumJDBCDAO dao = new ClubAlbumJDBCDAO();

		// 新增OK
//		ClubAlbumVO clubAlbumVO1 = new ClubAlbumVO();
//		clubAlbumVO1.setClubID(1);
//		clubAlbumVO1.setMemID(1);
//		clubAlbumVO1.setClubAlbumDate(java.sql.Date.valueOf("2017-09-15"));
//		clubAlbumVO1.setClubAlbumName("動手做DAO");
//		clubAlbumVO1.setClubAlbumStatus(1);
//		dao.insert(clubAlbumVO1);

		// 修改OK
//		 ClubAlbumVO clubAlbumVO2 = new ClubAlbumVO();
//		 clubAlbumVO2.setClubAlbumID(1);
//		 clubAlbumVO2.setClubID(2);
//		 clubAlbumVO2.setMemID(2);
//		 clubAlbumVO2.setClubAlbumDate(java.sql.Date.valueOf("2017-09-11"));
//		 clubAlbumVO2.setClubAlbumName("我要睡覺覺2");
//		 clubAlbumVO2.setClubAlbumStatus(3);
//		 dao.update(clubAlbumVO2);
		//
		// 刪除未做
//		 dao.delete(11);
		//
		// // 查詢OK
//		 ClubAlbumVO clubAlbumVO3 = dao.findByPrimaryKey(2);
//		 System.out.print(clubAlbumVO3.getClubAlbumID() + ",");
//		 System.out.print(clubAlbumVO3.getClubID() + ",");
//		 System.out.print(clubAlbumVO3.getMemID() + ",");
//		 System.out.print(clubAlbumVO3.getClubAlbumDate() + ",");
//		 System.out.print(clubAlbumVO3.getClubAlbumName() + ",");
//		 System.out.println(clubAlbumVO3.getClubAlbumStatus());
//		 System.out.println("---------------------");
		//
		// 查詢OK
//		 List<ClubAlbumVO> list = dao.getAll();
//		 for (ClubAlbumVO aClubAlbum : list) {
//		 System.out.print(aClubAlbum.getClubAlbumID() + ",");
//		 System.out.print(aClubAlbum.getClubID() + ",");
//		 System.out.print(aClubAlbum.getMemID() + ",");
//		 System.out.print(aClubAlbum.getClubAlbumName() + ",");
//		 System.out.print(aClubAlbum.getClubAlbumDate() + ",");
//		 System.out.print(aClubAlbum.getClubAlbumStatus());
//		 System.out.println();
//		 }
		 
		 
			//自己加的
		List<ClubImgVO> list = dao.getClubImgByClubAlbumID(1);
		for (ClubImgVO aClubImg : list) {
			System.out.print(aClubImg.getClubImgID() + ",");
			System.out.print(aClubImg.getClubAlbumID() + ",");
			System.out.print(aClubImg.getMemID() + ",");
			System.out.print(aClubImg.getClubImgDate() + ",");
			System.out.print(aClubImg.getClubImgContent() + ",");
			System.out.print(aClubImg.getClubImgStatus());
			System.out.println();
			}
			
		 
		 
	}

}