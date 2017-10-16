package com.act.act.model;
import java.io.IOException;
import java.sql.*;
import java.util.*;
//import com.act.model.ActVO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.act.act.model.Act_interface;
import com.act.actPOI.model.ActPOIVO;

public class ActDAOJDBC implements Act_interface {
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA103G2DB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G2";
	String passwd = "a123";
	private static final String INSERT_STMT = "insert into act(actType, actID, memID, actCreateDate, actName, actStatus, actPriID, actStartDate ,actEndDate, actIMG ,actContent ,actIsHot ,actLong,actLat ,actPost ,actLocName, actAdr) "+
																							"values (1, 		act_seq.nextval,?,		systimestamp,	?,					1,				?,					?,					?,					?,					?,				0,			?,				?,			?,				?,						?)";


private static final String UPDATE = "update act set values"
+"actName=?, actStartDate=?, actEndDate=?, actIMG=?, actContent=?, actLong=?, actLat=?, actPost=?, actLocName=?, actAdr=?)"
+ "where actID=?";


private static final String GET_ALL_STMT = "select "
+"member.memName as memName, "
+"act.actID as actID, "
+"act.memID as memID, "
+"act.actCreateDate as actCreateDate, "
+"act.actName as actName, "
+"act.actStatus as actStatus, "
+"act.actPriID as actPriID, "
+"act.actStartDate as actStartDate, "
+"act.actEndDate as actEndDate, "
+"act.actSignStartDate as actSignStartDate, "
+"act.actSignEndDate as actSignEndDate, "
+"act.actTimeTypeID as actTimeTypeID, "
+"act.actTimeTypeCnt as actTimeTypeCnt, "
+"act.actMemMax as actMemMax, "
+"act.actMemMin as actMemMin, "
+"act.actIMG as actIMG, "
+"act.actContent as actContent, "
+"act.actIsHot as actIsHot, "
+"act.actLong as actLong, "
+"act.actLat as actLat, "
+"act.actPost as actPost, "
+"act.actLocName as actLocName, "
+"act.actAdr as actAdr "
+"FROM ACT join MEMBER on act.memID=MEMBER.memID";





private static final String GET_ONE="select * from act where actID=? order by actStartDate";
private static final String GET_ALL="select * from act order by actStartDate";
private static final String GET_BY_WKS="SELECT * FROM act WHERE MOD(TO_CHAR(sysdate, 'J'), 7) + 1 IN (6, 7)";
private static final String GET_BY_MEM="select * from actmem join act on actmem.actid=act.actid where actmem.memID=?  where actmem.actmemStatus=? order by actStartDate";
private static final String GET_BY_MEM12="select * from actmem join act on actmem.actid=act.actid where actmem.memID=?  where actmem.actmemStatus in (1,2) order by actStartDate";
private static final String GET_BY_POI="select * from act join actpoi on act.actid=actpoi.actid "
																+ "join poi on actpoi.poiid=poi.poiid where actpoi.poiid=?";
private static final String GET_BY_DATE="select * from act where to_char(actStartDate,'yyyy/mm/dd') in to_char(?,'yyyy/mm/dd')";
private static final String GET_ACT_BY_CLUBID="select * from act join actclub on act.actID=actClub.actID join club on actClub.ClubID=Club.ClubID"
+"where actClub (actClub.ClubID!=0) AND actClub.ClubID=?";


	@Override
	public Integer insert(ActVO actVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt.setInt(1, actVO.getMemID());
			pstmt.setString(2, actVO.getActName());
			pstmt.setTimestamp(3, actVO.getActStartDate());
			pstmt.setTimestamp(4, actVO.getActEndDate());
			pstmt.setBytes(5, actVO.getActIMG());
			pstmt.setString(6, actVO.getActContent());
			pstmt.setDouble(7, actVO.getActLong());
			pstmt.setDouble(8, actVO.getActLat());
			pstmt.setInt(9, actVO.getActPost());
			pstmt.setString(10, actVO.getActLocName());
			pstmt.setString(11, actVO.getActAdr());

			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
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
		return null;
	}

	public void update(ActVO actVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setString(1, actVO.getActName());
			pstmt.setTimestamp(2, actVO.getActStartDate());
			pstmt.setTimestamp(3, actVO.getActEndDate());
			pstmt.setBytes(4, actVO.getActIMG());
			pstmt.setString(5, actVO.getActContent());
			pstmt.setDouble(6, actVO.getActLong());
			pstmt.setDouble(7, actVO.getActLat());
			pstmt.setInt(8, actVO.getActPost());
			pstmt.setString(9, actVO.getActLocName());
			pstmt.setString(10, actVO.getActAdr());

			pstmt.executeUpdate();
			rs=pstmt.getGeneratedKeys();
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
	public ActFVO getOne(Integer actID) {

			ActFVO actfvo=null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE);
				pstmt.setInt(1, actID);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					ActVO actVO= new ActVO();
					actVO.setActType(rs.getInt("actType"));
					actVO.setActID(rs.getInt("actID"));
					actVO.setMemID(rs.getInt("memID"));
					actVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
					actVO.setActName(rs.getString("actName"));
					actVO.setActStatus(rs.getInt("actStatus"));
					actVO.setActPriID(rs.getInt("actPriID"));
					actVO.setActStartDate(rs.getTimestamp("actStartDate"));
					actVO.setActEndDate(rs.getTimestamp("actEndDate"));
					actVO.setActIMG(rs.getBytes("actIMG"));
					actVO.setActContent(rs.getString("actContent"));
					actVO.setActIsHot(rs.getInt("actIsHot"));
					actVO.setActLong(rs.getDouble("actLong"));
					actVO.setActLat(rs.getDouble("actLat"));
					actVO.setActPost(rs.getInt("actPost"));
					actVO.setActLocName(rs.getString("actLocName"));
					actVO.setActAdr(rs.getString("actAdr"));
					actVO.setActUID(rs.getString("actUID"));
					actVO.setActShowUnit(rs.getString("actShowUnit"));
					actVO.setActMasterUnit(rs.getString("actMasterUnit"));
					actVO.setActWebSales(rs.getString("actWebSales"));
					actVO.setActSourceWebName(rs.getString("actSourceWebName"));
					actVO.setActOnSale(rs.getString("actOnSale"));
					actVO.setActPrice(rs.getString("actPrice"));

					actfvo = new ActFVO(actVO);	
				};

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
			return actfvo;
		}


	@Override
	public List<ActFVO> getAll() {
		List<ActFVO> list = new ArrayList<ActFVO>();

		ActFVO actfvo=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActVO actVO= new ActVO();
				actVO.setActType(rs.getInt("actType"));
				actVO.setActID(rs.getInt("actID"));
				actVO.setMemID(rs.getInt("memID"));
				actVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				actVO.setActName(rs.getString("actName"));
				actVO.setActStatus(rs.getInt("actStatus"));
				actVO.setActPriID(rs.getInt("actPriID"));
				actVO.setActStartDate(rs.getTimestamp("actStartDate"));
				actVO.setActEndDate(rs.getTimestamp("actEndDate"));
				actVO.setActIMG(rs.getBytes("actIMG"));
				actVO.setActContent(rs.getString("actContent"));
				actVO.setActIsHot(rs.getInt("actIsHot"));
				actVO.setActLong(rs.getDouble("actLong"));
				actVO.setActLat(rs.getDouble("actLat"));
				actVO.setActPost(rs.getInt("actPost"));
				actVO.setActLocName(rs.getString("actLocName"));
				actVO.setActAdr(rs.getString("actAdr"));
				actVO.setActUID(rs.getString("actUID"));
				actVO.setActShowUnit(rs.getString("actShowUnit"));
				actVO.setActMasterUnit(rs.getString("actMasterUnit"));
				actVO.setActWebSales(rs.getString("actWebSales"));
				actVO.setActSourceWebName(rs.getString("actSourceWebName"));
				actVO.setActOnSale(rs.getString("actOnSale"));
				actVO.setActPrice(rs.getString("actPrice"));

				actfvo = new ActFVO(actVO);	
				list.add(actfvo);
			};

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<ActFVO> getActByPOIID(Integer POIID) {
		List<ActFVO> list = new ArrayList<ActFVO>();

		ActFVO actfvo=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_POI);
			pstmt.setInt(1, POIID);		
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ActVO actVO= new ActVO();
				actVO.setActType(rs.getInt("actType"));
				actVO.setActID(rs.getInt("actID"));
				actVO.setMemID(rs.getInt("memID"));
				actVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				actVO.setActName(rs.getString("actName"));
				actVO.setActStatus(rs.getInt("actStatus"));
				actVO.setActPriID(rs.getInt("actPriID"));
				actVO.setActStartDate(rs.getTimestamp("actStartDate"));
				actVO.setActEndDate(rs.getTimestamp("actEndDate"));
				actVO.setActIMG(rs.getBytes("actIMG"));
				actVO.setActContent(rs.getString("actContent"));
				actVO.setActIsHot(rs.getInt("actIsHot"));
				actVO.setActLong(rs.getDouble("actLong"));
				actVO.setActLat(rs.getDouble("actLat"));
				actVO.setActPost(rs.getInt("actPost"));
				actVO.setActLocName(rs.getString("actLocName"));
				actVO.setActAdr(rs.getString("actAdr"));
				actVO.setActUID(rs.getString("actUID"));
				actVO.setActShowUnit(rs.getString("actShowUnit"));
				actVO.setActMasterUnit(rs.getString("actMasterUnit"));
				actVO.setActWebSales(rs.getString("actWebSales"));
				actVO.setActSourceWebName(rs.getString("actSourceWebName"));
				actVO.setActOnSale(rs.getString("actOnSale"));
				actVO.setActPrice(rs.getString("actPrice"));

				actfvo = new ActFVO(actVO);	
				list.add(actfvo);
			};

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<ActFVO> getActByDate(Timestamp actDate) {
		List<ActFVO> list = new ArrayList<ActFVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_DATE);
			pstmt.setTimestamp(1, actDate);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				ActVO actVO= new ActVO();
				actVO.setActType(rs.getInt("actType"));
				actVO.setActID(rs.getInt("actID"));
				actVO.setMemID(rs.getInt("memID"));
				actVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				actVO.setActName(rs.getString("actName"));
				actVO.setActStatus(rs.getInt("actStatus"));
				actVO.setActPriID(rs.getInt("actPriID"));
				actVO.setActStartDate(rs.getTimestamp("actStartDate"));
				actVO.setActEndDate(rs.getTimestamp("actEndDate"));
				actVO.setActIMG(rs.getBytes("actIMG"));
				actVO.setActContent(rs.getString("actContent"));
				actVO.setActIsHot(rs.getInt("actIsHot"));
				actVO.setActLong(rs.getDouble("actLong"));
				actVO.setActLat(rs.getDouble("actLat"));
				actVO.setActPost(rs.getInt("actPost"));
				actVO.setActLocName(rs.getString("actLocName"));
				actVO.setActAdr(rs.getString("actAdr"));
				actVO.setActUID(rs.getString("actUID"));
				actVO.setActShowUnit(rs.getString("actShowUnit"));
				actVO.setActMasterUnit(rs.getString("actMasterUnit"));
				actVO.setActWebSales(rs.getString("actWebSales"));
				actVO.setActSourceWebName(rs.getString("actSourceWebName"));
				actVO.setActOnSale(rs.getString("actOnSale"));
				actVO.setActPrice(rs.getString("actPrice"));

				ActFVO actfvo = new ActFVO(actVO);	
				list.add(actfvo);
			};

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<ActFVO> getActByWks() {
		List<ActFVO> list = new ArrayList<ActFVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_WKS);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				ActVO actVO= new ActVO();
				actVO.setActType(rs.getInt("actType"));
				actVO.setActID(rs.getInt("actID"));
				actVO.setMemID(rs.getInt("memID"));
				actVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				actVO.setActName(rs.getString("actName"));
				actVO.setActStatus(rs.getInt("actStatus"));
				actVO.setActPriID(rs.getInt("actPriID"));
				actVO.setActStartDate(rs.getTimestamp("actStartDate"));
				actVO.setActEndDate(rs.getTimestamp("actEndDate"));
				actVO.setActIMG(rs.getBytes("actIMG"));
				actVO.setActContent(rs.getString("actContent"));
				actVO.setActIsHot(rs.getInt("actIsHot"));
				actVO.setActLong(rs.getDouble("actLong"));
				actVO.setActLat(rs.getDouble("actLat"));
				actVO.setActPost(rs.getInt("actPost"));
				actVO.setActLocName(rs.getString("actLocName"));
				actVO.setActAdr(rs.getString("actAdr"));
				actVO.setActUID(rs.getString("actUID"));
				actVO.setActShowUnit(rs.getString("actShowUnit"));
				actVO.setActMasterUnit(rs.getString("actMasterUnit"));
				actVO.setActWebSales(rs.getString("actWebSales"));
				actVO.setActSourceWebName(rs.getString("actSourceWebName"));
				actVO.setActOnSale(rs.getString("actOnSale"));
				actVO.setActPrice(rs.getString("actPrice"));

				ActFVO actfvo = new ActFVO(actVO);	
				list.add(actfvo);
			};

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<ActFVO> getActByClub(Integer clubID) {
		List<ActFVO> list = new ArrayList<ActFVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_DATE);
			pstmt.setInt(1, clubID);
			rs = pstmt.executeQuery();


			while (rs.next()) {
				ActVO actVO= new ActVO();
				actVO.setActType(rs.getInt("actType"));
				actVO.setActID(rs.getInt("actID"));
				actVO.setMemID(rs.getInt("memID"));
				actVO.setActCreateDate(rs.getTimestamp("actCreateDate"));
				actVO.setActName(rs.getString("actName"));
				actVO.setActStatus(rs.getInt("actStatus"));
				actVO.setActPriID(rs.getInt("actPriID"));
				actVO.setActStartDate(rs.getTimestamp("actStartDate"));
				actVO.setActEndDate(rs.getTimestamp("actEndDate"));
				actVO.setActIMG(rs.getBytes("actIMG"));
				actVO.setActContent(rs.getString("actContent"));
				actVO.setActIsHot(rs.getInt("actIsHot"));
				actVO.setActLong(rs.getDouble("actLong"));
				actVO.setActLat(rs.getDouble("actLat"));
				actVO.setActPost(rs.getInt("actPost"));
				actVO.setActLocName(rs.getString("actLocName"));
				actVO.setActAdr(rs.getString("actAdr"));
				actVO.setActUID(rs.getString("actUID"));
				actVO.setActShowUnit(rs.getString("actShowUnit"));
				actVO.setActMasterUnit(rs.getString("actMasterUnit"));
				actVO.setActWebSales(rs.getString("actWebSales"));
				actVO.setActSourceWebName(rs.getString("actSourceWebName"));
				actVO.setActOnSale(rs.getString("actOnSale"));
				actVO.setActPrice(rs.getString("actPrice"));

				ActFVO actfvo = new ActFVO(actVO);	
				list.add(actfvo);
			};

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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





	public List<ActFVO> getMemActs(Integer memID, Integer stat) {
		List<ActFVO> list = new ArrayList<ActFVO>();
		ActVO ActVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement("GET_BY_MEM");
			pstmt.setInt(1, memID);
			pstmt.setInt(2, stat);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				ActVO actvo= new ActVO();
				actvo.setActType(rs.getInt("actType"));
				actvo.setActID(rs.getInt("actID"));
				actvo.setMemID(rs.getInt("memID"));
				actvo.setActCreateDate(rs.getTimestamp("actCreateDate"));
				actvo.setActName(rs.getString("actName"));
				actvo.setActStartDate(rs.getTimestamp("actStartDate"));
				actvo.setActIMG(rs.getBytes("actIMG"));
				actvo.setActContent((Objects.isNull(rs.getString("actContent")))? "^_^" :( rs.getString("actContent"))) ;
				actvo.setActLong(rs.getDouble("actLong"));
				actvo.setActLat(rs.getDouble("actLat"));
				actvo.setActPost(rs.getInt("actPost")) ;
				actvo.setActLocName((Objects.isNull(rs.getString("actLocName")))? "" : (rs.getString("actLocName")));
				actvo.setActAdr(rs.getString("actAdr"));
				actvo.setActUID((Objects.isNull(rs.getString("actUID")))? "" : (rs.getString("actUID")));
				actvo.setActShowUnit((Objects.isNull(rs.getString("actShowUnit")))? "" : (rs.getString("actShowUnit")));
				actvo.setActMasterUnit((Objects.isNull(rs.getString("actMasterUnit")))? "" : (rs.getString("actMasterUnit")));
				actvo.setActWebSales((Objects.isNull(rs.getString("actWebSales")))? "" : (rs.getString("actWebSales")));
				actvo.setActSourceWebName((Objects.isNull(rs.getString("actSourceWebName")))? "" : (rs.getString("actSourceWebName")));
				actvo.setActOnSale((Objects.isNull(rs.getString("actOnSale")))? "" : (rs.getString("actOnSale")));
				actvo.setActPrice((Objects.isNull(rs.getString("actPrice")))? "" : (rs.getString("actPrice")));
				
				ActFVO actfvo=new ActFVO(actvo);
				list.add(actfvo);

			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<ActFVO> getMemActs12(Integer memID) {
			List<ActFVO> list = new ArrayList<ActFVO>();
			ActVO ActVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				try {
					Class.forName(driver);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement("GET_BY_MEM12");
				pstmt.setInt(1, memID);
				pstmt.setInt(2, 1);
				
				rs = pstmt.executeQuery();

				while (rs.next()) {

					ActVO actvo= new ActVO();
					actvo.setActType(rs.getInt("actType"));
					actvo.setActID(rs.getInt("actID"));
					actvo.setMemID(rs.getInt("memID"));
					actvo.setActCreateDate(rs.getTimestamp("actCreateDate"));
					actvo.setActName(rs.getString("actName"));
					actvo.setActStartDate(rs.getTimestamp("actStartDate"));

					actvo.setActContent((Objects.isNull(rs.getString("actContent")))? "^_^" :( rs.getString("actContent"))) ;
					actvo.setActLong(rs.getDouble("actLong"));
					actvo.setActLat(rs.getDouble("actLat"));
					actvo.setActPost(rs.getInt("actPost")) ;
					actvo.setActLocName((Objects.isNull(rs.getString("actLocName")))? "" : (rs.getString("actLocName")));
					actvo.setActAdr(rs.getString("actAdr"));
					actvo.setActUID((Objects.isNull(rs.getString("actUID")))? "" : (rs.getString("actUID")));
					actvo.setActShowUnit((Objects.isNull(rs.getString("actShowUnit")))? "" : (rs.getString("actShowUnit")));
					actvo.setActMasterUnit((Objects.isNull(rs.getString("actMasterUnit")))? "" : (rs.getString("actMasterUnit")));
					actvo.setActWebSales((Objects.isNull(rs.getString("actWebSales")))? "" : (rs.getString("actWebSales")));
					actvo.setActSourceWebName((Objects.isNull(rs.getString("actSourceWebName")))? "" : (rs.getString("actSourceWebName")));
					actvo.setActOnSale((Objects.isNull(rs.getString("actOnSale")))? "" : (rs.getString("actOnSale")));
					actvo.setActPrice((Objects.isNull(rs.getString("actPrice")))? "" : (rs.getString("actPrice")));
					
					ActFVO actfvo=new ActFVO(actvo);
					list.add(actfvo);

				}


				

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
	public List<ActFVO> getAllWithPast() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static void main(String[] args) throws IOException {

		ActDAOJDBC dao = new ActDAOJDBC();

		// 鏂板	ok
//		Act_VO ActVOIns = new Act_VO();
//		byte[] actImg=com.gen.tool.tools.getPictureByteArray("E:\\Dropbox\\act12.jpg");
//		Timestamp ts=com.gen.tool.tools.strToTimestamp("2017-09-23 10:10:10");
//		Timestamp tsStart=com.gen.tool.tools.strToTimestamp("2017-09-24 18:00:00");
//		Timestamp tsEnd=com.gen.tool.tools.strToTimestamp("2017-09-25 18:00:00");
//		Timestamp tsSStart=com.gen.tool.tools.strToTimestamp("2017-09-22 18:00:00");
//		Timestamp tsSEnd=com.gen.tool.tools.strToTimestamp("2017-09-24 16:00:00");
//        ActVOIns.setMemID(1);
//		ActVOIns.setActCreateDate(ts);
//		ActVOIns.setActName("涓�璧风湅鎻挭<3");
//		ActVOIns.setActStatus(1);
//		ActVOIns.setActTimeTypeID(0);
//		ActVOIns.setActPriID(1);
//		ActVOIns.setActPost(111);
//		ActVOIns.setActStartDate(tsStart);
//		ActVOIns.setActEndDate(tsEnd);
//		ActVOIns.setActSignStartDate(tsSStart);
//		ActVOIns.setActSignEndDate(tsSEnd);
//		ActVOIns.setActTimeTypeCnt("");
//		ActVOIns.setActMemMax(5);
//		ActVOIns.setActMemMin(1);
//		ActVOIns.setActIMG(actImg);
//		ActVOIns.setActContent("123");
//		ActVOIns.setActLong(25.102364);
//		ActVOIns.setActLat(121.548502);
//		ActVOIns.setActLocName("鍦嬬珛鏁呭鍗氱墿闄�");
//		ActVOIns.setActAdr("鍙板寳甯傚＋鏋楀崁鑷冲杽璺簩娈�221铏�");
//
//		dao.insert(ActVOIns);

		// 淇敼	ok
//		Act_VO ActVOUpd = new Act_VO();
//		ActVOUpd.setActName("鏇存敼娓│");
//		ActVOUpd.setActPriID(1);
//		ActVOUpd.setActStartDate(tsStart);
//		ActVOUpd.setActEndDate(tsEnd);
//		ActVOUpd.setActSignStartDate(tsSStart);
//		ActVOUpd.setActSignEndDate(tsSEnd);
//		ActVOUpd.setActTimeTypeID(0);
//		ActVOUpd.setActTimeTypeCnt("");
//		ActVOUpd.setActMemMax(999);
//		ActVOUpd.setActMemMin(5);
//		ActVOUpd.setActIMG(actImg);
//		ActVOUpd.setActContent("鏀逛簡杈�");
//		ActVOUpd.setActAdr("鎴戝");
//		ActVOUpd.setActID(14);
//		System.out.print(ActVOUpd.getActName());
//		dao.update(ActVOUpd);


		// UPLOAD IMG	ok
//		for (int i=1; i<14; i++){
//		String path = "E:\\\\actBLOB\\act"+i+".jpg";
//		System.out.println(path);
//		int actID=i;
//		dao.updateIMG(path, actID);
//		}
		
			// 鍒櫎	涓嶈兘鍒荆
//		dao.delete(30);

		// 鏌ヨ	ok
//		Act_VO actVO = dao.getOne(5);
//		System.out.print(actVO.getActName() + ",");
//		System.out.print(actVO.getActStartDate() + ",");
//		System.out.println(actVO.getActAdr());
//		System.out.println("---------------------");

		List<ActFVO> list = dao.getAll();
		for (ActFVO aDept : list) {
			System.out.print(aDept.getMemName() + ",");
			System.out.print(aDept.getActCnt() + ",");
			System.out.print(aDept.getActVO().getActName());
			System.out.println();
		}

	}

	@Override
	public List<ActFVO> getx2ByPOIID(Integer pOIID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActFVO> getRDx2ByPOIID() {
		// TODO Auto-generated method stub
		return null;
	}


}

