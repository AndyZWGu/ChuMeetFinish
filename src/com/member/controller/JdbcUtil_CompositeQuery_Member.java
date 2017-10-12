/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package com.member.controller;

import java.util.*;

public class JdbcUtil_CompositeQuery_Member {

	static String sort = "";
	
	public static String get_aCondition_For_Oracle(String tableName, String columnName, String value) {

		String aCondition = null;

		//Member會員表
		if (tableName == "Member") {
			if ("memLv".equals(columnName)|| "memExp".equals(columnName)|| "memPt".equals(columnName)
					|| "memLoginNum".equals(columnName)|| "memPriv".equals(columnName)) // 用於大於等於
				aCondition = columnName + ">=" + value;
			if ("memID".equals(columnName) || "memberType".equals(columnName) 
					|| "memGender".equals(columnName)|| "memPhone".equals(columnName)
					|| "memLong".equals(columnName)|| "memLat".equals(columnName)
					|| "memPriv".equals(columnName)|| "memStatus".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			//出生地與居住地
			else if ("county".equals(columnName) || "memLocBorn".equals(columnName)) // 用於varchar
				aCondition = "memLocBorn" + " like '%" + value + "%'";
			else if ("county".equals(columnName) || "memLocLive".equals(columnName)) // 用於varchar
				aCondition = "memLocLive" + " like '%" + value + "%'";
			//==
			else if ("memEmail".equals(columnName) || "memPw".equals(columnName)
					|| "memName".equals(columnName)|| "memLocBorn".equals(columnName)
					|| "memLocLive".equals(columnName)|| "memInt".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("memBD".equals(columnName)|| "memJoinDate".equals(columnName)) // 用於Oracle的date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
			//排序
			else if ("sort".equals(columnName)){ // 用於排序
				if(value==null)
					sort=null;
				else if(value=="追蹤數(升序)")
					sort = "order by " + "" + value + "%'";
				else if(value=="追蹤數(降序)")
					sort = "order by" + " like '%" + value + "%'";
				else if(value=="好友數排序(升序)")
					sort = "order by" + " like '%" + value + "%'";
				else if(value=="好友數排序(降序)")
					sort = "order by" + " like '%" + value + "%'";
				else if(value=="評價數排序(升序)")
					sort = "order by" + " like '%" + value + "%'";
				else if(value=="評價數排序(降序)")
					sort = "order by" + " like '%" + value + "%'";
				else if(value=="加入日期排序(升序)")
					sort = "order by" + " like '%" + value + "%'";
				else if(value=="加入日期排序(降序)")
					sort = "order by" + " like '%" + value + "%'";
				else if("登入次數排序(升序)".equals(value))
					sort = "order by memLoginNum Asc";
				else if("登入次數排序(降序)".equals(value))
					sort = "order by memLoginNum Desc";
				aCondition="";
			}
		}
		//Friends好友表
		if (tableName == "Friends") {
			if ("friMem1".equals(columnName) || "friMem2".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			else if ("friendType".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("friendDate".equals(columnName)) // 用於Oracle的date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}
		//MemMail會員郵件表
		if (tableName == "MemMail") {
			if ("memMailID".equals(columnName) || "receiver".equals(columnName) 
					|| "author".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			else if ("mailTitle".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("mailDate".equals(columnName)) // 用於Oracle的date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}
		//MemNF會員動態表
		if (tableName == "MemNF") {
			if ("memNFID".equals(columnName) || "memID".equals(columnName) || "nfViews".equals(columnName)
					|| "nfStatus".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			else if ("nfTitle".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("nfDate".equals(columnName)) // 用於Oracle的date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}
		//MemMB會員留言表
		if (tableName == "MemMB") {
			if ("memMBID".equals(columnName) || "memNFID".equals(columnName) || "memID".equals(columnName)
					|| "memStatus".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			else if ("mbContent".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("mbDate".equals(columnName)) // 用於Oracle的date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}
		//MemPOI會員興趣表
		if (tableName == "MemPOI") {
			if ("empno".equals(columnName) || "sal".equals(columnName) || "comm".equals(columnName)
					|| "deptno".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			else if ("ename".equals(columnName) || "job".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("hiredate".equals(columnName)) // 用於Oracle的date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}
		//Ach成就表
		if (tableName == "Ach") {
			if ("achID".equals(columnName) || "rewID".equals(columnName) 
					|| "achStatus".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			else if ("achName".equals(columnName) || "achDesc".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("".equals(columnName)) // 用於Oracle的date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}
		//MemAch會員成就表
		if (tableName == "MemAch") {
			if ("memID".equals(columnName) || "achID".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			else if ("".equals(columnName) || "".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("acquireDate".equals(columnName)) // 用於Oracle的date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}
		//Reward獎賞表
		if (tableName == "Reward") {
			if ("rewID".equals(columnName) || "rewExp".equals(columnName) || "rewPt".equals(columnName)
					|| "rewStatus".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			else if ("rewName".equals(columnName) || "rewDesc".equals(columnName)
					|| "rewType".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("".equals(columnName)) // 用於Oracle的date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}
		//MemReward會員獎賞表
		if (tableName == "MemReward") {
			if ("memRewID".equals(columnName) || "memID".equals(columnName) 
					|| "rewID".equals(columnName)) // 用於其他
				aCondition = columnName + "=" + value;
			else if ("acquireType".equals(columnName)) // 用於varchar
				aCondition = columnName + " like '%" + value + "%'";
			else if ("rewDate".equals(columnName)) // 用於Oracle的date
				aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";
		}
		// if ("empno".equals(columnName) || "sal".equals(columnName) ||
		// "comm".equals(columnName) || "deptno".equals(columnName)) // 用於其他
		// aCondition = columnName + "=" + value;
		// else if ("ename".equals(columnName) || "job".equals(columnName)) //
		// 用於varchar
		// aCondition = columnName + " like '%" + value + "%'";
		// else if ("hiredate".equals(columnName)) // 用於Oracle的date
		// aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value +
		// "'";

		return aCondition + " ";
	}

	//DAO複合查詢用
	public static String get_WhereCondition(String tableName,Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(tableName,key, value.trim());

				if (count == 1 && !" ".equals(aCondition))
					whereCondition.append(" where " + aCondition);
				else if(!" ".equals(aCondition))
					whereCondition.append(" and " + aCondition);
				System.out.println("aCondition=<"+aCondition+">");
				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		if(tableName=="member"){
			whereCondition.append(sort);	
		}
		return whereCondition.toString();
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳
		// java.util.Map<java.lang.String,java.lang.String[]> 之測試
//		Map<String, String[]> map = new TreeMap<String, String[]>();
//		map.put("empno", new String[] { "7001" });
//		map.put("ename", new String[] { "KING" });
//		map.put("job", new String[] { "PRESIDENT" });
//		map.put("hiredate", new String[] { "1981-11-17" });
//		map.put("sal", new String[] { "5000.5" });
//		map.put("comm", new String[] { "0.0" });
//		map.put("deptno", new String[] { "10" });
//		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key
//
//		String finalSQL = "select * from emp2 " + jdbcUtil_CompositeQuery_Emp2.get_WhereCondition(map)
//				+ "order by empno";
//		System.out.println("●●finalSQL = " + finalSQL);

		//單一會員表複合查詢測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("memID", new String[] { "7001" });
		map.put("memEmail", new String[] { "KING" });
		map.put("memName", new String[] { "PRESIDENT" });
		map.put("memBD", new String[] { "1981-11-17" });
		map.put("memPt", new String[] { "5000.5" });
		map.put("memLong", new String[] { "0.0" });
		map.put("memLoginNum", new String[] { "10" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key
		String finalSQL = "select * from member " + JdbcUtil_CompositeQuery_Member.get_WhereCondition("Member",map)
		+ "order by memID";
System.out.println("●●finalSQL = " + finalSQL);
		
	}
}
