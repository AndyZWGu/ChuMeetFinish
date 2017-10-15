package com.member.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface MemberDAO_interface {
	//绠＄悊鍝＄敤
    public void insert(MemberVO memberVO);
    public void update(MemberVO memberVO);
    public void delete(Integer memID);
    public MemberVO findByPrimaryKey(Integer memID);
    //鏈冨摗鐢�
    public MemberVO findByMemEmail(String memEmail);
    public MemberVO findByMemPw(String memPw);
    //鏈冨摗鎷跨収鐗囩敤
//    public List<byte[]> getAvatarByMemID(Integer memID);//base64浣滄硶
    public ResultSet getAvatarByMemID(Integer memID);
    //鏈冨摗瑷诲唺鐢�
//    public void registerMember(MemberVO memberVO);
//    public void updateInfo(MemberVO memberVO);
    
    
    
    
    public List<MemberVO> getAll();
    //钀敤瑜囧悎鏌ヨ(鍌冲叆鍙冩暩鍨嬫厠Map)(鍥炲偝 List)
    public List<MemberVO> getAll(Map<String, String[]> map);
	List<MemberVO> getAllByHot(); 

}
