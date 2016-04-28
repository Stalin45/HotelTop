package com.hoteltop.util.exceptions;

/**
 * Created by Vlastelin on 13.04.2016.
 */
public class RoomUnavailableException extends HotelTopRuntimeException {

    public RoomUnavailableException() {
        super();
    }

    public RoomUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoomUnavailableException(String message) {
        super(message);
    }
}
