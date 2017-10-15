package com.act.act.model;

import java.sql.Timestamp;
import java.util.*;

import com.act.act.model.Act_VO;
import com.act.actMem.model.ActMemVO;
import com.act.actPOI.model.ActPOIVO;


public interface Act_interface {
    public Integer insert(Act_VO actVO);
    public void update(Act_VO actVO);

    public List<Act_VO> getAll();
    public ActFiestaVO getOne(Integer actID);
    
    public Set<ActMemVO> whosin(Integer actID);
    public Set<ActPOIVO> showthetags(Integer actID);
        
    public List<ActFiestaVO> getActByPOIID(Integer POIID);
    public List<ActFiestaVO> getActByDate(Timestamp actDate);
    public List<ActFiestaVO> getActByWks();
//    public List<ActVO> getActByDist(Integer Dist);
    public List<ActFiestaVO> getActByClub(Integer clubID);
	public List<ActFiestaVO> getAllFromNow();
}
