package com.example.studentmanagementapplicationn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TemplateController {

    public static final String LOGIN_PAGE = "/login";
    public static final String MAIN_PAGE = "/main";

    @GetMapping(LOGIN_PAGE)
    public String getLoginView() {
        return LOGIN_PAGE;
    }

    @GetMapping(MAIN_PAGE)
    public String getMainView() {
        return MAIN_PAGE;
    }

}
