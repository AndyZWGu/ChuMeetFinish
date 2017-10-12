package com.member.model;

import java.util.List;

public interface MemRewardDAO_interface {
    public void insert(MemRewardVO memRewardVO);
    public void update(MemRewardVO memRewardVO);
    
    public void delete(Integer memRewID);
    public MemRewardVO findByPrimaryKey(Integer memRewID);
    public List<MemRewardVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<memRewardVO> getAll(Map<String, String[]> map); 
}
