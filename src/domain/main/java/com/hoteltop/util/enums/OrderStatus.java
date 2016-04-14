package com.hoteltop.util.enums;

/**
 * Created by Vlastelin on 03.04.2016.
 */
public enum OrderStatus {

    WAITING("waiting"),

    SUCCESSFUL("successful"),

    CANCELED("canceled");

    OrderStatus(String value) {
        this.value = value;
    }

    private String value;

    @Override
    public String toString() {
        return "OrderStatus{" +
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
