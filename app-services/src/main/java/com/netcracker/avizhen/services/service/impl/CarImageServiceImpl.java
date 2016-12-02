package com.netcracker.avizhen.services.service.impl;

import com.netcracker.avizhen.persistence.entity.Car;
import com.netcracker.avizhen.persistence.entity.CarImage;
import com.netcracker.avizhen.persistence.repository.CarImageRepository;
import com.netcracker.avizhen.services.service.CarImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Александр on 09.11.2016.
 */
@Service
@Transactional
public class CarImageServiceImpl implements CarImageService {

    @Autowired
    private CarImageRepository carImageRepository;

    @Override
    public List<CarImage> getCarImagesByCar(Car car) {
        return carImageRepository.findByCar(car);
    }

    @Override
    public CarImage getPresentationCarImageByCar(Car car) {
        List<CarImage> images = carImageRepository.findByCar(car, new PageRequest(0, 1));
        if (images.iterator().hasNext()) {
            return images.iterator().next();
        }
        return null;
    }


}
