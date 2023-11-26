CREATE TABLE sensors (
    sensor_id INT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name      VARCHAR NOT NULL CHECK (LENGTH(name) > 2 AND LENGTH(name) < 31),
    CONSTRAINT uk_sensors_name UNIQUE (name)
);