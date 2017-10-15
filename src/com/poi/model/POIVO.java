package com.poi.model;

import java.util.HashSet;
import java.util.Set;

import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIVO;

public class POIVO {
	private Integer POIID;
	private String POINameC;
	private String POIName;
	private String POIAbr;
	private Integer POIStatus;
	private byte[] POIImg;
	private Set<ActPOIVO> actPOIs = new HashSet<ActPOIVO>();
 
	
	
	public Set<ActPOIVO> getActPOIs() {
		return actPOIs;
	}
	public void setActPOIs(Set<ActPOIVO> actPOIs) {
		this.actPOIs = actPOIs;
	}
	
	public Integer getPOIID() {
		return POIID;
	}
	public void setPOIID(Integer pOIID) {
		POIID = pOIID;
	}
	public String getPOINameC() {
		return POINameC;
	}
	public void setPOINameC(String pOINameC) {
		POINameC = pOINameC;
	}
	public String getPOIName() {
		return POIName;
	}
	public void setPOIName(String pOIName) {
		POIName = pOIName;
	}
	public String getPOIAbr() {
		return POIAbr;
	}
	public void setPOIAbr(String pOIAbr) {
		POIAbr = pOIAbr;
	}
	public Integer getPOIStatus() {
		return POIStatus;
	}
	public void setPOIStatus(Integer pOIStatus) {
		POIStatus = pOIStatus;
	}
	public byte[] getPOIImg() {
		return POIImg;
	}
	public void setPOIImg(byte[] pOIImg) {
		POIImg = pOIImg;
	}


}
