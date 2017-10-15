package com.tk.model;

import java.sql.Timestamp;

public class ActTKIDVO {
	private Integer albTKID;
	private Integer reporter;
	private Integer clubAlbumID;
	private String albTkMsg;
	private Integer albTkStatID;
	private Timestamp albTkDate;
	private Integer albTkCat;
	private Integer albID;
	public Integer getAlbTKID() {
		return albTKID;
	}
	public void setAlbTKID(Integer albTKID) {
		this.albTKID = albTKID;
	}
	public Integer getReporter() {
		return reporter;
	}
	public void setReporter(Integer reporter) {
		this.reporter = reporter;
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
	public Integer getAlbID() {
		return albID;
	}
	public void setAlbID(Integer albID) {
		this.albID = albID;
	}

}
