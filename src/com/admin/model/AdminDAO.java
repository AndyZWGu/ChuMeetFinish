package com.admin.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdminDAO implements AdminDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO admin (adminID,adminName,adminMail,adminPW,adminEmail,adminDate,adminStatus) VALUES (adminID_SEQ.NEXTVAL,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT adminID,adminName,adminMail,adminEmail,adminDate,adminStatus FROM admin order by adminID";
	
	private static final String GET_ONE_STMT=
			"SELECT adminID,adminName,adminMail,adminPW,adminEmail,to_char(adminDate,'yyyy-mm-dd hh:mm:ss')adminDate,adminStatus FROM admin where adminID=?";
	private static final String DELETE = "DELETE FROM admin where adminID=?";
	private static final String UPDATE = "UPDATE admin set adminName=?, adminMail=?,adminPW=?,adminEmail=?,adminDate=?,adminStatus=? where adminID=?";

	private static final String STATUSADMIN = "SELECT adminID,adminName,adminMail,adminPW,adminEmail,adminDate,adminStatus FROM admin where adminStatus=1";
	private static final String STATUSUPDATE = "UPDATE admin set adminStatus=0  where adminID=?";

	private static final String GET_ONE_STMT_BY_ADMINNAME=
			"SELECT adminID,adminName,adminMail,adminPW,adminEmail,to_char(adminDate,'yyyy-mm-dd hh:mm:ss')adminDate,adminStatus FROM admin where adminName=?";
	private static final String GET_ONE_STMT_BY_ADMINMAIL=
			"SELECT adminID,adminName,adminMail,adminPW,adminEmail,to_char(adminDate,'yyyy-mm-dd hh:mm:ss')adminDate,adminStatus FROM admin where adminMail=?";
	@Override
	public void status1(Integer AdminID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(STATUSUPDATE);

			pstmt.setInt(1, AdminID);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<AdminVO> statusadmin() {
		// TODO Auto-generated method stub
		List<AdminVO> list = new ArrayList<AdminVO>();
		AdminVO adminVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(STATUSADMIN);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminID(rs.getInt("adminID"));
				adminVO.setAdminName(rs.getString("adminName"));
				adminVO.setAdminMail(rs.getString("adminMail"));
				adminVO.setAdminEmail(rs.getString("adminEmail"));
				adminVO.setAdminDate(rs.getTimestamp("adminDate"));
				adminVO.setAdminStatus(rs.getInt("adminStatus"));
				list.add(adminVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(AdminVO adminVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, adminVO.getAdminName());
			pstmt.setString(2, adminVO.getAdminMail());
			pstmt.setString(3, adminVO.getAdminPW());
			pstmt.setString(4, adminVO.getAdminEmail());
			pstmt.setTimestamp(5, adminVO.getAdminDate());
			pstmt.setInt(6, adminVO.getAdminStatus());
			pstmt.setInt(7, adminVO.getAdminID());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer adminID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, adminID);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public AdminVO findByPrimaryKey(Integer adminID) {
		// TODO Auto-generated method stub
		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, adminID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminID(rs.getInt("adminID"));
				adminVO.setAdminName(rs.getString("adminName"));
				adminVO.setAdminMail(rs.getString("adminMail"));
				adminVO.setAdminPW(rs.getString("adminPW"));
				adminVO.setAdminEmail(rs.getString("adminEmail"));
				adminVO.setAdminDate(rs.getTimestamp("adminDate"));
				adminVO.setAdminStatus(rs.getInt("adminStatus"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return adminVO;
	}

	@Override
	public List<AdminVO> getAll() {
		// TODO Auto-generated method stub
		List<AdminVO> list = new ArrayList<AdminVO>();
		AdminVO adminVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminID(rs.getInt("adminID"));
				adminVO.setAdminName(rs.getString("adminName"));
				adminVO.setAdminMail(rs.getString("adminMail"));
				adminVO.setAdminEmail(rs.getString("adminEmail"));
				adminVO.setAdminDate(rs.getTimestamp("adminDate"));
				adminVO.setAdminStatus(rs.getInt("adminStatus"));
				list.add(adminVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void insert(AdminVO adminVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, adminVO.getAdminName());
			pstmt.setString(2, adminVO.getAdminMail());
			pstmt.setString(3, adminVO.getAdminPW());
			pstmt.setString(4, adminVO.getAdminEmail());
			pstmt.setTimestamp(5, adminVO.getAdminDate());
			pstmt.setInt(6, adminVO.getAdminStatus());

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured." + se.getMessage());
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
	public AdminVO findByAdminMail(String adminMail) {
		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			// �憓�撠�辣
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_ADMINMAIL);

			pstmt.setString(1, adminMail);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminID(rs.getInt("adminID"));
				adminVO.setAdminName(rs.getString("adminName"));
				adminVO.setAdminMail(rs.getString("adminMail"));
				adminVO.setAdminPW(rs.getString("adminPW"));
				adminVO.setAdminEmail(rs.getString("adminEmail"));
				adminVO.setAdminDate(rs.getTimestamp("adminDate"));
				adminVO.setAdminStatus(rs.getInt("adminStatus"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return adminVO;

	}

	@Override
	public AdminVO findByAdminName(String adminName) {
		AdminVO adminVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			// �憓�撠�辣
			pstmt = con.prepareStatement(GET_ONE_STMT_BY_ADMINNAME);

			pstmt.setString(1, adminName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adminVO = new AdminVO();
				adminVO.setAdminID(rs.getInt("adminID"));
				adminVO.setAdminName(rs.getString("adminName"));
				adminVO.setAdminMail(rs.getString("adminMail"));
				adminVO.setAdminPW(rs.getString("adminPW"));
				adminVO.setAdminEmail(rs.getString("adminEmail"));
				adminVO.setAdminDate(rs.getTimestamp("adminDate"));
				adminVO.setAdminStatus(rs.getInt("adminStatus"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return adminVO;

	}

}
