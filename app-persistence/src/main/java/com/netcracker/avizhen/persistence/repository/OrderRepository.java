package com.netcracker.avizhen.persistence.repository;

import com.netcracker.avizhen.persistence.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Александр on 25.10.2016.
 */
@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
