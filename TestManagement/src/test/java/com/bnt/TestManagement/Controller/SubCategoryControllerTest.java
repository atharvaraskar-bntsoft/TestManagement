package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bnt.TestManagement.Exception.DataIsNotPresentException;
import com.bnt.TestManagement.Exception.DataIsNullException;
import com.bnt.TestManagement.Exception.IdNotFoundException;
import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Service.SubCategoryService;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SubCategoryControllerTest {

     @Mock
    SubCategoryService subCategoryService;

    @InjectMocks
    SubCategoryController subCategoryController;

     public SubCategory createSampleSubCategory() {
        Category category = new Category(1, "Java", "Core Java category");
        return new SubCategory(1, category, "Collections", "Collections from Java");
    }

    @Test
    void CreateSubCategoryTest() {
        SubCategory expectedSubCategory = createSampleSubCategory();
        when(subCategoryService.saveSubCategory(expectedSubCategory)).thenReturn(expectedSubCategory);
        ResponseEntity<SubCategory> responseEntity = subCategoryController.createubCategory(expectedSubCategory);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedSubCategory, responseEntity.getBody());
    }

        

    @Test
    void GetAllSubCategoriesTest() {
        List<SubCategory> expectedSubCategories = new ArrayList<>();
        expectedSubCategories.add(createSampleSubCategory());
        when(subCategoryService.getAllSubCategories()).thenReturn(expectedSubCategories);
        ResponseEntity<List<SubCategory>> responseEntity = subCategoryController.getAllSubCategories();
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(expectedSubCategories, responseEntity.getBody());
    }

    @Test
    void getSubCategoryByIdTest() {
        int id = 1;
        SubCategory expectedSubCategory = createSampleSubCategory();
        Optional<SubCategory> optionalSubCategory = Optional.of(expectedSubCategory);
        when(subCategoryService.getSubCategoryById(id)).thenReturn(optionalSubCategory);
        ResponseEntity<Optional<SubCategory>> responseEntity = subCategoryController.getSubCategoryById(id);
        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(optionalSubCategory, responseEntity.getBody());
    }

    @Test
    void UpdateSubCategoryTest() {
        SubCategory subCategoryToUpdate = createSampleSubCategory();
        when(subCategoryService.updateSubCategory(subCategoryToUpdate)).thenReturn(subCategoryToUpdate);
        ResponseEntity<SubCategory> responseEntity = subCategoryController.updateSubCategory(subCategoryToUpdate);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subCategoryToUpdate, responseEntity.getBody());
    }

    @Test
    void DeleteSubCategoryTest() {
        int subCategoryId = 1;
        ResponseEntity<Object> responseEntity = subCategoryController.deleteSubCategory(subCategoryId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Subcategory Deleted Successfully", responseEntity.getBody());
        verify(subCategoryService, times(1)).deleteSubCategory(subCategoryId);
    }


    @Test
    void createubCategory_NullSubCategoryTest() {
        SubCategory nullSubCategory = null;
        when(subCategoryService.saveSubCategory(nullSubCategory)).thenThrow(new DataIsNullException("SubCategory cannot be null"));
        DataIsNullException exception = assertThrows(DataIsNullException.class, () -> {
            subCategoryController.createubCategory(nullSubCategory);
        });
        assertEquals("SubCategory cannot be null", exception.getMessage());
    }

    @Test
    void getAllSubCategories_EmptyListTest() {
        when(subCategoryService.getAllSubCategories()).thenThrow(new DataIsNotPresentException("No subcategories found"));
        DataIsNotPresentException exception = assertThrows(DataIsNotPresentException.class, () -> {
            subCategoryController.getAllSubCategories();
        });
        assertEquals("No subcategories found", exception.getMessage());
    }

    @Test
    void getSubCategoryById_IdNotFoundTest() {
        int id = 999;
        String errorMessage = "SubCategory not found with ID: " + id;
        doThrow(new IdNotFoundException(errorMessage)).when(subCategoryService).getSubCategoryById(id);
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
            subCategoryController.getSubCategoryById(id);
        });
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void updateSubCategory_IdNotFoundTest() {
        int id = 999;
        String errorMessage = "SubCategory not found with ID: " + id;
        SubCategory nonExistingSubCategory = new SubCategory(id, new Category(), "DotNet", "Descrption about DotNet");
        when(subCategoryService.updateSubCategory(nonExistingSubCategory)).thenThrow(new IdNotFoundException(errorMessage));
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
            subCategoryController.updateSubCategory(nonExistingSubCategory);
        });
        assertEquals(errorMessage, exception.getMessage());
    }


    @Test
    void deleteSubCategory_IdNotFoundTest() {
        int id = 999;
        String errorMessage = "SubCategory not found with ID: " + id;
        doThrow(new IdNotFoundException(errorMessage)).when(subCategoryService).deleteSubCategory(id);
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
            subCategoryController.deleteSubCategory(id);
        });
        assertEquals(errorMessage, exception.getMessage());
    }



}
