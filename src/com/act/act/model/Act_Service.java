package com.act.act.model;

import java.sql.*;
import java.util.*;

import com.act.act.model.Act_VO;
import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIVO;

public class Act_Service implements Act_interface{
	private Act_interface dao;

	public Act_Service() {
		dao = new Act_DAO();
	}

	public List<Act_VO> getAll() {
		return dao.getAll();
	}

	public List<ActFiestaVO> getAllFromNow() {
		return dao.getAllFromNow();
	}

	public Integer insert(Act_VO Act_VO) {
		return dao.insert(Act_VO);
	};

	public void update(Act_VO Act_VO) {
	};

	public ActFiestaVO getOne(Integer actID) {
		return dao.getOne(actID);
	};

	public List<ActFiestaVO> getActByDate(Timestamp actDate) {
		return null;
	};

	public List<ActFiestaVO> getActByWks() {
		return dao.getActByWks();
	};


	public List<ActFiestaVO> getActByClub(Integer clubID) {
		return null;
	}

	@Override
	public Set<ActMemVO> whosin(Integer actID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<ActPOIVO> showthetags(Integer actID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActFiestaVO> getActByPOIID(Integer POIID) {
		// TODO Auto-generated method stub
		return null;
	};

}
