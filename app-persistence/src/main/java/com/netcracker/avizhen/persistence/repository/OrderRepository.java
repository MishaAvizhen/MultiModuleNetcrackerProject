package com.netcracker.avizhen.persistence.repository;

import com.netcracker.avizhen.persistence.entity.Advert;
import com.netcracker.avizhen.persistence.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Александр on 25.10.2016.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Query("from Order")
    List<Order> getAll();

    @Query("from Order o where o.user.userName=?1")
    List<Order> findOrdersByUsername(String username);



}
