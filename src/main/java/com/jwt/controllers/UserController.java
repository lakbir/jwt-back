package com.jwt.controllers;

import com.jwt.entities.ERole;
import com.jwt.entities.Role;
import com.jwt.entities.User;
import com.jwt.repository.RoleRepository;
import com.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/all")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<User> getAllUsers(){
        Role role = roleRepository.findByName(ERole.ROLE_USER).get();
       List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if(user.getRoles().contains(role)){
                users.add(user);
            }
        });


        return users;
    }

    @GetMapping("/one")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN') or hasRole('USER')")
    public User getUserById(@RequestParam(name = "id") Long id){
        return userRepository.findById(id).get();
    }

    @GetMapping("/admins")
    @PreAuthorize("hasRole('MODERATOR')")
    public List<User> getAllAdmins(){
        Role role = roleRepository.findByName(ERole.ROLE_ADMIN).get();
        List<User> admins = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if(user.getRoles().contains(role)){
                admins.add(user);
            }
        });

        return admins;
    }

    @GetMapping("/admins/delete")
    @PreAuthorize("hasRole('MODERATOR')")
    public boolean onDeleteAdmin(@RequestParam(name = "id") Long id){
      User user = userRepository.getOne(id);
      Set<Role> roles = user.getRoles();
      roles.clear();
      roles.add(roleRepository.findByName(ERole.ROLE_USER).get());
      user.setRoles(roles);
        userRepository.save(user);
        return true;
    }




    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public boolean deleteUser(@RequestParam(name = "id") Long id){
        userRepository.deleteById(id);
        return true;
    }

}
