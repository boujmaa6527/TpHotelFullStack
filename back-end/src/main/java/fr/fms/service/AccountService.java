package fr.fms.service;

import fr.fms.entity.Role;
import fr.fms.entity.Supervisor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    Supervisor saveSupervisor(Supervisor supervisor);

    Role saveRole(Role role);

    void addRoleToSupervisor(String email, String role);

    ResponseEntity<List<Supervisor>> listSupervisors();
}
