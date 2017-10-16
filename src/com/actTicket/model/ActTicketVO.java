package com.actTicket.model;

import java.sql.Timestamp;

import javax.mail.search.IntegerComparisonTerm;

public class ActTicketVO implements java.io.Serializable{
		
	private Integer actTkID;
	private Integer reporter;
	private Integer actID;
	private String actTkMsg;
	private Integer actTkStatID;
	private Timestamp actTkDate;
	private Integer actTkCat;
	public Integer getActTkID() {
		return actTkID;
	}
	public void setActTkID(Integer actTkID) {
		this.actTkID = actTkID;
	}
	public Integer getReporter() {
		return reporter;
	}
	public void setReporter(Integer reporter) {
		this.reporter = reporter;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public String getActTkMsg() {
		return actTkMsg;
	}
	public void setActTkMsg(String actTkMsg) {
		this.actTkMsg = actTkMsg;
	}
	public Integer getActTkStatID() {
		return actTkStatID;
	}
	public void setActTkStatID(Integer actTkStatID) {
		this.actTkStatID = actTkStatID;
	}
	public Timestamp getActTkDate() {
		return actTkDate;
	}
	public void setActTkDate(Timestamp actTkDate) {
		this.actTkDate = actTkDate;
	}
	public Integer getActTkCat() {
		return actTkCat;
	}
	public void setActTkCat(Integer actTkCat) {
		this.actTkCat = actTkCat;
	}
	
	
}
