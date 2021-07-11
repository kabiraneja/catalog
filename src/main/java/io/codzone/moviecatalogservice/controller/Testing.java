package io.codzone.moviecatalogservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Testing")
public class Testing {
        @GetMapping("/test")
    public String t0() {
        return "Test";
    }
    @GetMapping("")
    public String t1(){
        String str = "Hello Ji !! Kaise ho ji";
        return str;
    }
    @GetMapping("/")
    public boolean t2() {
        return false;
    }
    @GetMapping ("test1")
    public int t3() {
        return 10;
    }
}
