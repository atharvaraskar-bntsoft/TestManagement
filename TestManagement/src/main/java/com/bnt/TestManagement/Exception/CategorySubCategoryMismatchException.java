package com.bnt.TestManagement.Exception;

public class CategorySubCategoryMismatchException extends RuntimeException  {

    public CategorySubCategoryMismatchException(int rowNum)  {
        super("Category and SubCategory mismatch for row " + rowNum);
    }

}
