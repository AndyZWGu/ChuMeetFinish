package com.act.act.model;

import java.sql.*;
import java.util.*;

import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIVO;

public class Act_Service implements Act_interface {
	private Act_interface dao;
		public Act_Service() {
		dao = new Act_DAO_JNDI();
	}

	public void update(ActVO actVO) {
		dao.update(actVO);
	};

	@Override
	public Integer insert(ActVO actVO) {
		return dao.insert(actVO);
	}
	
	
	
	public List<ActFVO> getx2ByPOIID(Integer POIID) {
		return dao.getx2ByPOIID(POIID);
	}
	

	@Override
	public ActFVO getOne(Integer actID) {
		return dao.getOne(actID);
	}

	@Override
	public List<ActFVO> getMemActs(Integer memID, Integer stat) {
		// TODO Auto-generated method stub
		return dao.getMemActs(memID, stat);
	}

	@Override
	public List<ActFVO> getMemActs12(Integer memID) {
		// TODO Auto-generated method stub
		return dao.getMemActs12(memID);
	}

	@Override
	public List<ActFVO> getAll() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public List<ActFVO> getActByPOIID(Integer POIID) {
		// TODO Auto-generated method stub
		return dao.getActByPOIID(POIID);
	}

	@Override
	public List<ActFVO> getActByDate(Timestamp actDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActFVO> getActByWks() {
		// TODO Auto-generated method stub
		return dao.getActByWks();
	}

	@Override
	public List<ActFVO> getActByClub(Integer clubID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActFVO> getAllWithPast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActFVO> getRDx2ByPOIID() {
		// TODO Auto-generated method stub
		return dao.getRDx2ByPOIID();
	}



}
