package com.admPril.model;

import java.sql.Timestamp;

public class AdmPrilVO implements java.io.Serializable  {
	//管理員ID
	private Integer admPrilID;
	//權限編號
	private Integer adminID;
	private Timestamp admPrildate;
	private Integer admPrilStatus;
	
	
	public Integer getAdmPrilID() {
		return admPrilID;
	}
	public void setAdmPrilID(Integer admPrilID) {
		this.admPrilID = admPrilID;
	}
	public Integer getAdminID() {
		return adminID;
	}
	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}
	public Timestamp getAdmPrildate() {
		return admPrildate;
	}
	public void setAdmPrildate(Timestamp admPrildate) {
		this.admPrildate = admPrildate;
	}
	public Integer getAdmPrilStatus() {
		return admPrilStatus;
	}
	public void setAdmPrilStatus(Integer admPrilStatus) {
		this.admPrilStatus = admPrilStatus;
	}
	
	
	
}