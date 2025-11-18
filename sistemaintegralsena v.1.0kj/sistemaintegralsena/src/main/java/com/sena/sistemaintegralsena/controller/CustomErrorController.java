package com.sena.sistemaintegralsena.controller;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Locale;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model, Locale locale) {

        Object status = request.getAttribute("jakarta.servlet.error.status_code");
        Object message = request.getAttribute("jakarta.servlet.error.message");
        Object exception = request.getAttribute("jakarta.servlet.error.exception");

        model.addAttribute("status", status);
        model.addAttribute("message", message);
        model.addAttribute("exception", exception);
        model.addAttribute("locale", locale);

        return "error"; // error.html


    }
}
