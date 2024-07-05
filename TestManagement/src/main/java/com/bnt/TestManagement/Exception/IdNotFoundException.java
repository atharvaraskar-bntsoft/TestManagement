package com.bnt.TestManagement.Exception;

public class IdNotFoundException  extends RuntimeException{
    
    public IdNotFoundException(String message) {
        super(message);
    }
}
