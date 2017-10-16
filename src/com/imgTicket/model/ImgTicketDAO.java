package com.imgTicket.model;

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

public class ImgTicketDAO implements ImgTicketDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT  = 											
			"INSERT INTO imgTicket  VALUES (imgTicket_seq.NEXTVAL, ?, ?, ?, ?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT imgTkID,reporter,actImgID,imgTkMsg,imgTkStatID,to_char(imgTkDate,'yyyy-mm-dd hh:mm:ss')imgTkDate,imgTkCat FROM imgTicket order by imgTkID";
		private static final String GET_ONE_STMT = 
			"SELECT imgTkID,reporter,actImgID,imgTkMsg,imgTkStatID,to_char(imgTkDate,'yyyy-mm-dd hh:mm:ss')imgTkDate,imgTkCat FROM imgTicket where imgTkID= ?";
		private static final String DELETE = 
			"DELETE FROM imgTicket where imgTkID = ?";
		private static final String UPDATE = 
			"UPDATE imgTicket set reporter=?,actImgID=?,imgTkMsg=?,imgTkStatID=?,imgTkDate=?,imgTkCat=?  where imgTkID = ?";

		
		
		@Override
		public void insert(ImgTicketVO imgTicketVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, imgTicketVO.getReporter());
				pstmt.setInt(2, imgTicketVO.getActImgID());
				pstmt.setString(3, imgTicketVO.getImgTkMsg());
				pstmt.setInt(4, imgTicketVO.getImgTkStatID());
				pstmt.setTimestamp(5, imgTicketVO.getImgTkDate());
				pstmt.setInt(6, imgTicketVO.getImgTkCat());
			

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
		public void update(ImgTicketVO imgTicketVO) {
			// TODO Auto-generated method stub
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, imgTicketVO.getReporter());
				pstmt.setInt(2, imgTicketVO.getActImgID());
				pstmt.setString(3, imgTicketVO.getImgTkMsg());
				pstmt.setInt(4, imgTicketVO.getImgTkStatID());
				pstmt.setTimestamp(5, imgTicketVO.getImgTkDate());
				pstmt.setInt(6, imgTicketVO.getImgTkCat());
				pstmt.setInt(7, imgTicketVO.getImgTkID());

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
		public void delete(Integer imgTkID) {
			// TODO Auto-generated method stub
			
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, imgTkID);

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
		public ImgTicketVO findByPrimaryKey(Integer imgTkID) {
			// TODO Auto-generated method stub
			ImgTicketVO imgTicketVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, imgTkID);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// memTicketVO �]�٬� Domain objects
					imgTicketVO = new ImgTicketVO();
					imgTicketVO.setImgTkID(rs.getInt("imgTkID"));			
					imgTicketVO.setReporter(rs.getInt("reporter"));
					imgTicketVO.setActImgID(rs.getInt("actImgID"));
					imgTicketVO.setImgTkMsg(rs.getString("imgTkMsg"));
					imgTicketVO.setImgTkStatID(rs.getInt("imgTkStatID"));
					imgTicketVO.setImgTkDate(rs.getTimestamp("imgTkDate"));
					imgTicketVO.setImgTkCat(rs.getInt("imgTkCat"));
					
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
			return imgTicketVO;
		}
		@Override
		public List<ImgTicketVO> getAll() {
			// TODO Auto-generated method stub
			List<ImgTicketVO> list = new ArrayList<ImgTicketVO>();
			ImgTicketVO imgTicketVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					imgTicketVO = new ImgTicketVO();
					imgTicketVO.setImgTkID(rs.getInt("imgTkID"));
					imgTicketVO.setReporter(rs.getInt("reporter"));
					imgTicketVO.setActImgID(rs.getInt("actImgID"));
					imgTicketVO.setImgTkMsg(rs.getString("imgTkMsg"));
					imgTicketVO.setImgTkStatID(rs.getInt("imgTkStatID"));
					imgTicketVO.setImgTkDate(rs.getTimestamp("imgTkDate"));
					imgTicketVO.setImgTkCat(rs.getInt("imgTkCat"));
			
					list.add(imgTicketVO); // Store the row in the list
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

		public static void main(String[] args) {

			ImgTicketJDBCDAO dao = new ImgTicketJDBCDAO();
			
//			// �s�W
			ImgTicketVO imgTicketVO1 = new ImgTicketVO();
			imgTicketVO1.setImgTkID(3);
			imgTicketVO1.setReporter(2);
			imgTicketVO1.setActImgID(2);
			imgTicketVO1.setImgTkMsg("MANAGER");
			imgTicketVO1.setImgTkStatID(1);
			imgTicketVO1.setImgTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
			imgTicketVO1.setImgTkCat(1);
			dao.insert(imgTicketVO1);

//			// �ק�
//			ImgTicketVO imgTicketVO2 = new ImgTicketVO();
//			imgTicketVO2.setImgTkID(3);
//			imgTicketVO2.setReporter(2);
//			imgTicketVO2.setActImgID(2);
//			imgTicketVO2.setImgTkMsg("MANAGER");
//			imgTicketVO2.setImgTkStatID(1);
//			imgTicketVO2.setImgTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
//			imgTicketVO2.setImgTkCat(1);
//			
//			
//			dao.update(imgTicketVO2);
			// �R��
//			dao.delete(1);
////			 
////			// �d��
//			ImgTicketVO imgTicketVO3 = dao.findByPrimaryKey(1);
//			System.out.print(imgTicketVO3.getImgTkID() + ",");
//			System.out.print(imgTicketVO3.getReporter() + ",");
//			System.out.print(imgTicketVO3.getActImgID() + ",");
//			System.out.print(imgTicketVO3.getImgTkMsg() + ",");
//			System.out.print(imgTicketVO3.getImgTkStatID() + ",");
//			System.out.print(imgTicketVO3.getImgTkDate() + ",");
//			System.out.print(imgTicketVO3.getImgTkCat() + ",");
//			System.out.println("---------------------");

					// �d��
			List<ImgTicketVO> list = dao.getAll();
			for (ImgTicketVO aImgTicket : list) {
				System.out.print(aImgTicket.getImgTkID() + ",");
				System.out.print(aImgTicket.getReporter() + ",");
				System.out.print(aImgTicket.getActImgID() + ",");
				System.out.print(aImgTicket.getImgTkMsg() + ",");
				System.out.print(aImgTicket.getImgTkStatID() + ",");
				System.out.print(aImgTicket.getImgTkDate() + ",");
				System.out.print(aImgTicket.getImgTkCat() + ",");
				System.out.println();
			}
		}
		}


