package com.clubMB.model;

import java.util.*;

public interface ClubMBDAO_interface {
          public void insert(ClubMBVO clubMBVO);
          public void update(ClubMBVO clubMBVO);
          public void delete(Integer clubMBID);
          public ClubMBVO findByPrimaryKey(Integer clubMBID);
          public List<ClubMBVO> getAll();
          public List<ClubMBVO> findByClubID(Integer clubID);

}
