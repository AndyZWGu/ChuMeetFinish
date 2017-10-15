package com.act.act.model;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIVO;

public class Act_VO  implements java.io.Serializable {
	private Integer actType;
	private Integer actID;
	private Integer memID;
	private Timestamp actCreateDate;
	private String actName;
	private Integer actStatus;
	private Integer actPriID;
	private Timestamp actStartDate;
	private Timestamp actEndDate;
	private Timestamp actSignStartDate;
	private Timestamp actSignEndDate;
	private Integer actTimeTypeID;
	private String actTimeTypeCnt;
	private Integer actMemMax;
	private Integer actMemMin;
	private byte[] actIMG;
	private String actContent;
	private Integer actIsHot;
	private Double actLong;
	private Double actLat;
	private Integer actPost;
	private String actLocName;
	private String actAdr;
	private String actUID;
	private String actShowUnit;
	private String actMasterUnit;
	private String actWebSales;
	private String actSourceWebName;
	private String actOnSale;
	private String actPrice;

	@Override
	public String toString() {
		return "Act_VO [actType=" + actType + ", actID=" + actID + ", memID=" + memID + ", actCreateDate="
				+ actCreateDate + ", actName=" + actName + ", actStatus=" + actStatus + ", actPriID=" + actPriID
				+ ", actStartDate=" + actStartDate + ", actEndDate=" + actEndDate + ", actSignStartDate="
				+ actSignStartDate + ", actSignEndDate=" + actSignEndDate + ", actTimeTypeID=" + actTimeTypeID
				+ ", actTimeTypeCnt=" + actTimeTypeCnt + ", actMemMax=" + actMemMax + ", actMemMin=" + actMemMin
				+ ", actIMG=" + Arrays.toString(actIMG) + ", actContent=" + actContent + ", actIsHot=" + actIsHot
				+ ", actLong=" + actLong + ", actLat=" + actLat + ", actPost=" + actPost + ", actLocName=" + actLocName
				+ ", actAdr=" + actAdr + ", actUID=" + actUID + ", actShowUnit=" + actShowUnit + ", actMasterUnit="
				+ actMasterUnit + ", actWebSales=" + actWebSales + ", actSourceWebName=" + actSourceWebName
				+ ", actOnSale=" + actOnSale + ", actPrice=" + actPrice + ", actMems=" + actMems + ", actPOIs="
				+ actPOIs + "]";
	}
	//	查一時，查出一群多(actMem) => set
	private Set<ActMemVO> actMems = new HashSet<ActMemVO>();
	private Set<ActPOIVO> actPOIs = new HashSet<ActPOIVO>();

	
	
	public Set<ActMemVO> getActMems() {
		return actMems;
	}
	public void setActMems(Set<ActMemVO> actMems) {
		this.actMems = actMems;
	}
	public Set<ActPOIVO> getActPOIs() {
		return actPOIs;
	}
	public void setActPOIs(Set<ActPOIVO> actPOIs) {
		this.actPOIs = actPOIs;
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
	public Timestamp getActCreateDate() {
		return actCreateDate;
	}
	public void setActCreateDate(Timestamp actCreateDate) {
		this.actCreateDate = actCreateDate;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public Integer getActStatus() {
		return actStatus;
	}
	public void setActStatus(Integer actStatus) {
		this.actStatus = actStatus;
	}
	public Integer getActPriID() {
		return actPriID;
	}
	public void setActPriID(Integer actPriID) {
		this.actPriID = actPriID;
	}
	public Timestamp getActStartDate() {
		return actStartDate;
	}
	public void setActStartDate(Timestamp actStartDate) {
		this.actStartDate = actStartDate;
	}
	public Timestamp getActEndDate() {
		return actEndDate;
	}
	public void setActEndDate(Timestamp actEndDate) {
		this.actEndDate = actEndDate;
	}
	public Timestamp getActSignStartDate() {
		return actSignStartDate;
	}
	public void setActSignStartDate(Timestamp actSignStartDate) {
		this.actSignStartDate = actSignStartDate;
	}
	public Timestamp getActSignEndDate() {
		return actSignEndDate;
	}
	public void setActSignEndDate(Timestamp actSignEndDate) {
		this.actSignEndDate = actSignEndDate;
	}
	public Integer getActTimeTypeID() {
		return actTimeTypeID;
	}
	public void setActTimeTypeID(Integer actTimeTypeID) {
		this.actTimeTypeID = actTimeTypeID;
	}
	public String getActTimeTypeCnt() {
		return actTimeTypeCnt;
	}
	public void setActTimeTypeCnt(String actTimeTypeCnt) {
		this.actTimeTypeCnt = actTimeTypeCnt;
	}
	public Integer getActMemMax() {
		return actMemMax;
	}
	public void setActMemMax(Integer actMemMax) {
		this.actMemMax = actMemMax;
	}
	public Integer getActMemMin() {
		return actMemMin;
	}
	public void setActMemMin(Integer actMemMin) {
		this.actMemMin = actMemMin;
	}
	public byte[] getActIMG() {
		return actIMG;
	}
	public void setActIMG(byte[] actIMG) {
		this.actIMG = actIMG;
	}
	public String getActContent() {
		return actContent;
	}
	public void setActContent(String actContent) {
		this.actContent = actContent;
	}
	public Integer getActIsHot() {
		return actIsHot;
	}
	public void setActIsHot(Integer actIsHot) {
		this.actIsHot = actIsHot;
	}
	public Double getActLong() {
		return actLong;
	}
	public void setActLong(Double actLong) {
		this.actLong = actLong;
	}
	public Double getActLat() {
		return actLat;
	}
	public void setActLat(Double actLat) {
		this.actLat = actLat;
	}
	public Integer getActPost() {
		return actPost;
	}
	public void setActPost(Integer actPost) {
		this.actPost = actPost;
	}
	public String getActLocName() {
		return actLocName;
	}
	public void setActLocName(String actLocName) {
		this.actLocName = actLocName;
	}
	public String getActAdr() {
		return actAdr;
	}
	public void setActAdr(String actAdr) {
		this.actAdr = actAdr;
	}
	
	
	public Integer getActType() {
		return actType;
	}
	public void setActType(Integer actType) {
		this.actType = actType;
	}
	
	public String getActUID() {
		return actUID;
	}
	public void setActUID(String actUID) {
		this.actUID = actUID;
	}

	public String getActMasterUnit() {
		return actMasterUnit;
	}
	public void setActMasterUnit(String actMasterUnit) {
		this.actMasterUnit = actMasterUnit;
	}
	public String getActWebSales() {
		return actWebSales;
	}
	public void setActWebSales(String actWebSales) {
		this.actWebSales = actWebSales;
	}
	public String getActSourceWebName() {
		return actSourceWebName;
	}
	public void setActSourceWebName(String actSourceWebName) {
		this.actSourceWebName = actSourceWebName;
	}
	public String getActOnSale() {
		return actOnSale;
	}
	public void setActOnSale(String actOnSale) {
		this.actOnSale = actOnSale;
	}
	public String getActPrice() {
		return actPrice;
	}
	public void setActPrice(String actPrice) {
		this.actPrice = actPrice;
	}
	public String getActShowUnit() {
		return actShowUnit;
	}
	public void setActShowUnit(String actShowUnit) {
		this.actShowUnit = actShowUnit;
	}

	
	
}
