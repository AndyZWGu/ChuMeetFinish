package com.clubMem.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ClubMemVO  implements java.io.Serializable{
	private Integer memID;
	private Integer clubID;
	private Integer clubMemType;
	private Timestamp clubMemJoinDate;
	private Integer	clubMemStatus;
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Integer getClubID() {
		return clubID;
	}
	public void setClubID(Integer clubID) {
		this.clubID = clubID;
	}
	public Integer getClubMemType() {
		return clubMemType;
	}
	public void setClubMemType(Integer clubMemType) {
		this.clubMemType = clubMemType;
	}
	public Timestamp getClubMemJoinDate() {
		return clubMemJoinDate;
	}
	public void setClubMemJoinDate(Timestamp clubMemJoinDate) {
		this.clubMemJoinDate = clubMemJoinDate;
	}
	public Integer getClubMemStatus() {
		return clubMemStatus;
	}
	public void setClubMemStatus(Integer clubMemStatus) {
		this.clubMemStatus = clubMemStatus;
	}

	
	
	
	
}
