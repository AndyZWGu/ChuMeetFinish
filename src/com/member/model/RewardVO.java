package com.member.model;

public class RewardVO implements java.io.Serializable{
	private Integer rewID;
	private String rewName;
	private byte[] rewContent;
	private Integer rewExp;
	private Integer rewPt;
	private String rewDesc;
	private String rewType;
	private Integer rewStatus;
	
	public Integer getRewID() {
		return rewID;
	}
	public void setRewID(Integer rewID) {
		this.rewID = rewID;
	}
	public String getRewName() {
		return rewName;
	}
	public void setRewName(String rewName) {
		this.rewName = rewName;
	}
	public byte[] getRewContent() {
		return rewContent;
	}
	public void setRewContent(byte[] rewContent) {
		this.rewContent = rewContent;
	}
	public Integer getRewExp() {
		return rewExp;
	}
	public void setRewExp(Integer rewExp) {
		this.rewExp = rewExp;
	}
	public Integer getRewPt() {
		return rewPt;
	}
	public void setRewPt(Integer rewPt) {
		this.rewPt = rewPt;
	}
	public String getRewDesc() {
		return rewDesc;
	}
	public void setRewDesc(String rewDesc) {
		this.rewDesc = rewDesc;
	}
	public String getRewType() {
		return rewType;
	}
	public void setRewType(String rewType) {
		this.rewType = rewType;
	}
	public Integer getRewStatus() {
		return rewStatus;
	}
	public void setRewStatus(Integer rewStatus) {
		this.rewStatus = rewStatus;
	}
}
