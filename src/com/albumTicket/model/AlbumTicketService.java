package com.albumTicket.model;

import java.util.List;


public class AlbumTicketService {
	private AlbumTicketDAO_interface dao;
	
	public AlbumTicketService(){
		dao = new AlbumTicketDAO();
	}
	public AlbumTicketVO addmemTicket(Integer reporter,Integer clubAlbumID,String albTkMsg,Integer albTkStatID,java.sql.Timestamp albTkDate,Integer albTkCat) {

		AlbumTicketVO albumTicketVO = new AlbumTicketVO();

		albumTicketVO.setReporter(reporter);
		albumTicketVO.setClubAlbumID(clubAlbumID);
		albumTicketVO.setAlbTkMsg(albTkMsg);
		albumTicketVO.setAlbTkStatID(albTkStatID);
		albumTicketVO.setAlbTkDate(albTkDate);
		albumTicketVO.setAlbTkCat(albTkCat);
		dao.insert(albumTicketVO);

		return albumTicketVO;
	}

	// �w�d�� Struts 2 �Ϊ�
	public void addAlbumTicket(AlbumTicketVO albumTicketVO) {
		dao.insert(albumTicketVO);
	}

	public AlbumTicketVO updateMemTicket(Integer albumTkID,Integer reporter,Integer clubAlbumID,String albTkMsg,Integer albTkStatID,java.sql.Timestamp albTkDate,Integer albTkCat) {

		AlbumTicketVO albumTicketVO = new AlbumTicketVO();
	
		albumTicketVO.setAlbumTkID(albumTkID);
		albumTicketVO.setReporter(reporter);
		albumTicketVO.setClubAlbumID(clubAlbumID);
		albumTicketVO.setAlbTkMsg(albTkMsg);
		albumTicketVO.setAlbTkStatID(albTkStatID);
		albumTicketVO.setAlbTkDate(albTkDate);
		albumTicketVO.setAlbTkCat(albTkCat);

		dao.update(albumTicketVO);

		return dao.findByPrimaryKey(albumTkID);
	}

	// �w�d�� Struts 2 �Ϊ�
	public void updateAlbumTicket(AlbumTicketVO albumTicketVO) {
		dao.update(albumTicketVO);
	}

	public void deleteAlbumTicket(Integer albumTkID) {
		dao.delete(albumTkID);
	}

	public AlbumTicketVO getOneMemTicket(Integer albumTkID) {
		return dao.findByPrimaryKey(albumTkID);
	}

	public List<AlbumTicketVO> getAll() {
		return dao.getAll();
	}



}
