package fr.fms.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Data@AllArgsConstructor@NoArgsConstructor@ToString
@Table(name = "T_roles")
public class Role  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;
//    @OneToMany(mappedBy = "role")
//    @JsonIgnore
//    private Collection<Gestionnaire> gestionnaires;
}
