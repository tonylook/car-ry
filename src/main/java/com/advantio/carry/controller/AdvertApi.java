package com.advantio.carry.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/api")
public class AdvertApi {
	private final AdvertValidator advertValidator;

	@Autowired
	public AdvertApi(AdvertValidator advertValidator) {
		this.advertValidator = advertValidator;
	}
	@Autowired
	private AdvertDao advertDao;

	@PostMapping("/adverts")
	public ResponseEntity<?> insertAdvert(@Valid @RequestBody Advert advert, Errors errors) {
		advertValidator.validate(advert, errors);
		if (errors.hasErrors()) {
			return new ResponseEntity<>(advertValidator.createError(errors), HttpStatus.BAD_REQUEST);
		}else {
			advertDao.save(advert);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
	}

	@GetMapping("/adverts")
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

	@GetMapping("/adverts/{id}")
	public Advert getNoteById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
	    return advertDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Advert", "id", id));
	}

	@PatchMapping("/adverts")
	public ResponseEntity<?> editAdvert(@RequestBody Advert edit, Errors errors) {
		if(edit.getId()!=null) {
			Advert advert = new Advert();
			advert = advertDao.findById(edit.getId()).orElseThrow(() -> new ResourceNotFoundException("Advert", "id", edit.getId()));
			if(edit.getTitle()!=null) advert.setTitle(edit.getTitle());
			if(edit.getFuel()!=null) advert.setFuel(edit.getFuel());
			if(edit.getPrice()!=null) advert.setPrice(edit.getPrice());
			if(edit.getIsNew()!=null) advert.setIsNew(edit.getIsNew());
			if(edit.getMileage()!=null) advert.setMileage(edit.getMileage());
			if(edit.getFirstRegistration()!=null) advert.setFirstRegistration(edit.getFirstRegistration());
			advertValidator.validate(advert, errors);
			if (errors.hasErrors()) {
				return new ResponseEntity<>(advertValidator.createError(errors), HttpStatus.BAD_REQUEST);
			}else {
				advertDao.save(advert);
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>("message: Id field is mandatory in order to update",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@DeleteMapping("/adverts")
	public ResponseEntity<?> deleteAdvert(@RequestParam(value="id", required=true) Integer id) throws ResourceNotFoundException {
		advertDao.deleteById(id);
		if(advertDao.findById(id).isPresent()) {
			return new ResponseEntity<>("Due to some errors, advert was not removed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
