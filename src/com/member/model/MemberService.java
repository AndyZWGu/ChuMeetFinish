package com.member.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class MemberService {

	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}

	public MemberVO addMember(
			String memEmail,
			String memPw,
			Integer memberType,
			Integer memLv,
			Integer memExp,
			Integer memPt,
			String memName,
			Integer memGender,
			Timestamp memBD, 
			String memPhone, 
			byte[] memAvatar,
			Timestamp memJoinDate,
			Integer memLoginNum,
			String memLocBorn,
			String memLocLive,
			String memInt,
			Double memLong,
			Double memLat,
		 	Integer memPriv,
			Integer memStatus) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMemEmail(memEmail);
		memberVO.setMemPw(memPw);
		memberVO.setMemberType(memberType);
		memberVO.setMemLv(memLv);
		memberVO.setMemExp(memExp);
		memberVO.setMemPt(memPt);
		memberVO.setMemName(memName);
		memberVO.setMemGender(memGender);
		memberVO.setMemBD(memBD);
		memberVO.setMemPhone(memPhone);
		memberVO.setMemAvatar(memAvatar);
		memberVO.setMemJoinDate(memJoinDate);
		memberVO.setMemLoginNum(memLoginNum);
		memberVO.setMemLocBorn(memLocBorn);
		memberVO.setMemLocLive(memLocLive);
		memberVO.setMemInt(memInt);
		memberVO.setMemLong(memLong);
		memberVO.setMemLat(memLat);
		memberVO.setMemPriv(memPriv);
		memberVO.setMemStatus(memStatus);
		dao.insert(memberVO);

		return memberVO;
	}
	
	public MemberVO updateTypeMember(MemberVO memberVO,Integer memberType) {

		memberVO.setMemID(memberVO.getMemID());
		memberVO.setMemEmail(memberVO.getMemEmail());
		memberVO.setMemPw(memberVO.getMemPw());
		memberVO.setMemberType(memberType);
		memberVO.setMemLv(memberVO.getMemLv());
		memberVO.setMemExp(memberVO.getMemExp());
		memberVO.setMemPt(memberVO.getMemPt());
		memberVO.setMemName(memberVO.getMemName());
		memberVO.setMemGender(memberVO.getMemGender());
		memberVO.setMemBD(memberVO.getMemBD());
		memberVO.setMemPhone(memberVO.getMemPhone());
		memberVO.setMemAvatar(memberVO.getMemAvatar());
		memberVO.setMemJoinDate(memberVO.getMemJoinDate());
		memberVO.setMemLoginNum(memberVO.getMemLoginNum()+1);
		memberVO.setMemLocBorn(memberVO.getMemLocBorn());
		memberVO.setMemLocLive(memberVO.getMemLocLive());
		memberVO.setMemInt(memberVO.getMemInt());
		memberVO.setMemLong(memberVO.getMemLong());
		memberVO.setMemLat(memberVO.getMemLat());
		memberVO.setMemPriv(memberVO.getMemPriv());
		memberVO.setMemStatus(memberVO.getMemStatus());
		dao.update(memberVO);
		return memberVO;
	}
	
	public MemberVO updateInfoMember(MemberVO memberVO,String memPw,String memName,String memLocBorn,String memLocLive) {

		memberVO.setMemID(memberVO.getMemID());
		memberVO.setMemEmail(memberVO.getMemEmail());
		memberVO.setMemPw(memPw);
		memberVO.setMemberType(memberVO.getMemberType());
		memberVO.setMemLv(memberVO.getMemLv());
		memberVO.setMemExp(memberVO.getMemExp());
		memberVO.setMemPt(memberVO.getMemPt());
		memberVO.setMemName(memName);
		memberVO.setMemGender(memberVO.getMemGender());
		memberVO.setMemBD(memberVO.getMemBD());
		memberVO.setMemPhone(memberVO.getMemPhone());
		memberVO.setMemAvatar(memberVO.getMemAvatar());
		memberVO.setMemJoinDate(memberVO.getMemJoinDate());
		memberVO.setMemLoginNum(memberVO.getMemLoginNum()+1);
		memberVO.setMemLocBorn(memLocBorn);
		memberVO.setMemLocLive(memLocLive);
		memberVO.setMemInt(memberVO.getMemInt());
		memberVO.setMemLong(memberVO.getMemLong());
		memberVO.setMemLat(memberVO.getMemLat());
		memberVO.setMemPriv(memberVO.getMemPriv());
		memberVO.setMemStatus(memberVO.getMemStatus());
		dao.update(memberVO);
		return memberVO;
	}
	
	public MemberVO updateSettingMember(MemberVO memberVO,String memInt,Integer memPriv) {

		memberVO.setMemID(memberVO.getMemID());
		memberVO.setMemEmail(memberVO.getMemEmail());
		memberVO.setMemPw(memberVO.getMemPw());
		memberVO.setMemberType(memberVO.getMemberType());
		memberVO.setMemLv(memberVO.getMemLv());
		memberVO.setMemExp(memberVO.getMemExp());
		memberVO.setMemPt(memberVO.getMemPt());
		memberVO.setMemName(memberVO.getMemName());
		memberVO.setMemGender(memberVO.getMemGender());
		memberVO.setMemBD(memberVO.getMemBD());
		memberVO.setMemPhone(memberVO.getMemPhone());
		memberVO.setMemAvatar(memberVO.getMemAvatar());
		memberVO.setMemJoinDate(memberVO.getMemJoinDate());
		memberVO.setMemLoginNum(memberVO.getMemLoginNum()+1);
		memberVO.setMemLocBorn(memberVO.getMemLocBorn());
		memberVO.setMemLocLive(memberVO.getMemLocLive());
		memberVO.setMemInt(memInt);
		memberVO.setMemLong(memberVO.getMemLong());
		memberVO.setMemLat(memberVO.getMemLat());
		memberVO.setMemPriv(memPriv);
		memberVO.setMemStatus(memberVO.getMemStatus());
		dao.update(memberVO);
		return memberVO;
	}
	
	public void updateLoginNumMember(MemberVO memberVO) {

		memberVO.setMemID(memberVO.getMemID());
		memberVO.setMemEmail(memberVO.getMemEmail());
		memberVO.setMemPw(memberVO.getMemPw());
		memberVO.setMemberType(memberVO.getMemberType());
		memberVO.setMemLv(memberVO.getMemLv());
		memberVO.setMemExp(memberVO.getMemExp());
		memberVO.setMemPt(memberVO.getMemPt());
		memberVO.setMemName(memberVO.getMemName());
		memberVO.setMemGender(memberVO.getMemGender());
		memberVO.setMemBD(memberVO.getMemBD());
		memberVO.setMemPhone(memberVO.getMemPhone());
		memberVO.setMemAvatar(memberVO.getMemAvatar());
		memberVO.setMemJoinDate(memberVO.getMemJoinDate());
		memberVO.setMemLoginNum(memberVO.getMemLoginNum()+1);
		memberVO.setMemLocBorn(memberVO.getMemLocBorn());
		memberVO.setMemLocLive(memberVO.getMemLocLive());
		memberVO.setMemInt(memberVO.getMemInt());
		memberVO.setMemLong(memberVO.getMemLong());
		memberVO.setMemLat(memberVO.getMemLat());
		memberVO.setMemPriv(memberVO.getMemPriv());
		memberVO.setMemStatus(memberVO.getMemStatus());
		dao.update(memberVO);

	}

	public MemberVO updateMember(Integer memID,
			String memEmail,
			String memPw,
			Integer memberType,
			Integer memLv,
			Integer memExp,
			Integer memPt,
			String memName,
			Integer memGender,
			Timestamp memBD, 
			String memPhone, 
			byte[] memAvatar,
			Timestamp memJoinDate,
			Integer memLoginNum,
			String memLocBorn,
			String memLocLive,
			String memInt,
			Double memLong,
			Double memLat,
		 	Integer memPriv,
			Integer memStatus) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMemID(memID);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemPw(memPw);
		memberVO.setMemberType(memberType);
		memberVO.setMemLv(memLv);
		memberVO.setMemExp(memExp);
		memberVO.setMemPt(memPt);
		memberVO.setMemName(memName);
		memberVO.setMemGender(memGender);
		memberVO.setMemBD(memBD);
		memberVO.setMemPhone(memPhone);
		memberVO.setMemAvatar(memAvatar);
		memberVO.setMemJoinDate(memJoinDate);
		memberVO.setMemLoginNum(memLoginNum);
		memberVO.setMemLocBorn(memLocBorn);
		memberVO.setMemLocLive(memLocLive);
		memberVO.setMemInt(memInt);
		memberVO.setMemLong(memLong);
		memberVO.setMemLat(memLat);
		memberVO.setMemPriv(memPriv);
		memberVO.setMemStatus(memStatus);
		dao.update(memberVO);

		return memberVO;
	}

	public void deleteMember(Integer memID) {
		dao.delete(memID);
	}

	public MemberVO getOneMember(Integer memID) {
		return dao.findByPrimaryKey(memID);
	}
	
	public ResultSet getAvatar(Integer memID) {
		return dao.getAvatarByMemID(memID);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
	
	public List<MemberVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
	public MemberVO getMemberByMemEmail(String memEmail) {
		return dao.findByMemEmail(memEmail);
	}
	public MemberVO getMemberByMemPw(String memPw) {
		return dao.findByMemPw(memPw);
	}

}
