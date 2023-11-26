package edu.restapi.utils.exceptions;

public class MeasurementException extends RuntimeException {
    public MeasurementException() {
        super();
    }

    public MeasurementException(final String message) {
        super(message);
    }

    public MeasurementException(final Exception e) {
        super(e);
    }
}