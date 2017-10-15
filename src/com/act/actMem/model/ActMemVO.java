package com.act.actMem.model;

import java.sql.Timestamp;

import com.act.act.model.Act_VO;
import com.member.model.MemberHVO;

public class ActMemVO implements java.io.Serializable{
	
	private Act_VO actVO;
	private MemberHVO memberHVO;
	
	private Integer actMemStatus;
	private Timestamp actJoinDate;
	private Integer actStar;
	private Timestamp actStarDate;
	private Integer QRStatus;

public ActMemVO(){}	
	
public ActMemVO(Act_VO actVO, MemberHVO memberHVO, Integer actMemStatus, Timestamp actJoinDate, Integer actStar,
			Timestamp actStarDate) {
		super();
		this.actVO = actVO;
		this.memberHVO = memberHVO;
		this.actMemStatus = actMemStatus;
		this.actJoinDate = actJoinDate;
		this.actStar = actStar;
		this.actStarDate = actStarDate;
	}

//	查多時，帶出一
	public MemberHVO getMemberHVO() {
		return memberHVO;
	}
	public void setMemberHVO(MemberHVO memberHVO) {
		this.memberHVO = memberHVO;
	}
	public Act_VO getActVO() {
		return actVO;
	}
	public void setActVO(Act_VO actVO) {
		this.actVO = actVO;
	}

	public Integer getActMemStatus() {
		return actMemStatus;
	}
	public void setActMemStatus(Integer actMemStatus) {
		this.actMemStatus = actMemStatus;
	}
	public Timestamp getActJoinDate() {
		return actJoinDate;
	}
	public void setActJoinDate(Timestamp actJoinDate) {
		this.actJoinDate = actJoinDate;
	}
	public Integer getActStar() {
		return actStar;
	}
	public void setActStar(Integer actStar) {
		this.actStar = actStar;
	}
	public Timestamp getActStarDate() {
		return actStarDate;
	}
	public void setActStarDate(Timestamp actStarDate) {
		this.actStarDate = actStarDate;
	}

	public Integer getQRStatus() {
		return QRStatus;
	}

	public void setQRStatus(Integer qRStatus) {
		QRStatus = qRStatus;
	}

}
