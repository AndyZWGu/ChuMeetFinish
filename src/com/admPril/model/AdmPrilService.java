package com.admPril.model;

import java.util.List;

public class AdmPrilService {
	private AdmPrilDAO_interface dao;

	public AdmPrilService() {
		dao = new AdmPrilDAO();
	}

	public AdmPrilVO addAdmPril(Integer adminPrilID,Integer adminID, java.sql.Timestamp admPrildate, 
			Integer admPrilStatus) {

		AdmPrilVO annVO = new AdmPrilVO();
		annVO.setAdmPrilID(adminPrilID);
		annVO.setAdminID(adminID);
		annVO.setAdmPrildate(admPrildate);
		annVO.setAdmPrilStatus(admPrilStatus);
	
		dao.insert(annVO);

		return annVO;
	}

	//�w�d�� Struts 2 �Ϊ�
	public void addAdmPril(AdmPrilVO annVO) {
		dao.insert(annVO);
	}
	
	public AdmPrilVO updateAdmPril(Integer admPrilID,Integer adminID, java.sql.Timestamp admPrildate, 
			Integer admPrilStatus) {

		AdmPrilVO annVO = new AdmPrilVO();

		annVO.setAdmPrilID(admPrilID);
		annVO.setAdminID(adminID);
		annVO.setAdmPrildate(admPrildate);
		annVO.setAdmPrilStatus(admPrilStatus);
		
		dao.update(annVO);

		return dao.findByPrimaryKey(admPrilID);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateAdmPril(AdmPrilVO annVO) {
		dao.update(annVO);
	}

	public void deleteAdmPril(Integer admPrilID) {
		dao.delete(admPrilID);
	}

	public AdmPrilVO getOneAdmPril(Integer admPrilID) {
		return dao.findByPrimaryKey(admPrilID);
	}

	public List<AdmPrilVO> getAll() {
		return dao.getAll();
}
	public List<AdmPrilVO> status2(){
		return dao.status2();
	}
	public List<AdmPrilVO> getAdmPrilList(Integer adminID){
		return dao.findByAdminID(adminID);
	}
}