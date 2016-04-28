package com.hoteltop.util.enums;

/**
 * Created by Vlastelin on 03.04.2016.
 */
public enum RoomStatus {

    WAITING_APPROVE("waiting approve"),

    BOOKED("booked"),

    INACTIVE("inactive");

    RoomStatus(String value) {
        this.value = value;
    }

    private String value;

    @Override
    public String toString() {
        return "RoomStatus{" +
                "value='" + value + '\'' +
                '}';
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
