package com.act.actMem.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import com.act.trashcan.ActMemDAO_JNDI;

public class ActMemService implements ActMem_Interface {
	private ActMem_Interface dao;

	@Override
	public ActMemVO getOne(Integer actID, Integer memID) {
		// TODO Auto-generated method stub
		return dao.getOne(actID,memID);
	}
	
	
	
	public ActMemService() {
		dao = new ActMemDAO();
	}

    public void delete(ActMemVO actmVO){
    	dao.delete(actmVO);
    };
    
	public void insert(ActMemVO actmVO){
		dao.insert(actmVO);
	}

	@Override
	public List<ActMemVO> myActList1(Integer memID) {
		return dao.myActList1(memID);
	}

	@Override
	public List<ActMemVO> myActList2(Integer memID) {
		return dao.myActList2(memID);
	}

	@Override
	public List<ActMemVO> myActList5(Integer memID) {
		return dao.myActList5(memID);
	}
	@Override
	public List<ActMemVO> myActListAll(Integer memID) {
		return dao.myActListAll(memID);
	}
	
	
	public Set<Integer> getMyActs1(Integer memID){
		Set<Integer> actIDs1=new HashSet<Integer>(); 
		for (ActMemVO amVO: myActList1(memID)){
			actIDs1.add(amVO.getActVO().getActID());
		}
		System.out.println(actIDs1.size());
		return actIDs1;
	}
	
	public Set<Integer> getMyActs2(Integer memID){
		Set<Integer> actIDs2=new HashSet<Integer>(); 
		for (ActMemVO amVO: myActList2(memID)){
			actIDs2.add(amVO.getActVO().getActID());
		}
		System.out.println(actIDs2.size());
		return actIDs2;
	}
	
	public Set<Integer> getMyActs5(Integer memID){
		Set<Integer> actIDs5=new HashSet<Integer>(); 
		for (ActMemVO amVO: myActList5(memID)){
			actIDs5.add(amVO.getActVO().getActID());
		}
		System.out.println(actIDs5.size());
		return actIDs5;
	}

	@Override
	public void update(ActMemVO actmVO) {
		// TODO Auto-generated method stub
		
	}




	
	
	
	
	
	
}
