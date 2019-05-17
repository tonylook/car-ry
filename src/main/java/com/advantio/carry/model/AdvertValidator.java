package com.advantio.carry.model;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

@Component
public class AdvertValidator {
	public void validate(Advert advert, Errors errors) {
		
		try {
			Fuel fuel = Fuel.valueOf(advert.getFuel());
		} catch (IllegalArgumentException e) {
			errors.reject("Fuel Type is not supported");
		}
		if (!advert.getnewIs()) {
			if (advert.getMileage()==null || advert.getFirstRegistration()==null) {
				errors.reject("When Car is not new, Mileage and First Registration fields are mandatory");
			}
		}else {
			if (advert.getMileage()!=null || advert.getFirstRegistration()!=null) {
				errors.reject("When Car is new, Mileage and First Registration fields must not be available");
			}
		}
	}

	public String createError(Errors errors) {
		return errors.getAllErrors().stream().map(ObjectError::toString).collect(Collectors.joining(","));
	}
}