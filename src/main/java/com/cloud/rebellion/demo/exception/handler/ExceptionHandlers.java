package com.cloud.rebellion.demo.exception.handler;

import com.cloud.rebellion.demo.exception.*;
import com.cloud.rebellion.demo.exception.bowl.NoSuchBowlException;
import com.cloud.rebellion.demo.exception.hookah.NoSuchHookahException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler class for the application.
 * This class uses {@link ControllerAdvice} to handle exceptions globally and return appropriate
 * HTTP responses.
 */
@ControllerAdvice
public class ExceptionHandlers {

    /**
     * Handles NoSuchHookahException.
     *
     * @param noSuchHookahException The exception thrown when a requested hookah is not found.
     * @return A ResponseEntity with an error message and the HTTP status NOT_FOUND.
     */
    @ExceptionHandler(NoSuchHookahException.class)
    public ResponseEntity<String> handleNoSuchHookahException(NoSuchHookahException noSuchHookahException) {
        return new ResponseEntity<>(noSuchHookahException.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles NoSuchFieldTypeException.
     *
     * @param noSuchFieldTypeException The exception thrown when a requested field type is not found.
     * @return A ResponseEntity with an error message and the HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(NoSuchFieldTypeException.class)
    public ResponseEntity<String> handleNoSuchFieldTypeException(NoSuchFieldTypeException noSuchFieldTypeException) {
        return new ResponseEntity<>(noSuchFieldTypeException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles ExceededBlobLengthException.
     *
     * @param exceededBlobLengthException The exception thrown when a Blob's length exceeds the allowable limit.
     * @return A ResponseEntity with an error message and the HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(ExceededBlobLengthException.class)
    public ResponseEntity<String> handleExceededBlobLengthException(ExceededBlobLengthException exceededBlobLengthException) {
        return new ResponseEntity<>(exceededBlobLengthException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles EmptyPatchMapFieldsException.
     *
     * @param emptyPatchMapFieldsException The exception thrown when the fields for a PATCH request are empty or invalid.
     * @return A ResponseEntity with an error message and the HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(EmptyPatchMapFieldsException.class)
    public ResponseEntity<String> handleEmptyPatchMapFieldsException(EmptyPatchMapFieldsException emptyPatchMapFieldsException) {
        return new ResponseEntity<>(emptyPatchMapFieldsException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles InvalidObjectFieldsException.
     *
     * @param invalidObjectFieldsException The exception thrown when object fields are invalid.
     * @return A ResponseEntity with an error message and the HTTP status BAD_REQUEST.
     */
    @ExceptionHandler(InvalidObjectFieldsException.class)
    public ResponseEntity<String> handleInvalidObjectFieldsException(InvalidObjectFieldsException invalidObjectFieldsException) {
        return new ResponseEntity<>(invalidObjectFieldsException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchBowlException.class)
    public ResponseEntity<String> handleNoSuchBowlException(NoSuchBowlException noSuchBowlException) {
        return new ResponseEntity<>(noSuchBowlException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
