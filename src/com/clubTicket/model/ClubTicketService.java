package com.clubTicket.model;

import java.util.List;


public class ClubTicketService {
	private ClubTicketDAO_interface dao;
	
	public ClubTicketService(){
		dao = new ClubTicketDAO();
	}
	public ClubTicketVO addclubTicket(Integer reporter,Integer clubID,String clubTkMsg,Integer clubTkStatID,java.sql.Timestamp clubTkDate,Integer clubTkCat) {

		ClubTicketVO clubTicketVO = new ClubTicketVO();
		
		clubTicketVO.setReporter(reporter);
		clubTicketVO.setClubID(clubID);
		clubTicketVO.setClubTkMsg(clubTkMsg);
		clubTicketVO.setClubTkStatID(clubTkStatID);
		clubTicketVO.setClubTkDate(clubTkDate);
		clubTicketVO.setClubTkCat(clubTkCat);
		dao.insert(clubTicketVO);

		return clubTicketVO;
	}

	// �w�d�� Struts 2 �Ϊ�
	public void addClubTicket(ClubTicketVO clubTicketVO) {
		dao.insert(clubTicketVO);
	}

	public ClubTicketVO updateMemTicket(Integer clubTkID,Integer reporter,Integer clubID,String clubTkMsg,Integer clubTkStatID,java.sql.Timestamp clubTkDate,Integer clubTkCat) {

		ClubTicketVO clubTicketVO = new ClubTicketVO();
//clubTkID,reporter,clubID,clubTkMsg,clubTkStatID,clubTkDate,clubTkCat
		clubTicketVO.setClubTkID(clubTkID);
		clubTicketVO.setReporter(reporter);
		clubTicketVO.setClubID(clubID);
		clubTicketVO.setClubTkMsg(clubTkMsg);
		clubTicketVO.setClubTkStatID(clubTkStatID);
		clubTicketVO.setClubTkDate(clubTkDate);
		clubTicketVO.setClubTkCat(clubTkCat);

		dao.update(clubTicketVO);

		return dao.findByPrimaryKey(clubTkID);
	}

	// �w�d�� Struts 2 �Ϊ�
	public void updateAdmin(ClubTicketVO clubTicketVO) {
		dao.update(clubTicketVO);
	}

	public void deleteMemTicket(Integer clubTkID) {
		dao.delete(clubTkID);
	}

	public ClubTicketVO getOneAdmin(Integer clubTkID) {
		return dao.findByPrimaryKey(clubTkID);
	}

	public List<ClubTicketVO> getAll() {
		return dao.getAll();
	}


}
	
	
	

