package com.act.actPOI.model;

import java.util.List;

import com.act.act.model.Act_VO;

public class ActPOIService {

		private ActPOIDAO_interface dao;

		public ActPOIService() {
			dao = new ActPOIDAO();
		}


	    //++POI
	    public void insert(ActPOIVO actPOIVO){
	    	
	    };
	    //--POI

		
		public List<String> getPOIByActID(Integer actID) {
			return dao.getPOIByActID(actID);
		}

		public  List<Act_VO> getActByPOIID(Integer POIID) {
			return dao.getActByPOIID(POIID);
		}

		public void delete(Integer actID) {
			dao.delete(actID);
		}
	}
