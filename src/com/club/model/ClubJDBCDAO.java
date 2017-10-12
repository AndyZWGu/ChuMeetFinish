package com.club.model;

import java.util.*;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.clubAlbum.model.ClubAlbumVO;
import com.clubMB.model.ClubMBVO;
import com.clubMem.model.ClubMemVO;
import com.clubNews.model.ClubNewsVO;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class ClubJDBCDAO implements ClubDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G2";
	String passwd = "testend";

	private static final String INSERT_STMT = 
		"INSERT INTO club (clubID,clubmemID,clubName,clubTypeID,clubContent,clubPhoto,clubStartDate,clubStatus,clubLong,clubLat) VALUES (club_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT clubID,clubmemID,clubName,clubTypeID,clubContent,clubPhoto,to_char(clubStartDate,'yyyy-mm-dd hh:mm:ss') clubStartDate,clubStatus,clubLong,clubLat FROM club order by clubID";
	private static final String GET_ONE_STMT = 
		"SELECT clubID,clubmemID,clubName,clubTypeID,clubContent,clubPhoto,to_char(clubStartDate,'yyyy-mm-dd hh:mm:ss') clubStartDate,clubStatus,clubLong,clubLat FROM club where clubID = ?";
	private static final String DELETE = 
		"DELETE FROM club where clubID = ?";
	private static final String UPDATE = 
		"UPDATE club set clubmemID=?, clubName=?, clubTypeID=?, clubContent=?, clubPhoto=?, clubStartDate=?, clubStatus=?, clubLong=?, clubLat=? where clubID = ?";


	//浠ヤ笅鑷繁鍔�
	private static final String GET_ClubAlbums_ByClubID_STMT = 
			"SELECT clubAlbumID,clubID,memID,to_char(clubAlbumDate,'yyyy-mm-dd hh:mm:ss') clubAlbumDate,clubAlbumName,clubAlbumStatus FROM clubAlbum where clubID=? order by clubAlbumID desc";
	
	//鑷繁鍔�
	private static final String GET_ClubNews_ByClubID_STMT = 
			"SELECT clubNewsID,clubID,memID,to_char(clubNewsDate,'yyyy-mm-dd hh:mm:ss') clubNewsDate,clubNewsTitle,clubNewsContent,actID,clubNewsStatus FROM clubNews where clubID=? order by clubNewsID desc";
	private static final String GET_ClubMB_ByClubID_STMT = 
			"SELECT clubMBID,clubID,memID,clubMBContent,to_char(clubMBDate,'yyyy-mm-dd hh:mm:ss') clubMBDate,clubMBStatus FROM clubMB where clubID=? order by clubMBID desc";
	private static final String GET_ClubMem_ByClubID_STMT = 
			"SELECT  clubID ,memID ,clubMemType,to_char(clubMemJoinDate,'yyyy-mm-dd hh:mm:ss') clubMemJoinDate FROM clubMem where clubID=? order by clubID";

	private static final String CLUBCHANGE = 
			"UPDATE club set clubName=?, clubTypeID=?, clubContent=?, clubPhoto=? where clubID = ?";

	

//	Timestamp utildate;
	  public static Timestamp nowTimestamp(){
		  java.util.Date utildate=new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(utildate.getTime());
			java.sql.Time sTime=new java.sql.Time(utildate.getTime());
			java.sql.Timestamp stp=new java.sql.Timestamp(utildate.getTime());
	       return stp;
	  }
	
//鏀硅嚜澧�	  
	@Override
	public Integer insert(ClubVO clubVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		Integer key=null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, clubVO.getClubmemID());
			pstmt.setString(2, clubVO.getClubName());
			pstmt.setInt(3, clubVO.getClubTypeID());
			pstmt.setString(4, clubVO.getClubContent());			

			pstmt.setBytes(5,clubVO.getClubPhoto());
			
			pstmt.setTimestamp(6, clubVO.getClubStartDate());
			pstmt.setInt(7, clubVO.getClubStatus());
			pstmt.setDouble(8, clubVO.getClubLong());
			pstmt.setDouble(9, clubVO.getClubLat());

			pstmt.executeUpdate();

			
			//鎺樺彇灏嶆噳鐨勮嚜澧炰富閸靛��
			ResultSet rsKeys = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rsKeys.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			
			if (rsKeys.next()) {
					for (int i = 1; i <= columnCount; i++) {
						key = rsKeys.getInt(i);
						System.out.println("鑷涓婚嵉鍊�(i=" + i + ") = " + key +"(鍓涙柊澧炴垚鍔熺殑鍝″伐绶ㄨ櫉)");

					}
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
		return key;

	}
	
	
	
	
	
	
//	//鏈敼鑷
//	@Override
//	public void insert(ClubVO clubVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		Integer key=null;
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, clubVO.getClubmemID());
//			pstmt.setString(2, clubVO.getClubName());
//			pstmt.setInt(3, clubVO.getClubTypeID());
//			pstmt.setString(4, clubVO.getClubContent());			
//
//			pstmt.setBytes(5,clubVO.getClubPhoto());
//			
//			pstmt.setTimestamp(6, clubVO.getClubStartDate());
//			pstmt.setInt(7, clubVO.getClubStatus());
//			pstmt.setDouble(8, clubVO.getClubLong());
//			pstmt.setDouble(9, clubVO.getClubLat());
//
//			pstmt.executeUpdate();
//
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
//	}

	
	


	@Override
	public void update(ClubVO clubVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, clubVO.getClubmemID());
			pstmt.setString(2, clubVO.getClubName());
			pstmt.setInt(3, clubVO.getClubTypeID());
			pstmt.setString(4, clubVO.getClubContent());
			
			pstmt.setBytes(5,clubVO.getClubPhoto());
			
			pstmt.setTimestamp(6, clubVO.getClubStartDate());
			pstmt.setInt(7, clubVO.getClubStatus());
			pstmt.setDouble(8, clubVO.getClubLong());
			pstmt.setDouble(9, clubVO.getClubLat());
			pstmt.setInt(10, clubVO.getClubID());

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
	public void delete(Integer clubID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, clubID);

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
	public ClubVO findByPrimaryKey(Integer clubID) {

		ClubVO clubVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, clubID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 涔熺ū鐐� Domain objects
				clubVO = new ClubVO();
				clubVO.setClubID(rs.getInt("clubID"));
				clubVO.setClubmemID(rs.getInt("clubmemID"));			
				clubVO.setClubName(rs.getString("clubName"));
				clubVO.setClubTypeID(rs.getInt("clubTypeID"));
				clubVO.setClubContent(rs.getString("clubContent"));
				clubVO.setClubPhoto(rs.getBytes("clubPhoto"));
				clubVO.setClubStartDate(rs.getTimestamp("clubStartDate"));
				clubVO.setClubStatus(rs.getInt("clubStatus"));
				clubVO.setClubLong(rs.getDouble("clubLong"));
				clubVO.setClubLat(rs.getDouble("clubLat"));
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
		return clubVO;
	}

	@Override
	public List<ClubVO> getAll() {
		List<ClubVO> list = new ArrayList<ClubVO>();
		ClubVO clubVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 涔熺ū鐐� Domain objects
				clubVO = new ClubVO();
				clubVO.setClubID(rs.getInt("clubID"));
				clubVO.setClubmemID(rs.getInt("clubMemID"));
				clubVO.setClubName(rs.getString("clubName"));
				clubVO.setClubTypeID(rs.getInt("clubTypeID"));
				clubVO.setClubContent(rs.getString("clubContent"));
				
				clubVO.setClubPhoto(rs.getBytes("clubPhoto"));
				
				clubVO.setClubStartDate(rs.getTimestamp("clubStartDate"));
				clubVO.setClubStatus(rs.getInt("clubStatus"));
				clubVO.setClubLong(rs.getDouble("clubLong"));
				clubVO.setClubLat(rs.getDouble("clubLat"));
				list.add(clubVO); // Store the row in the list
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
	
	
	
	
	//鑷繁鍔�
	@Override
	public List<ClubAlbumVO> getClubAlbumsByClubID(Integer clubID) {
		List<ClubAlbumVO> list = new ArrayList<ClubAlbumVO>();
		ClubAlbumVO clubAlbumVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ClubAlbums_ByClubID_STMT);
			pstmt.setInt(1, clubID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				clubAlbumVO = new ClubAlbumVO();
				clubAlbumVO.setClubAlbumID(rs.getInt("clubAlbumID"));
				clubAlbumVO.setClubID(rs.getInt("clubID"));
				clubAlbumVO.setMemID(rs.getInt("memID"));
				clubAlbumVO.setClubAlbumDate(rs.getTimestamp("clubAlbumDate"));
				clubAlbumVO.setClubAlbumName(rs.getString("clubAlbumName"));
				clubAlbumVO.setClubAlbumStatus(rs.getInt("clubAlbumStatus"));
				list.add(clubAlbumVO); // Store the row in the vector
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
	
	
	//鑷繁鍔�
	@Override
	public List<ClubMBVO> getClubMBByClubID(Integer clubID) {
		List<ClubMBVO> list = new ArrayList<ClubMBVO>();
		ClubMBVO clubMBVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	
		try {
	
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ClubMB_ByClubID_STMT);
			pstmt.setInt(1, clubID);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				clubMBVO = new ClubMBVO();
				clubMBVO.setClubMBID(rs.getInt("clubMBID"));
				clubMBVO.setClubID(rs.getInt("clubID"));
				clubMBVO.setMemID(rs.getInt("memID"));
				clubMBVO.setClubMBContent(rs.getString("clubMBContent"));
				clubMBVO.setClubMBDate(rs.getTimestamp("clubMBDate"));
				clubMBVO.setClubMBStatus(rs.getInt("clubMBStatus"));
				list.add(clubMBVO); // Store the row in the vector
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
	
	
	
	//鑷繁鍔�
		@Override
		public List<ClubNewsVO> getClubNewsByClubID(Integer clubID) {
			List<ClubNewsVO> list = new ArrayList<ClubNewsVO>();
			ClubNewsVO clubNewsVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
		
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ClubNews_ByClubID_STMT);
				pstmt.setInt(1, clubID);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					clubNewsVO = new ClubNewsVO();
					clubNewsVO.setClubNewsID(rs.getInt("clubNewsID"));
					clubNewsVO.setClubID(rs.getInt("clubID"));
					clubNewsVO.setMemID(rs.getInt("memID"));
					clubNewsVO.setClubNewsDate(rs.getTimestamp("clubNewsDate"));
					clubNewsVO.setClubNewsTitle(rs.getString("clubNewsTitle"));
					clubNewsVO.setClubNewsContent(rs.getString("clubNewsContent"));
					clubNewsVO.setActID(rs.getInt("actID"));
					clubNewsVO.setClubNewsStatus(rs.getInt("clubNewsStatus"));
					list.add(clubNewsVO); // Store the row in the vector
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
		
		
		
		
		
		//鑷繁鍔�
		@Override
		public List<ClubMemVO> getClubMemByClubID(Integer clubID) {
			List<ClubMemVO> list = new ArrayList<ClubMemVO>();
			ClubMemVO clubMemVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
		
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ClubMem_ByClubID_STMT);
				pstmt.setInt(1, clubID);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					clubMemVO = new ClubMemVO();
					clubMemVO.setClubID(rs.getInt("clubID"));
					clubMemVO.setMemID(rs.getInt("memID"));
					clubMemVO.setClubMemType(rs.getInt("clubMemType"));
					clubMemVO.setClubMemJoinDate(rs.getTimestamp("clubMemJoinDate"));
					list.add(clubMemVO); // Store the row in the vector
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
		
		
		
		
		
		@Override
		public void clubChange(ClubVO clubVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(CLUBCHANGE);

				pstmt.setString(1, clubVO.getClubName());
				pstmt.setInt(2, clubVO.getClubTypeID());
				pstmt.setString(3, clubVO.getClubContent());				
				pstmt.setBytes(4,clubVO.getClubPhoto());			
				pstmt.setInt(5, clubVO.getClubID());

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
	
	
	
	
	
	
	
	

	public static void main(String[] args) throws IOException {

		ClubJDBCDAO dao = new ClubJDBCDAO();

		// 鏂板OK
//		ClubVO clubVO1 = new ClubVO();
//		clubVO1.setClubmemID(7);
//		clubVO1.setClubName("闅ㄤ究鎵撴墦");
//		clubVO1.setClubTypeID(1);
//		clubVO1.setClubContent("瀹夊畨鎮ㄥソ");
//		
//		byte[] pic1=getByteArray("C:\\Users\\simpl\\Desktop\\ChuMeet锛積bsite\\club\\image\\music.jpg");
//		System.out.println(pic1.length);
//		clubVO1.setClubPhoto(pic1);
//		
//		clubVO1.setClubStartDate(nowTimestamp());
//		clubVO1.setClubStatus(1);
//		clubVO1.setClubLong(1.00006);
//		clubVO1.setClubLat(1.000007);
//		dao.insert(clubVO1);

		// 淇敼OK
//		ClubVO clubVO2 = new ClubVO();
//		clubVO2.setClubID(9);
//		clubVO2.setClubmemID(9);
//		clubVO2.setClubName("鍚崇JAVA!!!!!!!!!");
//		clubVO2.setClubTypeID(2);
//		clubVO2.setClubContent("瑕嚜鎺堣RRRRRRRRRRRRR");
//		
//		byte[] pic1=getByteArray("C:\\Users\\simpl\\Desktop\\ChuMeet锛積bsite\\club\\image\\music.jpg");
//		System.out.println(pic1.length);
//	
//		
//		clubVO2.setClubPhoto(pic1);
//
//		clubVO2.setClubStartDate(nowTimestamp());
//		clubVO2.setClubStatus(1);
//		clubVO2.setClubLong(19.1131131);
//		clubVO2.setClubLat(20.121416);
//		dao.update(clubVO2);

		// 鍒櫎
//		dao.delete(10);
//
		// 鏌ヨOK
//		ClubVO clubVO3 = dao.findByPrimaryKey(2);
//		System.out.print(clubVO3.getClubID() + ",");
//		System.out.print(clubVO3.getClubmemID() + ",");
//		System.out.print(clubVO3.getClubName() + ",");
//		System.out.print(clubVO3.getClubTypeID() + ",");
//		System.out.print(clubVO3.getClubContent() + ",");	
//		
//		System.out.print(clubVO3.getClubPhoto() + ",");	
//		
//		System.out.print(clubVO3.getClubStartDate() + ",");
//		System.out.print(clubVO3.getClubStatus() + ",");
//		System.out.print(clubVO3.getClubLong() + ",");
//		System.out.println(clubVO3.getClubLat());
//		System.out.println("---------------------");

		// 鏌ヨOK
//		List<ClubVO> list = dao.getAll();
//		for (ClubVO aClub : list) {
//			System.out.print(aClub.getClubID() + ",");
//			System.out.print(aClub.getClubmemID() + ",");
//			System.out.print(aClub.getClubName() + ",");
//			System.out.print(aClub.getClubTypeID() + ",");
//			System.out.print(aClub.getClubContent() + ",");
//		
//			System.out.print(aClub.getClubPhoto() + ",");
//		
//			System.out.print(aClub.getClubStartDate() + ",");
//			System.out.print(aClub.getClubStatus() + ",");
//			System.out.print(aClub.getClubLong() + ",");
//			System.out.print(aClub.getClubLat());
//			System.out.println();
//		}
		
		
		//鑷繁鍔犵殑
//		List<ClubAlbumVO> list = dao.getClubAlbumsByClubID(1);
//		for (ClubAlbumVO aClubAlbum : list) {
//			System.out.print(aClubAlbum.getClubAlbumID() + ",");
//			System.out.print(aClubAlbum.getClubID() + ",");
//			System.out.print(aClubAlbum.getMemID() + ",");
//			System.out.print(aClubAlbum.getClubAlbumDate() + ",");
//			System.out.print(aClubAlbum.getClubAlbumStatus() + ",");
//			System.out.print(aClubAlbum.getClubAlbumID());
//			System.out.println();
//		}
		
		//鑷繁鍔犵殑
//		List<ClubNewsVO> list = dao.getClubNewsByClubID(1);
//		for (ClubNewsVO aClubNews : list) {
//			System.out.print(aClubNews.getClubNewsID() + ",");
//			System.out.print(aClubNews.getClubID() + ",");
//			System.out.print(aClubNews.getMemID() + ",");
//			System.out.print(aClubNews.getClubNewsDate() + ",");
//			System.out.print(aClubNews.getClubNewsTitle() + ",");
//			System.out.print(aClubNews.getClubNewsContent()+ ",");
//			System.out.print(aClubNews.getActID());
//			System.out.println();
//		}
		
//		List<ClubMBVO> list = dao.getClubMBByClubID(1);
//		for (ClubMBVO aClubMB : list) {
//			System.out.print(aClubMB.getClubMBID() + ",");
//			System.out.print(aClubMB.getClubID() + ",");
//			System.out.print(aClubMB.getMemID() + ",");
//			System.out.print(aClubMB.getClubMBContent() + ",");
//			System.out.print(aClubMB.getClubMBDate() + ",");
//			System.out.print(aClubMB.getClubMBStatus()+ ",");
//			System.out.println();
//		}
		
//		List<ClubMemVO> list = dao.getClubMemByClubID(2);
//		for (ClubMemVO aClubMem : list) {
//			System.out.print(aClubMem.getClubID() + ",");
//			System.out.print(aClubMem.getMemID() + ",");
//			System.out.print(aClubMem.getClubMemType() + ",");
//			System.out.print(aClubMem.getClubMemJoinDate() + ",");
//			System.out.println();
//		}
		
		
//		// 淇敼OK
//		ClubVO clubVO2 = new ClubVO();
//		clubVO2.setClubID(5);
//		clubVO2.setClubName("鍚崇JAVA!!!!!!!!!");
//		clubVO2.setClubTypeID(2);
//		clubVO2.setClubContent("瑕嚜鎺堣RRRRRRRRRRRRR");
//		byte[] pic2=getByteArray("C:\\Users\\simpl\\Desktop\\ChuMeet锛積bsite\\club\\image\\music.jpg");
//		clubVO2.setClubPhoto(pic2);
//		dao.clubChange(clubVO2);
//		
		
		
		
	}
	
	
	public static byte[] getByteArray(String path) throws IOException {
		FileInputStream fin=new FileInputStream(new File(path));
		byte [] buffer=new byte[fin.available()];
		int bytes_read;
		while((bytes_read=fin.read(buffer))!=-1){   //-1浠ｈ〃娓告鏈夊線涓嬬Щ,鏈夋澅瑗�
		}
		return buffer;
	}

	@Override
	public void clubChangeIfNotPhoto(ClubVO clubVO) {
		// TODO Auto-generated method stub
		
	}
	

	




}