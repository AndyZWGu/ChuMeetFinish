package com.ad.model;

import java.util.List;



public class AdService {
	private AdDAO_interface dao;
	public AdService() {
		dao = new AdDAO();
	}

	public AdVO addAd(Integer adminID,String adName,byte[] adImg,String adContent,String adLink,java.sql.Timestamp adDate	) {

		AdVO adVO = new AdVO();

		
		adVO.setAdminID(adminID);
		adVO.setAdName(adName);
		adVO.setAdImg(adImg);
		adVO.setAdContent(adContent);
		adVO.setAdLink(adLink);
		adVO.setAdDate(adDate);
		dao.insert(adVO);
		//adID,adminID,adName,adImg,adContent,adLink,adDate	
		return adVO;
	}

	// �w�d�� Struts 2 �Ϊ�
	public void addAd(AdVO adVO) {
		dao.insert(adVO);
	}

	public AdVO updateAd(Integer adID,Integer adminID,String adName,byte[] adImg,String adContent,String adLink,java.sql.Timestamp adDate	) {

		AdVO adVO = new AdVO();

		adVO.setAdID(adID);
		adVO.setAdminID(adminID);
		adVO.setAdName(adName);
		adVO.setAdImg(adImg);
		adVO.setAdContent(adContent);
		adVO.setAdLink(adLink);
		adVO.setAdDate(adDate);
		dao.insert(adVO);

		dao.update(adVO);

		return dao.findByPrimaryKey(adID);
	}

	// �w�d�� Struts 2 �Ϊ�
	public void updateAd(AdVO adVO) {
		dao.update(adVO);
	}

	public void deleteAd(Integer adID) {
		dao.delete(adID);
	}

	public AdVO getOneAd(Integer adID) {
		return dao.findByPrimaryKey(adID);
	}

	public List<AdVO> getAll() {
		return dao.getAll();
	}

	

	
}