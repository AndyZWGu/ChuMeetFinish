package com.admPril.model;

import java.sql.*;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdmPrilDAO implements AdmPrilDAO_interface{

	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT=
			"INSERT INTO admPril (admPrilID,adminID,admPrildate,admPrilStatus) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT=
			"SELECT admPrilID,adminID,to_char(admPrildate,'yyyy-mm-dd hh:mm:ss')admPrildate,admPrilStatus From admPril order by admPrilID";
	private static final String GET_ONE_STMT=
			"SELECT admPrilID,adminID,to_char(admPrildate,'yyyy-mm-dd hh:mm:ss')admPrildate,admPrilStatus From admPril where admPrilID=?";
	private static final String DELETE=
			"DELETE FROM admPril where admPrilID =?";
	private static final String UPDATE=
			"UPDATE admPril set adminID=?, admPrildate=? ,admPrilStatus=? where admPrilID=?"; 
	
	private static final String STATUS2=
			"SELECT admPrilID,adminID From admPril order by admPrilID";
	
	private static final String GET_ONE_STMT_BY_ADMINID=
			"select * from admpril where adminId=?";

	@Override
	public List<AdmPrilVO> status2() {
		// TODO Auto-generated method stub
		List<AdmPrilVO> list = new ArrayList<AdmPrilVO>();
		AdmPrilVO admPrilVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(STATUS2);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				admPrilVO = new AdmPrilVO();
				admPrilVO.setAdmPrilID(rs.getInt("admPrilID"));
				admPrilVO.setAdminID(rs.getInt("adminID"));
				
				
				list.add(admPrilVO); // Store the row in the list
			}

			// Handle any driver errors
		}  catch (SQLException se) {
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
public void insert(AdmPrilVO admPrilVO) {
	
	// TODO Auto-generated method stub
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);

		//"INSERT INTO admPril (admPrilID,adminID,admPrildate,admPrilStatus) VALUSE (?,?,?,?)";
		
		pstmt.setInt(1, admPrilVO.getAdmPrilID());
		pstmt.setInt(2, admPrilVO.getAdminID());
		pstmt.setTimestamp(3, admPrilVO.getAdmPrildate());
		pstmt.setInt(4, admPrilVO.getAdmPrilStatus());		
		pstmt.executeUpdate();
		
	}  catch (SQLException se) {
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
public void update(AdmPrilVO admPrilVO) {
	// TODO Auto-generated method stub
	Connection con = null;
	PreparedStatement pstmt = null;

	try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(UPDATE);

		//UPDATE admPril set adminID=?, admPrildate=? ,admPrilStatus=? where admPrilID=?
		
		pstmt.setInt(1, admPrilVO.getAdminID());
		pstmt.setTimestamp(2, admPrilVO.getAdmPrildate());
		pstmt.setInt(3, admPrilVO.getAdmPrilStatus());
		pstmt.setInt(4, admPrilVO.getAdmPrilID());
	
		pstmt.executeUpdate();
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
public void delete(Integer admPrilID) {
	// TODO Auto-generated method stub
	Connection con = null;
	PreparedStatement pstmt = null;

	
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, admPrilID);

			pstmt.executeUpdate();
		}  catch (SQLException se) {
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
public AdmPrilVO findByPrimaryKey(Integer admPrilID) {
	// TODO Auto-generated method stub
	AdmPrilVO admPrilVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMT);

		pstmt.setInt(1, admPrilID);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVo �]�٬� Domain objects
			admPrilVO = new AdmPrilVO();
			admPrilVO.setAdmPrilID(rs.getInt("admPrilID"));
			admPrilVO.setAdminID(rs.getInt("adminID"));
			admPrilVO.setAdmPrildate(rs.getTimestamp("admPrildate"));
			admPrilVO.setAdmPrilStatus(rs.getInt("admPrilStatus"));
		
		}

		// Handle any driver errors
	}  catch (SQLException se) {
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
	return admPrilVO;
}
@Override
public List<AdmPrilVO> getAll() {
	// TODO Auto-generated method stub
	List<AdmPrilVO> list = new ArrayList<AdmPrilVO>();
	AdmPrilVO admPrilVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVO �]�٬� Domain objects
			admPrilVO = new AdmPrilVO();
			admPrilVO.setAdmPrilID(rs.getInt("admPrilID"));
			admPrilVO.setAdminID(rs.getInt("adminID"));
			admPrilVO.setAdmPrildate(rs.getTimestamp("admPrildate"));
			admPrilVO.setAdmPrilStatus(rs.getInt("admPrilStatus"));
			
			list.add(admPrilVO); // Store the row in the list
		}

		// Handle any driver errors
	}  catch (SQLException se) {
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
public List<AdmPrilVO> findByAdminID(Integer adminID) {
	// TODO Auto-generated method stub
	List<AdmPrilVO> list = new ArrayList<AdmPrilVO>();
	AdmPrilVO admPrilVO = null;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {

		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ONE_STMT_BY_ADMINID);

		pstmt.setInt(1, adminID);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			// empVo �]�٬� Domain objects
			admPrilVO = new AdmPrilVO();
			admPrilVO.setAdmPrilID(rs.getInt("admPrilID"));
			admPrilVO.setAdminID(rs.getInt("adminID"));
			admPrilVO.setAdmPrildate(rs.getTimestamp("admPrildate"));
			admPrilVO.setAdmPrilStatus(rs.getInt("admPrilStatus"));
			
			list.add(admPrilVO); // Store the row in the list
		
		}

		// Handle any driver errors
	}  catch (SQLException se) {
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
