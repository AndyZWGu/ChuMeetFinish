package com.member.model;

import java.sql.Timestamp;

public class FriendsVO implements java.io.Serializable {
	private Integer friMem1;
	private Integer friMem2;
	private String friendType;
	private Timestamp friendDate;
	
	public Integer getFriMem1() {
		return friMem1;
	}
	public void setFriMem1(Integer friMem1) {
		this.friMem1 = friMem1;
	}
	public Integer getFriMem2() {
		return friMem2;
	}
	public void setFriMem2(Integer friMem2) {
		this.friMem2 = friMem2;
	}
	public String getFriendType() {
		return friendType;
	}
	public void setFriendType(String friendType) {
		this.friendType = friendType;
	}
	public Timestamp getFriendDate() {
		return friendDate;
	}
	public void setFriendDate(Timestamp friendDate) {
		friendDate = friendDate;
	}
	
}
