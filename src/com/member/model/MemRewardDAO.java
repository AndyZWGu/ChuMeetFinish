package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemRewardDAO implements MemRewardDAO_interface {

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
			"INSERT INTO memReward VALUES (MEMREWARD_SEQ.NEXTVAL, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM memReward";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM memReward where memRew = ?";
		private static final String DELETE = 
			"DELETE FROM memReward where memRewID = ?";
		private static final String UPDATE = 
			"UPDATE memReward set rewDate=?, acquireType=? where memRewID = ?";

	@Override
	public void insert(MemRewardVO memRewardVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memRewardVO.getMemRewID());
			pstmt.setInt(2, memRewardVO.getMemID());
			pstmt.setInt(3, memRewardVO.getRewID());
			pstmt.setTimestamp(4, memRewardVO.getRewDate());
			pstmt.setString(5, memRewardVO.getAcquireType());

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
	public void update(MemRewardVO memRewardVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, memRewardVO.getMemID());
			pstmt.setInt(2, memRewardVO.getRewID());
			pstmt.setInt(3, memRewardVO.getMemRewID());


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
	public void delete(Integer memRewID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memRewID);

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
	public MemRewardVO findByPrimaryKey(Integer memRewID) {

		MemRewardVO memRewardVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memRewID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memRewardVO = new MemRewardVO();
				memRewardVO.setMemRewID(rs.getInt("memRewID"));
				memRewardVO.setMemID(rs.getInt("memID"));
				memRewardVO.setRewID(rs.getInt("rewID"));
				memRewardVO.setRewDate(rs.getTimestamp("rewDate"));
				memRewardVO.setAcquireType(rs.getString("acquireType"));
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
		return memRewardVO;
	}

	@Override
	public List<MemRewardVO> getAll() {
		List<MemRewardVO> list = new ArrayList<MemRewardVO>();
		MemRewardVO memRewardVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memRewardVO = new MemRewardVO();
				memRewardVO.setMemRewID(rs.getInt("memRewID"));
				memRewardVO.setMemID(rs.getInt("memID"));
				memRewardVO.setRewID(rs.getInt("rewID"));
				memRewardVO.setRewDate(rs.getTimestamp("rewDate"));
				memRewardVO.setAcquireType(rs.getString("acquireType"));
				list.add(memRewardVO); // Store the row in the list
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

