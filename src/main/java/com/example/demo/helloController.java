package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @RequestMapping("/test")
    public String test(@RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String address) {
        return "hello hahah -" + name + "-" + address;
    }

    @GetMapping("/hung")
    public String hung() {

        return "Hello  hùng";
    }
}
