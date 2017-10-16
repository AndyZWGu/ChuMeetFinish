package com.mbTicket.model;

import java.util.List;


public interface  MbTicketDAO_interface {
	public void insert(MbTicketVO mbTicketVO);
	public void update(MbTicketVO mbTicketVO);
	public void delete(Integer mbTkID);
	
	public MbTicketVO findByPrimaryKey(Integer mbTkID);
	
	public List<MbTicketVO> getAll();
	
}
