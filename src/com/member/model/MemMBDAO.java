package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.controller.JdbcUtil_CompositeQuery_Member;

public class MemMBDAO implements MemMBDAO_interface {

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
		"INSERT INTO memMB VALUES (MEMMB_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"select * from memMB";
	private static final String GET_ONE_STMT = 
		"select * from memMB where memID = ?";
	private static final String DELETE = 
		"DELETE FROM memMB where memID = ?";
	private static final String UPDATE = 
		"UPDATE memMB set mbContent=?, mbDate=?, mbStatus = ? where memID=?";
	private static final String COUNT_STMT = 
	"select count(*) as \"count\" from memMB where memID=?";
	private String count;

	@Override
	public void insert(MemMBVO memMBVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memMBVO.getMemNFID());
			pstmt.setInt(2, memMBVO.getMemID());
			pstmt.setString(3, memMBVO.getMbContent());
			pstmt.setTimestamp(4, memMBVO.getMbDate());
			pstmt.setInt(5, memMBVO.getMbStatus());

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
	public void update(MemMBVO memMBVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memMBVO.getMbContent());
			pstmt.setTimestamp(2, memMBVO.getMbDate());
			pstmt.setInt(3, memMBVO.getMbStatus());
			pstmt.setInt(4, memMBVO.getMemID());

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
	public void delete(Integer memID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memID);

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
	public MemMBVO findByPrimaryKey(Integer memID) {

		MemMBVO memMBVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memMBVO = new MemMBVO();
				memMBVO.setMemMBID(rs.getInt("memMBID"));
				memMBVO.setMemNFID(rs.getInt("memNFID"));
				memMBVO.setMemID(rs.getInt("memID"));
				memMBVO.setMbContent(rs.getString("mbContent"));
				memMBVO.setMbDate(rs.getTimestamp("mbDate"));
				memMBVO.setMbStatus(rs.getInt("mbStatus"));
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
		return memMBVO;
	}

	@Override
	public List<MemMBVO> getAll() {
		List<MemMBVO> list = new ArrayList<MemMBVO>();
		MemMBVO memMBVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// memMBVO Ҳ�Q�� Domain objects
				memMBVO = new MemMBVO();
				memMBVO.setMemMBID(rs.getInt("memMBID"));
				memMBVO.setMemNFID(rs.getInt("memNFID"));
				memMBVO.setMemID(rs.getInt("memID"));
				memMBVO.setMbContent(rs.getString("mbContent"));
				memMBVO.setMbDate(rs.getTimestamp("mbDate"));
				memMBVO.setMbStatus(rs.getInt("mbStatus"));
				list.add(memMBVO); // Store the row in the list
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
	public void insertHomeMB(MemMBVO memMBVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memMBVO.getMemNFID());
			pstmt.setInt(2, memMBVO.getMemID());
			pstmt.setString(3, memMBVO.getMbContent());
			pstmt.setTimestamp(4, memMBVO.getMbDate());
			pstmt.setInt(5, memMBVO.getMbStatus());

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
	public List<MemMBVO> getAll(Map<String, String[]> map) {
		List<MemMBVO> list = new ArrayList<MemMBVO>();
		MemMBVO memMBVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from memMB "
		          + JdbcUtil_CompositeQuery_Member.get_WhereCondition("MemMB", map)
		          +"order by mbdate Desc";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("〈〈finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memMBVO = new MemMBVO();
				memMBVO.setMemMBID(rs.getInt("memMBID"));
				memMBVO.setMemNFID(rs.getInt("memNFID"));
				memMBVO.setMemID(rs.getInt("memID"));
				memMBVO.setMbContent(rs.getString("mbContent"));
				memMBVO.setMbDate(rs.getTimestamp("mbDate"));
				memMBVO.setMbStatus(rs.getInt("mbStatus"));
				list.add(memMBVO); // Store the row in the list
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
	public String findCountByPrimaryKey(Integer memID) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		count = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(COUNT_STMT);

			pstmt.setInt(1, memID);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				count=rs.getString("count");
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
		return count;
	}
	
}

