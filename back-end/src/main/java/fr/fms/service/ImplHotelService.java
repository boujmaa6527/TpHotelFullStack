package fr.fms.service;

import fr.fms.entity.City;
import fr.fms.entity.Hotel;
import fr.fms.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ImplHotelService implements IHotelService{

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();

    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Optional<Hotel> readHotel(Long id) {

        return hotelRepository.findById(id);
    }

    @Override
    public Hotel updateHotel(Long id, Hotel hotelUpdate) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new RuntimeException("Hotel Not found"));
        hotel.setName(hotelUpdate.getName());
        hotel.setPhone(hotelUpdate.getPhone());
        hotel.setAddress(hotelUpdate.getAddress());
        hotel.setImageUrl(hotelUpdate.getImageUrl());
        hotel.setNumberOfStar(hotelUpdate.getNumberOfStar());
        hotel.setPrice(hotelUpdate.getPrice());
        hotel.setTotalRoomOfAvailable(hotelUpdate.getTotalRoomOfAvailable());
        if(hotelUpdate.getCity() != null){
            hotel.setCity(hotelUpdate.getCity());
        }else {
            throw new RuntimeException("City not found");
        }
        return hotelRepository.save(hotel);

    }

    @Override
    public Optional<Hotel> getHotelById(Long id) {
        return hotelRepository.findById(id);
    }

    @Override
    public List<City> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities;
    }

    @Override
    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }
}
