package com.tiunida.laundry0.ActivityForgetPass.events;

public class ForgetPassEvents {
    public static final int onSendEmailError = 0;
    public static final int onSendEmailSucces = 1;

    private int eventType;
    private String errorMessage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
