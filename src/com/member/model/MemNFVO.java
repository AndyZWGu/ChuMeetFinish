package com.member.model;

import java.sql.Timestamp;

public class MemNFVO implements java.io.Serializable{
	private Integer memNFID;
	private Integer memID;
	private String nfTitle;
	private String nfContent;
	private byte[] nfPic;
	private Timestamp nfDate;
	private Integer nfViews;
	private Integer nfStatus;
	
	public Integer getMemNFID() {
		return memNFID;
	}
	public void setMemNFID(Integer memNFID) {
		this.memNFID = memNFID;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public String getNfTitle() {
		return nfTitle;
	}
	public void setNfTitle(String nfTitle) {
		this.nfTitle = nfTitle;
	}
	public String getNfContent() {
		return nfContent;
	}
	public void setNfContent(String nfContent) {
		this.nfContent = nfContent;
	}
	public byte[] getNfPic() {
		return nfPic;
	}
	public void setNfPic(byte[] nfPic) {
		this.nfPic = nfPic;
	}
	public Timestamp getNfDate() {
		return nfDate;
	}
	public void setNfDate(Timestamp nfDate) {
		this.nfDate = nfDate;
	}
	public Integer getNfViews() {
		return nfViews;
	}
	public void setNfViews(Integer nfViews) {
		this.nfViews = nfViews;
	}
	public Integer getNfStatus() {
		return nfStatus;
	}
	public void setNfStatus(Integer nfStatus) {
		this.nfStatus = nfStatus;
	}

}
