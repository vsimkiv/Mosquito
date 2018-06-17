package com.softserve.mosquito.entities;

public class ErrorInfo {

    private String message;
    private String url;
    private int status;

    public ErrorInfo() {
    }

    public ErrorInfo(String message, String url, int status) {
        this.message = message;
        this.url = url;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
