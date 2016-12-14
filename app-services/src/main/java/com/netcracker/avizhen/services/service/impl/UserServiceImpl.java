package com.netcracker.avizhen.services.service.impl;

import com.netcracker.avizhen.persistence.entity.User;
import com.netcracker.avizhen.persistence.repository.UserRepository;
import com.netcracker.avizhen.persistence.repository.UserRoleRepository;
import com.netcracker.avizhen.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Александр on 14.12.2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public User findUserById(int userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public List<User> findAllAdmins() {
        return userRoleRepository.findUsersByRole("ROLE_ADMIN");
    }

}
