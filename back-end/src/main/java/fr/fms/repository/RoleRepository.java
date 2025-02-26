package fr.fms.repository;

import fr.fms.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
}
