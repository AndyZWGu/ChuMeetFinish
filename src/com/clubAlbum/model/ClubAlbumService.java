package com.clubAlbum.model;

import java.sql.Timestamp;
import java.util.List;

import com.clubAlbum.model.ClubAlbumVO;
import com.clubImg.model.ClubImgVO;
import com.clubNews.model.ClubNewsVO;

public class ClubAlbumService {

	private ClubAlbumDAO_interface dao;

	public ClubAlbumService() {
		dao = new ClubAlbumDAO();
	}

	//Integer clubAlbumID,Integer clubID,Integer memID,java.sql.Date clubAlbumDate,String clubAlbumName,Integer clubAlbumStatus
	//
	
	public ClubAlbumVO addClubAlbum(Integer clubID,
			Integer memID,Timestamp clubAlbumDate,String clubAlbumName,Integer clubAlbumStatus) {

		ClubAlbumVO clubAlbumVO = new ClubAlbumVO();
		clubAlbumVO.setClubID(clubID);
		clubAlbumVO.setMemID(memID);
		clubAlbumVO.setClubAlbumDate(clubAlbumDate);
		clubAlbumVO.setClubAlbumName(clubAlbumName);
		clubAlbumVO.setClubAlbumStatus(clubAlbumStatus);

		dao.insert(clubAlbumVO);

		return clubAlbumVO;
	}


	
	//Integer clubAlbumID,Integer clubID,Integer memID,java.sql.Date clubAlbumDate,String clubAlbumName,Integer clubAlbumStatus
	//
	
	public ClubAlbumVO updateClubAlbumVO(Integer clubAlbumID,Integer clubID,Integer memID,Timestamp clubAlbumDate,String clubAlbumName,Integer clubAlbumStatus) {

		ClubAlbumVO clubAlbumVO = new ClubAlbumVO();
		clubAlbumVO.setClubAlbumID(clubAlbumID);
		clubAlbumVO.setClubID(clubID);
		clubAlbumVO.setMemID(memID);
		clubAlbumVO.setClubAlbumDate(clubAlbumDate);
		clubAlbumVO.setClubAlbumName(clubAlbumName);
		clubAlbumVO.setClubAlbumStatus(clubAlbumStatus);
		dao.update(clubAlbumVO);

		return clubAlbumVO;
	}

//	public void deleteClubAlbum(Integer clubAlbumVO) {
//		dao.delete(clubAlbumVO);
//	}

	public ClubAlbumVO getOneClubAlbum(Integer clubAlbumID) {
		return dao.findByPrimaryKey(clubAlbumID);
	}

	public List<ClubAlbumVO> getAll() {
		return dao.getAll();
	}
	
	//自己加的
	public List<ClubImgVO> getClubImgByClubAlbumID(Integer clubAlbumID) {
		return dao. getClubImgByClubAlbumID(clubAlbumID);
    }
}
