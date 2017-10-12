package com.member.model;

import java.util.List;
import java.util.Map;


public interface MemMailDAO_interface {
    public void insert(MemMailVO memMailVO);
    public void update(MemMailVO memMailVO);
    
    public void delete(Integer memMailID);
    public MemMailVO findByPrimaryKey(Integer memMailID);
    public List<MemMailVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<MemMailVO> getAll(Map<String, String[]> map); 
}
