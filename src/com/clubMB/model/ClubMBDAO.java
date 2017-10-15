package com.clubMB.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ClubMBDAO implements ClubMBDAO_interface {

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO clubMB (clubMBID,clubID,memID,clubMBContent,clubMBDate,clubMBStatus) VALUES (clubMB_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT clubMBID,clubID,memID,clubMBContent,to_char(clubMBDate,'yyyy-mm-dd hh:mm:ss') clubMBDate,clubMBStatus FROM clubMB order by clubMBID";
	private static final String GET_ONE_STMT = "SELECT clubMBID,clubID,memID,clubMBContent,to_char(clubMBDate,'yyyy-mm-dd hh:mm:ss') clubMBDate,clubMBStatus FROM clubMB where clubMBID = ?";
	private static final String DELETE = "DELETE FROM clubMB where clubMBID = ?";
	private static final String UPDATE = "UPDATE clubMB set  clubID=?, memID=?, clubMBContent=?, clubMBDate=?, clubMBStatus=? where clubMBID = ?";

	private static final String GET_ONE_CLUB_MB_STMT = "SELECT clubMBID,clubID,memID,clubMBContent,to_char(clubMBDate,'yyyy-mm-dd hh:mm:ss') clubMBDate,clubMBStatus FROM clubMB where clubID = ? order by clubMBID desc";

//	private static final String GET_MEMNAME_BY_MEMID_STMT = "SELECT memName FROM member where memID = ?";
	@Override
	public void insert(ClubMBVO clubMBVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, clubMBVO.getClubID());
			pstmt.setInt(2, clubMBVO.getMemID());
			pstmt.setString(3, clubMBVO.getClubMBContent());
			pstmt.setTimestamp(4, clubMBVO.getClubMBDate());
			pstmt.setInt(5, clubMBVO.getClubMBStatus());

			pstmt.executeUpdate();

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
	public void update(ClubMBVO clubMBVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, clubMBVO.getClubID());
			pstmt.setInt(2, clubMBVO.getMemID());
			pstmt.setString(3, clubMBVO.getClubMBContent());
			pstmt.setTimestamp(4, clubMBVO.getClubMBDate());
			pstmt.setInt(5, clubMBVO.getClubMBStatus());
			pstmt.setInt(6, clubMBVO.getClubMBID());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(Integer clubMBID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, clubMBID);

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public ClubMBVO findByPrimaryKey(Integer clubMBID) {

		ClubMBVO clubMBVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, clubMBID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				clubMBVO = new ClubMBVO();
				clubMBVO.setClubMBID(rs.getInt("clubMBID"));
				clubMBVO.setClubID(rs.getInt("clubID"));			
				clubMBVO.setMemID(rs.getInt("memID"));
				clubMBVO.setClubMBContent(rs.getString("clubMBContent"));
				clubMBVO.setClubMBDate(rs.getTimestamp("clubMBDate"));
				clubMBVO.setClubMBStatus(rs.getInt("clubMBStatus"));
			}

			// Handle any driver errors
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
		return clubMBVO;
	}

	@Override
	public List<ClubMBVO> getAll() {
		List<ClubMBVO> list = new ArrayList<ClubMBVO>();
		ClubMBVO clubMBVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				clubMBVO = new ClubMBVO();
				clubMBVO.setClubMBID(rs.getInt("clubMBID"));
				clubMBVO.setClubID(rs.getInt("clubID"));
				clubMBVO.setMemID(rs.getInt("memID"));
				clubMBVO.setClubMBContent(rs.getString("clubMBContent"));
				clubMBVO.setClubMBDate(rs.getTimestamp("clubMBDate"));
				clubMBVO.setClubMBStatus(rs.getInt("clubMBStatus"));
				list.add(clubMBVO); // Store the row in the list
			}

			// Handle any driver errors
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
	
	
	
	
	//以下自己加的
	//查詢單一社團全部留言
	@Override
	public List<ClubMBVO> findByClubID(Integer clubID) {
		List<ClubMBVO> list = new ArrayList<ClubMBVO>();
		ClubMBVO clubMBVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CLUB_MB_STMT);
			pstmt.setInt(1, clubID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				clubMBVO = new ClubMBVO();
				clubMBVO.setClubMBID(rs.getInt("clubMBID"));
				clubMBVO.setClubID(rs.getInt("clubID"));
				clubMBVO.setMemID(rs.getInt("memID"));
				clubMBVO.setClubMBContent(rs.getString("clubMBContent"));
				clubMBVO.setClubMBDate(rs.getTimestamp("clubMBDate"));
				clubMBVO.setClubMBStatus(rs.getInt("clubMBStatus"));
				list.add(clubMBVO); // Store the row in the list
			}

			// Handle any driver errors
			// Handle any driver errors
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
	
	
	

	
	
	
	
	
	
	
	
	
	
	
}