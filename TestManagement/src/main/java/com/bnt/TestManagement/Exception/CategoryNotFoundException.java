package com.bnt.TestManagement.Exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String categoryName) {
        super("Category not found: " + categoryName);
    }

}
