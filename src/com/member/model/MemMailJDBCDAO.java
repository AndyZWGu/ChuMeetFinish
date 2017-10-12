package com.member.model;

import java.util.*;
import java.sql.*;
import java.text.ParseException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.controller.JdbcUtil_CompositeQuery_Member;



public class MemMailJDBCDAO implements MemMailDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ChuMeet3";
	String passwd = "say531328";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memMailID);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memMailID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				memMailVO = new MemMailVO();
				memMailVO.setMemMailID(rs.getInt("memMailID"));
				memMailVO.setReceiver(rs.getInt("memID1"));
				memMailVO.setAuthor(rs.getInt("memID2"));
				memMailVO.setMailTitle(rs.getString("mailTitle"));
				memMailVO.setMailDate(rs.getTimestamp("mailDate"));
				memMailVO.setMailContent(rs.getString("mailContent"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memMailVO = new MemMailVO();
				memMailVO.setMemMailID(rs.getInt("memMailID"));
				memMailVO.setReceiver(rs.getInt("memID1"));
				memMailVO.setAuthor(rs.getInt("memID2"));
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	public static void main(String[] args) throws ParseException {

		MemberJDBCDAO dao = new MemberJDBCDAO();

		// 新增
//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setMemEmail("ggpower@hotmail.com");
//		memberVO2.setMemPw("PPww");
//		memberVO2.setMemberType(1);
//		memberVO2.setMemLv(10);
//		memberVO2.setMemExp(300);
//		memberVO2.setMemPt(5900);
//		memberVO2.setMemName("GodGG");
//		memberVO2.setMemGender(1);
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
//        Date parsed = format.parse("20110210");
//        java.sql.Date sql = new java.sql.Date(parsed.getTime());
//		memberVO2.setMemBD(sql);
//		memberVO2.setMemPhone(12345678);
//		memberVO2.setMemAvatar(null);
//		memberVO2.setMemJoinDate(sql);
//		memberVO2.setMemLoginNum(5);
//		memberVO2.setMemLocBorn(null);
//		memberVO2.setMemLocLive(null);
//		memberVO2.setMemInt("歡迎歡迎");
//		memberVO2.setMemLong(1.2);
//		memberVO2.setMemLat(3.4);
//		memberVO2.setMemPriv(1);
//		memberVO2.setMemStatus(1);
//		dao.insert(memberVO2);

//		// 更新
//		MemberVO MemberVO2 = new MemberVO();
//		MemberVO2.setEmpno(7001);
//		MemberVO2.setEname("鍚虫案蹇�2");
//		MemberVO2.setJob("MANAGER2");
//		MemberVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		MemberVO2.setSal(new Double(20000));
//		MemberVO2.setComm(new Double(200));
//		MemberVO2.setDeptno(20);
//		dao.update(MemberVO2);

		// 刪除
//		dao.delete(7014);

		// 查詢特定一筆
//		MemberVO memberVO3 = dao.findByPrimaryKey(1);
//		System.out.print(memberVO3.getMemID() + ",");
//		System.out.print(memberVO3.getMemEmail() + ",");
//		System.out.print(memberVO3.getMemPw() + ",");
//		System.out.print(memberVO3.getMemberType() + ",");
//		System.out.print(memberVO3.getMemLv() + ",");
//		System.out.print(memberVO3.getMemExp() + ",");
//		System.out.println(memberVO3.getMemPt());
//		System.out.print(memberVO3.getMemName() + ",");
//		System.out.print(memberVO3.getMemGender() + ",");
//		System.out.print(memberVO3.getMemBD() + ",");
//		System.out.print(memberVO3.getMemPhone() + ",");
//		System.out.print(memberVO3.getMemAvatar() + ",");
//		System.out.print(memberVO3.getMemJoinDate() + ",");
//		System.out.print(memberVO3.getMemLoginNum());
//		System.out.print(memberVO3.getMemLocBorn() + ",");
//		System.out.print(memberVO3.getMemLocLive() + ",");
//		System.out.print(memberVO3.getMemInt() + ",");
//		System.out.print(memberVO3.getMemLong() + ",");
//		System.out.print(memberVO3.getMemLat() + ",");
//		System.out.print(memberVO3.getMemPriv() + ",");
//		System.out.print(memberVO3.getMemStatus());
//		System.out.println("---------------------");

		//查詢全部
//		List<MemberVO> list = dao.getAll();
//		for (MemberVO member : list) {
//			System.out.print(member.getMemID() + ",");
//			System.out.print(member.getMemEmail() + ",");
//			System.out.print(member.getMemPw() + ",");
//			System.out.print(member.getMemberType() + ",");
//			System.out.print(member.getMemLv() + ",");
//			System.out.print(member.getMemExp() + ",");
//			System.out.print(member.getMemPt());
//			System.out.print(member.getMemName() + ",");
//			System.out.print(member.getMemGender() + ",");
//			System.out.print(member.getMemBD() + ",");
//			System.out.print(member.getMemPhone() + ",");
//			System.out.print(member.getMemAvatar() + ",");
//			System.out.print(member.getMemJoinDate() + ",");
//			System.out.print(member.getMemLoginNum());
//			System.out.print(member.getMemLocBorn() + ",");
//			System.out.print(member.getMemLocLive() + ",");
//			System.out.print(member.getMemInt() + ",");
//			System.out.print(member.getMemLong() + ",");
//			System.out.print(member.getMemLat() + ",");
//			System.out.print(member.getMemPriv() + ",");
//			System.out.print(member.getMemStatus());
//			System.out.println("---------------------");
//			System.out.println();
//		}
	}

	@Override
	public List<MemMailVO> getAll(Map<String, String[]> map) {
		List<MemMailVO> list = new ArrayList<MemMailVO>();
		MemMailVO memMailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String CompositeSQL = "select * from MemMail "
			          + JdbcUtil_CompositeQuery_Member.get_WhereCondition("MemMail", map)
			          + "order by empno";
				pstmt = con.prepareStatement(CompositeSQL);
				System.out.println("〈〈finalSQL(by DAO) = "+CompositeSQL);
			pstmt = con.prepareStatement(CompositeSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memMailVO = new MemMailVO();
				memMailVO.setMemMailID(rs.getInt("memMailID"));
				memMailVO.setReceiver(rs.getInt("memID1"));
				memMailVO.setAuthor(rs.getInt("memID2"));
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

