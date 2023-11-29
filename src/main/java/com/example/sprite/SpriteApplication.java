package com.example.sprite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class SpriteApplication {

    @GetMapping("/")
    public String mainPage() {
        return "Welcome to the App. There is nothing to do here.";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpriteApplication.class, args);
    }

}
