package com.member.model;

import java.util.*;
import java.sql.*;
import java.text.ParseException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.controller.*;

public class MemberDAO implements MemberDAO_interface {

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
		"INSERT INTO member VALUES (MEMBER_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM member order by memID";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM member where memID = ?";
	private static final String DELETE = 
		"DELETE FROM member where memID = ?";
	private static final String UPDATE = 
		"UPDATE member set memEmail= ?,memPw= ?,memberType= ?,memLv= ?,memExp= ?,memPt= ?,memName= ?,memGender= ?,memBD= ?,memPhone= ?,memAvatar= ?,memJoinDate= ?,memLoginNum= ?,memLocBorn= ?,memLocLive= ?,memInt= ?,memLong= ?,memLat= ?,memPriv= ?,memStatus= ? where memID= ?";
	//登入用
	private static final String GET_ONE_STMT_BY_MEMEMAIL = 
			"SELECT memID,memEmail,memPw,memberType,memLv,memExp,memPt,memName,memGender,memBD,memPhone,memAvatar,memJoinDate,memLoginNum,memLocBorn,memLocLive,memInt,memLong,memLat,memPriv,memStatus FROM member where memEmail = ?";
	private static final String GET_ONE_STMT_BY_MEMPW = 
			"SELECT memID,memEmail,memPw,memberType,memLv,memExp,memPt,memName,memGender,memBD,memPhone,memAvatar,memJoinDate,memLoginNum,memLocBorn,memLocLive,memInt,memLong,memLat,memPriv,memStatus FROM member where memPw = ?";
	//拿照片用
	private static final String GET_AVATAR_STMT_BY_MEMID = 
			"SELECT memAvatar FROM member where memID = ?";
	//其他表透過memID來取用
	private static final String GET_ALL_BY_TABLE = 
			"SELECT memAvatar FROM member,? where member.memID = ?.memID";
	
	
	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, memberVO.getMemEmail());
			pstmt.setString(2, memberVO.getMemPw());
			pstmt.setInt(3, memberVO.getMemberType());
			pstmt.setInt(4, memberVO.getMemLv());
			pstmt.setInt(5, memberVO.getMemExp());
			pstmt.setInt(6, memberVO.getMemPt());
			pstmt.setString(7, memberVO.getMemName());
			pstmt.setInt(8, memberVO.getMemGender());
			pstmt.setTimestamp(9, memberVO.getMemBD());
			pstmt.setString(10, memberVO.getMemPhone());
			pstmt.setBytes(11, memberVO.getMemAvatar());
			pstmt.setTimestamp(12, memberVO.getMemJoinDate());
			pstmt.setInt(13, memberVO.getMemLoginNum());
			pstmt.setString(14, memberVO.getMemLocBorn());
			pstmt.setString(15, memberVO.getMemLocLive());
			pstmt.setString(16, memberVO.getMemInt());
			pstmt.setDouble(17, memberVO.getMemLong());
			pstmt.setDouble(18, memberVO.getMemLat());
			pstmt.setDouble(19, memberVO.getMemPriv());
			pstmt.setInt(20, memberVO.getMemStatus());
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
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, memberVO.getMemEmail());
			pstmt.setString(2, memberVO.getMemPw());
			pstmt.setInt(3, memberVO.getMemberType());
			pstmt.setInt(4, memberVO.getMemLv());
			pstmt.setInt(5, memberVO.getMemExp());
			pstmt.setInt(6, memberVO.getMemPt());
			pstmt.setString(7, memberVO.getMemName());
			pstmt.setInt(8, memberVO.getMemGender());
			pstmt.setTimestamp(9, memberVO.getMemBD());
			pstmt.setString(10, memberVO.getMemPhone());
			pstmt.setBytes(11, memberVO.getMemAvatar());
			pstmt.setTimestamp(12, memberVO.getMemJoinDate());
			pstmt.setInt(13, memberVO.getMemLoginNum());
			pstmt.setString(14, memberVO.getMemLocBorn());
			pstmt.setString(15, memberVO.getMemLocLive());
			pstmt.setString(16, memberVO.getMemInt());
			pstmt.setDouble(17, memberVO.getMemLong());
			pstmt.setDouble(18, memberVO.getMemLat());
			pstmt.setDouble(19, memberVO.getMemPriv());
			pstmt.setInt(20, memberVO.getMemStatus());
			pstmt.setInt(21, memberVO.getMemID());
			System.out.println(pstmt);
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
	public MemberVO findByPrimaryKey(Integer memID) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemID(rs.getInt("memID"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemPw(rs.getString("memPw"));
				memberVO.setMemberType(rs.getInt("memberType"));
				memberVO.setMemLv(rs.getInt("memLv"));
				memberVO.setMemExp(rs.getInt("memExp"));
				memberVO.setMemPt(rs.getInt("memPt"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemGender(rs.getInt("memGender"));
				memberVO.setMemBD(rs.getTimestamp("memBD"));
				memberVO.setMemPhone(rs.getString("memPhone"));
				memberVO.setMemAvatar(rs.getBytes("memAvatar"));
				memberVO.setMemJoinDate(rs.getTimestamp("memJoinDate"));
				memberVO.setMemLoginNum(rs.getInt("memLoginNum"));
				memberVO.setMemLocBorn(rs.getString("memLocBorn"));
				memberVO.setMemLocLive(rs.getString("memLocLive"));
				memberVO.setMemInt(rs.getString("memInt"));
				memberVO.setMemLong(rs.getDouble("memLong"));
				memberVO.setMemLat(rs.getDouble("memLat"));
				memberVO.setMemPriv(rs.getInt("memPriv"));
				memberVO.setMemStatus(rs.getInt("memStatus"));
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
		return memberVO;
	}
	
	@Override
	public ResultSet getAvatarByMemID(Integer memID) {
//		List<String> base64list = new ArrayList<String>();//base64作法
		List<byte[]> list = new ArrayList<byte[]>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_AVATAR_STMT_BY_MEMID);
			
			pstmt.setInt(1, memID);
			
			rs = pstmt.executeQuery();
			//base64作法
//			while (rs.next()) {
//				byte[] memAvatar = rs.getBytes("memAvatar"); 
//			    //base64作法
//				//list.add(org.apache.commons.codec.binary.Base64.encodeBase64String(memAvatar));
//				list.add(memAvatar);
//			}

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
		return rs;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemID(rs.getInt("memID"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemPw(rs.getString("memPw"));
				memberVO.setMemberType(rs.getInt("memberType"));
				memberVO.setMemLv(rs.getInt("memLv"));
				memberVO.setMemExp(rs.getInt("memExp"));
				memberVO.setMemPt(rs.getInt("memPt"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemGender(rs.getInt("memGender"));
				memberVO.setMemBD(rs.getTimestamp("memBD"));
				memberVO.setMemPhone(rs.getString("memPhone"));
				memberVO.setMemAvatar(rs.getBytes("memAvatar"));
				memberVO.setMemJoinDate(rs.getTimestamp("memJoinDate"));
				memberVO.setMemLoginNum(rs.getInt("memLoginNum"));
				memberVO.setMemLocBorn(rs.getString("memLocBorn"));
				memberVO.setMemLocLive(rs.getString("memLocLive"));
				memberVO.setMemInt(rs.getString("memInt"));
				memberVO.setMemLong(rs.getDouble("memLong"));
				memberVO.setMemLat(rs.getDouble("memLat"));
				memberVO.setMemPriv(rs.getInt("memPriv"));
				memberVO.setMemStatus(rs.getInt("memStatus"));
				list.add(memberVO); // Store the row in the list
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
	public MemberVO findByMemEmail(String memEmail) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_MEMEMAIL);

			pstmt.setString(1, memEmail);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemID(rs.getInt("memID"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemPw(rs.getString("memPw"));
				memberVO.setMemberType(rs.getInt("memberType"));
				memberVO.setMemLv(rs.getInt("memLv"));
				memberVO.setMemExp(rs.getInt("memExp"));
				memberVO.setMemPt(rs.getInt("memPt"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemGender(rs.getInt("memGender"));
				memberVO.setMemBD(rs.getTimestamp("memBD"));
				memberVO.setMemPhone(rs.getString("memPhone"));
				memberVO.setMemAvatar(rs.getBytes("memAvatar"));
				memberVO.setMemJoinDate(rs.getTimestamp("memJoinDate"));
				memberVO.setMemLoginNum(rs.getInt("memLoginNum"));
				memberVO.setMemLocBorn(rs.getString("memLocBorn"));
				memberVO.setMemLocLive(rs.getString("memLocLive"));
				memberVO.setMemInt(rs.getString("memInt"));
				memberVO.setMemLong(rs.getDouble("memLong"));
				memberVO.setMemLat(rs.getDouble("memLat"));
				memberVO.setMemPriv(rs.getInt("memPriv"));
				memberVO.setMemStatus(rs.getInt("memStatus"));
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
		return memberVO;
	}

	@Override
	public MemberVO findByMemPw(String memPw) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_MEMPW);

			pstmt.setString(1, memPw);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemID(rs.getInt("memID"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemPw(rs.getString("memPw"));
				memberVO.setMemberType(rs.getInt("memberType"));
				memberVO.setMemLv(rs.getInt("memLv"));
				memberVO.setMemExp(rs.getInt("memExp"));
				memberVO.setMemPt(rs.getInt("memPt"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemGender(rs.getInt("memGender"));
				memberVO.setMemBD(rs.getTimestamp("memBD"));
				memberVO.setMemPhone(rs.getString("memPhone"));
				memberVO.setMemAvatar(rs.getBytes("memAvatar"));
				memberVO.setMemJoinDate(rs.getTimestamp("memJoinDate"));
				memberVO.setMemLoginNum(rs.getInt("memLoginNum"));
				memberVO.setMemLocBorn(rs.getString("memLocBorn"));
				memberVO.setMemLocLive(rs.getString("memLocLive"));
				memberVO.setMemInt(rs.getString("memInt"));
				memberVO.setMemLong(rs.getDouble("memLong"));
				memberVO.setMemLat(rs.getDouble("memLat"));
				memberVO.setMemPriv(rs.getInt("memPriv"));
				memberVO.setMemStatus(rs.getInt("memStatus"));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll(Map<String, String[]> map) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from member "
		          + JdbcUtil_CompositeQuery_Member.get_WhereCondition("Member", map)
		          ;
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("〈〈finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemID(rs.getInt("memID"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemPw(rs.getString("memPw"));
				memberVO.setMemberType(rs.getInt("memberType"));
				memberVO.setMemLv(rs.getInt("memLv"));
				memberVO.setMemExp(rs.getInt("memExp"));
				memberVO.setMemPt(rs.getInt("memPt"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemGender(rs.getInt("memGender"));
				memberVO.setMemBD(rs.getTimestamp("memBD"));
				memberVO.setMemPhone(rs.getString("memPhone"));
				memberVO.setMemAvatar(rs.getBytes("memAvatar"));
				memberVO.setMemJoinDate(rs.getTimestamp("memJoinDate"));
				memberVO.setMemLoginNum(rs.getInt("memLoginNum"));
				memberVO.setMemLocBorn(rs.getString("memLocBorn"));
				memberVO.setMemLocLive(rs.getString("memLocLive"));
				memberVO.setMemInt(rs.getString("memInt"));
				memberVO.setMemLong(rs.getDouble("memLong"));
				memberVO.setMemLat(rs.getDouble("memLat"));
				memberVO.setMemPriv(rs.getInt("memPriv"));
				memberVO.setMemStatus(rs.getInt("memStatus"));
				list.add(memberVO); // Store the row in the list
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

