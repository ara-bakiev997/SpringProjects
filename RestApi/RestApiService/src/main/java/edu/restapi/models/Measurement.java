package edu.restapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "measurements")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurement_id")
    private int measurementId;

    @Column(name = "reining")
    private boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "sensor_id")
    private Sensor sensor;
}
