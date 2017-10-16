package com.act.actMem.model;

import java.sql.Timestamp;

public class ActMemVO implements java.io.Serializable{
	
	private Integer memID;
	private Integer actID;
	private Integer actMemStatus;
	private Timestamp actJoinDate;
	private Integer actStar;
	private Timestamp actStarDate;
	private Integer QRStatus;

	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public Integer getActMemStatus() {
		return actMemStatus;
	}
	public void setActMemStatus(Integer actMemStatus) {
		this.actMemStatus = actMemStatus;
	}
	public Timestamp getActJoinDate() {
		return actJoinDate;
	}
	public void setActJoinDate(Timestamp actJoinDate) {
		this.actJoinDate = actJoinDate;
	}
	public Integer getActStar() {
		return actStar;
	}
	public void setActStar(Integer actStar) {
		this.actStar = actStar;
	}
	public Timestamp getActStarDate() {
		return actStarDate;
	}
	public void setActStarDate(Timestamp actStarDate) {
		this.actStarDate = actStarDate;
	}

	public Integer getQRStatus() {
		return QRStatus;
	}

	public void setQRStatus(Integer qRStatus) {
		QRStatus = qRStatus;
	}

}
