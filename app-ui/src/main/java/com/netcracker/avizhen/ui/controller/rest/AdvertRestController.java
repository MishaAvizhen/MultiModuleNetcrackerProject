package com.netcracker.avizhen.ui.controller.rest;

import com.fasterxml.jackson.annotation.JsonView;
import com.netcracker.avizhen.persistence.entity.Advert;
import com.netcracker.avizhen.persistence.entity.Car;
import com.netcracker.avizhen.persistence.web.jsonview.Views;
import com.netcracker.avizhen.services.model.JsonPageResponse;
import com.netcracker.avizhen.services.service.AdvertService;
import com.netcracker.avizhen.services.service.CarService;
import com.netcracker.avizhen.ui.web.model.OrderAdvertsCriteria;
import com.netcracker.avizhen.ui.web.model.SearchAdvertsCriteria;
import com.netcracker.avizhen.ui.web.model.SearchOrderAdvertsCriteria;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Александр on 07.11.2016.
 */

@RestController
@RequestMapping(value = "/api")
public class AdvertRestController {
    private static Logger LOG = LogManager.getLogger();

    @Autowired
    private AdvertService advertService;

    @Autowired
    private CarService carService;

    @RequestMapping(value = "/advert/{advertId}", method = RequestMethod.GET)
    @JsonView(Views.Public.class)
    public Advert findAdvert(@PathVariable int advertId) {
        return advertService.findAdvertById(advertId);
    }

    @RequestMapping(value = "/advert", method = RequestMethod.POST)
    @JsonView(Views.Public.class)
    public Advert addAdvert(@RequestBody @Valid Advert advert) {
        LOG.info("Addition advert: " + advert);
        return advertService.addAdvert(advert);
    }

    @RequestMapping(value = "/advert/page/search", method = RequestMethod.POST)
    @Transactional
    @JsonView(Views.Public.class)
    public Page<Advert> findFilteringOrderPageWithAdverts(@RequestBody SearchOrderAdvertsCriteria criteria) {
        OrderAdvertsCriteria orderCriteria = criteria.getOrderCriteria();
        SearchAdvertsCriteria searchCriteria = criteria.getSearchCriteria();
        Sort.Direction direction = Sort.Direction.ASC;
        if (orderCriteria.getSortDirection().equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = new PageRequest(orderCriteria.getPageNumber(), orderCriteria.getPageSize(),
                direction, orderCriteria.getSortField());
        Page<Car> carPage = carService.findByMakeYearBetweenPriceBetween(searchCriteria.getMake(),
                searchCriteria.getYearFrom(), searchCriteria.getYearTo(), searchCriteria.getPriceFrom(),
                searchCriteria.getPriceTo(), pageable);
        List<Advert> advertList = new ArrayList<>();
        for (Car car : carPage.getContent()) {
            Advert advert = car.getAdvert();
            if (advert != null) {
                advertList.add(advert);
            }
        }
        Pageable pageable2 = new PageRequest(orderCriteria.getPageNumber(), orderCriteria.getPageSize(),
                direction, "car." + orderCriteria.getSortField());
        return new JsonPageResponse<>(advertList, pageable2, carPage.getTotalElements());
    }



}
