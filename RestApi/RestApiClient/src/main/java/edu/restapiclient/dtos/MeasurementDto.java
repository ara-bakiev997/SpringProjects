package edu.restapiclient.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDto {
    private Double value;

    private Boolean raining;

    private SensorDto sensor;
}
