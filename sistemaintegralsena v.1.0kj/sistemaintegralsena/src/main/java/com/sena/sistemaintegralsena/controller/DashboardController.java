package com.sena.sistemaintegralsena.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        model.addAttribute("usuario", userDetails.getUsername());
        model.addAttribute("roles", userDetails.getAuthorities());

        return "dashboard";
    }
}
