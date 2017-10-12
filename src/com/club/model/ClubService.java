package com.club.model;

import com.clubAlbum.model.ClubAlbumVO;
import com.clubMB.model.ClubMBVO;
import com.clubMem.model.ClubMemVO;
import com.clubNews.model.ClubNewsVO;

import java.util.List;

public class ClubService {

	private ClubDAO_interface dao;

	public ClubService() {
		dao = new ClubDAO();
	}
	

	
	
//自增主鍵
	public ClubVO addClub(Integer clubmemID, String clubName,Integer clubTypeID,String clubContent, byte[]clubPhoto,
			java.sql.Timestamp clubStartDate,Integer clubStatus,Double clubLong, Double clubLat) {

		ClubVO clubVO = new ClubVO();

		clubVO.setClubmemID(clubmemID);
		clubVO.setClubName(clubName);
		clubVO.setClubTypeID(clubTypeID);
		clubVO.setClubContent(clubContent);
		clubVO.setClubPhoto(clubPhoto);
		clubVO.setClubStartDate(clubStartDate);
		clubVO.setClubStatus(clubStatus);
		clubVO.setClubLong(clubLong);
		clubVO.setClubLat(clubLat);		
		clubVO.setClubID(dao.insert(clubVO));//dao.insert(clubVO)會回傳一個整數clubID

		return clubVO;
	}
	
	
	
	
	
	
	
//未自增主鍵
//	public ClubVO addClub(Integer clubmemID, String clubName,Integer clubTypeID,String clubContent, byte[]clubPhoto,
//			java.sql.Timestamp clubStartDate,Integer clubStatus,Double clubLong, Double clubLat) {
//
//		ClubVO clubVO = new ClubVO();
//
//		clubVO.setClubmemID(clubmemID);
//		clubVO.setClubName(clubName);
//		clubVO.setClubTypeID(clubTypeID);
//		clubVO.setClubContent(clubContent);
//		clubVO.setClubPhoto(clubPhoto);
//		clubVO.setClubStartDate(clubStartDate);
//		clubVO.setClubStatus(clubStatus);
//		clubVO.setClubLong(clubLong);
//		clubVO.setClubLat(clubLat);
//		dao.insert(clubVO);
//		
//		return clubVO;
//	}


	public ClubVO updateClub(Integer clubID,Integer clubmemID, String clubName,Integer clubTypeID,String clubContent, byte[]clubPhoto,
	java.sql.Timestamp clubStartDate,Integer clubStatus,Double clubLong, Double clubLat) {

		ClubVO clubVO = new ClubVO();
		clubVO.setClubID(clubID);
		clubVO.setClubmemID(clubmemID);
		clubVO.setClubName(clubName);
		clubVO.setClubTypeID(clubTypeID);
		clubVO.setClubContent(clubContent);
		clubVO.setClubPhoto(clubPhoto);
		clubVO.setClubStartDate(clubStartDate);
		clubVO.setClubStatus(clubStatus);
		clubVO.setClubLong(clubLong);
		clubVO.setClubLat(clubLat);
		dao.update(clubVO);

		return clubVO;
	}
	
	
	
//修改社團	
	public ClubVO ClubChange(Integer clubID,String clubName,Integer clubTypeID,String clubContent, byte[]clubPhoto) {

		ClubVO clubVO = new ClubVO();
		clubVO.setClubID(clubID);
		clubVO.setClubName(clubName);
		clubVO.setClubTypeID(clubTypeID);
		clubVO.setClubContent(clubContent);
		clubVO.setClubPhoto(clubPhoto);
		dao.clubChange(clubVO);
		return clubVO;
	}
	//修改社團(沒上傳新圖片的話,用資料庫裡舊的)	
		public ClubVO ClubChangeIfNotPhoto(Integer clubID,String clubName,Integer clubTypeID,String clubContent) {

			ClubVO clubVO = new ClubVO();
			clubVO.setClubID(clubID);
			clubVO.setClubName(clubName);
			clubVO.setClubTypeID(clubTypeID);
			clubVO.setClubContent(clubContent);
			dao.clubChangeIfNotPhoto(clubVO);
			return clubVO;
		}
	
	

	public void deleteEmp(Integer clubID) {
		dao.delete(clubID);
	}

	public ClubVO findByPrimaryKey(Integer clubID) {
		
		return dao.findByPrimaryKey(clubID);
	}

	public List<ClubVO> getAll() {
		return dao.getAll();
	}
	
	//自己加的
	public List<ClubAlbumVO> getClubAlbumsByClubID(Integer clubID) {
		return dao. getClubAlbumsByClubID(clubID);
	}
	
	//自己加的
	public List<ClubNewsVO> getClubNewsByClubID(Integer clubID) {
		return dao. getClubNewsByClubID(clubID);
	}
	public List<ClubMBVO> getClubMBByClubID(Integer clubID) {
		return dao. getClubMBByClubID(clubID);
	}
	public List<ClubMemVO> getClubMemByClubID(Integer clubID) {
		return dao.getClubMemByClubID(clubID);
	}
	
	

	
	
}
