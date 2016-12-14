package com.netcracker.avizhen.ui.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.avizhen.persistence.entity.Order;
import com.netcracker.avizhen.persistence.web.jsonview.Views;
import com.netcracker.avizhen.services.service.OrderService;
import com.netcracker.avizhen.ui.web.model.UserAdvertRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Александр on 13.12.2016.
 */
@RestController
@RequestMapping(value = "/api")
public class OrderRestController {
    private static Logger LOG = LogManager.getLogger();
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @JsonView(Views.Public.class)
    public Order addOrder(@RequestBody UserAdvertRequest userAdvertRequest) {
        LOG.info("Addition order to user with id " + userAdvertRequest.getUserId() + " advert " +
                userAdvertRequest.getAdvert());
        return orderService.addOrderByUserIdAndAdvert(userAdvertRequest.getUserId(), userAdvertRequest.getAdvert());
    }

}
