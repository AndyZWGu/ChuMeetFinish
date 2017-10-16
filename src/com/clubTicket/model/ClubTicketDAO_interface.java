package com.clubTicket.model;

import java.util.List;



public interface ClubTicketDAO_interface {

	public void insert(ClubTicketVO clubTicketVO);
	public void update(ClubTicketVO clubTicketVO);
	public void delete(Integer clubTkID);
	
	public ClubTicketVO findByPrimaryKey(Integer clubTkID);
	
	public List<ClubTicketVO> getAll();
	
}
