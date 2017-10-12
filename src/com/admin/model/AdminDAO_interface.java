package com.admin.model;

import java.util.List;

public interface AdminDAO_interface {
	public void insert(AdminVO adminVO);
	public void update(AdminVO adminVO);
	public void delete(Integer adminID);
	
	public void status1(Integer adminID);
	
	public AdminVO findByPrimaryKey(Integer adminID);
	
	public AdminVO findByAdminName(String adminName);
	
	public List<AdminVO> getAll();
	public List<AdminVO> statusadmin();
	AdminVO findByAdminMail(String adminMail);
}
