package com.clubAlbum.model;
import java.sql.Date;
import java.sql.Timestamp;

public class ClubAlbumVO implements java.io.Serializable{
	
	private Integer clubAlbumID;
	private Integer clubID;
	private Integer memID;
	private Timestamp clubAlbumDate;
	private String clubAlbumName;
	private Integer clubAlbumStatus;
	
	public Integer getClubAlbumID() {
		return clubAlbumID;
	}
	public void setClubAlbumID(Integer clubAlbumID) {
		this.clubAlbumID = clubAlbumID;
	}
	public Integer getClubID() {
		return clubID;
	}
	public void setClubID(Integer clubID) {
		this.clubID = clubID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public Timestamp getClubAlbumDate() {
		return clubAlbumDate;
	}
	public void setClubAlbumDate(Timestamp timestamp) {
		this.clubAlbumDate = timestamp;
	}
	public String getClubAlbumName() {
		return clubAlbumName;
	}
	public void setClubAlbumName(String clubAlbumName) {
		this.clubAlbumName = clubAlbumName;
	}
	public Integer getClubAlbumStatus() {
		return clubAlbumStatus;
	}
	public void setClubAlbumStatus(Integer clubAlbumStatus) {
		this.clubAlbumStatus = clubAlbumStatus;
	}
	
	
	

	
}
