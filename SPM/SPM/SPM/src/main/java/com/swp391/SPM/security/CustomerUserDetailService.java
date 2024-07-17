package com.swp391.SPM.security;

import com.swp391.SPM.entity.Role;
import com.swp391.SPM.entity.User;
import com.swp391.SPM.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /*@Autowired
    private BCryptPasswordEncoder passwordEncoder;*/
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
//        if (user == null) {
//            throw new UsernameNotFoundException(email);
//        }
//        Collection<GrantedAuthority> authorities = new HashSet<>();
//        Set<Role> roles = (Set<Role>) user.getRole();
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
//        }
//        return new CustomerUserDetail(user, authorities);
        if (user == null) {
            throw new UsernameNotFoundException("khong tim thay email");
        }
        Collection<GrantedAuthority> authorities = new HashSet<>();
        Role roles =  user.getRoles();
        authorities.add(new SimpleGrantedAuthority(roles.getRoleName()));
        return new CustomerUserDetail(user, authorities);
    }
}