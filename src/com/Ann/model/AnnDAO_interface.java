package com.Ann.model;

import java.util.*;

public interface AnnDAO_interface {
	public void insert(AnnVO annVO);
	public void update(AnnVO annVO);
	public void delete(Integer annID);
	public AnnVO findByPrimaryKey(Integer annID);
	public List<AnnVO> getAll();
	

}
