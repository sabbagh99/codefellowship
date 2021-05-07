package com.codefellowship.codefellowship;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class GeneralController {


    @GetMapping
    public String splashPage() {
       return "home_page.html";
    }
}
