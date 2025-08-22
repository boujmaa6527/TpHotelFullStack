package fr.fms.service;

import fr.fms.entity.Role;
import fr.fms.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {

    Role saveRole(Role role);

    void addRoleToUser(String email, String role);

    User registerUserWithRole(User user,String rolename);


}
