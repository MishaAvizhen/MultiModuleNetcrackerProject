package com.netcracker.avizhen.services.service;


import com.netcracker.avizhen.persistence.entity.Car;
import com.netcracker.avizhen.persistence.entity.CarImage;

import java.util.List;

/**
 * Created by Александр on 10.11.2016.
 */
public interface CarImageService {
    List<CarImage> getCarImagesByCar(Car car);

    CarImage getPresentationCarImageByCar(Car car);
}
