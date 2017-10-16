package com.albumTicket.model;

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


public class AlbumTicketDAO implements AlbumTicketDAO_interface{
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
			"INSERT INTO albumTicket  VALUES (albumTicket_seq.NEXTVAL, ?, ?, ?, ?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT albumTkID,reporter,clubAlbumID,albTkMsg,albTkStatID,to_char(albTkDate,'yyyy-mm-dd hh:mm:ss')albTkDate,albTkCat FROM albumTicket order by albumTkID";
		private static final String GET_ONE_STMT = 
			"SELECT albumTkID,reporter,clubAlbumID,albTkMsg,albTkStatID,to_char(albTkDate,'yyyy-mm-dd hh:mm:ss')albTkDate,albTkCat where albumTkID= ?";
		private static final String DELETE = 
			"DELETE FROM albumTicket where albumTkID = ?";
		private static final String UPDATE = 
			"UPDATE albumTicket set reporter=?,clubAlbumID=?,albTkMsg=?,albTkStatID=?,albTkDate=?,albTkCat=?  where albumTkID = ?";

		
		
		@Override
		public void insert(AlbumTicketVO albumTicketVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, albumTicketVO.getReporter());
				pstmt.setInt(2, albumTicketVO.getClubAlbumID());
				pstmt.setString(3, albumTicketVO.getAlbTkMsg());
				pstmt.setInt(4, albumTicketVO.getAlbTkStatID());
				pstmt.setTimestamp(5, albumTicketVO.getAlbTkDate());
				pstmt.setInt(6, albumTicketVO.getAlbTkCat());
				
			
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
		public void update(AlbumTicketVO albumTicketVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				
			
				pstmt.setInt(1, albumTicketVO.getReporter());
				pstmt.setInt(2, albumTicketVO.getClubAlbumID());
				pstmt.setString(3, albumTicketVO.getAlbTkMsg());
				pstmt.setInt(4, albumTicketVO.getAlbTkStatID());
				pstmt.setTimestamp(5, albumTicketVO.getAlbTkDate());
				pstmt.setInt(6, albumTicketVO.getAlbTkCat());
				pstmt.setInt(7, albumTicketVO.getAlbumTkID());


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
		public void delete(Integer albumTkID) {
			// TODO Auto-generated method stub
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, albumTkID);

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
		public AlbumTicketVO findByPrimaryKey(Integer albumTkID) {
			// TODO Auto-generated method stub
			AlbumTicketVO albumTicketVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, albumTkID);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// memTicketVO �]�٬� Domain objects
					albumTicketVO = new AlbumTicketVO();
					albumTicketVO.setAlbumTkID(rs.getInt("albumTkID"));			
					albumTicketVO.setReporter(rs.getInt("reporter"));
					albumTicketVO.setClubAlbumID(rs.getInt("clubAlbumID"));
					albumTicketVO.setAlbTkMsg(rs.getString("albTkMsg"));
					albumTicketVO.setAlbTkStatID(rs.getInt("albTkStatID"));
					albumTicketVO.setAlbTkDate(rs.getTimestamp("albTkDate"));
					albumTicketVO.setAlbTkCat(rs.getInt("albTkCat"));

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
			return albumTicketVO;
		}
		@Override
		public List<AlbumTicketVO> getAll() {
			// TODO Auto-generated method stub
			List<AlbumTicketVO> list = new ArrayList<AlbumTicketVO>();
			AlbumTicketVO albumTicketVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					albumTicketVO = new AlbumTicketVO();
					albumTicketVO.setAlbumTkID(rs.getInt("albumTkID"));
					albumTicketVO.setReporter(rs.getInt("reporter"));
					albumTicketVO.setClubAlbumID(rs.getInt("clubAlbumID"));
					albumTicketVO.setAlbTkMsg(rs.getString("albTkMsg"));
					albumTicketVO.setAlbTkStatID(rs.getInt("albTkStatID"));
					albumTicketVO.setAlbTkDate(rs.getTimestamp("albTkDate"));
					albumTicketVO.setAlbTkCat(rs.getInt("albTkCat"));
//					albumTkID, reporter, clubAlbumID, albTkMsg, albTkStatID, albTkDate, albTkCat
					list.add(albumTicketVO); // Store the row in the list
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
