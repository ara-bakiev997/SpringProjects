package edu.restapi.convertors;

import edu.restapi.dtos.SensorDto;
import edu.restapi.models.Sensor;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SensorToDtoConvertor {
    @Nonnull
    public SensorDto sensorToDto(@Nonnull final Sensor sensor) {
        return new SensorDto(sensor.getId(), sensor.getName());
    }

    @Nonnull
    public List<SensorDto> sensorsToDtoList(@Nonnull final List<Sensor> sensors) {
        return sensors.stream().map(this::sensorToDto).collect(Collectors.toList());
    }
}