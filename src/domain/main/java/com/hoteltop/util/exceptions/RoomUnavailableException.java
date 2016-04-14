package com.hoteltop.util.exceptions;

/**
 * Created by Vlastelin on 13.04.2016.
 */
public class RoomUnavailableException extends Exception {

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
