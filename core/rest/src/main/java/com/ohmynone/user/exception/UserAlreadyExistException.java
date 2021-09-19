package com.ohmynone.user.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String message){
        super(message);
    }

}
