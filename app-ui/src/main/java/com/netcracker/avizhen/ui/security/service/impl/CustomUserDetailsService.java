package com.netcracker.avizhen.ui.security.service.impl;

import com.netcracker.avizhen.persistence.entity.User;
import com.netcracker.avizhen.persistence.repository.UserRepository;
import com.netcracker.avizhen.persistence.repository.UserRoleRepository;
import com.netcracker.avizhen.ui.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Александр on 15.11.2016.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, UserRoleRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRolesRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("No user present with username: " + s);
        } else {
            List<String> roles = userRoleRepository.findRoleByUserName(s);
            return new CustomUserDetails(user, roles);
        }

    }
}
