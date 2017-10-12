package com.clubNews.model;

import java.util.*;

public interface ClubNewsDAO_interface {
          public void insert(ClubNewsVO clubNewsVO);
          public void update(ClubNewsVO clubNewsVO);
          public void delete(Integer clubNewsID);
          public ClubNewsVO findByPrimaryKey(Integer clubNewsID);
          public List<ClubNewsVO> getAll();
          
          //以下自己加
          public void deleteOneNews(ClubNewsVO clubNewsVO);
}
