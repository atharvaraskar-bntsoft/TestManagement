package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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

import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Service.SubCategoryService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SubCategoryControllerTest {

     @Mock
    SubCategoryService subCategoryService;

    @InjectMocks
    SubCategoryController subCategoryController;

     static SubCategory createSampleSubCategory() {
        Category category = new Category(1, "Java", "Core Java category");
        return new SubCategory(1, category, "Collections", "Collections from Java");
    }

    @Test
    void CreateSubCategorytest() {
        SubCategory expectedSubCategory = createSampleSubCategory();
        when(subCategoryService.saveSubCategory(any(SubCategory.class))).thenReturn(expectedSubCategory);
        ResponseEntity<SubCategory> responseEntity = subCategoryController.createubCategory(expectedSubCategory);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(expectedSubCategory, responseEntity.getBody());
    }

        

    @Test
    void testGetAllSubCategories() {
        List<SubCategory> expectedSubCategories = new ArrayList<>();
        expectedSubCategories.add(createSampleSubCategory());
        when(subCategoryService.getAllSubCategories()).thenReturn(expectedSubCategories);
        ResponseEntity<List<SubCategory>> responseEntity = subCategoryController.getAllSubCategories();

        assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
        assertEquals(expectedSubCategories, responseEntity.getBody());
    }

    @Test
    void testUpdateSubCategory() {
        SubCategory subCategoryToUpdate = createSampleSubCategory();
        when(subCategoryService.updateSubCategory(any(SubCategory.class))).thenReturn(subCategoryToUpdate);
        ResponseEntity<SubCategory> responseEntity = subCategoryController.updateSubCategory(subCategoryToUpdate);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subCategoryToUpdate, responseEntity.getBody());
    }

    @Test
    void testDeleteSubCategory() {
        int subCategoryId = 1;
        ResponseEntity<Object> responseEntity = subCategoryController.deleteSubCategory(subCategoryId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Subcategory Deleted Successfully", responseEntity.getBody());
        verify(subCategoryService, times(1)).deleteSubCategory(subCategoryId);
    }



}
