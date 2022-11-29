package com.example.reward.constant;

import org.springframework.http.HttpStatus;

public enum ResponseCode {
    Ok(HttpStatus.OK, "Point processed"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "Invalid request"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "No record found");

    public HttpStatus code;
    public String message;

    ResponseCode(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
}
