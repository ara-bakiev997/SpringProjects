package edu.restapiclient.client;

import edu.restapiclient.dtos.MeasurementDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestApiClient {

    public void printAllMeasurements() {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = "http://localhost:8080/measurements";
        final List<MeasurementDto> measurements = restTemplate.getForObject(url, List.class);
        System.out.println(measurements);
    }
}
