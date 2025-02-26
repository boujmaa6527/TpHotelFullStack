package fr.fms.repository;

import fr.fms.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

    Supervisor findByEmail(String email);
}
