package com.admPrilType.model;

import java.util.List;


public class AdmPrilTypeService {
	private AdmPrilTypeDAO_interface dao;

	public AdmPrilTypeService() {
		dao = new AdmPrilTypeDAO();
	}

	public AdmPrilTypeVO addAdmPrilType(String admPrilTypeName, Integer admPrilTypeStatus) {

		AdmPrilTypeVO admPrilTypeVO = new AdmPrilTypeVO();

		admPrilTypeVO.setAdmPrilTypeName(admPrilTypeName);
		admPrilTypeVO.setAdmPrilTypeStatus(admPrilTypeStatus);
		
		dao.insert(admPrilTypeVO);

		return admPrilTypeVO;
	}

	//�w�d�� Struts 2 �Ϊ�
	public void addAdmPrilType(AdmPrilTypeVO admPrilTypeVO) {
		dao.insert(admPrilTypeVO);
	}
	
	public AdmPrilTypeVO updateAdmPrilType(Integer admPrilID,String admPrilTypeName, Integer admPrilTypeStatus) {

		AdmPrilTypeVO admPrilTypeVO = new AdmPrilTypeVO();

		admPrilTypeVO.setAdmPrilID(admPrilID);
		admPrilTypeVO.setAdmPrilTypeName(admPrilTypeName);
		admPrilTypeVO.setAdmPrilTypeStatus(admPrilTypeStatus);
		
		dao.update(admPrilTypeVO);

		return dao.findByPrimaryKey(admPrilID);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateAdmPrilType(AdmPrilTypeVO admPrilTypeVO) {
		dao.update(admPrilTypeVO);
	}

	public void deleteAdmPrilType(Integer admPrilID) {
		dao.delete(admPrilID);
	}

	public AdmPrilTypeVO getOneAdmPrilType(Integer admPrilID) {
		return dao.findByPrimaryKey(admPrilID);
	}

	public List<AdmPrilTypeVO> getAll() {
		return dao.getAll();
	}
	public List<AdmPrilTypeVO> statusname() {
		return dao.statusname();
	}
}


