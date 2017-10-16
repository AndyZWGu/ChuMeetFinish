package com.act.model;

import java.util.List;

public interface ActMB_Interface {
	public List<ActMBVO> getAll(Integer actID) ;
	public void insert(Integer actID, Integer memID, String Cnt);
	public void delete(Integer actMBID);
	}
