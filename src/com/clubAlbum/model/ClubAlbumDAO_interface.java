package com.clubAlbum.model;

import java.util.*;

import com.clubImg.model.ClubImgVO;

public interface ClubAlbumDAO_interface {
          public void insert(ClubAlbumVO clubAlbumVO);
          public void update(ClubAlbumVO clubAlbumVO);
          //public void delete(Integer clubAlbumID);
          public ClubAlbumVO findByPrimaryKey(Integer clubAlbumID);
          public List<ClubAlbumVO> getAll();
        //自己加  
		public List<ClubImgVO> getClubImgByClubAlbumID(Integer clubAlbumID);

}
