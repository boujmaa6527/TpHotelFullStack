package fr.fms.service;

import fr.fms.entity.City;
import fr.fms.entity.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IHotelService {

    List<Hotel> getAllHotels();

    void deleteHotel(Long Id);

    Hotel saveHotel(Hotel hotel);

    Optional<Hotel> readHotel(Long id);

    public Hotel updateHotel(Long id, Hotel hotel);

    Optional<Hotel> getHotelById(Long id);

    List<City> getAllCities();

    Optional<City> getCityById(Long id);

    City saveCity(City city);




}
