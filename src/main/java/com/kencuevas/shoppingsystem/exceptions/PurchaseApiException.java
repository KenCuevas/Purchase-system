package com.kencuevas.shoppingsystem.exceptions;

import org.springframework.http.HttpStatus;

public class PurchaseApiException extends RuntimeException{
    private HttpStatus http;
    private String message;

    public PurchaseApiException(HttpStatus http, String message) {
        this.http = http;
        this.message = message;
    }

    public PurchaseApiException(String message, HttpStatus http, String message1) {
        super(message);
        this.http = http;
        this.message = message1;
    }

    public HttpStatus getHttp() {
        return http;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
