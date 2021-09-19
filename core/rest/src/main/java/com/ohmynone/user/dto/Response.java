package com.ohmynone.user.dto;

import org.springframework.http.ResponseEntity;

public class Response<T> {

    public boolean status;
    public String message;
    public T data;

    public Response(String message){
        this.message = message;
    }

    public Response(T data){
        this.data = data;
    }

    public static <T> ResponseEntity<Response<T>> ok(T data) {
        return ResponseEntity.ok(new Response<>(data));
    }

    public static ResponseEntity<Response<?>> error(String message) {
        return ResponseEntity.badRequest().body(new Response<>(message));
    }
}
