package com.bnt.TestManagement.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ExcelProcessingException.class)
     public void handleExcelProcessingException(ExcelProcessingException ex) {
        logger.error("Global exception handler caught an exception:", ex.getMessage());;
    }

     
    @ExceptionHandler(CategoryNotFoundException.class)
    public void handleCategoryNotFoundException(CategoryNotFoundException ex) {
        logger.error("Category not found exception occurred: {}", ex.getMessage());
    }

    @ExceptionHandler(SubCategoryNotFoundException.class)
    public void handleSubCategoryNotFoundException(SubCategoryNotFoundException ex) {
        logger.error("SubCategory not found exception occurred: {}", ex.getMessage());
    }

    @ExceptionHandler(CategorySubCategoryMismatchException.class)
    public void handleCategorySubCategoryMismatchException(CategorySubCategoryMismatchException ex) {
        logger.error("Category and SubCategory mismatch exception: {}", ex.getMessage());
    }

    @ExceptionHandler(DuplicateDataException.class)
    public void handleDuplicateData ( DuplicateDataException ex) {
        logger.error("Duplicate Data Detected: {}", ex.getMessage());
    }

 



}
