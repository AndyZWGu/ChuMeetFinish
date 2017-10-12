package com.info.model;

import java.util.*;



public interface InfoDAO_interface {
	public void insert(InfoVO infoVO);
	public void update(InfoVO infoVO);
	public void delete(Integer infoID);
	public InfoVO findByPrimaryKey(Integer infoID);
	public List<InfoVO> getAll();
//	public Set<memberVO> getMemberByActID(Integer actID);

}
