package com.member.model;

import java.util.*;
import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class MemberJDBCDAO implements MemberDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ChuMeet3";
	String passwd = "say531328";

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

	@Override
	public void insert(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void update(MemberVO memberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(Integer memID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memID);

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
	public MemberVO findByPrimaryKey(Integer memID) {

		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

	public static void main(String[] args) throws ParseException {

		MemberJDBCDAO dao = new MemberJDBCDAO();

		// 鏂板
		MemberVO memberVO2 = new MemberVO();
		memberVO2.setMemEmail("ggpower@hotmail.com");
		memberVO2.setMemPw("PPww");
		memberVO2.setMemberType(1);
		memberVO2.setMemLv(10);
		memberVO2.setMemExp(300);
		memberVO2.setMemPt(5900);
		memberVO2.setMemName("GodGG");
		memberVO2.setMemGender(1);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date parsed = format.parse("20110210");
        java.sql.Timestamp memBD=new java.sql.Timestamp(parsed.getTime());
		memberVO2.setMemBD(memBD);		
		memberVO2.setMemPhone("0983354626");
		memberVO2.setMemAvatar(null);
		java.util.Date utildate=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(utildate.getTime());
		java.sql.Time sTime=new java.sql.Time(utildate.getTime());
		java.sql.Timestamp stp=new java.sql.Timestamp(utildate.getTime());
		memberVO2.setMemJoinDate(stp);
		memberVO2.setMemLoginNum(5);
		memberVO2.setMemLocBorn(null);
		memberVO2.setMemLocLive(null);
		memberVO2.setMemInt("姝¤繋姝¤繋");
		memberVO2.setMemLong(1.2);
		memberVO2.setMemLat(3.4);
		memberVO2.setMemPriv(1);
		memberVO2.setMemStatus(1);
		dao.insert(memberVO2);

//		// 鏇存柊
//		MemberVO MemberVO2 = new MemberVO();
//		MemberVO2.setEmpno(7001);
//		MemberVO2.setEname("閸氳櫕妗堣箛锟�2");
//		MemberVO2.setJob("MANAGER2");
//		MemberVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		MemberVO2.setSal(new Double(20000));
//		MemberVO2.setComm(new Double(200));
//		MemberVO2.setDeptno(20);
//		dao.update(MemberVO2);

		// 鍒櫎
//		dao.delete(7014);

		// 鏌ヨ鐗瑰畾涓�绛�
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

		//鏌ヨ鍏ㄩ儴
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
	public MemberVO findByMemEmail(String memEmail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO findByMemPw(String memPw) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet getAvatarByMemID(Integer memID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberVO> getAll(Map<String, String[]> map) {
		return null;
	}

	@Override
	public List<MemberVO> getAllByHot() {
		// TODO Auto-generated method stub
		return null;
	}


}