package edu.restapi.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "measurements")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @SequenceGenerator(name = "measurements_measurement_id_seq")
    @Column(name = "measurement_id", unique = true)
    private Integer measurementId;

    @Column(name = "value")
    @Nonnull
    private Double value;

    @Column(name = "raining")
    @Nonnull
    private Boolean raining;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id", referencedColumnName = "sensor_id")
    @Setter
    @Nonnull
    private Sensor sensor;
}
