package fr.fms.service;

import fr.fms.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> authenticate(String email, String password);
    User saveUser(User user);
}
