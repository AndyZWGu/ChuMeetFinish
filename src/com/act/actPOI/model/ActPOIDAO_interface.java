package com.act.actPOI.model;

import java.util.List;

public interface ActPOIDAO_interface {
	//POI做標籤
    public List<Integer> getPOIByActID(Integer actID);

    //++POI
    public void insert(Integer actID, Integer POIID);
    //--POI
    public void delete(Integer actID);

}
