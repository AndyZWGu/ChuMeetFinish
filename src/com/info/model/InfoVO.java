package com.info.model;

public class InfoVO implements java.io.Serializable {

	private Integer infoID ;
	private String infoName;
	private String infoContent;
	
	
	public Integer getInfoID() {
		return infoID;
	}
	public void setInfoID(Integer infoID) {
		this.infoID = infoID;
	}
	public String getInfoName() {
		return infoName;
	}
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	public String getInfoContent() {
		return infoContent;
	}
	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	
}
