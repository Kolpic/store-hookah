package com.cloud.rebellion.demo.exception.handler;

import com.cloud.rebellion.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(NoSuchHookahException.class)
    public ResponseEntity<String> handleNoSuchHookahException(NoSuchHookahException noSuchHookahException) {
        return new ResponseEntity<>(noSuchHookahException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchFieldTypeException.class)
    public ResponseEntity<String> handleNoSuchFieldTypeException(NoSuchFieldTypeException noSuchFieldTypeException) {
        return new ResponseEntity<>(noSuchFieldTypeException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExceededBlobLengthException.class)
    public ResponseEntity<String> handleExceededBlobLengthException(ExceededBlobLengthException exceededBlobLengthException) {
        return new ResponseEntity<>(exceededBlobLengthException.getMessage(), HttpStatus.NOT_FOUND);
    }

     @ExceptionHandler(EmptyPatchMapFieldsException.class)
    public ResponseEntity<String> handleEmptyPatchMapFieldsException(EmptyPatchMapFieldsException emptyPatchMapFieldsException) {
        return new ResponseEntity<>(emptyPatchMapFieldsException.getMessage(), HttpStatus.NOT_FOUND);
    }

}
