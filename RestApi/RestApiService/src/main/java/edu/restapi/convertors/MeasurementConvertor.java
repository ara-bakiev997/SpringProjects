package edu.restapi.convertors;

import edu.restapi.dtos.MeasurementDto;
import edu.restapi.models.Measurement;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MeasurementConvertor {
    private final SensorConvertor sensorConvertor;

    @Nonnull
    public MeasurementDto convertMeasurementToDto(@Nonnull final Measurement measurement) {
        return new MeasurementDto(
                measurement.getValue(),
                measurement.getRaining(),
                sensorConvertor.convertSensorToDto(measurement.getSensor())
        );
    }

    @Nonnull
    public List<MeasurementDto> convertMeasurementsToDtos(@Nonnull final List<Measurement> measurements) {
        return measurements.stream().map(this::convertMeasurementToDto).collect(Collectors.toList());
    }

    @Nonnull
    public Measurement convertDtoToMeasurement(@Nonnull final MeasurementDto measurementDto) {
        return new Measurement(
                measurementDto.getValue(),
                measurementDto.getRaining(),
                sensorConvertor.convertDtoToSensor(measurementDto.getSensor())
        );
    }

    @Nonnull
    public List<Measurement> convertDtosToMeasurements(@Nonnull final List<MeasurementDto> measurementDtos) {
        return measurementDtos.stream().map(this::convertDtoToMeasurement).collect(Collectors.toList());
    }

    {

    }
}
