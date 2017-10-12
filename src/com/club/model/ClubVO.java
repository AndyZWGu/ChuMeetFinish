package com.club.model;

import java.sql.Timestamp;

public class ClubVO implements java.io.Serializable{
	private Integer clubID;
	private Integer clubmemID;
	private String clubName;
	private Integer clubTypeID;
	private String clubContent;
	private byte[] clubPhoto;
	private Timestamp clubStartDate;
	private Integer clubStatus;
	private Double clubLong;
	private Double clubLat;
	
	public Integer getClubID() {
		return clubID;
	}
	public void setClubID(Integer clubID) {
		this.clubID = clubID;
	}
	public Integer getClubmemID() {
		return clubmemID;
	}
	public void setClubmemID(Integer clubmemID) {
		this.clubmemID = clubmemID;
	}
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public Integer getClubTypeID() {
		return clubTypeID;
	}
	public void setClubTypeID(Integer clubTypeID) {
		this.clubTypeID = clubTypeID;
	}
	public String getClubContent() {
		return clubContent;
	}
	public void setClubContent(String clubContent) {
		this.clubContent = clubContent;
	}
	public Timestamp getClubStartDate() {
		return clubStartDate;
	}
	public void setClubStartDate(Timestamp clubStartDate) {
		this.clubStartDate = clubStartDate;
	}
	public Integer getClubStatus() {
		return clubStatus;
	}
	public void setClubStatus(Integer clubStatus) {
		this.clubStatus = clubStatus;
	}
	public Double getClubLong() {
		return clubLong;
	}
	public void setClubLong(Double clubLong) {
		this.clubLong = clubLong;
	}
	public Double getClubLat() {
		return clubLat;
	}
	public void setClubLat(Double clubLat) {
		this.clubLat = clubLat;
	}
	public byte[] getClubPhoto() {
		return clubPhoto;
	}
	public void setClubPhoto(byte[] clubPhoto) {
		this.clubPhoto = clubPhoto;
	}
	
	
}
