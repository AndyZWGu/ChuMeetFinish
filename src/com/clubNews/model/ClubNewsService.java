package com.clubNews.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ClubNewsService {

	private ClubNewsDAO_interface dao;

	public ClubNewsService() {
		dao = new ClubNewsDAO();
	}

	public ClubNewsVO addClubNews(Integer clubID, Integer memID, Timestamp clubNewsDate, String clubNewsTitle, String clubNewsContent,
		Integer actID,Integer clubNewsStatus){
		ClubNewsVO clubNewsVO = new ClubNewsVO();

		clubNewsVO.setClubID(clubID);
		clubNewsVO.setMemID(memID);
		clubNewsVO.setClubNewsDate(clubNewsDate);
		clubNewsVO.setClubNewsTitle(clubNewsTitle);
		clubNewsVO.setClubNewsContent(clubNewsContent);
		clubNewsVO.setActID(actID);
		clubNewsVO.setClubNewsStatus(clubNewsStatus);
		dao.insert(clubNewsVO);

		return clubNewsVO;
	}


	public void addClubNews(ClubNewsVO clubNewsVO) {
		dao.insert(clubNewsVO);
	}
	
	public ClubNewsVO updateClubNews(Integer clubNewsID,Integer clubID, Integer memID, Timestamp clubNewsDate, String clubNewsTitle, String clubNewsContent,
			Integer actID,Integer clubNewsStatus) {

		ClubNewsVO clubNewsVO = new ClubNewsVO();
		clubNewsVO.setClubNewsID(clubNewsID);
		clubNewsVO.setClubID(clubID);
		clubNewsVO.setMemID(memID);
		clubNewsVO.setClubNewsDate(clubNewsDate);
		clubNewsVO.setClubNewsTitle(clubNewsTitle);
		clubNewsVO.setClubNewsContent(clubNewsContent);
		clubNewsVO.setActID(actID);
		clubNewsVO.setClubNewsStatus(clubNewsStatus);	
		dao.update(clubNewsVO);

		return clubNewsVO;
	}
	

//	public void updateClubNews(ClubNewsVO clubNewsVO) {
//		dao.update(clubNewsVO);
//	}

	public void deleteClubNews(Integer clubNewsID) {
		dao.delete(clubNewsID);
	}

	public ClubNewsVO getOneClubNews(Integer clubNewsID) {
		return dao.findByPrimaryKey(clubNewsID);
	}

	public List<ClubNewsVO> getAll() {
		return dao.getAll();
	}
	
	
	
	public ClubNewsVO deleteOneNews(Integer clubNewsID,Integer clubNewsStatus) {

		ClubNewsVO clubNewsVO = new ClubNewsVO();
		clubNewsVO.setClubNewsID(clubNewsID);
		clubNewsVO.setClubNewsStatus(clubNewsStatus);	
		dao.deleteOneNews(clubNewsVO);

		return clubNewsVO;
	}
	
	
	
	
	
	
}
