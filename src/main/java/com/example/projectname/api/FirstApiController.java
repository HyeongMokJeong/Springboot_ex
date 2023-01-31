package com.example.projectname.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 반환하는 컨트롤러
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {
        return "hello world";
    }
}
