package fr.fms.service;

import fr.fms.entity.Role;
import fr.fms.entity.Supervisor;
import fr.fms.repository.GestionnaireRepository;
import fr.fms.repository.RoleRepository;
import fr.fms.repository.SupervisorRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service@Transactional@Slf4j
public class ImplAccountService implements AccountService{

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private  RoleRepository roleRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;


//    @Override
//    public Supervisor saveSupervisor(Supervisor supervisor) {
//        String hashPw = bCryptPasswordEncoder.encode(supervisor.getPassword());
//        supervisor.setPassword(hashPw);
//        log.info("sauvegarde d'un nouvel supervisor {} en base", supervisor);
//        return supervisorRepository.save(supervisor);
//
//    }

    @Override
    public Supervisor saveSupervisor(Supervisor supervisor) {
        return null;
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving role:" + role);
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToSupervisor(String email, String role) {
        Role roleRecord = roleRepository.findByRole(role);
        Supervisor supervisor = supervisorRepository.findByEmail(email);
        if(roleRecord != null && supervisor != null){
            supervisor.getRoles().add(roleRecord);
            log.info("Added role: "+ roleRecord+ "to supervisor: "+ email);
        }else {
            log.error("User or Role not found! Supervisor: "+ email+ ", Role: " +roleRecord);
        }



    }
    public Supervisor findSuperVisorByEmail(String email){
        return supervisorRepository.findByEmail(email);
    }

    @Override
    public ResponseEntity<List<Supervisor>> listSupervisors() {
        return ResponseEntity.ok(supervisorRepository.findAll());
    }
}
