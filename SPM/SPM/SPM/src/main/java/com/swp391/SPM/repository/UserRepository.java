package com.swp391.SPM.repository;

import com.swp391.SPM.entity.Role;
import com.swp391.SPM.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUsersByEmail(String email);
    public User findUsersById(int idUser);
    public void deleteUserByEmail(String email);
    public List<User> findAllUserByRoles(Role role);
}
