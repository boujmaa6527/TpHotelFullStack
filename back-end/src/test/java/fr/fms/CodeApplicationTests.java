package fr.fms;

import fr.fms.controller.HotelController;
import fr.fms.entity.Hotel;
import fr.fms.service.ImplHotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class CodeApplicationTests {

	@Mock
	private ImplHotelService implHotelService;

	@InjectMocks
	private HotelController hotelController;

	@BeforeEach
	public void setUp(){
		MockitoAnnotations.openMocks(this);
	}
	//test if hotelexits with id hotel
	@Test
	public void testGetHotelById_HotelExists(){
		Long hotelId = 1L;
		Hotel hotel = new Hotel();

		when(implHotelService.getHotelById(hotelId)).thenReturn(Optional.of(hotel));

		ResponseEntity<Hotel> response = hotelController.getHotelById(hotelId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(hotel, response.getBody());
	}

	// Test si l'hotel n'est pas trouvé avec son ID
	@Test
	public void testGetHotelById_HotelNotFound(){
		Long hotelId = 1L;
		when(implHotelService.getHotelById(hotelId)).thenReturn(Optional.empty());
		ResponseEntity<Hotel> response = hotelController.getHotelById(hotelId);

		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());

	}

	@Test
	void contextLoads() {
	}

}
