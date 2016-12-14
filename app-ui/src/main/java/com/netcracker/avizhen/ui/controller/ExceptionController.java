package com.netcracker.avizhen.ui.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by Александр on 19.11.2016.
 */
@Controller
@ControllerAdvice
public class ExceptionController {
    private static Logger LOG = LogManager.getLogger();

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handlerResourceNotFoundException(Exception e) {
        LOG.info("Handler resource not found: " + e.getMessage());
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorCode", 404);
        return mav;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ModelAndView methodNotSupportedException(Exception e) {
        LOG.info("Method not supported: " + e.getMessage());
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorCode", 405);
        return mav;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView badRequest(Exception e) {
        LOG.info("Bad request: " + e.getMessage());
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorCode", 400);
        return mav;
    }
}
