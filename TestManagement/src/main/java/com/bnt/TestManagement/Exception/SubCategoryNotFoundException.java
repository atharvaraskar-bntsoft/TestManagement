package com.bnt.TestManagement.Exception;

public class SubCategoryNotFoundException extends RuntimeException {
    public SubCategoryNotFoundException(String subCategoryName) {
        super("SubCategory not found: " + subCategoryName);
    }
}
