package com.clubMem.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.clubMB.model.ClubMBVO;
import com.clubMem.model.ClubMemVO;
import com.clubNews.model.ClubNewsVO;

public class ClubMemService {

	private ClubMemDAO_interface dao;

	public ClubMemService() {
		dao = new ClubMemDAO();
	}
	
	public ClubMemVO addClubMem(Integer clubID,Integer memID, Integer clubMemType, java.sql.Timestamp clubMemJoinDate,Integer clubMemStatus) {

		ClubMemVO clubMemVO = new ClubMemVO();
		clubMemVO.setClubID(clubID);
		clubMemVO.setMemID(memID);
		clubMemVO.setClubMemType(clubMemType);
		clubMemVO.setClubMemJoinDate(clubMemJoinDate);
		clubMemVO.setClubMemStatus(clubMemStatus);
		dao.insert(clubMemVO);

		return clubMemVO;
	}
	
	public ClubMemVO updateClubMem(Integer clubID,Integer memID, Integer clubMemType, java.sql.Timestamp clubMemJoinDate,Integer clubMemStatus) {

		ClubMemVO clubMemVO = new ClubMemVO();
		clubMemVO.setClubID(clubID);
		clubMemVO.setMemID(memID);
		clubMemVO.setClubMemType(clubMemType);
		clubMemVO.setClubMemJoinDate(clubMemJoinDate);
		clubMemVO.setClubMemStatus(clubMemStatus);
		dao.update(clubMemVO);

		return clubMemVO;
	}
	

	public ClubMemVO getOneClubMem(Integer clubID,Integer memID) {
		return dao.findByPrimaryKey(clubID,memID);
	}

	public List<ClubMemVO> getAll() {
		return dao.getAll();
	}
	
	
	//以下自己加
	//找尋某社團所以有MB
	public List<ClubMemVO> findMemByClubID(Integer clubID){
		return dao.findMemByClubID(clubID);
	}
	
	
	public List<ClubMemVO> getAllJoinClub(Integer memID){
		return dao.getAllJoinClub(memID);
	}
	
	
	//以下新作的
	public ClubMemVO updateClubMemType(Integer clubID,Integer memID,Integer clubMemType) {

		ClubMemVO clubMemVO = new ClubMemVO();
		clubMemVO.setClubID(clubID);
		clubMemVO.setMemID(memID);
		clubMemVO.setClubMemType(clubMemType);
		dao.updateClubMemType(clubMemVO);
System.out.println("ClubMemVOServicrOK");
		return clubMemVO;
	}
	
	
	public ClubMemVO changeMemStatus(Integer clubID,Integer memID,Integer clubMemStatus) {

		ClubMemVO clubMemVO = new ClubMemVO();
		clubMemVO.setClubID(clubID);
		clubMemVO.setMemID(memID);
		clubMemVO.setClubMemStatus(clubMemStatus);
System.out.println("ClubMemVOServicr1");		
		dao.changeMemStatus(clubMemVO);
System.out.println("ClubMemVOServicrOK");
		return clubMemVO;
	}
	
	
	
	
	public ClubMemVO exitMemStatus(Integer clubID,Integer memID,Integer clubMemStatus) {

		ClubMemVO clubMemVO = new ClubMemVO();
		clubMemVO.setClubID(clubID);
		clubMemVO.setMemID(memID);
		clubMemVO.setClubMemStatus(clubMemStatus);
System.out.println("ClubMemVOServicr1");		
		dao.changeMemStatus(clubMemVO);
System.out.println("ClubMemVOServicrOK");
		return clubMemVO;
	}
	
	
	
	
	
	
	
	
	
}





//
//public ClubMemVO updateClubMem(Integer memID, Integer clubID,Integer clubMemType, java.sql.Timestamp clubMemJoinDate) {
//
//	ClubMemVO clubMemVO = new ClubMemVO();
//
//	clubMemVO.setMemID(memID);
//	clubMemVO.setClubID(clubID);
//	clubMemVO.setClubMemType(clubMemType);
//	clubMemVO.setClubMemJoinDate(clubMemJoinDate);
//	dao.update(clubMemVO);
//
//	return dao.findByPrimaryKey(clubID,memID);
//}

