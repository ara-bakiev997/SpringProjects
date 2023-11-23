package edu.restapi.controllers;

import edu.restapi.aggregators.SensorsAggregator;
import edu.restapi.dtos.SensorDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@AllArgsConstructor
public class SensorsController {
    private final SensorsAggregator sensorsAggregator;

    @GetMapping
    public List<SensorDto> getAllSensors() {
        return sensorsAggregator.getAllSensors();
    }
}
