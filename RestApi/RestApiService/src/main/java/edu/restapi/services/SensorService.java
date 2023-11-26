package edu.restapi.services;

import edu.restapi.models.Sensor;
import edu.restapi.repositories.SensorRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    @Nonnull
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @Transactional
    public void save(@Nonnull final Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Nonnull
    public Optional<Sensor> findByName(@Nonnull final String name) {
        return sensorRepository.findByName(name);
    }
}
