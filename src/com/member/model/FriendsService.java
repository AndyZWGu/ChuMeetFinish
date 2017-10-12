package com.member.model;

import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class FriendsService {

	private FriendsDAO_interface dao;

	public FriendsService() {
		dao = new FriendsDAO();
	}

	public FriendsVO addMember(
			Integer friMem1,
			Integer friMem2,
			String friendType,
			Timestamp friendDate) {

		FriendsVO friendsVO = new FriendsVO();

		friendsVO.setFriMem1(friMem1);
		friendsVO.setFriMem2(friMem2);
		friendsVO.setFriendType(friendType);
		friendsVO.setFriendDate(friendDate);
		dao.insert(friendsVO);

		return friendsVO;
	}

	public FriendsVO updateMember(
			Integer friMem1,
			Integer friMem2,
			String friendType,
			Timestamp friendDate) {

		FriendsVO friendsVO = new FriendsVO();

		friendsVO.setFriMem1(friMem1);
		friendsVO.setFriMem2(friMem2);
		friendsVO.setFriendType(friendType);
		friendsVO.setFriendDate(friendDate);
		
		dao.update(friendsVO);

		return friendsVO;
	}

	public void deleteFriends(Integer friMem1) {
		dao.delete(friMem1);
	}

	public FriendsVO getOneFriends(Integer friMem1,Integer friMem2) {
		return dao.findByPrimaryKey(friMem1,friMem2);
	}
	
	public List<FriendsVO> getAllFriends(Integer friMem1) {
		return dao.findByPrimaryKeyAll(friMem1);
	}
	
	public List<FriendsVO> getAll() {
		return dao.getAll();
	}
	
	public List<FriendsVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
