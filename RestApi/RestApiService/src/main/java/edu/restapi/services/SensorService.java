package edu.restapi.services;

import edu.restapi.models.Sensor;
import edu.restapi.repositories.SensorRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    @Nonnull
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }
}
