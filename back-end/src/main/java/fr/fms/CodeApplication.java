package fr.fms;

import fr.fms.entity.City;
import fr.fms.entity.Hotel;
import fr.fms.entity.Role;
import fr.fms.entity.User;
import fr.fms.repository.CityRepository;
import fr.fms.repository.HotelRepository;
import fr.fms.service.ImplAccountService;
import fr.fms.service.ImplUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodeApplication  implements CommandLineRunner {
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private ImplAccountService implAccountService;
	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private ImplUserService implUserService;


	public static void main(String[] args) {
		SpringApplication.run(CodeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataUsers();
		dataHotels();
	}
	private void dataUsers() {
		Role admin = implAccountService.saveRole(new Role(null, "ADMIN"));
		Role user = implAccountService.saveRole(new Role(null, "USER"));

		implUserService.saveUser(new User(null, "rachid@gmail.com", "12345", "Rachid", admin));
		implUserService.saveUser(new User(null, "hugo@gmail.com", "12345", "Hugo", user));


	}
	private void dataHotels(){
		City paris = cityRepository.save(new City(null, "Paris","Ville Lumière", null));
		City lyon = cityRepository.save( new City(null, "Lyon","stade gerland", null));
		City rouen = cityRepository.save( new City(null, "Rouen"," la ville de la jeunesse", null));

		Hotel hotel1 = hotelRepository.save(new Hotel(null, "Ibis", "0123456789", "3 rue de paris", "http://localhost:8080/images/ibis.jpg", 3, 100, 50,paris ));
		Hotel hotel2 =  hotelRepository.save( new Hotel(null, "Premiere class", "0123456789", "30 rue de grenoble", "http://localhost:8080/images/1classe.jpg", 4, 50, 50,paris));
		Hotel hotel3 = hotelRepository.save( new Hotel(null, "Ibis Budget", "0123456789", "23 rue de bordeaux", "http://localhost:8080/images/ibis2.jpg", 5, 60, 50,paris ));
		Hotel hotel4 = hotelRepository.save( new Hotel(null, "Ibis class", "0123456789", "11 rue de nice", "http://localhost:8080/images/ibis.jpg", 3, 120, 50,paris ));
		Hotel hotel5 = hotelRepository.save( new Hotel(null, "Campanil", "0123456789", "12 rue de vernon", "http://localhost:8080/images/ibis.jpg", 4, 70, 50,paris ));
		Hotel hotel16 = hotelRepository.save( new Hotel(null, "Campanil", "0123456789", "15 rue de mulhouse", "http://localhost:8080/images/ibis.jpg", 5, 60, 50,paris ));
		Hotel hotel6 = hotelRepository.save( new Hotel(null, "F1", "0123456789", "7 rue de la victoire", "http://localhost:8080/images/ibis.jpg", 3, 60, 50,lyon ));
		Hotel hotel17 = hotelRepository.save( new Hotel(null, "F1", "0123456789", "14 rue du palais", "http://localhost:8080/images/ibis.jpg", 4, 80, 50,lyon ));
		Hotel hotel7 = hotelRepository.save( new Hotel(null, "Novotel", "0123456789", "43 rue de ibos", "http://localhost:8080/images/ibis.jpg", 5, 30, 50,lyon ));
		Hotel hotel8 = hotelRepository.save(new Hotel(null, "Ibis Styles", "0123456789", "14 rue de gauguin", "http://localhost:8080/images/ibis.jpg", 3, 50, 50,lyon ));
		Hotel hotel9 = hotelRepository.save(new Hotel(null, "B&B Hotel", "0123456789", "13 rue de la marniere riga", "http://localhost:8080/images/ibis.jpg", 4, 90, 50,lyon ));
		Hotel hotel10 = hotelRepository.save(new Hotel(null, "Ibis belle Vue", "0123456789", "9 ruejeran giraudoux", "http://localhost:8080/images/ibis.jpg", 5, 50, 50,lyon ));
		Hotel hotel11 = hotelRepository.save(new Hotel(null, "Best Hotel", "0123456789", "16 rue de lille", "http://localhost:8080/images/ibis.jpg", 4, 60, 50,rouen ));
		Hotel hotel12 = hotelRepository.save(new Hotel(null, "Ibis Budget", "0123456789", "19 rue de nice", "http://localhost:8080/images/ibis.jpg", 3, 70, 50,rouen ));
		Hotel hotel13 = hotelRepository.save(new Hotel(null, "Ibis Confor", "0123456789", "25 rue de saint jean", "http://localhost:8080/images/ibis.jpg", 5, 10, 50,rouen ));
		Hotel hotel14 = hotelRepository.save(new Hotel(null, "Appart Hotel ", "0123456789", "15 rue de rouen", "http://localhost:8080/images/ibis.jpg", 3, 80, 50,rouen));
		Hotel hotel15 = hotelRepository.save(new Hotel(null, "Enzo Hotel", "0123456789", "31 rue de poterie", "http://localhost:8080/images/ibis.jpg", 4, 80, 50,rouen ));
		Hotel hotel19 = hotelRepository.save(new Hotel(null, "Enzo Hotel", "0123456789", "14 rue de louviers", "http://localhost:8080/images/ibis.jpg", 2, 90, 50,rouen ));

	}
}
