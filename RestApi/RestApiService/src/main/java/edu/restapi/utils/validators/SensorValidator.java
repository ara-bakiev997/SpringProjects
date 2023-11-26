package edu.restapi.utils.validators;

import edu.restapi.dtos.SensorDto;
import edu.restapi.services.SensorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(final Class<?> clazz) {
        return SensorDto.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {
        if (target instanceof final SensorDto sensorDto) {
            if (sensorService.findByName(sensorDto.getName()).isPresent()) {
                errors.rejectValue("name", "", "Sensor name already exists");
            }
        }
    }
}
