package com.myLibrary.security.service;

import com.myLibrary.security.dao.AppRoleRepository;
import com.myLibrary.security.dao.AppUserRepository;
import com.myLibrary.security.entities.AppRole;
import com.myLibrary.security.entities.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service @Transactional @Slf4j
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser saveUser(AppUser user) {
        String hashPW = bCryptPasswordEncoder.encode((user.getPassword()));
        user.setPassword(hashPW);
        log.info("Sauvegarde d'un nouvel utilisateur {} en basse", user);
        return appUserRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        log.info("Sauvegarde d'un nouveau role en base");
        return appRoleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        AppRole role = appRoleRepository.findByRolename(rolename);
        AppUser user = appUserRepository.findByUsername(username);
        user.getRoles().add(role);
        log.info("Association d'un rôle à un utilisateur");
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public ResponseEntity<List<AppUser>> listUsers() {
        return ResponseEntity.ok().body(appUserRepository.findAll());
    }
}
