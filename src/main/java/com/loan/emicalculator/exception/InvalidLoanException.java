package com.loan.emicalculator.exception;

public class InvalidLoanException extends RuntimeException {

    public InvalidLoanException(String message) {
        super(message);
    }

    public InvalidLoanException(String message, Throwable cause) {
        super(message, cause);
    }
}