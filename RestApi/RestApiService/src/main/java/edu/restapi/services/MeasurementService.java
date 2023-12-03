package edu.restapi.services;

import edu.restapi.models.Measurement;
import edu.restapi.models.Sensor;
import edu.restapi.repositories.MeasurementRepository;
import edu.restapi.repositories.SensorRepository;
import edu.restapi.utils.exceptions.SensorException;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Nonnull
    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(@Nonnull final Measurement measurement) {
        final Sensor sensor = sensorRepository.findByName(measurement.getSensor().getName())
                                              .orElseThrow(SensorException::new);
        measurement.setSensor(sensor);
        measurementRepository.save(measurement);
    }

    @Transactional
    public void saveAll(@Nonnull final List<Measurement> measurements) {
        if (Objects.nonNull(measurements)
            &&
            !measurements.isEmpty()) {
            final Sensor sensor = sensorRepository.findByName(measurements.get(0).getSensor().getName())
                                                  .orElseThrow(SensorException::new);
//            measurementRepository.saveAll(measurements
//                    .stream()
//                    .peek(measurement -> measurement.setSensor(sensor))
//                    .collect(Collectors.toList())
//            );
            measurements.forEach(measurement -> {
                measurement.setSensor(sensor);
                measurementRepository.save(measurement);
            });
        }
    }

    public int getRainyDaysCount() {
        return measurementRepository.countMeasurementByRainingIsTrue();
    }
}
