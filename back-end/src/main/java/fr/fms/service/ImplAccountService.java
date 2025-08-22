package fr.fms.service;

import fr.fms.entity.Role;
import fr.fms.entity.User;
import fr.fms.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service@Transactional@Slf4j
public class ImplAccountService implements AccountService{

    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    private ImplUserService implUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


//    @Override
//    public Supervisor saveSupervisor(Supervisor supervisor) {
//        String hashPw = bCryptPasswordEncoder.encode(supervisor.getPassword());
//        supervisor.setPassword(hashPw);
//        log.info("sauvegarde d'un nouvel supervisor {} en base", supervisor);
//        return supervisorRepository.save(supervisor);
//
//    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role:" + role);
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String rolename) {
        Role roleRecord = roleRepository.findByRole(rolename);
        Optional<User> optionalUserser = implUserService.findByEmail(email);
        if(roleRecord != null && optionalUserser.isPresent()){
            User user = optionalUserser.get();
            user.setRole(roleRecord);
            implUserService.saveUser(user);
            log.info("Role '{}' assigne to user '{}', ", rolename, email);
        }else {
            log.info("User or Role not found email: {}, role: {}", email, rolename);
        }
    }

    @Override
    public User registerUserWithRole(User user, String rolename) {
        Role role = roleRepository.findByRole(rolename);
        if(role == null){
            throw new RuntimeException("Role no found: "+ rolename);
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(role);
        return implUserService.saveUser(user);
    }

//    @Override
//    public void addRoleToSupervisor(String email, String role) {
//        Role roleRecord = roleRepository.findByRole(role);
//        User user = ImplUserService.findByEmail(email);
//        if(roleRecord != null && supervisor != null){
//            user.getRoles().add(roleRecord);
//            log.info("Added role: "+ roleRecord+ "to supervisor: "+ email);
//        }else {
//            log.error("User or Role not found! Supervisor: "+ email+ ", Role: " +roleRecord);
//        }
//
//
//
//    }
//    public Supervisor findSuperVisorByEmail(String email){
//        return supervisorRepository.findByEmail(email);
//    }
//
//    @Override
//    public ResponseEntity<List<Supervisor>> listSupervisors() {
//        return ResponseEntity.ok(supervisorRepository.findAll());
//    }
}
