package com.admin.model;

import java.util.List;

public class AdminService {

	private AdminDAO_interface dao;

	public AdminService() {
		dao = new AdminDAO();
	}

	public AdminVO addAdmin(String adminName, String adminMail, String adminPW, String adminEmail,
			java.sql.Timestamp adminDate, Integer adminStatus) {

		AdminVO adminVO = new AdminVO();

		adminVO.setAdminName(adminName);
		adminVO.setAdminMail(adminMail);
		adminVO.setAdminPW(adminPW);
		adminVO.setAdminEmail(adminEmail);
		adminVO.setAdminDate(adminDate);
		adminVO.setAdminStatus(adminStatus);
		dao.insert(adminVO);

		return adminVO;
	}

	// �w�d�� Struts 2 �Ϊ�
	public void addAdmin(AdminVO adminVO) {
		dao.insert(adminVO);
	}

	public AdminVO updateAdmin(Integer adminID, String adminName, String adminMail, String adminPW, String adminEmail,
			java.sql.Timestamp adminDate, Integer adminStatus) {

		AdminVO adminVO = new AdminVO();

		adminVO.setAdminID(adminID);
		adminVO.setAdminName(adminName);
		adminVO.setAdminMail(adminMail);
		adminVO.setAdminPW(adminPW);
		adminVO.setAdminEmail(adminEmail);
		adminVO.setAdminDate(adminDate);
		adminVO.setAdminStatus(adminStatus);

		dao.update(adminVO);

		return dao.findByPrimaryKey(adminID);
	}

	// �w�d�� Struts 2 �Ϊ�
	public void updateAdmin(AdminVO adminVO) {
		dao.update(adminVO);
	}

	public void deleteAdmin(Integer adminID) {
		dao.delete(adminID);
	}

	public AdminVO getOneAdmin(Integer adminID) {
		return dao.findByPrimaryKey(adminID);
	}

	public List<AdminVO> getAll() {
		return dao.getAll();
	}

	public AdminVO getAdminByAdminName(String adminName) {
		return dao.findByAdminName(adminName);
	}
	public AdminVO getAdminByAdminMail(String adminMail) {
		return dao.findByAdminMail(adminMail);
	}

	public List<AdminVO> statusadmin() {
		return dao.statusadmin();
	}
	public void status1(Integer adminID) {
		dao.status1(adminID);
	}
}