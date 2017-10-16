package com.memTicket.model;

import java.util.List;



public class MemTicketService {
		private MemTicketDAO_interface dao;
		
		public MemTicketService(){
			dao = new MemTicketDAO();
		}
		public MemTicketVO addmemTicket(Integer reporter,Integer memID,String memTkMsg,Integer memTkStatID,java.sql.Timestamp memTkDate,Integer memTkCat) {

			MemTicketVO memTicketVO = new MemTicketVO();

			memTicketVO.setReporter(reporter);
			memTicketVO.setMemID(memID);
			memTicketVO.setMemTkMsg(memTkMsg);
			memTicketVO.setMemTkStatID(memTkStatID);
			memTicketVO.setMemTkDate(memTkDate);
			memTicketVO.setMemTkCat(memTkCat);
			dao.insert(memTicketVO);

			return memTicketVO;
		}

		// �w�d�� Struts 2 �Ϊ�
		public void addMemTicket(MemTicketVO memTicketVO) {
			dao.insert(memTicketVO);
		}

		public MemTicketVO updateMemTicket(Integer memTkID,Integer reporter,Integer memID,String memTkMsg,Integer memTkStatID,java.sql.Timestamp memTkDate,Integer memTkCat) {

			MemTicketVO memTicketVO = new MemTicketVO();

			memTicketVO.setMemTkID(memTkID);
			memTicketVO.setReporter(reporter);
			memTicketVO.setMemID(memID);
			memTicketVO.setMemTkMsg(memTkMsg);
			memTicketVO.setMemTkStatID(memTkStatID);
			memTicketVO.setMemTkDate(memTkDate);
			memTicketVO.setMemTkCat(memTkCat);

			dao.update(memTicketVO);

			return dao.findByPrimaryKey(memTkID);
		}

		// �w�d�� Struts 2 �Ϊ�
		public void updateMemTicket(MemTicketVO memTicketVO) {
			dao.update(memTicketVO);
		}

		public void deleteMemTicket(Integer memTkID) {
			dao.delete(memTkID);
		}

		public MemTicketVO getOneMemTicket(Integer memTkID) {
			return dao.findByPrimaryKey(memTkID);
		}

		public List<MemTicketVO> getAll() {
			return dao.getAll();
		}

	
}