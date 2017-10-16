package com.memTicket.model;

import java.util.List;



public  interface MemTicketDAO_interface {
	public void insert(MemTicketVO MemTicketVO);
	public void update(MemTicketVO MemTicketVO);
	public void delete(Integer memTkID);
	
	public MemTicketVO findByPrimaryKey(Integer memTkID);
	
	public List<MemTicketVO> getAll();
	
}
