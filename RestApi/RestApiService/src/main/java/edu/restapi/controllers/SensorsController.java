package edu.restapi.controllers;

import edu.restapi.models.Sensor;
import edu.restapi.servises.SensorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorsController {
    private final SensorsService sensorsService;

    @GetMapping
    public List<Sensor> getSensors() {
        return sensorsService.getSensors();
    }
}
