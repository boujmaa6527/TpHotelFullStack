package fr.fms.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data
@Table(name = "T_hotels")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hotel implements Serializable {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long id;
        private String name;
        private String phone;
        private String address;
        private String imageUrl;
        private double numberOfStar;
        private double totalRoomOfAvailable;
        private double price;

        @ManyToOne
        @JoinColumn(name = "city_id")
        private City city;

}
