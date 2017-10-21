package com.member.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.controller.JdbcUtil_CompositeQuery_Member;

public class MemNFDAO implements MemNFDAO_interface {

	// 涓�鍊嬫噳鐢ㄧ▼寮忎腑,閲濆皪涓�鍊嬭硣鏂欏韩 ,鍏辩敤涓�鍊婦ataSource鍗冲彲
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
			"INSERT INTO memNF VALUES (MEMNF_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT * FROM memNF where nfstatus=1 order by nfDate Desc";
		private static final String GET_ONE_STMT = 
			"SELECT * FROM memNF where memNFID = ?";
		private static final String DELETE = 
			"DELETE FROM memNF where memNFID = ?";
		private static final String UPDATE = 
			"UPDATE memNF set nfTitle=?, nfContent=?, nfPic=?, nfDate=?, nfViews=?, nfStatus=? where memNFID = ?";

		private static final String UPDATE_VIEWS = 
				"UPDATE memNF set nfViews=nfVIews+1 where memNFID = ?";
		
		private static final String GET_ALL_BY_MEMID = 
				"SELECT * FROM memNF where memID = ? and nfstatus=1 order by nfDate Desc";
		private static final String COUNT_STMT = 
				"select count(*) as \"count\" from memNF where memID=?";
		private String count;

	@Override
	public void insert(MemNFVO memNFVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memNFVO.getMemID());
			pstmt.setString(2, memNFVO.getNfTitle());
			pstmt.setString(3, memNFVO.getNfContent());
			pstmt.setBytes(4, memNFVO.getNfPic());
			pstmt.setTimestamp(5, memNFVO.getNfDate());
			pstmt.setInt(6, memNFVO.getNfViews());
			pstmt.setInt(7, memNFVO.getNfStatus());
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
	public void update(MemNFVO memNFVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memNFVO.getNfTitle());
			pstmt.setString(2, memNFVO.getNfContent());
			pstmt.setBytes(3, memNFVO.getNfPic());
			pstmt.setTimestamp(4, memNFVO.getNfDate());
			pstmt.setInt(5, memNFVO.getNfViews());
			pstmt.setInt(6, memNFVO.getNfStatus());
			pstmt.setInt(7, memNFVO.getMemNFID());

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
	public void updateViews(Integer memNFID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_VIEWS);
			pstmt.setInt(1, memNFID);

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
	public void delete(Integer memNFID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memNFID);

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
	public MemNFVO findByPrimaryKey(Integer memNFID) {

		MemNFVO memNFVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memNFID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memNFVO = new MemNFVO();
				memNFVO.setMemNFID(rs.getInt("memNFID"));
				memNFVO.setMemID(rs.getInt("memID"));
				memNFVO.setNfTitle(rs.getString("nfTitle"));
				memNFVO.setNfContent(rs.getString("nfContent"));
				memNFVO.setNfPic(rs.getBytes("nfPic"));
				memNFVO.setNfDate(rs.getTimestamp("nfDate"));
				memNFVO.setNfViews(rs.getInt("nfViews"));
				memNFVO.setNfStatus(rs.getInt("nfStatus"));
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
		return memNFVO;
	}

	@Override
	public List<MemNFVO> getAll() {
		List<MemNFVO> list = new ArrayList<MemNFVO>();
		MemNFVO memNFVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memNFVO = new MemNFVO();
				memNFVO.setMemNFID(rs.getInt("memNFID"));
				memNFVO.setMemID(rs.getInt("memID"));
				memNFVO.setNfTitle(rs.getString("nfTitle"));
				memNFVO.setNfContent(rs.getString("nfContent"));
				memNFVO.setNfPic(rs.getBytes("nfPic"));
				memNFVO.setNfDate(rs.getTimestamp("nfDate"));
				memNFVO.setNfViews(rs.getInt("nfViews"));
				memNFVO.setNfStatus(rs.getInt("nfStatus"));
				list.add(memNFVO); // Store the row in the list
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
	public List<MemNFVO> getAll(Map<String, String[]> map) {
		List<MemNFVO> list = new ArrayList<MemNFVO>();
		MemNFVO memNFVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from memNF "
		          + JdbcUtil_CompositeQuery_Member.get_WhereCondition("MemNF", map)
		          ;
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("銆堛�坒inalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memNFVO = new MemNFVO();
				memNFVO.setMemNFID(rs.getInt("memNFID"));
				memNFVO.setMemID(rs.getInt("memID"));
				memNFVO.setNfTitle(rs.getString("nfTitle"));
				memNFVO.setNfContent(rs.getString("nfContent"));
				memNFVO.setNfPic(rs.getBytes("nfPic"));
				memNFVO.setNfDate(rs.getTimestamp("nfDate"));
				memNFVO.setNfViews(rs.getInt("nfViews"));
				memNFVO.setNfStatus(rs.getInt("nfStatus"));
				list.add(memNFVO); // Store the row in the list
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
	public List<MemNFVO> findByMemID(Integer memID) {
		List<MemNFVO> list = new ArrayList<MemNFVO>();
		MemNFVO memNFVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_MEMID);
			pstmt.setInt(1, memID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memNFVO = new MemNFVO();
				memNFVO.setMemNFID(rs.getInt("memNFID"));
				memNFVO.setMemID(rs.getInt("memID"));
				memNFVO.setNfTitle(rs.getString("nfTitle"));
				memNFVO.setNfContent(rs.getString("nfContent"));
				memNFVO.setNfPic(rs.getBytes("nfPic"));
				memNFVO.setNfDate(rs.getTimestamp("nfDate"));
				memNFVO.setNfViews(rs.getInt("nfViews"));
				memNFVO.setNfStatus(rs.getInt("nfStatus"));
				list.add(memNFVO); // Store the row in the list
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
	public String findCountByMemID(Integer memID) {
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


