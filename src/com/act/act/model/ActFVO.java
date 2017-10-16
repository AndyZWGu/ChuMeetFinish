package com.act.act.model;

import java.util.HashSet;
import java.util.Set;

import com.act.actMem.model.ActMemVO;
import com.gen.tool.actCodeTrans;

public class ActFVO implements java.io.Serializable  {

	private String memName;
	private String actCnt;
	private ActVO actVO;
	
	public ActFVO() {
	}
	

	public ActFVO(ActVO actVO) {
		this.actVO = actVO;
		this.memName = actCodeTrans.whoRU(actVO.getMemID());
		this.actCnt = actCodeTrans.delHTMLTag(actVO.getActContent());
	}


	public String getMemName() {
		return memName;
	}


	public void setMemName(String memName) {
		this.memName = memName;
	}


	public String getActCnt() {
		return actCnt;
	}


	public void setActCnt(String actCnt) {
		this.actCnt = actCnt;
	}


	public ActVO getActVO() {
		return actVO;
	}


	public void setActVO(ActVO actVO) {
		this.actVO = actVO;
	}
	
	

}
