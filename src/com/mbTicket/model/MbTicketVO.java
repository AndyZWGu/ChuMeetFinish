package com.mbTicket.model;

import java.sql.Timestamp;

public class MbTicketVO {
	private Integer	 mbTkID ;	
private Integer	 Reporter ;
private Integer	 actMBID ;
private Integer	 clubMBID ;
private Integer	 memNFID ;
private Integer	 memMBID ;
private String	MBTkMsg ;
private Integer	MBTkStatID ;
	private Timestamp	MBTkDate ;
	private Integer	MBTkCat;
	
	public Integer getMbTkID() {
		return mbTkID;
	}
	public void setMbTkID(Integer mbTkID) {
		this.mbTkID = mbTkID;
	}
	public Integer getReporter() {
		return Reporter;
	}
	public void setReporter(Integer reporter) {
		Reporter = reporter;
	}
	public Integer getActMBID() {
		return actMBID;
	}
	public void setActMBID(Integer actMBID) {
		this.actMBID = actMBID;
	}
	public Integer getClubMBID() {
		return clubMBID;
	}
	public void setClubMBID(Integer clubMBID) {
		this.clubMBID = clubMBID;
	}
	public Integer getMemNFID() {
		return memNFID;
	}
	public void setMemNFID(Integer memNFID) {
		this.memNFID = memNFID;
	}
	public Integer getMemMBID() {
		return memMBID;
	}
	public void setMemMBID(Integer memMBID) {
		this.memMBID = memMBID;
	}
	public String getMBTkMsg() {
		return MBTkMsg;
	}
	public void setMBTkMsg(String mBTkMsg) {
		MBTkMsg = mBTkMsg;
	}
	
	public Integer getMBTkStatID() {
		return MBTkStatID;
	}
	public void setMBTkStatID(Integer mBTkStatID) {
		MBTkStatID = mBTkStatID;
	}
	public Timestamp getMBTkDate() {
		return MBTkDate;
	}
	public void setMBTkDate(Timestamp mBTkDate) {
		MBTkDate = mBTkDate;
	}
	public Integer getMBTkCat() {
		return MBTkCat;
	}
	public void setMBTkCat(Integer mBTkCat) {
		MBTkCat = mBTkCat;
	}

	
	
	
}
