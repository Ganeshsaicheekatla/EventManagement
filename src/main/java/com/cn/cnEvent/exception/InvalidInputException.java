package com.cn.cnEvent.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends  RuntimeException{

    public InvalidInputException(String msg){
        super(msg);
    }
}