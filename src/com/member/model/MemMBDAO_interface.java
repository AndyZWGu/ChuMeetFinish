package com.member.model;

import java.util.List;
import java.util.Map;

public interface MemMBDAO_interface {
    public void insert(MemMBVO memMBVO);
    public void update(MemMBVO memMBVO);
    
    public void delete(Integer memID);
    
    public MemMBVO findByPrimaryKey(Integer memID);
    public List<MemMBVO> getAll();
    
    public void insertHomeMB(MemMBVO memMBVO);
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<MemMBVO> getAll(Map<String, String[]> map);
	public String findCountByPrimaryKey(Integer memID); 
}
