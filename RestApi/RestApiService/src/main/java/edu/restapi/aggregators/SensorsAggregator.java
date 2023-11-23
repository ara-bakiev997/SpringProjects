package edu.restapi.aggregators;

import edu.restapi.convertors.SensorToDtoConvertor;
import edu.restapi.dtos.SensorDto;
import edu.restapi.servises.SensorsService;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SensorsAggregator {
    private final SensorsService sensorsService;
    private final SensorToDtoConvertor convertor;

    @Nonnull
    public List<SensorDto> getAllSensors() {
        return convertor.sensorsToDtoList(
                sensorsService.getSensors()
        );
    }
}
