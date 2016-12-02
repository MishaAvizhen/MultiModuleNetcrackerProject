package com.netcracker.avizhen.persistence.repository;

import com.netcracker.avizhen.persistence.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Александр on 25.10.2016.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    User findByUserName(String username);

    @Query("from User")
    List<User> getAll();

}

