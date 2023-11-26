CREATE TABLE measurements (
    measurement_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    value          DOUBLE PRECISION NOT NULL CHECK ( value > -100 AND value < 100),
    raining        BOOLEAN          NOT NULL,
    sensor_id      INT              REFERENCES sensors(sensor_id) ON DELETE SET NULL
);