package edu.restapi.utils.exceptions;

public class SensorException extends RuntimeException {
    public SensorException() {
        super();
    }

    public SensorException(final String message) {
        super(message);
    }

    public SensorException(final Exception e) {
        super(e);
    }
}