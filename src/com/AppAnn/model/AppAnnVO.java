package com.AppAnn.model;

import java.sql.Timestamp;

public class AppAnnVO implements java.io.Serializable{
	private Integer appAnnID;
	private Integer adminID;
	private String appComtent;
	private Timestamp appAnnDate;
	private Timestamp appAnnTime;
	
	
	public Integer getAppAnnID() {
		return appAnnID;
	}
	public void setAppAnnID(Integer appAnnID) {
		this.appAnnID = appAnnID;
	}
	public Integer getAdminID() {
		return adminID;
	}
	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}
	public String getAppComtent() {
		return appComtent;
	}
	public void setAppComtent(String appComtent) {
		this.appComtent = appComtent;
	}
	public Timestamp getAppAnnDate() {
		return appAnnDate;
	}
	public void setAppAnnDate(Timestamp appAnnDate) {
		this.appAnnDate = appAnnDate;
	}
	public Timestamp getAppAnnTime() {
		return appAnnTime;
	}
	public void setAppAnnTime(Timestamp appAnnTime) {
		this.appAnnTime = appAnnTime;
	}
	
	
	
}
