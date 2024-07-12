package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.bnt.TestManagement.Exception.DataIsNotPresentException;
import com.bnt.TestManagement.Exception.DataIsNullException;
import com.bnt.TestManagement.Exception.IdNotFoundException;
import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Service.CategoryService;
import static org.springframework.http.HttpStatus.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
     CategoryController categoryController;

     public Category  ExpectedData(){
        Category expectedCategory=new Category();
        expectedCategory.setCategoryId(1);
        expectedCategory.setCategoryName("Java");
        expectedCategory.setCategoryDescription("Core Java category");
         return expectedCategory;
    }


        @Test
        void saveCategoryTest(){
            Category expectedCategory = ExpectedData();
            when(categoryService.saveCategory(expectedCategory)).thenReturn(expectedCategory);  
            ResponseEntity<Category> responseEntity = categoryController.createCategory(expectedCategory); 
            assertEquals(CREATED, responseEntity.getStatusCode());
            assertEquals(expectedCategory, responseEntity.getBody());
     }

     @Test
     void updateCategoryTest(){
            Category expectedCategory = ExpectedData();
             when(categoryService.updateCategory(expectedCategory)).thenReturn(expectedCategory);
            ResponseEntity<Category> actualResponseEntity=categoryController.updateCategory(expectedCategory);
            assertEquals(OK, actualResponseEntity.getStatusCode()); 
     }

     @Test
     void getCategoryByIdTest() {
         int id = 1;
         Category expectedCategory = ExpectedData();
         Optional<Category> optionalCategory = Optional.of(expectedCategory);
         when(categoryService.getCategoryById(id)).thenReturn(optionalCategory);   
         ResponseEntity<Optional<Category>>actualresponseEntity = categoryController.getCategoryById(id);
         assertEquals(FOUND, actualresponseEntity.getStatusCode());     
     }

     @Test
    void getAllCategoriesTest() {
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(ExpectedData());
        when(categoryService.getAllCategories()).thenReturn(expectedCategories);
        ResponseEntity<List<Category>> responseEntity = categoryController.getAllCategories();
        assertEquals(FOUND, responseEntity.getStatusCode());
        assertSame(expectedCategories, responseEntity.getBody());
    }

    @Test
     void deleteCategoryTest() {
        int id = 1;
        doNothing().when(categoryService).deleteCategory(id);
        ResponseEntity<Object> responseEntity = categoryController.deleteCategory(id);
        assertEquals(OK, responseEntity.getStatusCode());
        assertEquals("Category Deleted Successfully", responseEntity.getBody());
    }

    @Test
    void createCategory_NullCategoryTest() {
        Category nullCategory = null;
        when(categoryService.saveCategory(nullCategory)).thenThrow(new DataIsNullException("Category cannot be null"));
        DataIsNullException exception = assertThrows(DataIsNullException.class, () -> {
            categoryController.createCategory(nullCategory);
        });
        assertEquals("Category cannot be null", exception.getMessage());
    }


    @Test
    void updateCategory_IdNotFoundTest() {
        int id = 999;
        String errorMessage = "Category not found with ID: " + id;
        Category nonExistingIdCategory = new Category(id, "Python", "Description of python");
        when(categoryService.updateCategory(nonExistingIdCategory)).thenThrow(new IdNotFoundException(errorMessage));
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
            categoryController.updateCategory(nonExistingIdCategory);
        });
        assertEquals(errorMessage, exception.getMessage());
    }

        @Test
    void getAllCategories_EmptyListTest() {
        when(categoryService.getAllCategories()).thenThrow(new DataIsNotPresentException("No categories found"));
        DataIsNotPresentException exception = assertThrows(DataIsNotPresentException.class, () -> {
            categoryController.getAllCategories();
        });
        assertEquals("No categories found", exception.getMessage());
    }
        
        
   @Test
    void getCategoryById_IdNotFoundTest() {
        int id = 999;
        String errorMessage = "Category not found with ID: " + id;
        doThrow(new IdNotFoundException(errorMessage)).when(categoryService).getCategoryById(id);
        Exception exception = assertThrows(IdNotFoundException.class, () -> {
            categoryController.getCategoryById(id); });
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void deleteCategory_IdNotFoundTest() {
        int id = 999;
        String errorMessage = "Category not found with ID: " + id;
        doThrow(new IdNotFoundException(errorMessage)).when(categoryService).deleteCategory(id);
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
            categoryController.deleteCategory(id);
        });
        assertEquals(errorMessage, exception.getMessage());
    }


}
