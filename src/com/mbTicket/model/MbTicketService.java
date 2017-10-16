package com.mbTicket.model;

import java.util.List;


public class MbTicketService {
	private MbTicketDAO_interface dao;
	
	public MbTicketService(){
		dao = new MbTicketDAO();
	}
	public MbTicketVO addmbTicket(Integer reporter,Integer actMBID,Integer clubMBID,Integer memNFID,String MBTkMsg,Integer memMBID,Integer MBTkStatID,java.sql.Timestamp MBTkDate,Integer MBTkCat) {

		MbTicketVO mbTicketVO = new MbTicketVO();

		mbTicketVO.setReporter(reporter);
		mbTicketVO.setActMBID(actMBID);
		mbTicketVO.setClubMBID(clubMBID);
		mbTicketVO.setMemNFID(memNFID);
		mbTicketVO.setMemMBID(memMBID);
		mbTicketVO.setMBTkMsg(MBTkMsg);
		mbTicketVO.setMBTkStatID(MBTkStatID);
		mbTicketVO.setMBTkDate(MBTkDate);
		mbTicketVO.setMBTkCat(MBTkCat);
		dao.insert(mbTicketVO);

		return mbTicketVO;
	}

	// �w�d�� Struts 2 �Ϊ�
	public void addMbTicket(MbTicketVO mbTicketVO) {
		dao.insert(mbTicketVO);
	}

	public MbTicketVO updateMemTicket(Integer mbTkID,Integer reporter,Integer actMBID,Integer clubMBID,Integer memNFID,String MBTkMsg,Integer memMBID,Integer MBTkStatID,java.sql.Timestamp MBTkDate,Integer MBTkCat) {
		//mbTkID,reporter,actMBID,clubMBID,memNFID,memMBID,MBTkMsg,MBTkStatID,MBTkDate,MBTkCat
		MbTicketVO mbTicketVO = new MbTicketVO();

		mbTicketVO.setMbTkID(mbTkID);
		mbTicketVO.setReporter(reporter);
		mbTicketVO.setActMBID(actMBID);
		mbTicketVO.setClubMBID(clubMBID);
		mbTicketVO.setMemNFID(memNFID);
		mbTicketVO.setMemMBID(memMBID);
		mbTicketVO.setMBTkMsg(MBTkMsg);
		mbTicketVO.setMBTkStatID(MBTkStatID);
		mbTicketVO.setMBTkDate(MBTkDate);
		mbTicketVO.setMBTkCat(MBTkCat);
		dao.update(mbTicketVO);

		return dao.findByPrimaryKey(mbTkID);
	}

	// �w�d�� Struts 2 �Ϊ�
	public void updateMemTicket(MbTicketVO memTicketVO) {
		dao.update(memTicketVO);
	}

	public void deleteMemTicket(Integer mbTkID) {
		dao.delete(mbTkID);
	}

	public MbTicketVO getOneMemTicket(Integer mbTkID) {
		return dao.findByPrimaryKey(mbTkID);
	}

	public List<MbTicketVO> getAll() {
		return dao.getAll();
	}


}

