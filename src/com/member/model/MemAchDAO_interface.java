package com.member.model;

import java.util.List;

public interface MemAchDAO_interface {
    public void insert(MemAchVO memAchVO);
    public void update(MemAchVO memAchVO);
    public void delete(Integer memID);
    public MemAchVO findByPrimaryKey(Integer memID);
    public List<MemAchVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<memAchVO> getAll(Map<String, String[]> map); 
}
