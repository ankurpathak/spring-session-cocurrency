package com.github.ankurpathak.springsession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/me")
public class MeController {

    @GetMapping
    public User get(@CurrentUser User user){
        return user;
    }
}
