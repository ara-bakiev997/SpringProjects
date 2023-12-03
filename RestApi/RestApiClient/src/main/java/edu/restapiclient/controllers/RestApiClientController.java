package edu.restapiclient.controllers;

import edu.restapiclient.client.RestApiClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class RestApiClientController {
    private final RestApiClient client;

    @GetMapping("/printAllMeasurements")
    public void printAllMeasurements() {
        client.printAllMeasurements();
    }

    @PostMapping("/createNewSensor")
    public void createNewSensor(@RequestBody final String sensorName) {
        client.createNewSensor(sensorName);
    }

    @PostMapping("addMeasurements")
    public void addMeasurements(@RequestBody final String sensorName) {
        client.addRandomMeasurementsToSensorBySensorName(sensorName);
    }

    @PostMapping("/plotGraph")
    public void plotGraph() {
        client.plotGraph();
    }

}
