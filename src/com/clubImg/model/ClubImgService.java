package com.clubImg.model;

import java.util.List;
import java.util.Set;

import com.clubImg.model.ClubImgVO;

public class ClubImgService {

	private ClubImgDAO_interface dao;

	public ClubImgService() {
		dao = new ClubImgDAO();
	}

	public ClubImgVO addClubImg(Integer clubAlbumID,Integer memID,
			java.sql.Timestamp clubImgDate,byte[] clubImg,String clubImgContent,Integer clubImgStatus) {

		ClubImgVO clubImgVO = new ClubImgVO();

		clubImgVO.setClubAlbumID(clubAlbumID);
		clubImgVO.setMemID(memID);
		clubImgVO.setClubImgDate(clubImgDate);
		clubImgVO.setClubImg(clubImg);
		clubImgVO.setClubImgContent(clubImgContent);
		clubImgVO.setClubImgStatus(clubImgStatus);
		dao.insert(clubImgVO);
		return clubImgVO;
	}

	//可刪除Integer clubID,Integer clubmemID, String clubName,Integer clubTypeID,String clubContent, byte[]clubPhoto,java.sql.Date clubStartDate,Integer clubStatus,Double clubLong, Double clubLat
	//
	public ClubImgVO updateClubImg(Integer clubImgID,Integer clubAlbumID,Integer memID,
			java.sql.Timestamp clubImgDate,byte[] clubImg,String clubImgContent,Integer clubImgStatus) {

		ClubImgVO clubImgVO = new ClubImgVO();
		clubImgVO.setClubImgID(clubImgID);
		clubImgVO.setClubAlbumID(clubAlbumID);
		clubImgVO.setMemID(memID);
		clubImgVO.setClubImgDate(clubImgDate);
		clubImgVO.setClubImg(clubImg);
		clubImgVO.setClubImgContent(clubImgContent);
		clubImgVO.setClubImgStatus(clubImgStatus);
		dao.update(clubImgVO);

		return clubImgVO;
	}



	public ClubImgVO getOneClubImg(Integer clubImgID) {
		
		return dao.findByPrimaryKey(clubImgID);
	}

	public List<ClubImgVO> getAll() {
		return dao.getAll();
	}
	
	
	
	//以下自己加
	public ClubImgVO deleteOneImg(Integer clubImgID,Integer clubImgStatus) {

		ClubImgVO clubImgVO = new ClubImgVO();
		clubImgVO.setClubImgID(clubImgID);		
		clubImgVO.setClubImgStatus(clubImgStatus);
		dao.deleteOneImg(clubImgVO);

		return clubImgVO;
	}
	
	
	
	
	
	
	
	
	
	
}
