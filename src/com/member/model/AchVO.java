package com.member.model;

public class AchVO implements java.io.Serializable{
	private Integer achID;
	private Integer rewID;
	private String achName;
	private String achDesc;
	private Integer achStatus;
	
	public Integer getAchID() {
		return achID;
	}
	public void setAchID(Integer achID) {
		this.achID = achID;
	}
	public Integer getRewID() {
		return rewID;
	}
	public void setRewID(Integer rewID) {
		this.rewID = rewID;
	}
	public String getAchName() {
		return achName;
	}
	public void setAchName(String achName) {
		this.achName = achName;
	}
	public String getAchDesc() {
		return achDesc;
	}
	public void setAchDesc(String achDesc) {
		this.achDesc = achDesc;
	}
	public Integer getAchStatus() {
		return achStatus;
	}
	public void setAchStatus(Integer achStatus) {
		this.achStatus = achStatus;
	}
	
}
