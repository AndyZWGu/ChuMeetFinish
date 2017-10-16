package com.act.actMem.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.act.actMem.model.*;

//import com.act.trashcan.ActMemDAO_JNDI;

public class ActMemService implements ActMem_Interface {
	private ActMem_Interface dao;

	public ActMemService() {
		dao = new ActMemDAO_JNDI();
	}

    public void delete(Integer actID,Integer memID ){
    	dao.delete(actID,memID);
    };
    
	public void insert(Integer actID,Integer memID,Integer stat){
		dao.insert(actID,memID, stat);
	}
	
	
	public List<AmFaceVO> whosIn(Integer actID){
		return dao.whosIn(actID);
	}

	@Override
	public List<AmFaceVO> whosT(Integer actID) {
		// TODO Auto-generated method stub
		return dao.whosT(actID);
	}
	
}
