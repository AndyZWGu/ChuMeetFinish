package com.member.model;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.act.actMem.model.ActMemVO;

public class MemberHVO implements java.io.Serializable {
	private Integer memID;
	private String memEmail;
	private String memPW;
	private Integer memberType;
	private Integer memLv;
	private Integer memExp;
	private Integer memPt;
	private String memName;
	private Integer memGender;
	private Timestamp memBD;
	private String memPhone;
	private byte[] memAvatar;
	private Timestamp memJoinDate;
	private Integer memLoginNum;
	private String memLocBorn;
	private String memLocLive;
	private String memInt;
	private Integer memLong;
	private Integer memLat;
	private Integer memPriv;
	private Integer memStatus;
	
	
	private Set<ActMemVO> actMems = new HashSet<ActMemVO>();
	
	
	
	public Set<ActMemVO> getActMems() {
		return actMems;
	}
	public void setActMems(Set<ActMemVO> actMems) {
		this.actMems = actMems;
	}
	public Integer getMemID() {
		return memID;
	}
	public void setMemID(Integer memID) {
		this.memID = memID;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemPW() {
		return memPW;
	}
	public void setMemPW(String memPW) {
		this.memPW = memPW;
	}
	public Integer getMemberType() {
		return memberType;
	}
	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
	public Integer getMemLv() {
		return memLv;
	}
	public void setMemLv(Integer memLv) {
		this.memLv = memLv;
	}
	public Integer getMemExp() {
		return memExp;
	}
	public void setMemExp(Integer memExp) {
		this.memExp = memExp;
	}
	public Integer getMemPt() {
		return memPt;
	}
	public void setMemPt(Integer memPt) {
		this.memPt = memPt;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public Integer getMemGender() {
		return memGender;
	}
	public void setMemGender(Integer memGender) {
		this.memGender = memGender;
	}
	public Timestamp getMemBD() {
		return memBD;
	}
	public void setMemBD(Timestamp memBD) {
		this.memBD = memBD;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public byte[] getMemAvatar() {
		return memAvatar;
	}
	public void setMemAvatar(byte[] memAvatar) {
		this.memAvatar = memAvatar;
	}
	public Timestamp getMemJoinDate() {
		return memJoinDate;
	}
	public void setMemJoinDate(Timestamp memJoinDate) {
		this.memJoinDate = memJoinDate;
	}
	public Integer getMemLoginNum() {
		return memLoginNum;
	}
	public void setMemLoginNum(Integer memLoginNum) {
		this.memLoginNum = memLoginNum;
	}
	public String getMemLocBorn() {
		return memLocBorn;
	}
	public void setMemLocBorn(String memLocBorn) {
		this.memLocBorn = memLocBorn;
	}
	public String getMemLocLive() {
		return memLocLive;
	}
	public void setMemLocLive(String memLocLive) {
		this.memLocLive = memLocLive;
	}
	public String getMemInt() {
		return memInt;
	}
	public void setMemInt(String memInt) {
		this.memInt = memInt;
	}
	public Integer getMemLong() {
		return memLong;
	}
	public void setMemLong(Integer memLong) {
		this.memLong = memLong;
	}
	public Integer getMemLat() {
		return memLat;
	}
	public void setMemLat(Integer memLat) {
		this.memLat = memLat;
	}
	public Integer getMemPriv() {
		return memPriv;
	}
	public void setMemPriv(Integer memPriv) {
		this.memPriv = memPriv;
	}
	public Integer getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}

}
