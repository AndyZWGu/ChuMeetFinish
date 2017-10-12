package com.member.model;
import java.sql.Date;
import java.sql.Timestamp;

public class MemMBVO implements java.io.Serializable {
	private Integer memMBID;
	private Integer memNFID;
	private Integer memID;
	private String mbContent;
	private Timestamp mbDate;
	private Integer mbStatus;
	
	public Integer getMemMBID() {
		return memMBID;
	}
	public void setMemMBID(Integer memMBID) {
		this.memMBID = memMBID;
	}
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
	public String getMbContent() {
		return mbContent;
	}
	public void setMbContent(String mbContent) {
		this.mbContent = mbContent;
	}
	public Timestamp getMbDate() {
		return mbDate;
	}
	public void setMbDate(Timestamp mbDate) {
		this.mbDate = mbDate;
	}
	public Integer getMbStatus() {
		return mbStatus;
	}
	public void setMbStatus(Integer mbStatus) {
		this.mbStatus = mbStatus;
	}
	
}
