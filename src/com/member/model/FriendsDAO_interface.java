package com.member.model;

import java.util.List;
import java.util.Map;

public interface FriendsDAO_interface {
	public void insert(FriendsVO friendsVO);

	public void update(FriendsVO friendsVO);


	public List<FriendsVO> getAll();

	// �f���}�ϲ�ԃ(���녢���͑BMap)(�؂� List)
	public List<FriendsVO> getAll(Map<String, String[]> map);

	
	//多數好友
	public List<FriendsVO> findByPrimaryKeyAll(Integer friMem1);

	//特定好友詳情
	FriendsVO findByPrimaryKey(Integer friMem1, Integer friMem2);

	public List<FriendsVO> getAllMap(Map<String, String[]> map);

	public void delete(Integer friMem1, Integer friMem2);



}
