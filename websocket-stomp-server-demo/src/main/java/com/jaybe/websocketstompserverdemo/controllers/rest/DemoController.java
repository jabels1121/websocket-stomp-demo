package com.jaybe.websocketstompserverdemo.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DemoController extends AbstractRestController {

    @GetMapping(path = "/hello")
    public String greetings() {
        return "Hello from server!";
    }
}
