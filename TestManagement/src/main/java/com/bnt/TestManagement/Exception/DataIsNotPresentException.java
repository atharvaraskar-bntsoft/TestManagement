package com.bnt.TestManagement.Exception;

public class DataIsNotPresentException extends RuntimeException{
    public DataIsNotPresentException(String message) {
        super(message);
    }
}
