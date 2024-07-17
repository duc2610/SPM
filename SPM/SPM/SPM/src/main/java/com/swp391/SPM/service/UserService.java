package com.swp391.SPM.service;

import com.swp391.SPM.entity.Role;
import com.swp391.SPM.entity.User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.List;

public interface UserService {
    public List<User> getAllUser();
    public void saveUserByEmail(OidcUser oidcUser);
    public User getUserByEmail(String email);
    public void save(User user);
    public User update(User user);
    public User getUserById(int idUser);
    public List<User> findAllByRole(Role role);
    public  void deleteUserByEmail(String email);
}
