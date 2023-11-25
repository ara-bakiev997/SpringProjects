package edu.restapi.dtos;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SensorDto {
    private int sensorId;
    @Nonnull
    private String name;
}
