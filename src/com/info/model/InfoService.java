package com.info.model;

import java.util.List;

public class InfoService {

	private InfoDAO_interface dao;

	public InfoService() {
		dao = new InfoDAO();
	}

	public InfoVO addInfo(Integer infoID, String infoName, String infoContent) {

		InfoVO infoVO = new InfoVO();

		infoVO.setInfoID(infoID);
		infoVO.setInfoName(infoName);
		infoVO.setInfoContent(infoContent);
		
		dao.insert(infoVO);

		return infoVO;
	}

	public InfoVO updateEmp(Integer infoID, String infoName, String infoContent
			) {

		InfoVO infoVO = new InfoVO();

		infoVO.setInfoID(infoID);
		infoVO.setInfoName(infoName);
		infoVO.setInfoContent(infoContent);
		
		dao.update(infoVO);

		return infoVO;
	}

	public void deleteEmp(Integer infoID) {
		dao.delete(infoID);
	}

	public InfoVO getOneInfo(Integer infoID) {
		return dao.findByPrimaryKey(infoID);
	}

	public List<InfoVO> getAll() {
		return dao.getAll();
	}
}
