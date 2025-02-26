package fr.fms.controller;


import fr.fms.entity.City;
import fr.fms.entity.Hotel;
import fr.fms.repository.HotelRepository;
import fr.fms.service.ImplHotelService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Slf4j
@Transactional
@RequestMapping("/api")
public class HotelController {

    @Autowired
    private ImplHotelService implHotelService;

    @Autowired
    private HotelRepository hotelRepository;

    private  static final Logger logger = (Logger) LoggerFactory.getLogger(HotelController.class);
    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>>  allHotels(){
        List<Hotel> hotels = implHotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }
    @PostMapping("/hotels")
    public ResponseEntity<Hotel> createHotel(@RequestParam("name") String name,
                                             @RequestParam("phone") String phone,
                                             @RequestParam("address") String address,
                                             @RequestParam("numberOfStar") double numberStar,
                                             @RequestParam("totalRoomOfAvailable") double totalRoom,
                                             @RequestParam("price") double price,
                                             @RequestParam("city") Long cityId,
                                             @RequestParam(value = "file", required = false)MultipartFile file){
        System.out.println("Received hotel creation request");
        logger.info("Received hotel creation");
        Optional<City> city = implHotelService.getCityById(cityId);
        if(!city.isPresent()){
            logger.info("City not Found for id " +cityId);
            System.out.println("City not Found for id " +cityId);
            return ResponseEntity.badRequest().build();
        }
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setPhone(phone);
        hotel.setAddress(address);
        hotel.setNumberOfStar(numberStar);
        hotel.setTotalRoomOfAvailable(totalRoom);
        hotel.setPrice(price);
        hotel.setCity(city.get());
        if(file != null && !file.isEmpty()){
            try {
                String fileName = file.getOriginalFilename();
                String uploadDir ="src/main/resources/static/images/";
                Path path = Paths.get(uploadDir +fileName);
                Files.createDirectories(path.getParent());
                file.transferTo(path);
                hotel.setImageUrl("http://localhost:8080/images/" + fileName);
            } catch (IOException e) {
                logger.info("Error while saving image" + e.getMessage());
                System.out.println("Error while saving image" + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        Hotel saveHotel = implHotelService.saveHotel(hotel);
        logger.info("Hotel saved successfully: " + saveHotel.getName());
        System.out.println("Hotel saved successfully: " + saveHotel.getName());
        return ResponseEntity.ok(saveHotel);
    }


    @GetMapping("hotels/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("id") Long id){
        Optional<Hotel> hotel = implHotelService.getHotelById(id);
        if(hotel.isPresent()){
           return new ResponseEntity<>(hotel.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    //@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotel);
    }
    @PutMapping("/hotels/{id}")
    //@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id,
                                                @RequestParam("name") String name,
                                                @RequestParam("phone") String phone,
                                                @RequestParam("address") String address,
                                                @RequestParam("numberOfStar") double numberStar,
                                                @RequestParam("totalRoomOfAvailable") double totalRoom,
                                                @RequestParam("price") double price,
                                                @RequestParam("city") Long cityId,
                                                @RequestParam(value = "file", required = false)MultipartFile file) {

        Optional<Hotel> existingHotel = implHotelService.readHotel(id);
        Optional<City> city = implHotelService.getCityById(cityId);

        if (!existingHotel.isPresent() || !city.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setPhone(phone);
        hotel.setAddress(address);
        hotel.setNumberOfStar(numberStar);
        hotel.setTotalRoomOfAvailable(totalRoom);
        hotel.setPrice(price);
        hotel.setCity(city.get());

        if (file != null && !file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                String uploadDir = "src/main/resources/static/images/";
                Path path = Paths.get(uploadDir + fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());

                hotel.setImageUrl("http://localhost:8080/images/" + fileName);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }

        Hotel updatedHotel = implHotelService.saveHotel(hotel);
        return ResponseEntity.ok(updatedHotel);
    }
    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable("id") Long id) {
        Optional<Hotel> HotelOpt = implHotelService.readHotel(id);

        if (!HotelOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Hotel hotel = HotelOpt.get();
        String imageUrl = hotel.getImageUrl();

        // Supprimer l'image associée
        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                // Extraire le nom du fichier depuis l'URL de l'image
                String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
                Path imagePath = Paths.get("src/main/resources/static/images/" + fileName);

                if (Files.exists(imagePath)) {
                    Files.delete(imagePath);
                    System.out.println("Image supprimée: " + imagePath);
                }
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erreur lors de la suppression de l'image.");
            }
        }

        // Supprimer la formation après suppression de l'image
        implHotelService.deleteHotel(id);
        return ResponseEntity.ok().body("Hotel supprimée avec succès.");
    }
    @GetMapping("/cities")
    public List<City>  allCities(){
        return implHotelService.getAllCities();
    }

    @GetMapping("cities/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id") Long id){
        Optional<City> city = implHotelService.getCityById(id);
        if(city.isPresent()){
            return new ResponseEntity<>(city.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
