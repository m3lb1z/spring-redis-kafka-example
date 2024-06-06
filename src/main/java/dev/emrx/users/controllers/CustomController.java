package dev.emrx.users.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gretting")
@Hidden
public class CustomController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world!";
    }

}
