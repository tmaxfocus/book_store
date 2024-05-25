package com.tmaxnoda.bookinventory.Core.Application.Responses;

public class GenericResponse<T> {
    private int status;
    private String Message;
    private long timeStamp;
    private T data;

    public GenericResponse(){}
    public GenericResponse(int status, String message, long timeStamp, T data) {
        this.status = status;
        Message = message;
        this.timeStamp = timeStamp;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "status=" + status +
                ", Message='" + Message + '\'' +
                ", timeStamp=" + timeStamp +
                ", data=" + data +
                '}';
    }
}
