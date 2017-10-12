package com.Ann.model;

import java.util.List;



public class AnnService {

	private AnnDAO_interface dao;

	public AnnService() {
		dao = new AnnDAO();
	}

	public AnnVO addAnn(Integer adminID, String annName,
			 String annContent,java.sql.Timestamp annDate) {

		AnnVO annVO = new AnnVO();

		annVO.setAdminID(adminID);
		annVO.setAnnName(annName);
		annVO.setAnnContent(annContent);
		annVO.setAnnDate(annDate);
		dao.insert(annVO);

		return annVO;
	}

	//�w�d�� Struts 2 �Ϊ�
	public void addAnn(AnnVO annIDVO) {
		dao.insert(annIDVO);
	}
	
	public AnnVO updateEmp(Integer annID, Integer adminID, String annName,
			 String annContent,java.sql.Timestamp annDate) {

		AnnVO annVO = new AnnVO();

		annVO.setAnnID(annID);
		annVO.setAdminID(adminID);
		annVO.setAnnName(annName);
		annVO.setAnnContent(annContent);
		annVO.setAnnDate(annDate);
		
		dao.update(annVO);

		return dao.findByPrimaryKey(annID);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateAnn(AnnVO annVO) {
		dao.update(annVO);
	}

	public void deleteAnn(Integer annID) {
		dao.delete(annID);
	}

	public AnnVO getOneAnn(Integer annID) {
		return dao.findByPrimaryKey(annID);
	}

	public List<AnnVO> getAll() {
		return dao.getAll();
	}
}

