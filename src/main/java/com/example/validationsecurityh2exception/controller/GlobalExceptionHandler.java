package com.example.validationsecurityh2exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(Exception exception){
        ModelAndView errorpage = new ModelAndView();
        errorpage.setViewName("error");
        errorpage.addObject("errorMessage", exception.getMessage());
        return errorpage;
    }
}
