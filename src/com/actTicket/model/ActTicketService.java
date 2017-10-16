package com.actTicket.model;

import java.util.List;


public class ActTicketService {
	private ActTicketDAO_interface dao;
	
	public ActTicketService(){
		dao = new ActTicketDAO();
	}
	public ActTicketVO addactTicket(Integer reporter,Integer actID,String actTkMsg,Integer actTkStatID,java.sql.Timestamp actTkDate,Integer actTkCat) {

		ActTicketVO actTicketVO = new ActTicketVO();

		actTicketVO.setReporter(reporter);
		actTicketVO.setActID(actID);
		actTicketVO.setActTkMsg(actTkMsg);
		actTicketVO.setActTkStatID(actTkStatID);
		actTicketVO.setActTkDate(actTkDate);
		actTicketVO.setActTkCat(actTkCat);
		dao.insert(actTicketVO);
//actTkID,reporter,actID,actTkMsg,actTkStatID,actTkDate,actTkCat From actTicket
		return actTicketVO;
	}

	// �w�d�� Struts 2 �Ϊ�
	public void addActTicket(ActTicketVO actTicketVO) {
		dao.insert(actTicketVO);
	}

	public ActTicketVO updateMemTicket(Integer actTkID,Integer reporter,Integer actID,String actTkMsg,Integer actTkStatID,java.sql.Timestamp actTkDate,Integer actTkCat) {

		ActTicketVO actTicketVO = new ActTicketVO();

		actTicketVO.setActTkID(actTkID);
		actTicketVO.setReporter(reporter);
		actTicketVO.setActID(actID);
		actTicketVO.setActTkMsg(actTkMsg);
		actTicketVO.setActTkStatID(actTkStatID);
		actTicketVO.setActTkDate(actTkDate);
		actTicketVO.setActTkCat(actTkCat);
		dao.update(actTicketVO);

		return dao.findByPrimaryKey(actTkID);
	}

	// �w�d�� Struts 2 �Ϊ�
	
	public void deleteActTicket(Integer actTkID) {
		dao.delete(actTkID);
	}

	public ActTicketVO getOneActTicket(Integer actTkID) {
		return dao.findByPrimaryKey(actTkID);
	}

	public List<ActTicketVO> getAll() {
		return dao.getAll();
	}


}