package com.admPril.model;

import java.util.*;

public interface AdmPrilDAO_interface {
		public void insert(AdmPrilVO admPrilVO);
		public void update(AdmPrilVO admPrilVO);
		public void delete(Integer admPrilID);
		public AdmPrilVO findByPrimaryKey(Integer admPrilID);
		public List<AdmPrilVO> getAll();
		public List<AdmPrilVO> status2();
		public List<AdmPrilVO> findByAdminID(Integer adminID);
}
