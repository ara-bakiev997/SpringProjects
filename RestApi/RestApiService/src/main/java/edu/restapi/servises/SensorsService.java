package edu.restapi.servises;

import edu.restapi.models.Sensor;
import edu.restapi.repositories.SensorsRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    @Nonnull
    public List<Sensor> getSensors() {
        return sensorsRepository.findAll();
    }
}
