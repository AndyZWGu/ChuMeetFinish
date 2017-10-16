package com.gen.tool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oracle.sql.BLOB;
import oracle.sql.CLOB;

public class tools {

	//Clob轉String
	public static String clobToString(Clob sc) throws SQLException, IOException {
		if (sc == null) {
			return "null";
		} else {
			String reString = "";
			Reader is = sc.getCharacterStream();
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {
				sb.append(s);
				s = br.readLine();
			}
			reString = sb.toString();
			return reString;

		}

	}

	//String轉Clob(需連線參數)
	public static Clob stringToClob(String str, Connection con) throws SQLException {
		if (str == null) {
			return null;
		} else {
			java.sql.Clob clob = con.createClob();
			clob.setString(1, str);
			return clob;
		}
	}
	//String轉Clob(有資料庫連線不要用此方法)
	public static Clob stringToClob(String str) {
		if (null == str)
			return null;
		else {
			try {
				java.sql.Clob c = new javax.sql.rowset.serial.SerialClob(str.toCharArray());
				return c;
			} catch (Exception e) {
				return null;
			}
		}
	}

	//byte轉Blob(需連線參數) 本機圖片>>資料庫
	public static Blob byteToBlob(String path, Connection con) throws SQLException, IOException {
		Blob blob = con.createBlob();
		byte[] pic2 = getPictureByteArray(path);
		blob.setBytes(1, pic2);
		return blob;
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();

		return baos.toByteArray();
	}

	public static byte[] getUrlPictureByteArray(String Urll) throws IOException {
	    URL url = new URL(Urll);
	    InputStream is = url.openStream();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = is.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		is.close();

		return baos.toByteArray();
	}
	
	
	//BLOB轉byte
	public static byte[] blobToBytes(Blob blob) {

		BufferedInputStream is = null;

		try {
			is = new BufferedInputStream(blob.getBinaryStream());
			byte[] bytes = new byte[(int) blob.length()];
			int len = bytes.length;
			int offset = 0;
			int read = 0;

			while (offset < len && (read = is.read(bytes, offset, len - offset)) >= 0) {
				offset += read;
			}
			return bytes;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				is.close();
				is = null;
			} catch (IOException e) {
				return null;
			}
		}
	}
	  
	//string 轉 date, 如 ("2012-05-01")
	  public static Date toSqlDate(String date) {		  

		    java.sql.Date d2 = java.sql.Date.valueOf(date);  
		   // System.out.println("String use sDate.valueOf to Date objcet =>"+d2.toString());
		    return d2;
		  }
	  
	  //取得現在時間(java.sql.Date型態)
	  public static Date todayTime(){
		  java.util.Date now = new java.util.Date();
	      java.sql.Date sqlDate = new java.sql.Date(now.getTime());
	     
	      return sqlDate;
	  }
	  
	  
	  //java.sql.Date轉字串,格式為yyyy/MM/dd hh:mm:ss
	  public static String sqlDateToStr(Date sqlDate){
	        java.util.Date utilDate = new java.util.Date();
	        utilDate.setTime(sqlDate.getTime());
	        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	        String strDate = sdFormat.format(sqlDate);
	        
	        return strDate;
	  }
	  
	  //------------------timestsmp--------------------
	  
	  //取得現在的Timestamp時間
	  public static Timestamp nowTimestamp(){
		  java.util.Date utildate=new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(utildate.getTime());
			java.sql.Time sTime=new java.sql.Time(utildate.getTime());
			java.sql.Timestamp stp=new java.sql.Timestamp(utildate.getTime());
	       return stp;
	  }
	  
	//Timestamp轉String
	  public static String timestampToString(Timestamp timestamp){
		  
		  if(timestamp==null){
			  return "null";
		  }else{
			  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 
				 // Timestamp now = new Timestamp(System.currentTimeMillis());/ 
				  String str = df.format(timestamp); 
				  System.out.println(str); 
			        return str;
		  }

	  }
	  
		//Timestamp轉String@Act
	  public static String tsToActStr(Timestamp timestamp){
		  
		  if(timestamp==null){
			  return "null";
		  }else{
			  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 
				 // Timestamp now = new Timestamp(System.currentTimeMillis());/ 
				  String str = df.format(timestamp); 
				  System.out.println(str); 
			        return str;
		  }

	  }
		//Timestamp轉String@Act
	  public static String tsToActStrOT(Timestamp timestamp){
		  
		  if(timestamp==null){
			  return "null";
		  }else{
			  SimpleDateFormat df = new SimpleDateFormat("HH:mm");// 
				 // Timestamp now = new Timestamp(System.currentTimeMillis());/ 
				  String str = df.format(timestamp); 
				  System.out.println(str); 
			        return str;
		  }

	  }
	//String轉Timestamp時間,可自訂,如 2014-09-23 21:34:12""
	    public static java.sql.Timestamp strToTimestamp(String strDate) {
	        java.util.Date utDate;
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            sdf.setLenient(false);
	            utDate = sdf.parse(strDate);
	        } catch (Exception e) {
	            return null;
	        }
	        return new java.sql.Timestamp(utDate.getTime());
	    }
	    
	    
	    public static java.sql.Timestamp strHTML5ToTimestamp(String strDate) {
	        java.util.Date utDate;
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	            sdf.setLenient(false);
	            utDate = sdf.parse(strDate);
	        } catch (Exception e) {
	            return null;
	        }
	        return new java.sql.Timestamp(utDate.getTime());
	    }
	    
	    
	    

	    public static java.sql.Timestamp strToTimestampGson(String strDate) {
	        java.util.Date utDate;
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	            sdf.setLenient(false);
	            utDate = sdf.parse(strDate);
	        } catch (Exception e) {
	            return null;
	        }
	        return new java.sql.Timestamp(utDate.getTime());
	    }
	    
	    public static java.sql.Timestamp strToTimestampGsonDO(String strDate) {
	        java.util.Date utDate;
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	            sdf.setLenient(false);
	            utDate = sdf.parse(strDate);
	        } catch (Exception e) {
	            return null;
	        }
	        return new java.sql.Timestamp(utDate.getTime());
	    }
	    
		public static byte[] sendPicture(String path) throws IOException {
			
			FileInputStream in = new FileInputStream(path);
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buff = new byte[in.available()];
			int len;
			while((len=in.read(buff)) != -1) {
				out.write(buff, 0 , len);
			}
			out.close();
			in.close();
			return out.toByteArray();
		}
		
		
		
		public static String sendInfo(String text) throws IOException {
			
			BufferedReader in = new BufferedReader(new FileReader(text));
			StringBuilder sb = new StringBuilder();
			String info;
			while((info = in.readLine()) != null) {
				sb.append(info);
				sb.append("\n\r");
			}
			return sb.toString();
		}
		
		    public static String GetWKSFromNow() {
		    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		    	Calendar today =Calendar.getInstance();
		    	Calendar cal =Calendar.getInstance();
//		    	System.out.println(cal.getTime());
		    	cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		    	cal.add(Calendar.DAY_OF_WEEK, 6);
		    	String sb=dateFormat.format(cal.getTime());
		    	cal.add(Calendar.DAY_OF_WEEK, 1);
//		    	System.out.println(dateFormat.format(cal.getTime()));
		    	sb="'"+sb+"','"+dateFormat.format(cal.getTime())+"'";
		    	System.out.println(sb);
		    	
		    	return sb;
		    }
		    
		    
		}

	    

