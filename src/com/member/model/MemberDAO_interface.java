package com.member.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface MemberDAO_interface {
	//管理員用
    public void insert(MemberVO memberVO);
    public void update(MemberVO memberVO);
    public void delete(Integer memID);
    public MemberVO findByPrimaryKey(Integer memID);
    //會員用
    public MemberVO findByMemEmail(String memEmail);
    public MemberVO findByMemPw(String memPw);
    //會員拿照片用
//    public List<byte[]> getAvatarByMemID(Integer memID);//base64作法
    public ResultSet getAvatarByMemID(Integer memID);
    //會員註冊用
//    public void registerMember(MemberVO memberVO);
//    public void updateInfo(MemberVO memberVO);
    
    
    
    
    public List<MemberVO> getAll();
    //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<MemberVO> getAll(Map<String, String[]> map); 

}
