package com.advantio.carry.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.advantio.carry.exception.ResourceNotFoundException;
import com.advantio.carry.model.Advert;
import com.advantio.carry.model.AdvertValidator;
import com.advantio.carry.repository.AdvertDao;

@RestController
@CrossOrigin //CORS Enabled
@RequestMapping("/advertApi")
public class AdvertApi {
	private final AdvertValidator advertValidator;

	@Autowired
	public AdvertApi(AdvertValidator advertValidator) {
		this.advertValidator = advertValidator;
	}
	@Autowired
	private AdvertDao advertDao;

	@PostMapping("/insertAdvert")
	public ResponseEntity<?> insertAdvert(@Valid @RequestBody Advert advert, Errors errors) {
		advertValidator.validate(advert, errors);
		if (errors.hasErrors()) {
			return new ResponseEntity<>(advertValidator.createError(errors), HttpStatus.BAD_REQUEST);
		}else {
			advertDao.save(advert);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@GetMapping("/allAdverts")
	public List<Advert> allAdverts(@RequestParam(defaultValue="id", required=false) String sort) {
		switch (sort) {
		case "title":
			return advertDao.findAllByOrderByTitleAsc();
		case "fuel":
			return advertDao.findAllByOrderByFuelAsc();
		case "price":
			return advertDao.findAllByOrderByPriceAsc();
		case "isNew":
			return advertDao.findAllByOrderByIsNewAsc();
		case "mileage":
			return advertDao.findAllByOrderByMileageAsc();
		case "firstRegistration":
			return advertDao.findAllByOrderByFirstRegistrationAsc();
		default:
			return advertDao.findAllByOrderByIdAsc();
		}
	}
	
	@GetMapping("/advert/{id}")
	public Advert getNoteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
	    return advertDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Advert", "id", id));
	}
}
