package com.poi.model;

import java.util.List;

public class POI_service {
	private POI_interface dao;
	public POI_service() {
	dao = new POI_DAO();
}

public List<POIVO> getAll() {
	return dao.getAll();
};
}
