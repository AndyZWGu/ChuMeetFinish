package com.albumTicket.model;

import java.sql.Timestamp;

public class AlbumTicketVO implements java.io.Serializable{
	private Integer	albumTkID ;
	private Integer	Reporter ;
	private Integer	clubAlbumID ;
	private String	albTkMsg ;
	private Integer	albTkStatID ;
	private Timestamp	albTkDate;
	private Integer	albTkCat ;
	
	public Integer getAlbumTkID() {
		return albumTkID;
	}
	public void setAlbumTkID(Integer albumTkID) {
		this.albumTkID = albumTkID;
	}
	public Integer getReporter() {
		return Reporter;
	}
	public void setReporter(Integer reporter) {
		Reporter = reporter;
	}
	public Integer getClubAlbumID() {
		return clubAlbumID;
	}
	public void setClubAlbumID(Integer clubAlbumID) {
		this.clubAlbumID = clubAlbumID;
	}
	public String getAlbTkMsg() {
		return albTkMsg;
	}
	public void setAlbTkMsg(String albTkMsg) {
		this.albTkMsg = albTkMsg;
	}
	public Integer getAlbTkStatID() {
		return albTkStatID;
	}
	public void setAlbTkStatID(Integer albTkStatID) {
		this.albTkStatID = albTkStatID;
	}
	public Timestamp getAlbTkDate() {
		return albTkDate;
	}
	public void setAlbTkDate(Timestamp albTkDate) {
		this.albTkDate = albTkDate;
	}
	public Integer getAlbTkCat() {
		return albTkCat;
	}
	public void setAlbTkCat(Integer albTkCat) {
		this.albTkCat = albTkCat;
	}
	
	
	
}
