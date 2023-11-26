package edu.restapi.repositories;

import edu.restapi.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    int countMeasurementByRainingIsTrue();
}
