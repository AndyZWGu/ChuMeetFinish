package com.act.actPOI.model;

import java.util.List;
public class ActPOIService {

		private ActPOIDAO_interface dao;

		public ActPOIService() {
			dao = new ActPOIDAO();
		}


	    //++POI
	    public void insert(Integer actID, Integer POIID){
	    	dao.insert(actID, POIID);
	    };
	    //--POI

		
		public List<Integer> getPOIByActID(Integer actID) {
			return dao.getPOIByActID(actID);
		}

		public void delete(Integer actID) {
			dao.delete(actID);
		}
	}
