package com.member.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class MemNFService {

	private MemNFDAO_interface dao;

	public MemNFService() {
		dao = new MemNFDAO();
	}

	public MemNFVO addNF(
			Integer memID,
			String nfTitle,
			String nfContent,
			byte[] nfPic,
			Timestamp nfDate,
			Integer nfViews,
			Integer nfStatus) {

		MemNFVO memNFVO = new MemNFVO();

		memNFVO.setMemID(memID);
		memNFVO.setNfTitle(nfTitle);
		memNFVO.setNfContent(nfContent);
		memNFVO.setNfPic(nfPic);
		memNFVO.setNfDate(nfDate);
		memNFVO.setNfViews(nfViews);
		memNFVO.setNfStatus(nfStatus);
		dao.insert(memNFVO);

		return memNFVO;
	}

	public MemNFVO addNFtoHome(
			Integer memID,
			String nfTitle,
			String nfContent,
			byte[] nfPic,
			Timestamp nfDate,
			Integer nfViews) {

		MemNFVO memNFVO = new MemNFVO();

		memNFVO.setMemID(memID);
		memNFVO.setNfTitle(nfTitle);
		memNFVO.setNfContent(nfContent);
		memNFVO.setNfPic(nfPic);
		memNFVO.setNfDate(nfDate);
		memNFVO.setNfViews(nfViews);
		memNFVO.setNfStatus(2);
		dao.insert(memNFVO);

		return memNFVO;
	}
	
	public MemNFVO updateNF(
			Integer memNFID,
			String nfTitle,
			String nfContent,
			byte[] nfPic,
			Timestamp nfDate,
			Integer nfViews,
			Integer nfStatus) {

		MemNFVO memNFVO = new MemNFVO();

		memNFVO.setMemID(memNFID);
		memNFVO.setNfTitle(nfTitle);
		memNFVO.setNfContent(nfContent);
		memNFVO.setNfPic(nfPic);
		memNFVO.setNfDate(nfDate);
		memNFVO.setNfViews(nfViews);
		memNFVO.setNfStatus(nfStatus);
		
		dao.update(memNFVO);

		return memNFVO;
	}
	
	public void updateNFViews(Integer memNFID) {
		dao.updateViews(memNFID);
	}
	
	public MemNFVO updateNFtoHome(
			Integer memID,
			String nfTitle,
			String nfContent,
			byte[] nfPic,
			Timestamp nfDate,
			Integer nfViews,
			Integer nfStatus) {

		MemNFVO memNFVO = new MemNFVO();

		memNFVO.setMemID(memID);
		memNFVO.setNfTitle(nfTitle);
		memNFVO.setNfContent(nfContent);
		memNFVO.setNfPic(nfPic);
		memNFVO.setNfDate(nfDate);
		memNFVO.setNfViews(nfViews);
		memNFVO.setNfStatus(2);
		
		dao.update(memNFVO);

		return memNFVO;
	}

	public void deleteNF(Integer memNFID) {
		dao.delete(memNFID);
	}

	public MemNFVO getOneNF(Integer memNFID) {
		return dao.findByPrimaryKey(memNFID);
	}
	
	public List<MemNFVO> getOneNFByMemID(Integer memID) {
		return dao.findByMemID(memID);
	}
	
	
	public List<MemNFVO> getAll() {
		return dao.getAll();
	}
	
	public List<MemNFVO> getAllNF(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public String getCountByMemID(Integer memID) {
		return dao.findCountByMemID(memID);
	}

}
