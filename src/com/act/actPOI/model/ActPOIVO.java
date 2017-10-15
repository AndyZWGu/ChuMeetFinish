package com.act.actPOI.model;

import com.act.act.model.Act_VO;
import com.poi.model.POIVO;

public class ActPOIVO implements java.io.Serializable  {
	
	private Act_VO actVO;
	private POIVO POIVO;
	
	public Act_VO getActVO() {
		return actVO;
	}
	public void setActVO(Act_VO actVO) {
		this.actVO = actVO;
	}
	public POIVO getPOIVO() {
		return POIVO;
	}
	public void setPOIVO(POIVO pOIVO) {
		POIVO = pOIVO;
	}
	
}
