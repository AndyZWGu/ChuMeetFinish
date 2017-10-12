package com.member.model;

import java.sql.Timestamp;

public class MemRewardVO implements java.io.Serializable{
	private Integer memRewID;
	private Integer memID;
	private Integer rewID;
	private Timestamp rewDate;
	private String acquireType;
	
	public Integer getMemRewID() {
		return memRewID;
	}
	public void setMemRewID(Integer memRewID) {
		this.memRewID = memRewID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getRewID() {
		return rewID;
	}
	public void setRewID(Integer rewID) {
		this.rewID = rewID;
	}
	public Timestamp getRewDate() {
		return rewDate;
	}
	public void setRewDate(Timestamp rewDate) {
		this.rewDate = rewDate;
	}
	public String getAcquireType() {
		return acquireType;
	}
	public void setAcquireType(String acquireType) {
		this.acquireType = acquireType;
	}
	
}
