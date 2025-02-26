//package fr.fms.dto;
//
//import fr.fms.entity.City;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.web.multipart.MultipartFile;
//
//
//
//
//@Builder
//@Data@NoArgsConstructor
//@AllArgsConstructor
//public class HotelDto {
//    private Long id;
//    private String name;
//    private String phone;
//    private String address;
//    private double numberStar;
//    private double totalRoom;
//    private double price;
//    private City city;
//    private MultipartFile file;
//
//    public static HotelDto fromEntityDto(Hotel entityDto){
//        return HotelDto.builder()
//                .id(entityDto.getId())
//                .name(entityDto.getName())
//                .phone(entityDto.getPhone())
//                .address(entityDto.getAddress())
//                .numberStar(entityDto.getNumberOfStar())
//                .totalRoom(entityDto.getTotalRoomOfAvailable())
//                .price(entityDto.getPrice())
//                .city(entityDto.getCity())
//                .file(entityDto.getImageUrl())
//                .build();
//
//
//    }
//}
