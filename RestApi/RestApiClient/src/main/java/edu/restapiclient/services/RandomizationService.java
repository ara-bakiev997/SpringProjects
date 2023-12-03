package edu.restapiclient.services;

import edu.restapiclient.dtos.MeasurementDto;
import edu.restapiclient.dtos.SensorDto;
import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class RandomizationService {
    @Nonnull
    public List<MeasurementDto> createRandomMeasurementsBySensorNameAndCount(
            @Nonnull final String sensorName,
            final int count
    ) {
        final Random random = new Random();
        return IntStream.range(0, count).mapToObj(
                i -> new MeasurementDto(
                        random.nextDouble(),
                        random.nextBoolean(),
                        new SensorDto(sensorName)
                )).collect(Collectors.toCollection(() -> new ArrayList<>(count)));
    }
}
