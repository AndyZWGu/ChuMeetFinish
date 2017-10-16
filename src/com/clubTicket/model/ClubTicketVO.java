package com.clubTicket.model;

import java.sql.Timestamp;

public class ClubTicketVO implements java.io.Serializable{
	private Integer clubTkID;
	private Integer Reporter;
	private Integer clubID ;
	private String clubTkMsg ;
	private Integer clubTkStatID ;
	private Timestamp clubTkDate ;
	private Integer clubTkCat ;
	public Integer getClubTkID() {
		return clubTkID;
	}
	public void setClubTkID(Integer clubTkID) {
		this.clubTkID = clubTkID;
	}
	public Integer getReporter() {
		return Reporter;
	}
	public void setReporter(Integer reporter) {
		Reporter = reporter;
	}
	public Integer getClubID() {
		return clubID;
	}
	public void setClubID(Integer clubID) {
		this.clubID = clubID;
	}
	public String getClubTkMsg() {
		return clubTkMsg;
	}
	public void setClubTkMsg(String clubTkMsg) {
		this.clubTkMsg = clubTkMsg;
	}
	public Integer getClubTkStatID() {
		return clubTkStatID;
	}
	public void setClubTkStatID(Integer clubTkStatID) {
		this.clubTkStatID = clubTkStatID;
	}
	public Timestamp getClubTkDate() {
		return clubTkDate;
	}
	public void setClubTkDate(Timestamp clubTkDate) {
		this.clubTkDate = clubTkDate;
	}
	public Integer getClubTkCat() {
		return clubTkCat;
	}
	public void setClubTkCat(Integer clubTkCat) {
		this.clubTkCat = clubTkCat;
	}
	
	
	
}
