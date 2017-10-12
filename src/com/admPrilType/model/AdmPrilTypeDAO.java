package com.admPrilType.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdmPrilTypeDAO implements AdmPrilTypeDAO_interface{
	
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
			"INSERT INTO admPrilType (admPrilID,admPrilTypeName,admPrilTypeStatus) VALUES ( ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT admPrilID,admPrilTypeName,admPrilTypeStatus FROM admPrilType order by admPrilID";
		private static final String GET_ONE_STMT = 
			"SELECT admPrilID,admPrilTypeName,admPrilTypeStatus FROM admPrilType where admPrilID = ?";
		private static final String DELETE = 
			"DELETE FROM admPrilType where admPrilID = ?";
		private static final String UPDATE = 
			"UPDATE admPrilType set admPrilTypeName=?, admPrilTypeStatus=? where admPrilID = ?";
		
		private static final String STATUSNAME = 
				"SELECT admPrilID,admPrilTypeName FROM admPrilType order by admPrilID";
		
		@Override
		public List<AdmPrilTypeVO> statusname() {
			// TODO Auto-generated method stub
			List<AdmPrilTypeVO> list = new ArrayList<AdmPrilTypeVO>();
			AdmPrilTypeVO admPrilTypeVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(STATUSNAME);
				rs = pstmt.executeQuery();
				
		
				while (rs.next()) {
					// empVO �]�٬� Domain objects
					admPrilTypeVO = new AdmPrilTypeVO();
					admPrilTypeVO.setAdmPrilID(rs.getInt("admPrilID"));
					admPrilTypeVO.setAdmPrilTypeName(rs.getString("admPrilTypeName"));
								
					list.add(admPrilTypeVO); // Store the row in the list
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
		public void insert(AdmPrilTypeVO admPrilTypeVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);
					//admPrilID,admPrilTypeName,admPrilTypeStatus)
				pstmt.setInt(1, admPrilTypeVO.getAdmPrilID());
				pstmt.setString(2, admPrilTypeVO.getAdmPrilTypeName());
				pstmt.setInt(3, admPrilTypeVO.getAdmPrilTypeStatus());		
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
		public void update(AdmPrilTypeVO admPrilTypeVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				//admPrilType set admPrilTypeName=?, admPrilTypeStatus=? where admPrilID 
				pstmt.setString(1, admPrilTypeVO.getAdmPrilTypeName());
				pstmt.setInt(2, admPrilTypeVO.getAdmPrilTypeStatus());
				pstmt.setInt(3, admPrilTypeVO.getAdmPrilID());

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
		public void delete(Integer admPrilID) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, admPrilID);

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
		public AdmPrilTypeVO findByPrimaryKey(Integer admPrilID) {
			// TODO Auto-generated method stub
			AdmPrilTypeVO admPrilTypeVO = null;
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
					admPrilTypeVO = new AdmPrilTypeVO();
					admPrilTypeVO.setAdmPrilID(rs.getInt("admPrilID"));
					admPrilTypeVO.setAdmPrilTypeName(rs.getString("admPrilTypeName"));
					admPrilTypeVO.setAdmPrilTypeStatus(rs.getInt("admPrilTypeStatus"));
					
					
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
			return admPrilTypeVO;
		}
		@Override
		public List<AdmPrilTypeVO> getAll() {
			// TODO Auto-generated method stub
			List<AdmPrilTypeVO> list = new ArrayList<AdmPrilTypeVO>();
			AdmPrilTypeVO admPrilTypeVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
		
				while (rs.next()) {
					// empVO �]�٬� Domain objects
					admPrilTypeVO = new AdmPrilTypeVO();
					admPrilTypeVO.setAdmPrilID(rs.getInt("admPrilID"));
					admPrilTypeVO.setAdmPrilTypeName(rs.getString("admPrilTypeName"));
					admPrilTypeVO.setAdmPrilTypeStatus(rs.getInt("admPrilTypeStatus"));				
					list.add(admPrilTypeVO); // Store the row in the list
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
