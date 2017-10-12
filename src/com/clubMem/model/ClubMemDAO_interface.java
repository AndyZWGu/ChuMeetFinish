package com.clubMem.model;

import java.util.List;

import com.clubMB.model.ClubMBVO;

public interface ClubMemDAO_interface {

	public void insert(ClubMemVO clubMemVO);

	public void update(ClubMemVO clubMemVO);

	// public void delete(Integer memID,Integer clubID);
	public ClubMemVO findByPrimaryKey( Integer clubID,Integer memID);

	public List<ClubMemVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
	// public List<EmpVO> getAll(Map<String, String[]> map);

	// 查詢某部門的員工(一對多)(回傳 Set)
	// public Set<EmpVO> getEmpsByDeptno(Integer deptno);

	// 以下自己加
	public List<ClubMemVO> findMemByClubID(Integer clubID);

	public void changeType(Integer memID, Integer clubID);

	//還沒做改社團成員變成幹部,會長還是一般會員
//	public void updateClubMemStatus(Integer memID, Integer clubID);
	
	//還沒做找管理員人數
//	public ClubMemVO findManagers( Integer clubID,Integer memID);
	
	//找某會員有參加那些社團
	public List<ClubMemVO> getAllJoinClub(Integer memID);
}
