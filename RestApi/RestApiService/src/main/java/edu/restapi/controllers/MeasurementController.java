package edu.restapi.controllers;

import edu.restapi.aggregators.MeasurementAggregator;
import edu.restapi.dtos.MeasurementDto;
import edu.restapi.utils.erros.MeasurementErrorResponse;
import edu.restapi.utils.exceptions.MeasurementException;
import edu.restapi.utils.validators.MeasurementValidator;
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
@RequestMapping("/measurements")
@AllArgsConstructor
public class MeasurementController {
    private final MeasurementAggregator measurementAggregator;
    private final MeasurementValidator measurementValidator;

    @GetMapping
    public List<MeasurementDto> getAllMeasurements() {
        return measurementAggregator.getAllMeasurements();
    }

    @PostMapping("/add")
    public void add(
            @RequestBody @Valid final MeasurementDto measurementDto,
            final BindingResult bindingResult
    ) {
        measurementValidator.validate(measurementDto, bindingResult);
        if (bindingResult.hasErrors()) {
            final StringBuilder errorMessage = new StringBuilder();
            final List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(error -> errorMessage.append(error.getField())
                                                .append(" - ")
                                                .append(error.getDefaultMessage())
                                                .append(";")
            );
            throw new MeasurementException(errorMessage.toString());
        }
        measurementAggregator.add(measurementDto);
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementAggregator.getRainyDaysCount();
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(@Nonnull final MeasurementException exception) {
        final MeasurementErrorResponse response = new MeasurementErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
