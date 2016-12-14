package com.netcracker.avizhen.ui.controller;

import com.netcracker.avizhen.persistence.entity.Order;
import com.netcracker.avizhen.services.service.OrderService;
import com.netcracker.avizhen.services.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Александр on 28.10.2016.
 */

@Controller
@SessionAttributes({"cart"})
public class MainController {
    private static Logger LOG = LogManager.getLogger();

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public String showWelcomePage(ModelMap model) {
        initSession(model);
        return "index";
    }

    @RequestMapping(value = "/advert", method = RequestMethod.GET)
    public String showAdverts(ModelMap model) {
        initSession(model);
        return "adverts";
    }

    @RequestMapping(value = "/advert/{id}", method = RequestMethod.GET)
    public ModelAndView showAdvert(@PathVariable Integer id, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("advert");
        initSession(model);
        modelAndView.addObject("advertId", id);
        return modelAndView;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(ModelMap model) {
        initSession(model);
        return "cart";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView showContacts(ModelMap model) {
        initSession(model);
        ModelAndView modelAndView = new ModelAndView("contacts");
        modelAndView.addObject("contacts", userService.findAllAdmins());
        return modelAndView;
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ModelAndView showOrders(ModelMap model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || (auth instanceof AnonymousAuthenticationToken)) {
            ModelAndView notAuthModelAndView = new ModelAndView("redirect:/");
            notAuthModelAndView.addObject("msg", "You are not authentication");
            return notAuthModelAndView;
        }
        String username = auth.getName();
        LOG.info("Trying get orders for username " + username);
        ModelAndView modelAndView = new ModelAndView("orders");
        List<Order> orders = orderService.findOrdersByUsername(username);
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              ModelMap modelMap) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
            initSession(modelMap);
        }
        model.setViewName("login");
        return model;

    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied() {

        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }
        model.setViewName("error");
        model.addObject("errorCode", 403);
        return model;

    }


    @RequestMapping("/favicon.ico")
    public String favicon() {
        return "forward:/resources/images/favicon.ico";
    }

    private void initSession(ModelMap model) {
        if(!model.containsAttribute("cart")) {
            model.addAttribute("cart", new LinkedList<Integer>());
        }
    }

}

