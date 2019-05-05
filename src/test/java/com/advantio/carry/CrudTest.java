package com.advantio.carry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.advantio.carry.model.Advert;
import com.advantio.carry.model.Fuel;
import com.advantio.carry.repository.AdvertDao;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CrudTest {
	
@Autowired
private AdvertDao advertDao;
DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;


	@Test
	public void acreateTest() throws ParseException {
		Advert advert = new Advert();
		advert.setFirstRegistration(LocalDate.parse("2000-03-28", dtf));
		advert.setIsNew(false);
		advert.setMileage(125000);
		advert.setPrice(30000);
		advert.setTitle("Audi A4");
		advert.setFuel(Fuel.GASOLINE.name());
		advertDao.save(advert);
	}
	
	@Test
	public void breadTest() throws ParseException {

		Optional<Advert> value = advertDao.findById(1);
		Advert advert = new Advert();

		if(value.isPresent()) advert = value.get();
		
		assertTrue("No record found for this id", value.isPresent());
		assertTrue("The record found is not correct", advert.getPrice().equals(Integer.valueOf(30000))); 
		assertTrue("The record found is not correct",advert.getIsNew().equals(Boolean.valueOf(false)));
		assertTrue("The record found is not correct",advert.getFirstRegistration().equals(LocalDate.parse("2000-03-28", dtf)));
		assertTrue("The record found is not correct",advert.getFuel().equals(String.valueOf(Fuel.GASOLINE.name())));
	}
	
	@Test
	public void cupdateTest() throws ParseException {

		Optional<Advert> value = advertDao.findById(1);
		Advert advert = new Advert();

		if(value.isPresent()) {
			advert = value.get();
			advert.setMileage(130000);
			advert.setPrice(25000);
			advert.setTitle("Audi A4 Sedan");
			advertDao.save(advert);
			value = advertDao.findById(1);
			advert = value.get();
		}
		
		assertTrue("No record to update was found for this id", value.isPresent());
		assertTrue("The record found was not updated", advert.getPrice().equals(Integer.valueOf(25000))); 
		assertTrue("The record found was not updated", advert.getMileage().equals(Integer.valueOf(130000))); 
		assertTrue("The record found is not correct",advert.getIsNew().equals(Boolean.valueOf(false)));
		assertTrue("The record found was not updated",advert.getTitle().equals(String.valueOf("Audi A4 Sedan")));
		assertTrue("The record found is not correct",advert.getFirstRegistration().equals(LocalDate.parse("2000-03-28", dtf)));
		assertTrue("The record found is not correct",advert.getFuel().equals(String.valueOf(Fuel.GASOLINE.name())));
	}
	
	@Test
	public void deleteTest() throws ParseException {
		Optional<Advert> value = advertDao.findById(1);
		Advert advert = new Advert();

		if(value.isPresent()) {
			advert = value.get();
			advertDao.delete(advert);
		}
		assertTrue("No record to delete was found for this id", value.isPresent());
		
		value = advertDao.findById(1);
		assertFalse("Record not deleted", value.isPresent());

	}
}
