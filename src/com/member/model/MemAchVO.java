package com.member.model;
import java.sql.Date;

public class MemAchVO implements java.io.Serializable {
	private Integer memID;
	private Integer achID;
	private Date acquireDate;
	
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getAchID() {
		return achID;
	}
	public void setAchID(Integer achID) {
		this.achID = achID;
	}
	public Date getAcquireDate() {
		return acquireDate;
	}
	public void setAcquireDate(Date acquireDate) {
		this.acquireDate = acquireDate;
	}
	
}
