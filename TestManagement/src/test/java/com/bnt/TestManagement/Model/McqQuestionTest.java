package com.bnt.TestManagement.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class McqQuestionTest {

     @Test
    public void SettersTest() {
        Category category = new Category(1, "Java", "Java programming language");

        SubCategory subCategory = new SubCategory();
        subCategory.setSubcategoryId(2);
        subCategory.setCategory(category);
        subCategory.setSubcategoryName("Annotations");
        subCategory.setSubcategoryDescription("Annotations in Java");

        assertEquals(2, subCategory.getSubcategoryId());
        assertEquals(category, subCategory.getCategory());
        assertEquals("Annotations", subCategory.getSubcategoryName());
        assertEquals("Annotations in Java", subCategory.getSubcategoryDescription());
    }

    @Test
    public void GettersTest() {
        Category category = new Category(1, "Java", "Java programming language");

        SubCategory subCategory = new SubCategory();
        subCategory.setSubcategoryId(1);
        subCategory.setCategory(category);
        subCategory.setSubcategoryName("Collections");
        subCategory.setSubcategoryDescription("Collections in Java");

        assertEquals(1, subCategory.getSubcategoryId());
        assertEquals(category, subCategory.getCategory());
        assertEquals("Collections", subCategory.getSubcategoryName());
        assertEquals("Collections in Java", subCategory.getSubcategoryDescription());
    }




}
