package com.hoteltop.util.exceptions;

/**
 * Created by Vlastelin on 15.04.2016.
 */
public class HotelTopTechnicalException extends Exception {

    public HotelTopTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public HotelTopTechnicalException() {
        super();
    }

    public HotelTopTechnicalException(String message) {
        super(message);
    }
}
