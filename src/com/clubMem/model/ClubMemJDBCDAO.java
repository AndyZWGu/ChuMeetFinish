package com.clubMem.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.clubMB.model.ClubMBVO;


public class ClubMemJDBCDAO implements ClubMemDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G2";
	String passwd = "a123";

	private static final String INSERT_STMT = "INSERT INTO clubMem (clubID,memID,clubMemType,clubMemJoinDate,clubMemStatus) VALUES ( ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT clubID, memID , clubMemType,clubMemJoinDate,clubMemStatus FROM clubMem";
	private static final String GET_ONE_STMT = "SELECT memID , clubID, clubMemType, clubMemJoinDate,clubMemStatus FROM clubMem where clubID = ? and memID = ?";
	
	private static final String UPDATE = "UPDATE clubMem set clubMemType=? ,clubMemJoinDate=?, clubMemStatus=? where clubID = ? and memID = ?";

	//浠ヤ笅鑷繁鍔犵殑
	private static final String GET_ALL_CLUBMEM_STMT = "SELECT memID , clubID, clubMemType, clubMemJoinDate,clubMemStatus FROM clubMem where clubID = ?";

	//璁婃洿鏈冨摗TYPE鐐�:1绀鹃暦2.骞归儴3.涓�鑸�
	private static final String changeType = "UPDATE clubMem set clubMemType=? where clubID = ? and memID = ?";
	
	//璁婃洿宸查��鍑虹殑鏈冨摗鍐嶅姞鍏�
	private static final String update_ClubMem_Status = "UPDATE clubMem set clubMemStatus=? where clubID = ? and memID = ?";

	//鎵炬煇鏈冨摗鏈夊弮鍔犻偅浜涚ぞ鍦�
	private static final String FIND_ALL_JOIN_CLUB = "SELECT memID , clubID, clubMemType, clubMemJoinDate,clubMemStatus FROM clubMem where memID = ? and clubmemstatus=1";
	
	@Override
	public void insert(ClubMemVO clubMemVO) {


		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, clubMemVO.getClubID());
			pstmt.setInt(2, clubMemVO.getMemID());
			pstmt.setInt(3, clubMemVO.getClubMemType());
			pstmt.setTimestamp(4, clubMemVO.getClubMemJoinDate());
			pstmt.setInt(5, clubMemVO.getClubMemStatus());		
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
	public void update(ClubMemVO clubMemVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setInt(1, clubMemVO.getClubMemType());
			pstmt.setTimestamp(2, clubMemVO.getClubMemJoinDate());
			pstmt.setInt(3, clubMemVO.getClubMemStatus());	
			pstmt.setInt(4, clubMemVO.getClubID());
			pstmt.setInt(5, clubMemVO.getMemID());
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
	public ClubMemVO findByPrimaryKey(Integer clubID,Integer memID) {

		ClubMemVO clubMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, clubID);
			pstmt.setInt(2, memID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 涔熺ū鐐� Domain objects
				clubMemVO = new ClubMemVO();
				clubMemVO.setClubID(rs.getInt("clubID"));
				clubMemVO.setMemID(rs.getInt("memID"));
				clubMemVO.setClubMemType(rs.getInt("clubMemType"));
				clubMemVO.setClubMemJoinDate(rs.getTimestamp("clubMemJoinDate"));
				clubMemVO.setClubMemStatus(rs.getInt("clubMemStatus"));
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
		return clubMemVO;
	}
//
	@Override
	public List<ClubMemVO> getAll() {
		List<ClubMemVO> list = new ArrayList<ClubMemVO>();
		ClubMemVO clubMemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				clubMemVO = new ClubMemVO();
				clubMemVO.setClubID(rs.getInt("clubID"));
				clubMemVO.setMemID(rs.getInt("memID"));
				clubMemVO.setClubMemType(rs.getInt("clubMemType"));
				clubMemVO.setClubMemJoinDate(rs.getTimestamp("clubMemJoinDate"));
				clubMemVO.setClubMemStatus(rs.getInt("clubMemStatus"));
				list.add(clubMemVO); // Store the row in the list
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
	public List<ClubMemVO> findMemByClubID(Integer clubID) {
		List<ClubMemVO> list = new ArrayList<ClubMemVO>();
		ClubMemVO clubMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_CLUBMEM_STMT);

			pstmt.setInt(1, clubID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 涔熺ū鐐� Domain objects
				clubMemVO = new ClubMemVO();
				clubMemVO.setClubID(rs.getInt("clubID"));
				clubMemVO.setMemID(rs.getInt("memID"));
				clubMemVO.setClubMemType(rs.getInt("clubMemType"));
				clubMemVO.setClubMemJoinDate(rs.getTimestamp("clubMemJoinDate"));
				clubMemVO.setClubMemStatus(rs.getInt("clubMemStatus"));
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
	
	
	
	@Override
	public void changeType(Integer memID, Integer clubID) {

		ClubMemVO clubMemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);


			pstmt.setInt(1, clubMemVO.getClubMemType());
			pstmt.setInt(2, clubMemVO.getClubID());
			pstmt.setInt(3, clubMemVO.getMemID());
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
	
	
	
	
	
	//鏀圭ぞ鍦樻垚鍝＄媭鎱嬮倓娌掑仛
//	@Override
//	public void updateClubMemStatus(Integer memID, Integer clubID) {
//
//		ClubMemVO clubMemVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(update_ClubMem_Status);
//
//
//			pstmt.setInt(1, clubMemVO.getClubMemStatus());
//			pstmt.setInt(2, clubMemVO.getClubID());
//			pstmt.setInt(3, clubMemVO.getMemID());
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
//	}
	
	
	
	
	@Override
	public List<ClubMemVO> getAllJoinClub(Integer memID) {
		List<ClubMemVO> list = new ArrayList<ClubMemVO>();
		ClubMemVO clubMemVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_ALL_JOIN_CLUB);
			
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				clubMemVO = new ClubMemVO();
				clubMemVO.setMemID(rs.getInt("memID"));
				clubMemVO.setClubID(rs.getInt("clubID"));
				clubMemVO.setClubMemType(rs.getInt("clubMemType"));
				clubMemVO.setClubMemJoinDate(rs.getTimestamp("clubMemJoinDate"));
				clubMemVO.setClubMemStatus(rs.getInt("clubMemStatus"));
				list.add(clubMemVO); // Store the row in the list
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

		ClubMemJDBCDAO dao = new ClubMemJDBCDAO();

		// 鏂板OK
//		ClubMemVO clubMemVO1 = new ClubMemVO();
//		clubMemVO1.setClubID(7);
//		clubMemVO1.setMemID(3);
//		clubMemVO1.setClubMemType(1);
//		clubMemVO1.setClubMemJoinDate(java.sql.Date.valueOf("2017-09-12"));
//		dao.insert(clubMemVO1);

		
		
		// 淇敼OK
//		ClubMemVO clubMemVO2 = new ClubMemVO();
//		clubMemVO2.setClubMemType(3);
//		clubMemVO2.setClubMemJoinDate(java.sql.Date.valueOf("2017-07-30"));
//		clubMemVO2.setClubID(7);
//		clubMemVO2.setMemID(2);
//		dao.update(clubMemVO2);


		// 鍒櫎閭勬矑鍋�
		//dao.delete(30);

		// 鏌ヨOK
//		ClubMemVO clubMemVO3 = dao.findByPrimaryKey(1,1);
//		System.out.print(clubMemVO3.getClubID() + ",");
//		System.out.print(clubMemVO3.getMemID() + ",");
//		System.out.println(clubMemVO3.getClubMemType()+ ",");
//		System.out.println(clubMemVO3.getClubMemJoinDate());	
//		System.out.print(clubMemVO3.getClubMemStatus() + ",");
//		System.out.println("---------------------");

		// 鏌ヨ閮ㄩ杸OK
//		List<ClubMemVO> list = dao.getAll();
//		for (ClubMemVO aClubMem : list) {
//			System.out.print(aClubMem.getClubID() + ",");
//			System.out.print(aClubMem.getMemID() + ",");
//			System.out.print(aClubMem.getClubMemType() + ",");
//			System.out.print(aClubMem.getClubMemJoinDate());
//			System.out.println();
//		}


		
		// 鏌ヨ鏌愰儴闁�鐨勫摗宸ラ倓娌�
//		Set<EmpVO> set = dao.getEmpsByDeptno(10);
//		for (EmpVO aEmp : set) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			System.out.print(aEmp.getDeptno());
//			System.out.println();
//		}
		
//		鏌ヨ鏌愮ぞ鍦樻垚鍝�
//		List<ClubMemVO> list = dao.getAll();
//		for (ClubMemVO aClubMem : list) {
//			System.out.print(aClubMem.getClubID() + ",");
//			System.out.print(aClubMem.getMemID() + ",");
//			System.out.print(aClubMem.getClubMemType() + ",");
//			System.out.print(aClubMem.getClubMemJoinDate());
//			System.out.println();
//		}
		
		
		
//		鏌ヨ鏌愭垚鍝″姞鍏ラ偅浜涚ぞ鍦�
		List<ClubMemVO> list = dao.getAllJoinClub(1);
		for (ClubMemVO aClubMem : list) {
			System.out.print(aClubMem.getClubID() + ",");
			System.out.print(aClubMem.getMemID() + ",");
			System.out.print(aClubMem.getClubMemType() + ",");
			System.out.print(aClubMem.getClubMemJoinDate() + ",");
			System.out.print(aClubMem.getClubMemStatus());
			System.out.println();
		}
		
		
		
		
		
	}

	@Override
	public void updateClubMemType(ClubMemVO clubMemVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeMemStatus(ClubMemVO clubMemVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitMemStatus(ClubMemVO clubMemVO) {
		// TODO Auto-generated method stub
		
	}


}