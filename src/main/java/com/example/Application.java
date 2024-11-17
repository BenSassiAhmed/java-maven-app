package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        Logger log = LoggerFactory.getLogger(Application.class);
        log.info("Java app started");
    }
}

@RestController  // Define a REST controller
class StatusController {

    @GetMapping("/status")  // Map the /status endpoint to this method
    public String getStatus() {
        return "OK";
    }
}
