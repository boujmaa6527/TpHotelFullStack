package fr.fms.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@Data@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "T_users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String username;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
