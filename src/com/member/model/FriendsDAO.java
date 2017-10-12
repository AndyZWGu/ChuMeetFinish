package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.controller.JdbcUtil_CompositeQuery_Member;

public class FriendsDAO implements FriendsDAO_interface {

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

	private static final String INSERT_STMT = 
			"INSERT INTO friends VALUES (?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM friends";
		private static final String GET_ALL_BY_FRIMEM1 = 
			"SELECT * FROM friends where friMem1 = ?";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM friends where friMem1 = ? and friMem2 = ?";
		private static final String DELETE = 
			"DELETE FROM friends where friMem1 = ?";
		private static final String UPDATE = 
			"UPDATE friends set friendType= ?,friendDate=? where friMem1 = ? and friMem2=?";

	@Override
	public void insert(FriendsVO friendsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, friendsVO.getFriMem1());
			pstmt.setInt(2, friendsVO.getFriMem2());
			pstmt.setString(3, friendsVO.getFriendType());
			pstmt.setTimestamp(4, friendsVO.getFriendDate());

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
	public void update(FriendsVO friendsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, friendsVO.getFriendType());
			pstmt.setTimestamp(2, friendsVO.getFriendDate());
			pstmt.setInt(3, friendsVO.getFriMem1());
			pstmt.setInt(4, friendsVO.getFriMem2());

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
	public void delete(Integer friMem1) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, friMem1);

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
	public FriendsVO findByPrimaryKey(Integer friMem1,Integer friMem2) {

		FriendsVO friendsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, friMem1);
			pstmt.setInt(2, friMem2);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendsVO = new FriendsVO();
				friendsVO.setFriMem1(rs.getInt("friMem1"));
				friendsVO.setFriMem2(rs.getInt("friMem2"));
				friendsVO.setFriendType(rs.getString("friendType"));
				friendsVO.setFriendDate(rs.getTimestamp("friendDate"));
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
		return friendsVO;
	}

	@Override
	public List<FriendsVO> getAll() {
		List<FriendsVO> list = new ArrayList<FriendsVO>();
		FriendsVO friendsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendsVO = new FriendsVO();
				friendsVO.setFriMem1(rs.getInt("friMem1"));
				friendsVO.setFriMem2(rs.getInt("friMem2"));
				friendsVO.setFriendType(rs.getString("friendType"));
				friendsVO.setFriendDate(rs.getTimestamp("friendDate"));
				list.add(friendsVO); // Store the row in the list
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

	@Override
	public List<FriendsVO> getAll(Map<String, String[]> map) {
		List<FriendsVO> list = new ArrayList<FriendsVO>();
		FriendsVO friendsVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from friends "
		          + JdbcUtil_CompositeQuery_Member.get_WhereCondition("Friends", map)
		          ;
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("〈〈finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				friendsVO = new FriendsVO();
				friendsVO.setFriMem1(rs.getInt("memID1"));
				friendsVO.setFriMem2(rs.getInt("memID2"));
				friendsVO.setFriendType(rs.getString("friendType"));
				friendsVO.setFriendDate(rs.getTimestamp("friendDate"));
				list.add(friendsVO); // Store the row in the list
			}
	
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
	public List<FriendsVO> findByPrimaryKeyAll(Integer friMem1) {
		List<FriendsVO> list = new ArrayList<FriendsVO>();
		FriendsVO friendsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_FRIMEM1);
			pstmt.setInt(1, friMem1);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				friendsVO = new FriendsVO();
				friendsVO.setFriMem1(rs.getInt("friMem1"));
				friendsVO.setFriMem2(rs.getInt("friMem2"));
				friendsVO.setFriendType(rs.getString("friendType"));
				friendsVO.setFriendDate(rs.getTimestamp("friendDate"));
				list.add(friendsVO); // Store the row in the list
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
}

