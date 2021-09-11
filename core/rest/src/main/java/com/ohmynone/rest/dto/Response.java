package com.ohmynone.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Response<T> {
    private boolean status;
    private String message;
    private T data;

    public Response(T data){
        this.status = false;
        this.data = data;
    }
}
