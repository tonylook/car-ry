package com.advantio.carry;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.advantio.carry.model.Advert;
import com.advantio.carry.model.Fuel;
import com.advantio.carry.repository.AdvertDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SortTest {

	@Autowired
	private AdvertDao advertDao;
	DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

	@Before
	public void apopulate() throws ParseException {
		Advert advert1 = new Advert();
		Advert advert2 = new Advert();
		Advert advert3 = new Advert();

		advert1.setFirstRegistration(LocalDate.parse("2015-03-28", dtf));
		advert1.setnewIs(false);
		advert1.setMileage(125000);
		advert1.setPrice(30000);
		advert1.setTitle("Audi A4");
		advert1.setFuel(Fuel.GASOLINE.name());
		advertDao.save(advert1);
		advert2.setFirstRegistration(LocalDate.parse("2017-01-23", dtf));
		advert2.setnewIs(false);
		advert2.setMileage(5000);
		advert2.setPrice(34250);
		advert2.setTitle("Toyota CHR");
		advert2.setFuel(Fuel.DIESEL.name());
		advertDao.save(advert2);
		advert3.setnewIs(true);
		advert3.setPrice(1280300);
		advert3.setTitle("Pagani Huayara");
		advert3.setFuel(Fuel.GASOLINE.name());
		advertDao.save(advert3);
	}

	@Test
	public void bidSortTest() throws ParseException {

		List<Advert> adverts = advertDao.findAllByOrderByIdAsc();
		Advert advert = new Advert();
		advert = adverts.get(0);

		assertTrue("The record found is not correct", advert.getId().equals(Integer.valueOf(2))); 
	}

	@Test
	public void cfirstRegistrationTest() throws ParseException {

		List<Advert> adverts = advertDao.findAllByOrderByFirstRegistrationAsc();
		Advert advert = new Advert();
		advert = adverts.get(0);

		assertTrue("The record found is not correct",advert.getFirstRegistration()==null);
	}

	@Test
	public void dnewIsTest() throws ParseException {

		List<Advert> adverts = advertDao.findAllByOrderByNewIsAsc();
		Advert advert = new Advert();
		advert = adverts.get(0);

		assertTrue("The record found is not correct",advert.getnewIs().equals(Boolean.valueOf(false)));
	}

	@Test
	public void emileageTest() throws ParseException {

		List<Advert> adverts = advertDao.findAllByOrderByMileageAsc();
		Advert advert = new Advert();
		advert = adverts.get(0);

		assertTrue("The order is not correct",advert.getTitle().equals(String.valueOf("Pagani Huayara")));
	}

	@Test
	public void fpriceTest() throws ParseException {

		List<Advert> adverts = advertDao.findAllByOrderByPriceAsc();
		Advert advert = new Advert();
		advert = adverts.get(0);

		assertTrue("The record found is not correct", advert.getPrice().equals(Integer.valueOf(30000))); 
	}

	@Test
	public void gtitleTest() throws ParseException {

		List<Advert> adverts = advertDao.findAllByOrderByTitleAsc();

		Advert advert = new Advert();
		advert = adverts.get(0);
		assertTrue("The order is not correct",advert.getTitle().equals(String.valueOf("Audi A4")));
	}

	@Test
	public void hfuelTest() throws ParseException {

		List<Advert> adverts = advertDao.findAllByOrderByFuelAsc();
		Advert advert = new Advert();
		advert = adverts.get(0);
		assertTrue("The order is not correct",advert.getFuel().equals(String.valueOf(Fuel.DIESEL.name())));
	}
}
