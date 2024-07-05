package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.McqQuestion;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Service.McqQuestionService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class McqQuestionControllerTest {
    @Mock
    McqQuestionService mcqQuestionService;

    @InjectMocks
    McqQuestionController mcqQuestionController;

    public McqQuestion ExpectedData() {
        
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
    void saveMcqQuestionTest(){
        McqQuestion expectedQuestion = ExpectedData();
        when(mcqQuestionService.saveMcqQuestion(expectedQuestion)).thenReturn(expectedQuestion);
        ResponseEntity<McqQuestion> responseEntity = mcqQuestionController.createMcqQuestion(expectedQuestion);
        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(expectedQuestion, responseEntity.getBody());
    }

    @Test
    void updateMcqQuestionTest(){
           McqQuestion expectedQuestion = ExpectedData();
           when(mcqQuestionService.updateMcqQuestion(expectedQuestion)).thenReturn(expectedQuestion);
           ResponseEntity<McqQuestion> actualResponseEntity=mcqQuestionController.updateMcqQuestion(expectedQuestion);
           assertEquals(OK, actualResponseEntity.getStatusCode());

    }

    @Test
    void getMcqQuestionByIdTest() {
        int id = 1;
        McqQuestion expectedQuestion = ExpectedData();
        Optional<McqQuestion> optionalQuestion = Optional.of(expectedQuestion);
        when(mcqQuestionService.getMcqQuestionById(id)).thenReturn(optionalQuestion);
        ResponseEntity<Optional<McqQuestion>> actualResponseEntity = mcqQuestionController.getMcqQuestionById(id);
        assertEquals(FOUND, actualResponseEntity.getStatusCode());
        assertSame(optionalQuestion, actualResponseEntity.getBody());
    }

     @Test
    void getAllMcqQuestionTest() {
        List<McqQuestion> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(ExpectedData());
        when(mcqQuestionService.getAllEMcqQuestions()).thenReturn(expectedQuestions);
        ResponseEntity<List<McqQuestion>> actualResponseEntity = mcqQuestionController.getAllMcqQuestion();

        assertEquals(FOUND, actualResponseEntity.getStatusCode());
        assertSame(expectedQuestions, actualResponseEntity.getBody());
    }


    @Test
    void deleteMcqQuestionTest(){
         int id=1;
        doNothing().when(mcqQuestionService).deleteMcqQuestion(id);
        ResponseEntity<String> actualResponseEntity=mcqQuestionController.deleteMcqQuestion(id);
        assertEquals(OK, actualResponseEntity.getStatusCode());
        assertEquals("User Deleted Successfully", actualResponseEntity.getBody());

    }

 
    
}
