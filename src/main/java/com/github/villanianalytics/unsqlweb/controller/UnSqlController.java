package com.github.villanianalytics.unsqlweb.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class UnSqlController {

    @GetMapping("/")
    public String main(Model model) {
        return "index"; 
    }
}
