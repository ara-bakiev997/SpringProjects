package edu.restapi.servises;

import edu.restapi.models.Sensor;
import edu.restapi.repositories.SensorsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    public List<Sensor> getSensors() {
        return sensorsRepository.findAll();
    }
}
