package edu.restapi.repositories;

import edu.restapi.models.Measuring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measuring, Integer> {
}
