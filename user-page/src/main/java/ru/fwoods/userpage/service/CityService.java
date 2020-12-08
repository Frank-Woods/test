package ru.fwoods.userpage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fwoods.entities.City;
import ru.fwoods.userpage.repository.CityRepository;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long city) {
        return cityRepository.getOne(city);
    }
}
