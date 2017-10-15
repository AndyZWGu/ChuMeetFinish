package com.act.actMem.model;


import java.util.List;

public interface ActMem_Interface {

    public List<ActMemVO> myActList1(Integer memID);
    public List<ActMemVO> myActList2(Integer memID);
    public List<ActMemVO> myActList5(Integer memID);

    void update(ActMemVO actmVO);
	void insert(ActMemVO actmVO);
	void delete(ActMemVO actMemVO);
	public ActMemVO getOne(Integer actID, Integer memID);
	List<ActMemVO> myActListAll(Integer memID);


}
