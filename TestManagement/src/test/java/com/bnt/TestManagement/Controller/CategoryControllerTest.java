package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

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

     static Category  ExpectedData(){
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






}
