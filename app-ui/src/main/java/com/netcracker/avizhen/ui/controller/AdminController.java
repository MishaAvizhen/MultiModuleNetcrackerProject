package com.netcracker.avizhen.ui.controller;

import com.netcracker.avizhen.persistence.entity.Advert;
import com.netcracker.avizhen.services.service.AdvertService;
import com.netcracker.avizhen.services.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Александр on 22.11.2016.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static Logger LOG = LogManager.getLogger();

    @Autowired
    private AdvertService advertService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/advert/delete", method = RequestMethod.POST)
    public ModelAndView removeAdvert(@RequestParam("deleteAdvertId") int deleteAdvertId) {
        LOG.info("Deleting advert with id " + deleteAdvertId);
        advertService.removeAdvertById(deleteAdvertId);
        ModelAndView modelAndView = new ModelAndView("redirect:/advert");
        modelAndView.addObject("msg", "Advert was deleted successful");
        return modelAndView;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView showAllOrders() {
        ModelAndView modelAndView = new ModelAndView("orders");
        modelAndView.addObject("orders", orderService.findAllOrders());
        return modelAndView;
    }

    @RequestMapping(value = "/advert/edit", method = RequestMethod.POST)
    public ModelAndView editAdvert(@RequestParam("editAdvertId") int editAdvertId) {
        LOG.info("Modification advert with id " + editAdvertId);
        ModelAndView modelAndView = new ModelAndView("editAdvert");
        Advert advert = advertService.findAdvertById(editAdvertId);
        if (advert == null) {
            return modelAndView;
        }
        modelAndView.addObject("advertId", advert.getId());
        modelAndView.addObject("carId", advert.getCar().getId());
        return modelAndView;
    }
    @RequestMapping(value = "/advert/add", method = RequestMethod.POST)
    public String addAdvert() {
        return "editAdvert";
    }
}
