package com.act.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ActMBDAO implements ActMB_Interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

private static final String INSERT_STMT = "insert into actMB(actmbid, actid, memid, actMBContent, actmbdate) "+
			"values (actmb_seq.nextval,?,?,?,systimestamp)";

private static final String GET_ALL_STMT = "SELECT * from ACTMB where actID=? order by actmbDate desc";
private static final String DELETE_CHILD= "DELETE FROM (select * from ACTMB join mbticket using (actmbid) where ACTMBID =?)";
private static final String DELETE_ME= "DELETE FROM ACTMB where ACTMBID =?";
	
@Override
public void delete(Integer actMBID) {

	Connection con = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(DELETE_CHILD);
		pstmt.setInt(1, actMBID);
		pstmt.executeUpdate();
		pstmt2 = con.prepareStatement(DELETE_ME);
		pstmt2.setInt(1, actMBID);
		pstmt2.executeUpdate();

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












	public void insert(Integer actID, Integer memID, String Cnt) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, actID);
			pstmt.setInt(2, memID);
			pstmt.setString(3, Cnt);
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

	
	public List<ActMBVO> getAll(Integer actID) {
		List<ActMBVO> list = new ArrayList<ActMBVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, actID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActMBVO amb= new ActMBVO();
				amb.setActMBID(rs.getInt("actMBID"));
				amb.setActID(rs.getInt("actID"));
				amb.setMemID(rs.getInt("memID"));
				amb.setActMBContent(rs.getString("actMBContent"));
				amb.setActMBDate(rs.getTimestamp("actMBDate"));
				
				list.add(amb); // Store the row in the list
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
