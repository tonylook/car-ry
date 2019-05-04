package com.advantio.carry.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.advantio.carry.model.Advert;

public interface AdvertDao extends CrudRepository<Advert, Integer>{
    public List<Advert> findAllByOrderByIdAsc();
    public List<Advert> findAllByOrderByTitleAsc();
    public List<Advert> findAllByOrderByFuelAsc();
    public List<Advert> findAllByOrderByPriceAsc();
    public List<Advert> findAllByOrderByIsNewAsc();
    public List<Advert> findAllByOrderByMileageAsc();
    public List<Advert> findAllByOrderByFirstRegistrationAsc();
}
