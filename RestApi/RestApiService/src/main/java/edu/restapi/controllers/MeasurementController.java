package edu.restapi.controllers;

import edu.restapi.aggregators.MeasurementAggregator;
import edu.restapi.dtos.MeasurementDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/measurements")
@AllArgsConstructor
public class MeasurementController {
    private final MeasurementAggregator measurementAggregator;

    @GetMapping
    public List<MeasurementDto> getAllMeasurements() {
        return measurementAggregator.getAllMeasurements();
    }

    @PostMapping("/add")
    public void add(@RequestBody final MeasurementDto measurementDto) {
        measurementAggregator.add(measurementDto);
    }

}
