package com.act.model;

import java.util.List;

import com.act.actMem.model.ActMemDAO_JNDI;
import com.act.actMem.model.ActMem_Interface;


public class ActMBService implements ActMB_Interface{
	
	private ActMBDAO dao;
	public ActMBService() {
	 dao = new ActMBDAO();
	}

	public List<ActMBVO> getAll(Integer actID) {
		return dao.getAll(actID);
	}

	
	
	public void insert(Integer actID, Integer memID, String Cnt) {
		dao.insert(actID, memID, Cnt);
	}

	@Override
	public void delete(Integer actMBID) {
		dao.delete(actMBID);
	};

}
