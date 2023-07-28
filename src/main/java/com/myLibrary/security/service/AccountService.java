package com.myLibrary.security.service;

import com.myLibrary.security.entities.AppRole;
import com.myLibrary.security.entities.AppUser;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    public AppUser saveUser(AppUser user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String username, String rolename);
    public AppUser findUserByUsername(String username);
    ResponseEntity<List<AppUser>> listUsers();
}
