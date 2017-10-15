package com.act.actPOI.model;

import java.util.List;

import com.act.act.model.Act_VO;

public interface ActPOIDAO_interface {
	//POI做標籤
    public List<String> getPOIByActID(Integer actID);
    //select by POI
    public List<Act_VO> getActByPOIID(Integer POIID);
    //++POI
    public void insert(ActPOIVO actPOIVO);
    //--POI
    public void delete(Integer actID);
    
    public List<ActPOIVO> mutate(Integer actID, List<Integer> poilist);

}
