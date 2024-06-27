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

import com.bnt.TestManagement.Model.McqQuestion;
import com.bnt.TestManagement.Service.TestManageMentService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestMangementControllerTest {
    @Mock
    TestManageMentService testManageMentService;

    @InjectMocks
    TestManagementController testManagementController;
   
    static McqQuestion  Expectedata(){
        McqQuestion expectedQuestion = new McqQuestion();
        expectedQuestion.setQuestion_id(1);
         expectedQuestion.setQuestion("What is Spring Boot?");
         expectedQuestion.setOption_one("A Java framework");
         expectedQuestion.setOption_two("A Spring project");
         expectedQuestion.setOption_three("A Spring module");
         expectedQuestion.setOption_four("An annotation");
         expectedQuestion.setCorrect_option("A Spring project");
         expectedQuestion.setPositive_mark(3);
         expectedQuestion.setNegative_mark(-1);
         return expectedQuestion;
    }

    @Test
    void saveMcqQuestion(){
        McqQuestion expectedQuestion = Expectedata();

        when(testManageMentService.saveMcqQuestion(expectedQuestion)).thenReturn(expectedQuestion);

        ResponseEntity<Object> responseEntity = testManagementController.createMcqQuestion(expectedQuestion);

        assertEquals(CREATED, responseEntity.getStatusCode());
        assertEquals(expectedQuestion, responseEntity.getBody());
    }

    @Test
    void updateMcqQuestion(){
        McqQuestion expectedQuestion = Expectedata();
            when(testManageMentService.updateMcqQuestion(expectedQuestion)).thenReturn(expectedQuestion);

           ResponseEntity<Object> actualResponseEntity=testManagementController.updateMcqQuestion(expectedQuestion);
           assertEquals(OK, actualResponseEntity.getStatusCode());

    }

    @Test
    void getMcqQuestionById() {
        int id = 1;
        McqQuestion expectedQuestion = Expectedata();
        Optional<McqQuestion> optionalQuestion = Optional.of(expectedQuestion);

        when(testManageMentService.getMcqQuestionById(id)).thenReturn(optionalQuestion);
        ResponseEntity<Object> actualResponseEntity = testManagementController.getMcqQuestionById(id);
        assertEquals(FOUND, actualResponseEntity.getStatusCode());
        assertSame(optionalQuestion, actualResponseEntity.getBody());
    }

     @Test
    void getAllMcqQuestion() {
        List<McqQuestion> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(Expectedata());
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
