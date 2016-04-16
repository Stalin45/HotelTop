package com.hoteltop.util.exceptions;

/**
 * Created by Vlastelin on 14.04.2016.
 */
public class HotelTopRuntimeException extends RuntimeException {
    public HotelTopRuntimeException() {
        super();
    }

    public HotelTopRuntimeException(String message) {
        super(message);
    }

    public HotelTopRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
