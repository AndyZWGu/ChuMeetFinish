package com.member.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class MemMBService {

	private MemMBDAO_interface dao;

	public MemMBService() {
		dao = new MemMBDAO();
	}

	public MemMBVO addMB(
			Integer memNFID,
			Integer memID,
			String mbContent,
			Timestamp mbDate,
			Integer mbStatus) {

		MemMBVO memMBVO = new MemMBVO();

		memMBVO.setMemNFID(memNFID);
		memMBVO.setMemID(memID);
		memMBVO.setMbContent(mbContent);
		memMBVO.setMbDate(mbDate);
		memMBVO.setMbStatus(mbStatus);
		dao.insert(memMBVO);

		return memMBVO;
	}

	public MemMBVO addMBtoHome(
			Integer memNFID,
			Integer memID,
			String mbContent,
			Timestamp mbDate) {

		MemMBVO memMBVO = new MemMBVO();

		memMBVO.setMemNFID(memNFID);
		memMBVO.setMemID(memID);
		memMBVO.setMbContent(mbContent);
		memMBVO.setMbDate(mbDate);
		memMBVO.setMbStatus(2);
		dao.insert(memMBVO);

		return memMBVO;
	}
	
	public MemMBVO updateMB(
			Integer memNFID,
			Integer memID,
			String mbContent,
			Timestamp mbDate,
			Integer mbStatus) {

		MemMBVO memMBVO = new MemMBVO();

		memMBVO.setMemNFID(memNFID);
		memMBVO.setMemID(memID);
		memMBVO.setMbContent(mbContent);
		memMBVO.setMbDate(mbDate);
		memMBVO.setMbStatus(mbStatus);
		
		dao.update(memMBVO);

		return memMBVO;
	}
	
	public MemMBVO updateMBtoHome(
			Integer memNFID,
			Integer memID,
			String mbContent,
			Timestamp mbDate) {

		MemMBVO memMBVO = new MemMBVO();

		memMBVO.setMemNFID(memNFID);
		memMBVO.setMemID(memID);
		memMBVO.setMbContent(mbContent);
		memMBVO.setMbDate(mbDate);
		memMBVO.setMbStatus(2);
		
		dao.update(memMBVO);

		return memMBVO;
	}

	public void deleteMB(Integer memMBID) {
		dao.delete(memMBID);
	}

	public MemMBVO getOneMB(Integer memMBID) {
		return dao.findByPrimaryKey(memMBID);
	}
	
	
	public List<MemMBVO> getAll() {
		return dao.getAll();
	}
	
	public List<MemMBVO> getAllMB(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public String getCountByMemID(Integer memID) {
		return dao.findCountByPrimaryKey(memID);
	}

}
