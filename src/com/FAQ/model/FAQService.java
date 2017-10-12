package com.FAQ.model;

import java.util.List;



public class FAQService {

	
	private FAQDAO_interface dao;

	public FAQService() {
		dao = new FAQDAO();
	}

	public FAQVO addFAQ(String faqTitle, String faqContent,java.sql.Timestamp faqDate
			) {

		FAQVO faqVO = new FAQVO();

		faqVO.setFAQTitle(faqTitle);
		faqVO.setFAQContent(faqContent);
		faqVO.setFAQDate(faqDate);		
		dao.insert(faqVO);

		return faqVO;
	}

	//�w�d�� Struts 2 �Ϊ�
	public void addFAQ(FAQVO faqVO) {
		dao.insert(faqVO);
	}
	
	public FAQVO updateFAQ(Integer faqID,String faqTitle, String faqContent,java.sql.Timestamp faqDate) {

		FAQVO faqVO = new FAQVO();

		faqVO.setFAQID(faqID);
		faqVO.setFAQTitle(faqTitle);
		faqVO.setFAQContent(faqContent);
		faqVO.setFAQDate(faqDate);	
		dao.update(faqVO);

		return dao.findByPrimaryKey(faqID);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateFAQ(FAQVO faqVO) {
		dao.update(faqVO);
	}

	public void deleteFAQ(Integer faqID) {
		dao.delete(faqID);
	}

	public FAQVO getOneFAQ(Integer faqID) {
		return dao.findByPrimaryKey(faqID);
	}

	public List<FAQVO> getAll() {
		return dao.getAll();
	}
}


