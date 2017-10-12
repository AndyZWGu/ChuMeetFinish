package com.member.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class MemMailService {

	private MemMailDAO_interface dao;

	public MemMailService() {
		dao = new MemMailDAO();
	}

	public MemMailVO addMail(
			Integer receiver,
			Integer author,
			String mailTitle,
			Timestamp mailDate,
			String mailContent) {

		MemMailVO memMailVO = new MemMailVO();

		memMailVO.setReceiver(receiver);
		memMailVO.setAuthor(author);
		memMailVO.setMailTitle(mailTitle);
		memMailVO.setMailDate(mailDate);
		memMailVO.setMailContent(mailContent);
		dao.insert(memMailVO);

		return memMailVO;
	}
	
	public MemMailVO updateMail(
			Integer memMailID,
			Integer receiver,
			Integer author,
			String mailTitle,
			Timestamp mailDate,
			String mailContent) {

		MemMailVO memMailVO = new MemMailVO();

		memMailVO.setMemMailID(memMailID);
		memMailVO.setReceiver(receiver);
		memMailVO.setAuthor(author);
		memMailVO.setMailTitle(mailTitle);
		memMailVO.setMailDate(mailDate);
		memMailVO.setMailContent(mailContent);
		
		dao.update(memMailVO);

		return memMailVO;
	}

	public void deleteMail(Integer memMailID) {
		dao.delete(memMailID);
	}
	
	public List<MemMailVO> getAll(Map<String, String[]> mailMap) {
		return dao.getAll(mailMap);
	}

	public MemMailVO getOneMail(Integer memMailID){
		return dao.findByPrimaryKey(memMailID);
	}


}
