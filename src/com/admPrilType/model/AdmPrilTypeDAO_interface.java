package com.admPrilType.model;

import java.util.*;

public interface AdmPrilTypeDAO_interface {
	public void insert(AdmPrilTypeVO admPrilTypeVO);
	public void update(AdmPrilTypeVO admPrilTypeVO);
	public void delete(Integer admPrilID);
	public AdmPrilTypeVO findByPrimaryKey(Integer admPrilID);
	
	public List<AdmPrilTypeVO> getAll();
	public List<AdmPrilTypeVO> statusname();
}
