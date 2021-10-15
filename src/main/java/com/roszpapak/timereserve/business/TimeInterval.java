package com.roszpapak.timereserve.business;

public enum TimeInterval {

    QUARTER(15),
    HALF(30),
    HOUR(60),
    TWO_HOUR(120);

    private int minute;

    TimeInterval(int minute){

        this.minute = minute;

    }

    int getMinute() {
        return minute;
    }

}
