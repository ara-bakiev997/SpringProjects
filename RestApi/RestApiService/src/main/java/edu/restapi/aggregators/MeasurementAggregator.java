package edu.restapi.aggregators;

import edu.restapi.convertors.MeasurementConvertor;
import edu.restapi.dtos.MeasurementDto;
import edu.restapi.services.MeasurementService;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MeasurementAggregator {
    private final MeasurementService measurementService;
    private final MeasurementConvertor measurementConvertor;

    @Nonnull
    public List<MeasurementDto> getAllMeasurements() {
        return measurementConvertor.convertMeasurementsToDtos(
                measurementService.getAllMeasurements()
        );
    }

    public void add(@Nonnull final MeasurementDto measurementDto) {
        measurementService.save(measurementConvertor.convertDtoToMeasurement(measurementDto));
    }

}
