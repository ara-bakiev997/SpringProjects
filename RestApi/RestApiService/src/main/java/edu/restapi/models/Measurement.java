package edu.restapi.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "measurements")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurement_id")
    private Integer measurementId;

    @Column(name = "value")
    @Nonnull
    private Double value;

    @Column(name = "raining")
    @Nonnull
    private Boolean raining;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sensor_id", referencedColumnName = "sensor_id")
    @Nonnull
    private Sensor sensor;
}
