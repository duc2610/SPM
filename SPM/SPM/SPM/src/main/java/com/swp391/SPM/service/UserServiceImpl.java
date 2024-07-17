package com.swp391.SPM.service;

import com.swp391.SPM.entity.Role;
import com.swp391.SPM.entity.User;
import com.swp391.SPM.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void saveUserByEmail(OidcUser oidcUser) {
        String email = oidcUser.getEmail();
        User user = userRepository.findUsersByEmail(email);
        if (user == null) {
            user = new User();
            Role role = new Role();
            role.setId(2);
            user.setFullName(oidcUser.getFullName());
            user.setEmail(email);
            user.setPassword("123");
            user.setRoles(role);
            user.setLogType("GG");
            userRepository.save(user);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUsersByEmail(email);
    }

    @Override
    public void save(User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(int idUser) {
        return userRepository.findUsersById(idUser);
    }

    @Override
    public List<User> findAllByRole(Role role) {
        return userRepository.findAllUserByRoles(role);
    }
    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }
}
