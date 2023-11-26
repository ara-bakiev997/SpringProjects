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

    public int getRainyDaysCount() {
        return measurementRepository.countMeasurementByRainingIsTrue();
    }
}
