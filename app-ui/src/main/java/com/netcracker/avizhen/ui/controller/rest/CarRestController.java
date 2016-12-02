package com.netcracker.avizhen.ui.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.avizhen.persistence.web.jsonview.Views;
import com.netcracker.avizhen.services.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Александр on 10.11.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class CarRestController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "/car/makes", method = RequestMethod.GET)
    @JsonView(Views.Public.class)
    public Set<String> findAllMakes() {
        return carService.findAllMakes();
    }

}
