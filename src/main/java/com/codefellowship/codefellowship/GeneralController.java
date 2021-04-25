package com.codefellowship.codefellowship;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {


    @GetMapping("/")
    public String splashPage() {
        return "splashPage.html";
    }
}
