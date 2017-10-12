package com.AppAnn.model;

import java.util.*;

public interface AppAnnDAO_interface {
	public void insert(AppAnnVO appAnnVO);
	public void update(AppAnnVO appAnnVO);
	public void delete(Integer appAnnID);
	public AppAnnVO findByPrimaryKey(Integer appAnnId);
	public List<AppAnnVO>getAll();
}
