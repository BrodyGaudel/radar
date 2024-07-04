package org.mounanga.radarservice.common.exception;

public class RadarNotFoundException extends RuntimeException {
    public RadarNotFoundException(String message) {
        super(message);
    }
}
