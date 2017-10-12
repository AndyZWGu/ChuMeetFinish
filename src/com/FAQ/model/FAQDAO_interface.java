package com.FAQ.model;

import java.util.*;

public interface FAQDAO_interface {
	public void insert(FAQVO faqVO);
	public void update(FAQVO faqVO);
	public void delete(Integer faqID);
	public FAQVO findByPrimaryKey(Integer faqID);
	public List<FAQVO>getAll();
	

}
