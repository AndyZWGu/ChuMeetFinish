package com.clubImg.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ClubImgVO  implements java.io.Serializable{

	private Integer clubImgID;
	private Integer clubAlbumID;
	private Integer memID;
	private Timestamp clubImgDate;
	private byte[] clubImg;
	private String clubImgContent;
	private Integer clubImgStatus;
	
	
	public Integer getClubImgID() {
		return clubImgID;
	}
	public void setClubImgID(Integer clubImgID) {
		this.clubImgID = clubImgID;
	}
	public Integer getClubAlbumID() {
		return clubAlbumID;
	}
	public void setClubAlbumID(Integer clubAlbumID) {
		this.clubAlbumID = clubAlbumID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Timestamp getClubImgDate() {
		return clubImgDate;
	}
	public void setClubImgDate(Timestamp clubImgDate) {
		this.clubImgDate = clubImgDate;
	}
	public byte[] getClubImg() {
		return clubImg;
	}
	public void setClubImg(byte[] clubImg) {
		this.clubImg = clubImg;
	}
	public String getClubImgContent() {
		return clubImgContent;
	}
	public void setClubImgContent(String clubImgContent) {
		this.clubImgContent = clubImgContent;
	}
	public Integer getClubImgStatus() {
		return clubImgStatus;
	}
	public void setClubImgStatus(Integer clubImgStatus) {
		this.clubImgStatus = clubImgStatus;
	}

	
	
	
	
	
	
	
}
