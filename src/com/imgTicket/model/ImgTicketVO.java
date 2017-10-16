package com.imgTicket.model;

import java.sql.Timestamp;

public class ImgTicketVO {

	private Integer	imgTkID ;
	private Integer	Reporter;
	private Integer	actImgID ;
	private Integer	clubImgID;
	private String	imgTkMsg ;
	private Integer	imgTkStatID ;
	private	Timestamp	imgTkDate;
	private Integer	imgTkCat ;
	public Integer getImgTkID() {
		return imgTkID;
	}
	public void setImgTkID(Integer imgTkID) {
		this.imgTkID = imgTkID;
	}
	public Integer getReporter() {
		return Reporter;
	}
	public void setReporter(Integer reporter) {
		Reporter = reporter;
	}
	public Integer getActImgID() {
		return actImgID;
	}
	public void setActImgID(Integer actImgID) {
		this.actImgID = actImgID;
	}
	public Integer getClubImgID() {
		return clubImgID;
	}
	public void setClubImgID(Integer clubImgID) {
		this.clubImgID = clubImgID;
	}
	public String getImgTkMsg() {
		return imgTkMsg;
	}
	public void setImgTkMsg(String imgTkMsg) {
		this.imgTkMsg = imgTkMsg;
	}
	public Integer getImgTkStatID() {
		return imgTkStatID;
	}
	public void setImgTkStatID(Integer imgTkStatID) {
		this.imgTkStatID = imgTkStatID;
	}
	public Timestamp getImgTkDate() {
		return imgTkDate;
	}
	public void setImgTkDate(Timestamp imgTkDate) {
		this.imgTkDate = imgTkDate;
	}
	public Integer getImgTkCat() {
		return imgTkCat;
	}
	public void setImgTkCat(Integer imgTkCat) {
		this.imgTkCat = imgTkCat;
	}
	
	
	
}
