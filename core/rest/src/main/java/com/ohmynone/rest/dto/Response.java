package com.ohmynone.rest.dto;

public class Response<T> {

    public boolean status = true;
    public String message;
    public T data;

    public Response(String message){
        this.message = message;
    }

    public Response(T data){
        this.data = data;
    }
}
