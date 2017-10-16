package com.memTicket.model;

import java.sql.Timestamp;

public class MemTicketVO implements java.io.Serializable{
	private Integer memTkID;
	private Integer reporter;
	private Integer memID;
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	private String memTkMsg;
	private Integer memTkStatID;
	private Timestamp memTkDate;
	private Integer memTkCat;
	public Integer getMemTkID() {
		return memTkID;
	}
	public void setMemTkID(Integer memTkID) {
		this.memTkID = memTkID;
	}
	public Integer getReporter() {
		return reporter;
	}
	public void setReporter(Integer reporter) {
		this.reporter = reporter;
	}
	public String getMemTkMsg() {
		return memTkMsg;
	}
	public void setMemTkMsg(String memTkMsg) {
		this.memTkMsg = memTkMsg;
	}
	public Integer getMemTkStatID() {
		return memTkStatID;
	}
	public void setMemTkStatID(Integer memTkStatID) {
		this.memTkStatID = memTkStatID;
	}
	public Timestamp getMemTkDate() {
		return memTkDate;
	}
	public void setMemTkDate(Timestamp memTkDate) {
		this.memTkDate = memTkDate;
	}
	public Integer getMemTkCat() {
		return memTkCat;
	}
	public void setMemTkCat(Integer memTkCat) {
		this.memTkCat = memTkCat;
	}
	
	
	
	
}
