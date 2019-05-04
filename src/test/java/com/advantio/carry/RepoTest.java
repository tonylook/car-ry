package com.advantio.carry;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
public class RepoTest {
	
@Autowired
private AdvertDao advertDao;

	@Test
	public void saveTest() throws ParseException {
		Advert advert = new Advert();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		advert.setFirstRegistration(sdf.parse("2000-28-03"));
		advert.setIsNew(false);
		advert.setMileage(125000);
		advert.setPrice(30000);
		advert.setTitle("Audi A4");
		advert.setFuel(Fuel.GASOLINE.name());
		advertDao.save(advert);
		
		
	}

}
