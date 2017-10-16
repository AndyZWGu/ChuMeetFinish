package com.ad.model;

import java.sql.Timestamp;

public class AdVO implements java.io.Serializable{

	private Integer adID ;
	private Integer adminID ;
	private String adName ;
	private byte[] adImg ;
	private String adContent ;
	 private String adLink ;
	 private Timestamp adDate ;
	public Integer getAdID() {
		return adID;
	}
	public void setAdID(Integer adID) {
		this.adID = adID;
	}
	public Integer getAdminID() {
		return adminID;
	}
	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public byte[] getAdImg() {
		return adImg;
	}
	public void setAdImg(byte[] bs) {
		this.adImg = bs;
	}
	public String getAdContent() {
		return adContent;
	}
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}
	public String getAdLink() {
		return adLink;
	}
	public void setAdLink(String adLink) {
		this.adLink = adLink;
	}
	public Timestamp getAdDate() {
		return adDate;
	}
	public void setAdDate(Timestamp adDate) {
		this.adDate = adDate;
	}
	 
	 
	 
}
