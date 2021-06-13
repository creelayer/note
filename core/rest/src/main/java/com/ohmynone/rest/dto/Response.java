package com.ohmynone.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Response<T> {

    public Response(T data){
        this.data = data;
    }

    private String message;
    private T data;
}
