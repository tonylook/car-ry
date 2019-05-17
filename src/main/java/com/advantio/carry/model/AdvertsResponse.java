package com.advantio.carry.model;

import java.util.List;

public class AdvertsResponse {
	private List <Advert> adverts;

	public AdvertsResponse(List<Advert> adverts) {
		super();
		this.adverts = adverts;
	}
	
	public List<Advert> getAdverts() {
		return adverts;
	}

	public void setAdverts(List<Advert> adverts) {
		this.adverts = adverts;
	}
}
