package com.example.bands_service_3.controller;

import entity.Coordinates;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.CoordService;

@RestController
@RequestMapping("/")
public class CoordinatesController {
    private final CoordService coordService;

    public CoordinatesController(CoordService coordService) {
        this.coordService = coordService;
    }

    @GetMapping("/first")
    public Coordinates getFirst() {
        return coordService.getCoord("1");
    }

    @GetMapping("/a")
    public String helloFromSpring() {
        return "Hello from Sping!";
    }

    @GetMapping("/b")
    public String helloFromEjb() {
        return coordService.helloFromEjb();
    }
}
