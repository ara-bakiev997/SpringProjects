package edu.restapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDto {
    private double value;

    private boolean raining;

    private SensorDto sensor;
}
