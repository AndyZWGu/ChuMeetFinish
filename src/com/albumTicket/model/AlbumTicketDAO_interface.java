package com.albumTicket.model;

import java.util.List;



public interface AlbumTicketDAO_interface {
	public void insert(AlbumTicketVO albumTicketVO);
	public void update(AlbumTicketVO albumTicketVO);
	public void delete(Integer albumTkID);
	
	public AlbumTicketVO findByPrimaryKey(Integer albumTkID);
	
	public List<AlbumTicketVO> getAll();
}
