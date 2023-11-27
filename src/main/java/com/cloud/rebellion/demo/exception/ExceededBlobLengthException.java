package com.cloud.rebellion.demo.exception;

public class ExceededBlobLengthException extends RuntimeException {

    public ExceededBlobLengthException(String message) {
        super(message);
    }
}
