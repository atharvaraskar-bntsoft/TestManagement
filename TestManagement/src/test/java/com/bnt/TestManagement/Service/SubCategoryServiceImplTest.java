package com.bnt.TestManagement.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Repository.SubCategoryRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SubCategoryServiceImplTest {

    @Mock
    SubCategoryRepository subCategoryRepository;

    @InjectMocks
    SubCategoryServiceImpl subCategoryServiceImpl; 

    public SubCategory ExpectedData() {
        Category category = new Category(1, "Java", "Core Java category");
        return new SubCategory(1, category, "Collections", "Collections from Java");
    }
    
    @Test
    void SaveSubCategoryTest() {
        SubCategory expectedSubCategory = ExpectedData();
        when(subCategoryRepository.save(expectedSubCategory)).thenReturn(expectedSubCategory);
        SubCategory savedSubCategory = subCategoryServiceImpl.saveSubCategory(expectedSubCategory);
        assertEquals(expectedSubCategory, savedSubCategory);
        verify(subCategoryRepository, times(1)).save(expectedSubCategory);
      
    }

    @Test
    void GetSubCategoryByIdTest() {
        int id = 1;
        SubCategory expectedSubCategory = ExpectedData();

        when(subCategoryRepository.findById(id)).thenReturn(Optional.of(expectedSubCategory));

        Optional<SubCategory> retrievedSubCategory = subCategoryServiceImpl.getSubCategoryById(id);
        assertTrue(retrievedSubCategory.isPresent());
        assertEquals(expectedSubCategory, retrievedSubCategory.get());
        verify(subCategoryRepository, times(1)).findById(id);
    }
      
    @Test
     void GetAllSubCategoriesTest() {
        List<SubCategory> expectedSubCategories = new ArrayList<>();
        expectedSubCategories.add(ExpectedData());

        when(subCategoryRepository.findAll()).thenReturn(expectedSubCategories);

        List<SubCategory> retrievedSubCategories = subCategoryServiceImpl.getAllSubCategories();

        assertEquals(expectedSubCategories, retrievedSubCategories);
        verify(subCategoryRepository, times(1)).findAll();
    }

    @Test
    void UpdateSubCategoryTest() {
        SubCategory expectedSubCategory = ExpectedData();
        when(subCategoryRepository.findById(expectedSubCategory.getSubcategoryId())).thenReturn(Optional.of(expectedSubCategory));
        when(subCategoryRepository.save(expectedSubCategory)).thenReturn(expectedSubCategory);
        SubCategory updatedSubCategory = subCategoryServiceImpl.updateSubCategory(expectedSubCategory);
        assertEquals(expectedSubCategory, updatedSubCategory);
        verify(subCategoryRepository, times(1)).save(expectedSubCategory);
    }



    @Test
    void testDeleteSubCategory() {
        int id = 1;
        SubCategory expectedSubCategory = ExpectedData();
        when(subCategoryRepository.findById(id)).thenReturn(Optional.of(expectedSubCategory));
        subCategoryServiceImpl.deleteSubCategory(id);
        verify(subCategoryRepository, times(1)).deleteById(id);
    }
  


    
}
