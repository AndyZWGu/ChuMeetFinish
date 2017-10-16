package com.albumTicket.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class AlbumTicketJBDCDAO implements AlbumTicketDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G2";
	String passwd = "a123";

	private static final String INSERT_STMT = 											
		"INSERT INTO albumTicket  VALUES (albumTicket_seq.NEXTVAL, ?, ?, ?, ?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT albumTkID,reporter,clubAlbumID,albTkMsg,albTkStatID,to_char(albTkDate,'yyyy-mm-dd hh:mm:ss')albTkDate,albTkCat FROM albumTicket order by albumTkID";
	private static final String GET_ONE_STMT = 
		"SELECT albumTkID,reporter,clubAlbumID,albTkMsg,albTkStatID,to_char(albTkDate,'yyyy-mm-dd hh:mm:ss')albTkDate,albTkCat where albumTkID= ?";
	private static final String DELETE = 
		"DELETE FROM albumTicket where albumTkID = ?";
	private static final String UPDATE = 
		"UPDATE albumTicket set reporter=?,clubAlbumID=?,albTkMsg=?,albTkStatID=?,albTkDate=?,albTkCat=?  where albumTkID = ?";

	
	
	@Override
	public void insert(AlbumTicketVO albumTicketVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, albumTicketVO.getReporter());
			pstmt.setInt(2, albumTicketVO.getClubAlbumID());
			pstmt.setString(3, albumTicketVO.getAlbTkMsg());
			pstmt.setInt(4, albumTicketVO.getAlbTkStatID());
			pstmt.setTimestamp(5, albumTicketVO.getAlbTkDate());
			pstmt.setInt(6, albumTicketVO.getAlbTkCat());
			
		
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
	public void update(AlbumTicketVO albumTicketVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
		
			pstmt.setInt(1, albumTicketVO.getReporter());
			pstmt.setInt(2, albumTicketVO.getClubAlbumID());
			pstmt.setString(3, albumTicketVO.getAlbTkMsg());
			pstmt.setInt(4, albumTicketVO.getAlbTkStatID());
			pstmt.setTimestamp(5, albumTicketVO.getAlbTkDate());
			pstmt.setInt(6, albumTicketVO.getAlbTkCat());
			pstmt.setInt(7, albumTicketVO.getAlbumTkID());


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
	public void delete(Integer albumTkID) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, albumTkID);

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
	public AlbumTicketVO findByPrimaryKey(Integer albumTkID) {
		// TODO Auto-generated method stub
		AlbumTicketVO albumTicketVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, albumTkID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memTicketVO �]�٬� Domain objects
				albumTicketVO = new AlbumTicketVO();
				albumTicketVO.setAlbumTkID(rs.getInt("albumTkID"));			
				albumTicketVO.setReporter(rs.getInt("reporter"));
				albumTicketVO.setClubAlbumID(rs.getInt("clubAlbumID"));
				albumTicketVO.setAlbTkMsg(rs.getString("albTkMsg"));
				albumTicketVO.setAlbTkStatID(rs.getInt("albTkStatID"));
				albumTicketVO.setAlbTkDate(rs.getTimestamp("albTkDate"));
				albumTicketVO.setAlbTkCat(rs.getInt("albTkCat"));

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
		return albumTicketVO;
	}
	@Override
	public List<AlbumTicketVO> getAll() {
		// TODO Auto-generated method stub
		List<AlbumTicketVO> list = new ArrayList<AlbumTicketVO>();
		AlbumTicketVO albumTicketVO = null;

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
				albumTicketVO = new AlbumTicketVO();
				albumTicketVO.setAlbumTkID(rs.getInt("albumTkID"));
				albumTicketVO.setReporter(rs.getInt("reporter"));
				albumTicketVO.setClubAlbumID(rs.getInt("clubAlbumID"));
				albumTicketVO.setAlbTkMsg(rs.getString("albTkMsg"));
				albumTicketVO.setAlbTkStatID(rs.getInt("albTkStatID"));
				albumTicketVO.setAlbTkDate(rs.getTimestamp("albTkDate"));
				albumTicketVO.setAlbTkCat(rs.getInt("albTkCat"));
//				albumTkID, reporter, clubAlbumID, albTkMsg, albTkStatID, albTkDate, albTkCat
				list.add(albumTicketVO); // Store the row in the list
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

		AlbumTicketJBDCDAO dao = new AlbumTicketJBDCDAO();

//		// �s�W
		AlbumTicketVO albumTicketVO1 = new AlbumTicketVO();
		albumTicketVO1.setAlbumTkID(1);
		albumTicketVO1.setReporter(12);
		albumTicketVO1.setAlbumTkID(1);
		albumTicketVO1.setAlbTkMsg("AlbTkMsg");
		albumTicketVO1.setAlbTkStatID(1);
		albumTicketVO1.setAlbTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
		albumTicketVO1.setAlbTkCat(2);
		dao.insert(albumTicketVO1);

//		// �ק�
		AlbumTicketVO albumTicketVO2 = new AlbumTicketVO();
		albumTicketVO2.setAlbumTkID(1);
		albumTicketVO2.setReporter(12);
		albumTicketVO2.setAlbumTkID(2);
		albumTicketVO2.setAlbTkMsg("AlbTkMsg");
		albumTicketVO2.setAlbTkStatID(1);
		albumTicketVO2.setAlbTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
		albumTicketVO2.setAlbTkCat(2);
		

		dao.update(albumTicketVO2);
		// �R��
		dao.delete(1);
////		 
////		// �d��
		AlbumTicketVO albumTicketVO3 = dao.findByPrimaryKey(1);
		System.out.print(albumTicketVO3.getAlbumTkID() + ",");
		System.out.print(albumTicketVO3.getReporter() + ",");
		System.out.print(albumTicketVO3.getClubAlbumID() + ",");
		System.out.print(albumTicketVO3.getAlbTkMsg() + ",");
		System.out.print(albumTicketVO3.getAlbTkStatID() + ",");
		System.out.print(albumTicketVO3.getAlbTkDate() + ",");
		System.out.print(albumTicketVO3.getAlbTkCat() + ",");
		System.out.println("---------------------");

		// �d��
		List<AlbumTicketVO> list = dao.getAll();
		for (AlbumTicketVO aAlbumTicket : list) {
			System.out.print(aAlbumTicket.getAlbumTkID() + ",");
			System.out.print(aAlbumTicket.getReporter() + ",");
			System.out.print(aAlbumTicket.getClubAlbumID() + ",");
			System.out.print(aAlbumTicket.getAlbTkMsg() + ",");
			System.out.print(aAlbumTicket.getAlbTkStatID() + ",");
			System.out.print(aAlbumTicket.getAlbTkDate() + ",");
			System.out.print(aAlbumTicket.getAlbTkCat() + ",");
			System.out.println();
		}
	}

}
