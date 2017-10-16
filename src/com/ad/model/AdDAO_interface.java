package com.ad.model;

import java.util.List;



public interface AdDAO_interface {
	
		public void insert(AdVO adVO);
		public void update(AdVO adVO);
		public void delete(Integer adID);
		public AdVO findByPrimaryKey(Integer adID);
		public List<AdVO> getAll();
		

	}


