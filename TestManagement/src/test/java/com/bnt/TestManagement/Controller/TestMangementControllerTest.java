package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.McqQuestion;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Service.TestManageMentService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestMangementControllerTest {
    @Mock
    TestManageMentService testManageMentService;

    @InjectMocks
    TestManagementController testManagementController;

    static McqQuestion ExpectedData() {
        
        Category category = new Category();
        category.setCategoryId(3); 
        category.setCategoryName("Spring Boot");
        category.setCategoryDescription("Spring Boot Framework category");

        SubCategory subcategory = new SubCategory();
        subcategory.setSubcategoryId(4); 
        subcategory.setCategory(category);
        subcategory.setSubcategoryName("Annotation");
        subcategory.setSubcategoryDescription("Annotations in Spring");

        McqQuestion expectedQuestion = new McqQuestion();
        expectedQuestion.setQuestion_id(1);
        expectedQuestion.setSubCategory(subcategory); 
        expectedQuestion.setQuestion("In Spring Boot @RestController annotation is equivalent to");
        expectedQuestion.setOption_one("@Controller and @PostMapping");
        expectedQuestion.setOption_two("@Controller and @Component");
        expectedQuestion.setOption_three("@Controller and @ResponseBody");
        expectedQuestion.setOption_four("@Controller and @ResponseStatus");
        expectedQuestion.setCorrect_option("@Controller and @ResponseBody");
        expectedQuestion.setPositive_mark(3);
        expectedQuestion.setNegative_mark(-1);

        return expectedQuestion;
    }

   


    @Test
    void saveMcqQuestion(){
        McqQuestion expectedQuestion = ExpectedData();

        when(testManageMentService.saveMcqQuestion(expectedQuestion)).thenReturn(expectedQuestion);

        ResponseEntity<Object> responseEntity = testManagementController.createMcqQuestion(expectedQuestion);

        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(expectedQuestion, responseEntity.getBody());
    }

    @Test
    void updateMcqQuestion(){
        McqQuestion expectedQuestion = ExpectedData();
            when(testManageMentService.updateMcqQuestion(expectedQuestion)).thenReturn(expectedQuestion);

           ResponseEntity<Object> actualResponseEntity=testManagementController.updateMcqQuestion(expectedQuestion);
           assertEquals(OK, actualResponseEntity.getStatusCode());

    }

    @Test
    void getMcqQuestionById() {
        int id = 1;
        McqQuestion expectedQuestion = ExpectedData();
        Optional<McqQuestion> optionalQuestion = Optional.of(expectedQuestion);

        when(testManageMentService.getMcqQuestionById(id)).thenReturn(optionalQuestion);
        ResponseEntity<Object> actualResponseEntity = testManagementController.getMcqQuestionById(id);
        assertEquals(FOUND, actualResponseEntity.getStatusCode());
        assertSame(optionalQuestion, actualResponseEntity.getBody());
    }

     @Test
    void getAllMcqQuestion() {
        List<McqQuestion> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(ExpectedData());
        when(testManageMentService.getAllEMcqQuestions()).thenReturn(expectedQuestions);
        ResponseEntity<Object> actualResponseEntity = testManagementController.getAllMcqQuestion();

        assertEquals(FOUND, actualResponseEntity.getStatusCode());
        assertSame(expectedQuestions, actualResponseEntity.getBody());
    }


    @Test
    void deleteMcqQuestion(){
         int id=1;
        doNothing().when(testManageMentService).deleteMcqQuestion(id);
        ResponseEntity<Object> actualResponseEntity=testManagementController.deleteMcqQuestion(id);
        assertEquals(OK, actualResponseEntity.getStatusCode());
        assertEquals("User Deleted Successfully", actualResponseEntity.getBody());

    }

 
    
}
