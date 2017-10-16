package com.actTicket.model;

import java.util.List;



public interface ActTicketDAO_interface {
	public void insert(ActTicketVO actTicketVO);
	public void update(ActTicketVO actTicketVO);
	public void delete(Integer actTkID);
	
	public ActTicketVO findByPrimaryKey(Integer actTkID);
	
	public List<ActTicketVO> getAll();
	
}

