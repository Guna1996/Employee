package com.ideas2it.employeeProject.exception;

/**
 * The {@code CustomException} has methods used to throw the caught Exception.
 *
 *
 * @author  Gunaseelan K
 * since 1.0
 * jls Advanced logics+
 */
public class CustomException extends Exception {

    public CustomException() {
        super();
    }

    public CustomException(String errorMessage) {
        super(errorMessage);
    }

    public CustomException(Throwable error) {
        super(error);
    }

    public CustomException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}