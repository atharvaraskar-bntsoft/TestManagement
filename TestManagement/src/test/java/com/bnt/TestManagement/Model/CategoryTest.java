package com.bnt.TestManagement.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CategoryTest {

    @Test
    void SetAndGetCategoryIdTest() {
        Category category = new Category();
        category.setCategoryId(1);
        assertEquals(1, category.getCategoryId());
    }

    @Test
    void SetAndGetCategoryNameTest() {
        Category category = new Category();
        category.setCategoryName("Java");
        assertEquals("Java", category.getCategoryName());
    }

    @Test
    void SetAndGetCategoryDescriptionTest() {
        Category category = new Category();
        category.setCategoryDescription("Java programming language");
        assertEquals("Java programming language", category.getCategoryDescription());
    }



    
}
