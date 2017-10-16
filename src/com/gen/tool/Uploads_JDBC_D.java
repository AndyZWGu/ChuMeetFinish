package com.gen.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.act.act.model.Act_interface;
import com.gen.tool.tools;

import oracle.sql.BLOB;


public class Uploads_JDBC_D {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA103G2";
	String passwd = "a123";

	private static final String UPDATEBLOB = "update act set actIMG=? where actID=?";
	private static final String UPDATEPOIBLOB = "update POI set POIIMG =? where POIID=?";
	private static final String UPDATEMEMBLOB = "update MEMBER set MEMAVATAR=? where memID=?";
	private static final String UPDATECLOB = "update act set actContent=? where actID=?";
	
	public void updateIMG(String path, Integer actID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATEBLOB);
				
			FileInputStream fin=new FileInputStream(path);
			Blob blobbb=con.createBlob();
			byte[] bytesss=tools.sendPicture(path);
			pstmt.setInt(2, actID);
			blobbb.setBytes(1, bytesss);
			pstmt.setBlob(1, blobbb);

			pstmt.executeUpdate();
			System.out.println("updated img");
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	public void updatePOIIMG(String path, Integer POIID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATEPOIBLOB);
				
			FileInputStream fin=new FileInputStream(path);
			Blob blobbb=con.createBlob();
			byte[] bytesss=tools.sendPicture(path);
			pstmt.setInt(2, POIID);
			blobbb.setBytes(1, bytesss);
			pstmt.setBlob(1, blobbb);

			pstmt.executeUpdate();
			System.out.println("updated img");
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	
	public void updateMemIMG(String path, Integer memID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATEMEMBLOB);
				
			FileInputStream fin=new FileInputStream(path);
			Blob blobbb=con.createBlob();
			byte[] bytesss=tools.sendPicture(path);
			pstmt.setInt(2, memID);
			blobbb.setBytes(1, bytesss);
			pstmt.setBlob(1, blobbb);

			pstmt.executeUpdate();
			System.out.println("updated img");
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	
	public void updateCnt(String path, Integer actID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATECLOB);
				
			FileInputStream fin=new FileInputStream(path);
			Clob clobbb=con.createClob();
			String str=tools.sendInfo(path);
			pstmt.setInt(2, actID);
			clobbb.setString(1, str);
			pstmt.setClob(1, clobbb);

			pstmt.executeUpdate();
			System.out.println("updated Cnt");
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public static void main(String[] args) throws IOException {

		Uploads_JDBC_D dao = new Uploads_JDBC_D();

		// UPLOAD IMG	ok
		for (int i=1; i<14; i++){
		String path = "E://DB/actBLOB/act"+i+".jpg";
		System.out.println(path);
		int actID=i;
		dao.updateIMG(path, actID);
		}
		
		// UPLOAD CLOB ok
		for (int i=1; i<14; i++){
		String path = "E://DB/actCLOB/act"+i+".txt";
		System.out.println(path);
		int actID=i;
		dao.updateCnt(path, actID);
		}		
		
		
		// UPLOAD mem	ok
		for (int i=1; i<13; i++){
		String path = "E://DB/memBLOB/"+i+".jpg";
		System.out.println(path);
		int memID=i;
		dao.updateMemIMG(path, memID);
		}

		// UPLOAD POI	ok
		for (int i=1; i<=25; i++){
		String path = "E://DB/POIBLOB/"+i+".jpg";
		System.out.println(path);
		int poiID=i;
		dao.updatePOIIMG(path, poiID);
		}

	}


	}


	

