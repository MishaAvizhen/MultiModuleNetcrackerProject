package com.netcracker.avizhen.services.service;

import com.netcracker.avizhen.persistence.entity.User;

import java.util.List;

/**
 * Created by Александр on 14.12.2016.
 */
public interface UserService {
    User findUserById(int userId);

    List<User> findAllAdmins();
}
