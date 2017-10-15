package com.act.model;

public class ActImgVO {
	private Integer actImgID;
	private Integer actID;
	private Integer memID;
	private byte[] actImg;
	public Integer getActImgID() {
		return actImgID;
	}
	public void setActImgID(Integer actImgID) {
		this.actImgID = actImgID;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public byte[] getActImg() {
		return actImg;
	}
	public void setActImg(byte[] actImg) {
		this.actImg = actImg;
	}

}
