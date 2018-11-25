package com.manjurulhoque.scheduler.model;

import java.io.Serializable;

public class Email implements Serializable {

    public String id;
    public String recipient;
    public String subject;
    public String body;
    public String time;
    public String date;
    public long milli;

    public Email() {
    }

    public Email(String id, String recipient, String subject, String body, String time, String date, long milli) {
        this.id = id;
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
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

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
}
