package com.company.finalprojectauthorization.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoController {

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/userinfo", method = RequestMethod.GET)
    public Principal getUserInfo(Principal principal) {
        System.out.println("CALLED userinfo");
        return principal;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String returnString() {
        return "Hello";
    }
}