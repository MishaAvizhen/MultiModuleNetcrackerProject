package com.netcracker.avizhen.ui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by Александр on 19.11.2016.
 */
@Controller
@ControllerAdvice
public class ExceptionController {
    private static Logger LOG = LogManager.getLogger();

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handlerResourceNotFoundException() {
        LOG.info("handler resource not found");
        return "redirect:/404";
    }

    @RequestMapping(value = {"/404"}, method = RequestMethod.GET)
    public String notFoundPage() {
        return "404";
    }
}
