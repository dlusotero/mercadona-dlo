package com.mercadonarest.infraestructure.api.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class UserResource {

    @GetMapping
    public String get() {
        return "HOLA";
    }
}
