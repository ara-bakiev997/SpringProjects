package edu.restapi.convertors;

import edu.restapi.dtos.SensorDto;
import edu.restapi.models.Sensor;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SensorConvertor {

    @Nonnull
    public SensorDto convertSensorToDto(@Nonnull final Sensor sensor) {
        return new SensorDto(sensor.getName());
    }

    @Nonnull
    public List<SensorDto> convertSensorsToDtos(@Nonnull final List<Sensor> sensors) {
        return sensors.stream().map(this::convertSensorToDto).collect(Collectors.toList());
    }

    @Nonnull
    public Sensor convertDtoToSensor(@Nonnull final SensorDto sensorDto) {
       return new Sensor(sensorDto.getName());
    }
}
