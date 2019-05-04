package com.advantio.carry.model;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

@Component
public class AdvertValidator {
    public void validate(Advert advert, Errors errors) {
        if (!advert.getIsNew()) {
            if (advert.getMileage()==null || advert.getFirstRegistration()==null) {
                errors.reject("When Car is not new, Mileage and First Registration fields are mandatory");
            }
        }
    }
    
    public String createError(Errors errors) {
        return errors.getAllErrors().stream().map(ObjectError::toString).collect(Collectors.joining(","));
    }
}