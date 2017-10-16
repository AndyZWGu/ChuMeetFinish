package com.imgTicket.model;

import java.util.List;

public interface ImgTicketDAO_interface {

		public void insert(ImgTicketVO imgTicketVO);
		public void update(ImgTicketVO imgTicketVO);
		public void delete(Integer imgTkID);
		public ImgTicketVO findByPrimaryKey(Integer imgTkID);
		public List<ImgTicketVO> getAll();
}
