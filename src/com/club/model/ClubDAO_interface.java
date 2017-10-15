package com.club.model;

import java.util.*;

import com.clubAlbum.model.ClubAlbumVO;
import com.clubMB.model.ClubMBVO;
import com.clubMem.model.ClubMemVO;
import com.clubNews.model.ClubNewsVO;



public interface ClubDAO_interface {
//闈炶嚜澧炰富閸电増
//          public void insert(ClubVO clubVO);
          
          public Integer insert(ClubVO clubVO);
          
          public void update(ClubVO clubVO);
          public void delete(Integer clubID);
          public ClubVO findByPrimaryKey(Integer clubID);
          public List<ClubVO> getAll();

 
          //鑷繁鍔犵敱绀惧湗绶ㄨ櫉鏌ヨ鎵�鏈夌浉绨�
          public List<ClubAlbumVO> getClubAlbumsByClubID(Integer clubID);
          //鑷繁鍔犵敱绀惧湗绶ㄨ櫉鏌ヨ鎵�鏈夋秷鎭�
          public List<ClubNewsVO> getClubNewsByClubID(Integer clubID);
          //鑷繁鍔犵敱绀惧湗绶ㄨ櫉鏌ヨ鎵�鏈夌暀瑷�
          public List<ClubMBVO> getClubMBByClubID(Integer clubID);
          //鑷繁鍔犵敱绀惧湗绶ㄨ櫉鏌ヨ鎵�鏈夌ぞ鍦樻垚鍝�
          public List<ClubMemVO> getClubMemByClubID(Integer clubID);
          //淇敼绀惧湗
          public void clubChange(ClubVO clubVO);

          public void clubChangeIfNotPhoto(ClubVO clubVO);
          
          public void clubChangeClubMem(ClubVO clubVO);

          public void deleteClub(ClubVO clubVO);
}
