package edu.restapi.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDto {
    @NotNull(message = "Value should not be empty")
    @Range(min = -100, max = 100, message = "Value should be between -100 and 100")
    private Double value;

    @NotNull(message = "Raining should not be empty")
    private Boolean raining;

    private SensorDto sensor;
}
