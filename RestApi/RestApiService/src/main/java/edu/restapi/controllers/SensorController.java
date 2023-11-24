package edu.restapi.controllers;

import edu.restapi.aggregators.SensorAggregator;
import edu.restapi.dtos.SensorDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@AllArgsConstructor
public class SensorController {
    private final SensorAggregator sensorAggregator;

    @GetMapping
    public List<SensorDto> sensors() {
        return sensorAggregator.getAllSensors();
    }

}
