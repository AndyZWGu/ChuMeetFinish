package com.member.model;


import java.sql.Timestamp;

public class MemMailVO implements java.io.Serializable {
	private Integer memMailID;
	private Integer receiver;
	private Integer author;
	private String mailTitle;
	private Timestamp mailDate;
	private String mailContent;
	
	public Integer getMemMailID() {
		return memMailID;
	}
	public void setMemMailID(Integer memMailID) {
		this.memMailID = memMailID;
	}
	public Integer getReceiver() {
		return receiver;
	}
	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}
	public Integer getAuthor() {
		return author;
	}
	public void setAuthor(Integer author) {
		this.author = author;
	}
	public String getMailTitle() {
		return mailTitle;
	}
	public void setMailTitle(String mailTitle) {
		this.mailTitle = mailTitle;
	}
	public Timestamp getMailDate() {
		return mailDate;
	}
	public void setMailDate(Timestamp mailDate) {
		this.mailDate = mailDate;
	}
	public String getMailContent() {
		return mailContent;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	
}
