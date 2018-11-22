package com.manjurulhoque.scheduler.model;

import java.io.Serializable;

public class Sms implements Serializable {
    public String id;
    public String number;
    public String message;
    public String time;
    public String date;
    public long milli;

    public Sms() {
    }

    public Sms(String id, String number, String message, String time, String date, long milli) {
        this.id = id;
        this.number = number;
        this.message = message;
        this.time = time;
        this.date = date;
        this.milli = milli;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getMilli() {
        return milli;
    }

    public void setMilli(long milli) {
        this.milli = milli;
    }

    @Override
    public String toString() {
        return getNumber() + " " + getMessage() + " " + getDate() + " " + getTime();
    }
}
