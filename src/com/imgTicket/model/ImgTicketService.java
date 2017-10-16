package com.imgTicket.model;

import java.util.List;


public class ImgTicketService {
	private ImgTicketDAO_interface dao;
	
	public ImgTicketService(){
		dao = new ImgTicketDAO();
	}
	public ImgTicketVO addimgTicket(Integer reporter,Integer actImgID,String imgTkMsg,Integer imgTkStatID,java.sql.Timestamp imgTkDate,Integer imgTkCat) {

		ImgTicketVO imgTicketVO = new ImgTicketVO();

		imgTicketVO.setReporter(reporter);
		imgTicketVO.setActImgID(actImgID);
		imgTicketVO.setImgTkMsg(imgTkMsg);
		imgTicketVO.setImgTkStatID(imgTkStatID);
		imgTicketVO.setImgTkDate(imgTkDate);
		imgTicketVO.setImgTkCat(imgTkCat);
		dao.insert(imgTicketVO);
				
		return imgTicketVO;
	}

	// �w�d�� Struts 2 �Ϊ�
	public void addImgTicket(ImgTicketVO imgTicketVO) {
		dao.insert(imgTicketVO);
	}

	public ImgTicketVO updateImgTicket(Integer imgTkID,Integer reporter,Integer actImgID,String imgTkMsg,Integer imgTkStatID,java.sql.Timestamp imgTkDate,Integer imgTkCat) {
		//imgTkID,Reporter,ActImgID,ImgTkMsg,ImgTkStatID,ImgTkDate,ImgTkCat
		ImgTicketVO imgTicketVO = new ImgTicketVO();

		imgTicketVO.setImgTkID(imgTkID);
		imgTicketVO.setReporter(reporter);
		imgTicketVO.setActImgID(actImgID);
		imgTicketVO.setImgTkMsg(imgTkMsg);
		imgTicketVO.setImgTkDate(imgTkDate);
		imgTicketVO.setImgTkDate(imgTkDate);
		imgTicketVO.setImgTkCat(imgTkCat);

		dao.update(imgTicketVO);

		return dao.findByPrimaryKey(imgTkID);
	}

	// �w�d�� Struts 2 �Ϊ�
	public void updateImgTicket(ImgTicketVO imgTicketVO) {
		dao.update(imgTicketVO);
	}

	public void deleteImgTicket(Integer imgTkID) {
		dao.delete(imgTkID);
	}

	public ImgTicketVO getOneImgTicket(Integer imgTkID) {
		return dao.findByPrimaryKey(imgTkID);
	}

	public List<ImgTicketVO> getAll() {
		return dao.getAll();
	}


}
