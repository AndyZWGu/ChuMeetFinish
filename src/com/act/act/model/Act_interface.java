package com.act.act.model;

import java.sql.Timestamp;
import java.util.*;
import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIVO;


public interface Act_interface {
    public Integer insert(ActVO actVO);
    public void update(ActVO actVO);

    public List<ActFVO> getAll();
    public ActFVO getOne(Integer actID);

    public List<ActFVO> getActByPOIID(Integer POIID);
    public List<ActFVO> getActByDate(Timestamp actDate);
    public List<ActFVO> getActByWks();
//    public List<ActVO> getActByDist(Integer Dist);
    public List<ActFVO> getActByClub(Integer clubID);
	public List<ActFVO> getAllWithPast();
	
	public List<ActFVO> getMemActs(Integer memID, Integer stat);
		public List<ActFVO> getMemActs12(Integer memID);
		public List<ActFVO> getx2ByPOIID(Integer pOIID);
		public List<ActFVO> getRDx2ByPOIID();
		

}
