package dev.emrx.users.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gretting")
public class CustomController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world!";
    }

}
