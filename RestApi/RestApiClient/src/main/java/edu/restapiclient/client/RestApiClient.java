package edu.restapiclient.client;

import edu.restapiclient.dtos.MeasurementDto;
import edu.restapiclient.dtos.SensorDto;
import edu.restapiclient.services.RandomizationService;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RestApiClient {
    private static final String measurementsUrl = "http://localhost:8080/measurements";
    private static final String measurementsAddUrl = "http://localhost:8080/measurements/addMany";
    private static final String sensorsRegistrationUrl = "http://localhost:8080/sensors/registration";


    private final RestTemplate restTemplate;
    private final RandomizationService randomizationService;

    public void printAllMeasurements() {
        final List<MeasurementDto> measurements = restTemplate.getForObject(measurementsUrl, List.class);
        System.out.println(measurements);
    }

    public void createNewSensor(@Nonnull final String sensorName) {
        final String response = restTemplate.postForObject(
                sensorsRegistrationUrl,
                new SensorDto(sensorName),
                String.class
        );
        System.out.println(response);
    }

    public void addRandomMeasurementsToSensorBySensorName(@Nonnull final String sensorName) {
        final List<MeasurementDto> randomMeasurements =
                randomizationService.createRandomMeasurementsBySensorNameAndCount(sensorName, 1000);

        restTemplate.postForLocation(measurementsAddUrl, randomMeasurements);
    }

    public void plotGraph() {

        final ResponseEntity<MeasurementDto[]> responseEntity =
                restTemplate.getForEntity(measurementsUrl, MeasurementDto[].class);
        final MeasurementDto[] measurements = responseEntity.getBody();
        final List<Double> values = Arrays.stream(measurements)
                                          .map(MeasurementDto::getValue)
                                          .collect(Collectors.toList());
        if (!values.isEmpty()) {
            final List<Double> xDatas = new ArrayList<>(values.size());
            final AtomicInteger i = new AtomicInteger();
            values.forEach(value -> xDatas.add((double) i.getAndIncrement()));
            System.setProperty("java.awt.headless", "false");

            final XYChart chart = QuickChart.getChart("Sample Chart", "   X", "Y", "y(x)", xDatas, values);

            new SwingWrapper(chart).displayChart();
        }
    }
}