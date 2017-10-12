package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.controller.JdbcUtil_CompositeQuery_Member;

public class MemMailDAO implements MemMailDAO_interface {

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
			"INSERT INTO memMail VALUES (MEMMAIL_SEQ.NEXTVAL,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM memMail";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM memMail where memMailID = ?";
		private static final String DELETE = 
			"DELETE FROM memMail where memMailID = ?";
		private static final String UPDATE = 
			"UPDATE memMail set mailTitle=?,mailDate=?,mailContent=? where memMailID = ?";

	@Override
	public void insert(MemMailVO memMailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memMailVO.getReceiver());
			pstmt.setInt(2, memMailVO.getAuthor());
			pstmt.setString(3, memMailVO.getMailTitle());
			pstmt.setTimestamp(4, memMailVO.getMailDate());
			pstmt.setString(5, memMailVO.getMailContent());

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
	public void update(MemMailVO memMailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memMailVO.getMailTitle());
			pstmt.setTimestamp(2, memMailVO.getMailDate());
			pstmt.setString(3, memMailVO.getMailContent());
			pstmt.setInt(4, memMailVO.getMemMailID());

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
	public void delete(Integer memMailID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memMailID);

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
	public MemMailVO findByPrimaryKey(Integer memMailID) {

		MemMailVO memMailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memMailID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memMailVO = new MemMailVO();
				memMailVO.setMemMailID(rs.getInt("memMailID"));
				memMailVO.setReceiver(rs.getInt("receiver"));
				memMailVO.setAuthor(rs.getInt("author"));
				memMailVO.setMailTitle(rs.getString("mailTitle"));
				memMailVO.setMailDate(rs.getTimestamp("mailDate"));
				memMailVO.setMailContent(rs.getString("mailContent"));
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
		return memMailVO;
	}

	@Override
	public List<MemMailVO> getAll() {
		List<MemMailVO> list = new ArrayList<MemMailVO>();
		MemMailVO memMailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memMailVO = new MemMailVO();
				memMailVO.setMemMailID(rs.getInt("memMailID"));
				memMailVO.setReceiver(rs.getInt("receiver"));
				memMailVO.setAuthor(rs.getInt("author"));
				memMailVO.setMailTitle(rs.getString("mailTitle"));
				memMailVO.setMailDate(rs.getTimestamp("mailDate"));
				memMailVO.setMailContent(rs.getString("mailContent"));
				list.add(memMailVO); // Store the row in the list
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
	public List<MemMailVO> getAll(Map<String, String[]> map) {
		List<MemMailVO> list = new ArrayList<MemMailVO>();
		MemMailVO memMailVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from memMail "
		          + JdbcUtil_CompositeQuery_Member.get_WhereCondition("MemMail", map)
		          +"order by mailDate Desc";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("〈〈finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memMailVO = new MemMailVO();
				memMailVO.setMemMailID(rs.getInt("memMailID"));
				memMailVO.setReceiver(rs.getInt("receiver"));
				memMailVO.setAuthor(rs.getInt("author"));
				memMailVO.setMailTitle(rs.getString("mailTitle"));
				memMailVO.setMailDate(rs.getTimestamp("mailDate"));
				memMailVO.setMailContent(rs.getString("mailContent"));
				list.add(memMailVO); // Store the row in the list
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
}

