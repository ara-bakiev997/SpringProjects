package edu.restapi.utils.validators;

import edu.restapi.dtos.MeasurementDto;
import edu.restapi.services.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
@AllArgsConstructor
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(final Class<?> clazz) {
        return MeasurementDto.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        if (target instanceof final MeasurementDto measurementDto) {
            if (Objects.nonNull(measurementDto.getSensor())
                && Objects.nonNull(measurementDto.getSensor().getName())) {
                if (sensorService.findByName(measurementDto.getSensor().getName()).isEmpty()) {
                    errors.rejectValue("sensor", "", "Sensor name does not exist");
                }
            } else {
                errors.rejectValue("sensor", "", "Sensor should not be empty");
            }
        }

    }
}
