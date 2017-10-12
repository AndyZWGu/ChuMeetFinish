package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class RewardDAO implements RewardDAO_interface {

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
			"INSERT INTO reward VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM reward";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM reward where rewID = ?";
		private static final String DELETE = 
			"DELETE FROM reward where rewID = ?";
		private static final String UPDATE = 
			"UPDATE reward set rewName=?, rewContent=?, rewExp=?, rewPt=?, rewDesc=?, rewType=?, rewStatus=? where rewID = ?";

	@Override
	public void insert(RewardVO rewardVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, rewardVO.getRewID());
			pstmt.setString(2, rewardVO.getRewName());
			pstmt.setBytes(3, rewardVO.getRewContent());
			pstmt.setDouble(4, rewardVO.getRewExp());
			pstmt.setDouble(5, rewardVO.getRewPt());
			pstmt.setString(6, rewardVO.getRewDesc());
			pstmt.setString(7, rewardVO.getRewType());
			pstmt.setInt(8, rewardVO.getRewStatus());

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
	public void update(RewardVO rewardVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, rewardVO.getRewName());
			pstmt.setBytes(2, rewardVO.getRewContent());
			pstmt.setDouble(3, rewardVO.getRewExp());
			pstmt.setDouble(4, rewardVO.getRewPt());
			pstmt.setString(5, rewardVO.getRewDesc());
			pstmt.setString(6, rewardVO.getRewType());
			pstmt.setInt(7, rewardVO.getRewStatus());
			pstmt.setInt(8, rewardVO.getRewID());

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
	public void delete(Integer rewID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rewID);

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
	public RewardVO findByPrimaryKey(Integer rewID) {

		RewardVO rewardVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rewID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				rewardVO = new RewardVO();
				rewardVO.setRewID(rs.getInt("rewID"));
				rewardVO.setRewName(rs.getString("rewName"));
				rewardVO.setRewContent(rs.getBytes("rewContent"));
				rewardVO.setRewExp(rs.getInt("rewExp"));
				rewardVO.setRewPt(rs.getInt("rewPt"));
				rewardVO.setRewDesc(rs.getString("RewDesc"));
				rewardVO.setRewType(rs.getString("RewType"));
				rewardVO.setRewStatus(rs.getInt("RewStatus"));
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
		return rewardVO;
	}

	@Override
	public List<RewardVO> getAll() {
		List<RewardVO> list = new ArrayList<RewardVO>();
		RewardVO rewardVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rewardVO = new RewardVO();
				rewardVO.setRewID(rs.getInt("rewID"));
				rewardVO.setRewName(rs.getString("rewName"));
				rewardVO.setRewContent(rs.getBytes("rewContent"));
				rewardVO.setRewExp(rs.getInt("rewExp"));
				rewardVO.setRewPt(rs.getInt("rewPt"));
				rewardVO.setRewDesc(rs.getString("RewDesc"));
				rewardVO.setRewType(rs.getString("RewType"));
				rewardVO.setRewStatus(rs.getInt("RewStatus"));
				list.add(rewardVO); // Store the row in the list
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

