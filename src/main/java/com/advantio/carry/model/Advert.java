package com.advantio.carry.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "adverts")

public class Advert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	private String title;

	@NotNull
	private String fuel;

	@NotNull
	private Integer price;

	@NotNull
	private Boolean isNew;

	private Integer mileage;

	@Temporal(TemporalType.DATE)
	private Date firstRegistration;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public Date getFirstRegistration() {
		return firstRegistration;
	}

	public void setFirstRegistration(Date firstRegistration) {
		this.firstRegistration = firstRegistration;
	}

	public Advert(Integer id, @NotNull String title, @NotNull String fuel, @NotNull Integer price,
			@NotNull Boolean isNew, Integer mileage, Date firstRegistration) {
		super();
		this.id = id;
		this.title = title;
		this.fuel = fuel;
		this.price = price;
		this.isNew = isNew;
		this.mileage = mileage;
		this.firstRegistration = firstRegistration;
	}

	public Advert() {
		super();
	}


}
