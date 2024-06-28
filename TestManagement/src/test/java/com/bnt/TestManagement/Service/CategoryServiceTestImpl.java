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
import com.bnt.TestManagement.Repository.CategoryRepository;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTestImpl {

     @Mock
     CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImpl categoryServiceImpl;  

         static Category  ExpectedData(){
        Category expectedCategory=new Category();
        expectedCategory.setCategoryId(1);
        expectedCategory.setCategoryName("Java");
        expectedCategory.setCategoryDescription("Core Java category");
         return expectedCategory;
    }

     @Test
    void SaveCategoryTest() {
        Category expectedCategory = ExpectedData();
        when(categoryRepository.save(expectedCategory)).thenReturn(expectedCategory);

        Category savedCategory = categoryServiceImpl.saveCategory(expectedCategory);
        assertEquals(expectedCategory, savedCategory);
        verify(categoryRepository, times(1)).save(expectedCategory);
    }

   @Test
void GetCategoryByIdTest() {
    int id = 1;
    Category expectedCategory = ExpectedData();

    when(categoryRepository.findById(id)).thenReturn(Optional.of(expectedCategory));

    Optional<Category> retrievedCategory = categoryServiceImpl.getCategoryById(id);
    assertTrue(retrievedCategory.isPresent()); 
    assertEquals(expectedCategory, retrievedCategory.get()); 

    verify(categoryRepository, times(1)).findById(id);
}

    @Test
    void GetAllCategoriestest() {
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(ExpectedData());

        when(categoryRepository.findAll()).thenReturn(expectedCategories);

        List<Category> retrievedCategories = categoryServiceImpl.getAllCategories();

        assertEquals(expectedCategories, retrievedCategories);
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testUpdateCategory() {
        Category expectedCategory = ExpectedData();
        when(categoryRepository.save(expectedCategory)).thenReturn(expectedCategory);
        Category updatedCategory = categoryServiceImpl.updateCategory(expectedCategory);
        assertEquals(expectedCategory, updatedCategory);
        verify(categoryRepository, times(1)).save(expectedCategory);
    }



    @Test
    void DeleteCategoryTest() {
        int id = 1;
        categoryServiceImpl.deleteCategory(id);
        verify(categoryRepository, times(1)).deleteById(id);
    }



    


}
