package com.actTicket.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.admPril.model.AdmPrilJDBCDAO;
import com.admPril.model.AdmPrilVO;


public class ActTicketJDBCDAO implements ActTicketDAO_interface{

	
	String driver="oracle.jdbc.driver.OracleDriver";
	String url="jdbc:oracle:thin:@localhost:1521:xe";
	String userid="BA103G2";
	String passwd="a123";
		private static final String INSERT_STMT=
				"INSERT INTO actTicket VALUES (actTicket_seq.nextval,?,?,?,?,?,?)";
		private static final String GET_ALL_STMT=
				"SELECT actTkID,reporter,actID,actTkMsg,actTkStatID,to_char(actTkDate,'yyyy-mm-dd hh:mm:ss')actTkDate,actTkCat From actTicket order by actTkID";
		private static final String GET_ONE_STMT=
				"SELECT actTkID,reporter,actID,actTkMsg,actTkStatID,to_char(actTkDate,'yyyy-mm-dd hh:mm:ss')actTkDate,actTkCat From actTicket where actTkID=?";
		private static final String DELETE=
				"DELETE FROM actTicket where actTkID =?";
		private static final String UPDATE=
				"UPDATE actTicket set reporter=?, actID=? ,actTkMsg=? ,actTkStatID=?, actTkDate=?,actTkCat=? where actTkID=?"; 
		
	
	

	@Override
	public void insert(ActTicketVO actTicketVO) {
		
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, actTicketVO.getReporter());
			pstmt.setInt(2, actTicketVO.getActID());
			pstmt.setString(3, actTicketVO.getActTkMsg());
			pstmt.setInt(4, actTicketVO.getActTkStatID());	
			pstmt.setTimestamp(5, actTicketVO.getActTkDate());
			pstmt.setInt(6, actTicketVO.getActTkCat());
			pstmt.executeUpdate();
//	INSERT INTO actTicket (actTkID,reporter,actID,actTkMsg,actTkStstID,actTkDate,actTkCat)
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
	public void update(ActTicketVO actTicketVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
	
			pstmt.setInt(1, actTicketVO.getReporter());
			pstmt.setInt(2, actTicketVO.getActID());			
			pstmt.setString(3, actTicketVO.getActTkMsg());
			pstmt.setInt(4, actTicketVO.getActTkStatID());
			pstmt.setTimestamp(5, actTicketVO.getActTkDate());
			pstmt.setInt(6, actTicketVO.getActTkCat());
			pstmt.setInt(7, actTicketVO.getActTkID());
		//UPDATE actTicket set reporter=?, actID=? ,actTkMsg=?, actTkStstID=?, actTkDate=?,actTkCat=? where actTkID=?";
			pstmt.executeUpdate();
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
	public void delete(Integer actTkID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
			try{
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, actTkID);
				//"DELETE FROM actTicket where actTkID =?";
				pstmt.executeUpdate();
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
	public ActTicketVO findByPrimaryKey(Integer actTkID) {
		// TODO Auto-generated method stub
		ActTicketVO actTicketVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actTkID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				actTicketVO = new ActTicketVO();
				actTicketVO.setActTkID(rs.getInt("actTkID"));
				actTicketVO.setReporter(rs.getInt("reporter"));
				actTicketVO.setActID(rs.getInt("actID"));
				actTicketVO.setActTkMsg(rs.getString("actTkMsg"));
				actTicketVO.setActTkStatID(rs.getInt("actTkStatID"));
				actTicketVO.setActTkDate(rs.getTimestamp("actTkDate"));
				actTicketVO.setActTkCat(rs.getInt("actTkCat"));
//INSERT INTO actTicket (actTkID,reporter,actID,actTkMsg,actTkStstID,actTkDate,actTkCat)		
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
		return actTicketVO;
	}
	@Override
	public List<ActTicketVO> getAll() {
		// TODO Auto-generated method stub
		List<ActTicketVO> list = new ArrayList<ActTicketVO>();
		ActTicketVO actTicketVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				actTicketVO = new ActTicketVO();
				actTicketVO.setActTkID(rs.getInt("actTkID"));
				actTicketVO.setReporter(rs.getInt("reporter"));
				actTicketVO.setActID(rs.getInt("actID"));
				actTicketVO.setActTkMsg(rs.getString("actTkMsg"));	
				actTicketVO.setActTkStatID(rs.getInt("actTkStatID"));
				actTicketVO.setActTkDate(rs.getTimestamp("actTkDate"));
				actTicketVO.setActTkCat(rs.getInt("actTkCat"));
				list.add(actTicketVO); // Store the row in the list
				
//INSERT INTO actTicket (actTkID,reporter,actID,actTkMsg,actTkStstID,actTkDate,actTkCat)	
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
	public static void main(String[] args) {

		ActTicketJDBCDAO dao = new ActTicketJDBCDAO();

		// �s�W   
//		ActTicketVO actTicketVO1 = new ActTicketVO();
//		actTicketVO1.setReporter(2);
//		actTicketVO1.setActID(1);		
//		actTicketVO1.setActTkMsg("1");	
//		actTicketVO1.setActTkStatID(1);
//		actTicketVO1.setActTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
//		actTicketVO1.setActTkCat(3);
//		dao.insert(actTicketVO1);
//		

		
//		// �ק�
		ActTicketVO actTicketVO2 = new ActTicketVO();
		actTicketVO2.setReporter(2);
		actTicketVO2.setActID(1);
		actTicketVO2.setActTkMsg("1");
		actTicketVO2.setActTkStatID(1);
		actTicketVO2.setActTkDate(java.sql.Timestamp.valueOf("2005-01-01 10:10:10"));
		actTicketVO2.setActTkCat(2);
	
		dao.update(actTicketVO2);
//
//		// �R��
//		dao.delete(3);

		// �d��//INSERT INTO actTicket (actTkID,reporter,actID,actTkMsg,actTkStstID,actTkDate,actTkCat)	
//		ActTicketVO actTicketVO3 = dao.findByPrimaryKey(1);
//		System.out.print(actTicketVO3.getActTkID() + ",");
//		System.out.print(actTicketVO3.getReporter() + ",");
//		System.out.print(actTicketVO3.getActID() + ",");
//		System.out.print(actTicketVO3.getActTkMsg() + ",");
//		System.out.print(actTicketVO3.getActTkStatID() + ",");
//		System.out.print(actTicketVO3.getActTkDate() + ",");
//		System.out.print(actTicketVO3.getActTkCat() + ",");
//		System.out.println("---------------------");

		// �d��//INSERT INTO actTicket (actTkID,reporter,actID,actTkMsg,actTkStstID,actTkDate,actTkCat)	
		List<ActTicketVO> list = dao.getAll();
		for (ActTicketVO aActTicket : list) {
			System.out.print(aActTicket.getActTkID() + ",");
			System.out.print(aActTicket.getReporter() + ",");
			System.out.print(aActTicket.getActID() + ",");
			System.out.print(aActTicket.getActTkMsg() + ",");
			System.out.print(aActTicket.getActTkStatID() + ",");
			System.out.print(aActTicket.getActTkDate() + ",");
			System.out.print(aActTicket.getActTkCat() + ",");
			
			System.out.println();
		}
	}


}
