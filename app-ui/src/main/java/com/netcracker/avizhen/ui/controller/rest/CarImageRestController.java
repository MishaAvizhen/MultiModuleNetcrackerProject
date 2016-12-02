package com.netcracker.avizhen.ui.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.avizhen.persistence.entity.Car;
import com.netcracker.avizhen.persistence.entity.CarImage;
import com.netcracker.avizhen.persistence.web.jsonview.Views;
import com.netcracker.avizhen.services.service.CarImageService;
import com.netcracker.avizhen.services.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Александр on 09.11.2016.
 */

@RestController
@RequestMapping(value = "/api")
public class CarImageRestController {

    @Autowired
    private CarImageService carImageService;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/car/{carId}/images", method = RequestMethod.GET)
    @Transactional
    @JsonView(Views.Public.class)
    public List<CarImage> findAllImages(@PathVariable int carId) {
        Car car = carService.findCarById(carId);
        return carImageService.getCarImagesByCar(car);
    }

    @RequestMapping(value = "/car/{carId}/image", method = RequestMethod.GET)
    @Transactional
    @JsonView(Views.Public.class)
    public CarImage findPresentationCarImage(@PathVariable int carId) {
        Car car = carService.findCarById(carId);
        return carImageService.getPresentationCarImageByCar(car);
    }
}
