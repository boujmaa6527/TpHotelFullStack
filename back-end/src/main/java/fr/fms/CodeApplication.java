package fr.fms;

import fr.fms.entity.City;
import fr.fms.entity.Hotel;
import fr.fms.entity.Role;
import fr.fms.entity.Supervisor;
import fr.fms.repository.CityRepository;
import fr.fms.repository.HotelRepository;
import fr.fms.service.ImplAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class CodeApplication  implements CommandLineRunner {
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private ImplAccountService implAccountService;
	@Autowired
	private CityRepository cityRepository;



	public static void main(String[] args) {
		SpringApplication.run(CodeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataUsers();
		dataHotels();
	}
	private void dataUsers() {
		implAccountService.saveSupervisor(new Supervisor(null, "rachid@gmail.com", "12345", new ArrayList<>()));
		implAccountService.saveSupervisor(new Supervisor(null, "hugo@gmail", "12345", new ArrayList<>()));
		implAccountService.saveRole(new Role(null,  "SUPERVISOR"));
		implAccountService.saveRole(new Role(null, "GESTIONNAIRE"));
		implAccountService.addRoleToSupervisor("rachid@gmail.com", "SUPERVISOR");
		implAccountService.addRoleToSupervisor("hugo@gmail.com", "GESTIONNAIRE");
		implAccountService.addRoleToSupervisor("rachid", "GESTIONNAIRE");

	}
	private void dataHotels(){
		City paris = cityRepository.save(new City(null, "Paris",null, null));
		City lyon = cityRepository.save( new City(null, "Lyon",null, null));
		City rouen = cityRepository.save( new City(null, "Rouen",null, null));

		Hotel hotel1 = hotelRepository.save(new Hotel(null, "Ibis", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,paris ));
		Hotel hotel2 =  hotelRepository.save( new Hotel(null, "Premiere class", "0123456789", "3 rue de paris", "http://localhost:8080/images/1classe.jpg", 3, 10, 50,paris));
		Hotel hotel3 = hotelRepository.save( new Hotel(null, "Ibis Budget", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis2.jpg", 3, 10, 50,paris ));
		Hotel hotel4 = hotelRepository.save( new Hotel(null, "Ibis class", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,paris ));
		Hotel hotel5 = hotelRepository.save( new Hotel(null, "Campanil", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,paris ));
		Hotel hotel16 = hotelRepository.save( new Hotel(null, "Campanil", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,paris ));
		Hotel hotel6 = hotelRepository.save( new Hotel(null, "F1", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,lyon ));
		Hotel hotel17 = hotelRepository.save( new Hotel(null, "F1", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,lyon ));
		Hotel hotel7 = hotelRepository.save( new Hotel(null, "Novotel", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,lyon ));
		Hotel hotel8 = hotelRepository.save(new Hotel(null, "Ibis Styles", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,lyon ));
		Hotel hotel9 = hotelRepository.save(new Hotel(null, "B&B Hotel", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,lyon ));
		Hotel hotel10 = hotelRepository.save(new Hotel(null, "Ibis belle Vue", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,lyon ));
		Hotel hotel11 = hotelRepository.save(new Hotel(null, "Best Hotel", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,rouen ));
		Hotel hotel12 = hotelRepository.save(new Hotel(null, "Ibis Budget", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,rouen ));
		Hotel hotel13 = hotelRepository.save(new Hotel(null, "Ibis Confor", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,rouen ));
		Hotel hotel14 = hotelRepository.save(new Hotel(null, "Appart Hotel ", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,rouen));
		Hotel hotel15 = hotelRepository.save(new Hotel(null, "Enzo Hotel", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,rouen ));
		Hotel hotel19 = hotelRepository.save(new Hotel(null, "Enzo Hotel", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 10, 50,rouen ));

	}
}
