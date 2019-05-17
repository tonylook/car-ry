package com.advantio.carry.model;

public class AdvertRequest {
	private Advert advert;

	public Advert getAdvert() {
		return advert;
	}

	public void setAdvert(Advert advert) {
		this.advert = advert;
	}

	public AdvertRequest(Advert advert) {
		super();
		this.advert = advert;
	}

	public AdvertRequest() {
		super();
	}
	
}
