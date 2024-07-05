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
    public ResponseEntity<String> handleExcelProcessingException(ExcelProcessingException ex) {
        logger.error("Excel processing exception occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing Excel file");
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        logger.error("Category not found exception occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SubCategoryNotFoundException.class)
    public ResponseEntity<String> handleSubCategoryNotFoundException(SubCategoryNotFoundException ex) {
        logger.error("SubCategory not found exception occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CategorySubCategoryMismatchException.class)
    public ResponseEntity<String> handleCategorySubCategoryMismatchException(CategorySubCategoryMismatchException ex) {
        logger.error("Category and SubCategory mismatch exception: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<String> handleDuplicateData(DuplicateDataException ex) {
        logger.error("Duplicate Data Detected: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(DataIsNullException.class)
    public ResponseEntity<String> handleDataIsNullException(DataIsNullException ex) {
        logger.error("Null data detected: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handleIdNotFoundException(IdNotFoundException ex) {
        logger.error("ID not found exception occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}
