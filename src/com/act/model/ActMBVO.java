package com.act.model;

import java.sql.Timestamp;

public class ActMBVO {
	private Integer actMBID;
	private Integer actID;
	private Integer memID;
	private String actMBContent;
	private Timestamp actMBDate;
	
	public Integer getActMBID() {
		return actMBID;
	}
	public void setActMBID(Integer actMBID) {
		this.actMBID = actMBID;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public String getActMBContent() {
		return actMBContent;
	}
	public void setActMBContent(String actMBContent) {
		this.actMBContent = actMBContent;
	}
	public Timestamp getActMBDate() {
		return actMBDate;
	}
	public void setActMBDate(Timestamp actMBDate) {
		this.actMBDate = actMBDate;
	}

}
