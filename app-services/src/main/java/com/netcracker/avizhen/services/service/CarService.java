package com.netcracker.avizhen.services.service;

import com.netcracker.avizhen.persistence.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Created by Александр on 10.11.2016.
 */
public interface CarService {
    List<Car> findAllCars();

    Car findCarById(int carId);

    Set<String> findAllMakes();

    List<Car> findByMakeYearBetweenPriceBetween(String make, int yearFrom, int yearTo,
                                                int priceFrom, int priceTo);
    Page<Car> findByMakeYearBetweenPriceBetween(String make, int yearFrom, int yearTo,
                                                int priceFrom, int priceTo, Pageable pageable);
    long getCount();
}
