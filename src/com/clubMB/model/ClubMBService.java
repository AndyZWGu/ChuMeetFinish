package com.clubMB.model;

import java.sql.Timestamp;
import java.util.List;

public class ClubMBService {

	private ClubMBDAO_interface dao;

	public ClubMBService() {
		dao = new ClubMBDAO();
	}

	public ClubMBVO addClubMB( Integer clubID,Integer memID,String clubMBContent, Timestamp clubMBDate,
			Integer clubMBStatus) {

		ClubMBVO clubMBVO = new ClubMBVO();


		clubMBVO.setClubID(clubID);
		clubMBVO.setMemID(memID);
		clubMBVO.setClubMBContent(clubMBContent);
		clubMBVO.setClubMBDate(clubMBDate);
		clubMBVO.setClubMBStatus(clubMBStatus);
		dao.insert(clubMBVO);

		return clubMBVO;
	}

	
	public ClubMBVO updateClubMB(Integer clubMBID,Integer clubID, Integer memID,String clubMBContent, Timestamp clubMBDate,
			Integer clubMBStatus) {

		ClubMBVO clubMBVO = new ClubMBVO();

		clubMBVO.setClubMBID(clubMBID);
		clubMBVO.setClubID(clubID);
		clubMBVO.setMemID(memID);
		clubMBVO.setClubMBContent(clubMBContent);
		clubMBVO.setClubMBDate(clubMBDate);
		clubMBVO.setClubMBStatus(clubMBStatus);
		dao.update(clubMBVO);

		return dao.findByPrimaryKey(clubMBID);
	}
	


	public void deleteClubMB(Integer clubMBID) {
		dao.delete(clubMBID);
	}

	public ClubMBVO getOneClubMB(Integer clubMBID) {
		return dao.findByPrimaryKey(clubMBID);
	}

	public List<ClubMBVO> getAll() {
		return dao.getAll();
	}
	

	//以下自己加
	//找尋某社團所有MB
	public List<ClubMBVO> findByClubID(Integer clubID){
		return dao.findByClubID(clubID);
	}
	
	

}
