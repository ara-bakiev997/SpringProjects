package edu.restapi.controllers;

import edu.restapi.aggregators.SensorAggregator;
import edu.restapi.dtos.SensorDto;
import edu.restapi.utils.erros.SensorErrorResponse;
import edu.restapi.utils.exceptions.SensorException;
import edu.restapi.utils.validators.SensorValidator;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensors")
@AllArgsConstructor
public class SensorController {
    private final SensorAggregator sensorAggregator;
    private final SensorValidator sensorValidator;

    @GetMapping
    public List<SensorDto> sensors() {
        return sensorAggregator.getAllSensors();
    }

    @PostMapping("/registration")
    public void registration(
            @RequestBody @Valid final SensorDto sensorDto,
            final BindingResult bindingResult
    ) {
        sensorValidator.validate(sensorDto, bindingResult);
        if (bindingResult.hasErrors()) {
            final StringBuilder errorMessage = new StringBuilder();
            final List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(error -> errorMessage.append(error.getField())
                                                .append(" - ")
                                                .append(error.getDefaultMessage())
                                                .append(";"));
            throw new SensorException(errorMessage.toString());
        }
        sensorAggregator.registration(sensorDto);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(@Nonnull final SensorException exception) {
        final SensorErrorResponse response = new SensorErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
