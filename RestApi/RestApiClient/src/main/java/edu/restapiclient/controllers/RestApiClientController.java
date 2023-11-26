package edu.restapiclient.controllers;

import edu.restapiclient.client.RestApiClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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
}
