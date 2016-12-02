package com.netcracker.avizhen.persistence.repository;

import com.netcracker.avizhen.persistence.entity.Advert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Александр on 25.10.2016.
 */
@Repository
public interface AdvertRepository extends PagingAndSortingRepository<Advert, Integer> {
    @Query("from Advert")
    List<Advert> getAll();

}
