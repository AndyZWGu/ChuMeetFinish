package com.member.model;

import java.util.List;

public interface RewardDAO_interface {
    public void insert(RewardVO rewardVO);
    public void update(RewardVO rewardVO);

    public void delete(Integer rewID);
    public RewardVO findByPrimaryKey(Integer rewID);
    public List<RewardVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<rewardVO> getAll(Map<String, String[]> map); 
}
