package com.ad.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class AdDAO implements AdDAO_interface{
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
			"INSERT INTO ad  VALUES (adID_SEQ.NEXTVAL, ?, ?, ?,?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT adID,adminID,adName,adImg,adContent,adLink,to_char(adDate,'yyyy-mm-dd hh:mm:ss')adDate FROM ad order by adID";
		private static final String GET_ONE_STMT = 
			"SELECT adID,adminID,adName,adImg,adContent,adLink,to_char(adDate,'yyyy-mm-dd hh:mm:ss')adDate FROM ad where adID = ?";
		private static final String DELETE = 
			"DELETE FROM ad where adID = ?";
		private static final String UPDATE = 
			"UPDATE ad set  adminID=?, adName=?,adImg=?, adContent=?,adLink=?, adDate=? where adID =?";

		@Override
		public void insert(AdVO adVO) {

		
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, adVO.getAdminID());
				pstmt.setString(2, adVO.getAdName());
				pstmt.setBytes(3, adVO.getAdImg());
				pstmt.setString(4, adVO.getAdContent());
				pstmt.setString(5, adVO.getAdLink());
				pstmt.setTimestamp(6, adVO.getAdDate());

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
		public void update(AdVO adVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				
				pstmt.setInt(1, adVO.getAdminID());
				pstmt.setString(2, adVO.getAdName());
				pstmt.setBytes(3, adVO.getAdImg());
				pstmt.setString(4, adVO.getAdContent());
				pstmt.setString(5, adVO.getAdLink());
				pstmt.setTimestamp(6, adVO.getAdDate());
				pstmt.setInt(7, adVO.getAdID());
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
		public void delete(Integer adID) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, adID);

				pstmt.executeUpdate();

				// Handle any driver errors
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
		public AdVO findByPrimaryKey(Integer adID) {

			AdVO adVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, adID);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					adVO = new AdVO();
					adVO.setAdID(rs.getInt("adID"));
					adVO.setAdminID(rs.getInt("adminID"));
					adVO.setAdName(rs.getString("adName"));
					adVO.setAdImg(rs.getBytes("adImg"));
					adVO.setAdContent(rs.getString("adContent"));
					adVO.setAdLink(rs.getString("adLink"));
					adVO.setAdDate(rs.getTimestamp("adDate"));
				
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
			return adVO;
		}

		@Override
		public List<AdVO> getAll() {
			List<AdVO> list = new ArrayList<AdVO>();
			AdVO adVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					adVO = new AdVO();
					adVO.setAdID(rs.getInt("adID"));
					adVO.setAdminID(rs.getInt("adminID"));
					adVO.setAdName(rs.getString("adName"));
					adVO.setAdImg(rs.getBytes("adImg"));
					adVO.setAdContent(rs.getString("adContent"));
					adVO.setAdLink(rs.getString("adLink"));
					adVO.setAdDate(rs.getTimestamp("adDate"));
					list.add(adVO); // Store the row in the list
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


