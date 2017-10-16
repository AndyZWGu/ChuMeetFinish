package com.act.actMem.model;


import java.util.List;

public interface ActMem_Interface {

	void insert(Integer actID,Integer memID,Integer stat);
	void delete(Integer actID,Integer memID);
	List<AmFaceVO> whosIn(Integer actID);
	List<AmFaceVO> whosT(Integer actID);


}
