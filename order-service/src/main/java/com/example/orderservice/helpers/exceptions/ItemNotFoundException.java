package com.example.orderservice.helpers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,reason = "We Dont have The Item with respective Product Code")
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(){}

    @Override
    public String getMessage() {
        return "We Dont have The Item with respective Product Code";
    }
}
