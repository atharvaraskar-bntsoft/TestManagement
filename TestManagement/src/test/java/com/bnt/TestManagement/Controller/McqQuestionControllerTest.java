package com.bnt.TestManagement.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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

import com.bnt.TestManagement.Exception.DataIsNotPresentException;
import com.bnt.TestManagement.Exception.DataIsNullException;
import com.bnt.TestManagement.Exception.IdNotFoundException;
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

    @Test
    void createMcqQuestion_NullQuestionTest() {
        McqQuestion nullQuestion = null;
        when(mcqQuestionService.saveMcqQuestion(nullQuestion)).thenThrow(new DataIsNullException("Question cannot be null"));
        DataIsNullException exception = assertThrows(DataIsNullException.class, () -> {
            mcqQuestionController.createMcqQuestion(nullQuestion);
        });
        assertEquals("Question cannot be null", exception.getMessage());
    }

    @Test
    void getAllMcqQuestions_EmptyListTest() {
        when(mcqQuestionService.getAllEMcqQuestions()).thenThrow(new DataIsNotPresentException("No questions found"));
        DataIsNotPresentException exception = assertThrows(DataIsNotPresentException.class, () -> {
            mcqQuestionController.getAllMcqQuestion();
        });
        assertEquals("No questions found", exception.getMessage());
    }

    @Test
    void getMcqQuestionById_IdNotFoundTest() {
        int id = 999;
        String errorMessage = "Question not found with ID: " + id;
        doThrow(new IdNotFoundException(errorMessage)).when(mcqQuestionService).getMcqQuestionById(id);
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
            mcqQuestionController.getMcqQuestionById(id);
        });
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void updateMcqQuestion_IdNotFoundTest() {
        int id = 999;
        String errorMessage = "Question not found with ID: " + id;
        McqQuestion nonExistingQuestion = new McqQuestion(id, new SubCategory(), "Non-existent question", "Option 1", "Option 2", "Option 3", "Option 4", "Correct option", 1, -1);
        when(mcqQuestionService.updateMcqQuestion(nonExistingQuestion)).thenThrow(new IdNotFoundException(errorMessage));
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
            mcqQuestionController.updateMcqQuestion(nonExistingQuestion);
        });
        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void deleteMcqQuestion_IdNotFoundTest() {
        int id = 999;
        String errorMessage = "Question not found with ID: " + id;
        doThrow(new IdNotFoundException(errorMessage)).when(mcqQuestionService).deleteMcqQuestion(id);
        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> {
            mcqQuestionController.deleteMcqQuestion(id);
        });
        assertEquals(errorMessage, exception.getMessage());
    }


 
    
}
