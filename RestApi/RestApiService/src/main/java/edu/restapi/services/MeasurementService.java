package edu.restapi.services;

import edu.restapi.models.Measurement;
import edu.restapi.repositories.MeasurementRepository;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    @Nonnull
    public List<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(@Nonnull final Measurement measurement) {
        measurementRepository.save(measurement);
    }

}
