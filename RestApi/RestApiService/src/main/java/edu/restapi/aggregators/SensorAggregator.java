package edu.restapi.aggregators;

import edu.restapi.convertors.SensorConvertor;
import edu.restapi.dtos.SensorDto;
import edu.restapi.services.SensorService;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SensorAggregator {
    private final SensorService sensorService;
    private final SensorConvertor sensorConvertor;

    @Nonnull
    public List<SensorDto> getAllSensors() {
        return sensorConvertor.convertSensorsToDtos(
                sensorService.getAllSensors()
        );
    }

    public void registration(@Nonnull final SensorDto sensorDto) {
        sensorService.save(sensorConvertor.convertDtoToSensor(sensorDto));
    }
}
