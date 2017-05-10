package com.tpg.par.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

@Controller
@RequestMapping("/par")
public class SearchController {
    @GetMapping(value = "/", produces = TEXT_HTML_VALUE)
    public String handleIndexViewRequest() {
        return "index";
    }
}
