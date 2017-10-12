package com.clubImg.model;

import java.util.*;
import com.clubImg.model.ClubImgVO;

public interface ClubImgDAO_interface {
	      public void insert(ClubImgVO clubImgVO);
          public void update(ClubImgVO clubImgVO);
//          public void delete(Integer clubImgVO);
          public ClubImgVO findByPrimaryKey(Integer clubImgID);
	      public List<ClubImgVO> getAll();
	      //查詢某部門的員工(一對多)(回傳 Set)
	      //public Set<ClubImg> getEmpsByDeptno(Integer clubImgID);
	      
	      //以下自己加
          public void deleteOneImg(ClubImgVO clubImgVO);
}
