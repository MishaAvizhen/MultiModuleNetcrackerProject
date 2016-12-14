package com.netcracker.avizhen.persistence.repository;

import com.netcracker.avizhen.persistence.entity.Item;
import com.netcracker.avizhen.persistence.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Александр on 25.10.2016.
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {
    @Query("from Item")
    List<Item> getAll();
}
