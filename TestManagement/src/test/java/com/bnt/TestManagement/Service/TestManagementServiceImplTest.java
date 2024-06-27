package com.bnt.TestManagement.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.bnt.TestManagement.Model.McqQuestion;
import com.bnt.TestManagement.Repository.TestMangementRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestManagementServiceImplTest {

     @Mock
     TestMangementRepository testMangementRepository;

    @InjectMocks
    TestManagementServiceImpl testManagementServiceImpl;   
    
    static McqQuestion Expectedata() {
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
    void testSaveMcqQuestion() {
        McqQuestion expectedQuestion = Expectedata();
        when(testMangementRepository.save(expectedQuestion)).thenReturn(expectedQuestion);
        McqQuestion savedQuestion = testManagementServiceImpl.saveMcqQuestion(expectedQuestion);
        assertEquals(expectedQuestion, savedQuestion);
        verify(testMangementRepository, times(1)).save(expectedQuestion);

    }
     
    @Test
    void testGetMcqQuestionById() {
        int id = 1;
        McqQuestion expectedQuestion = Expectedata();

        when(testMangementRepository.findById(id)).thenReturn(Optional.of(expectedQuestion));

        Optional<McqQuestion> retrievedQuestion = testManagementServiceImpl.getMcqQuestionById(id);

        assertEquals(expectedQuestion, retrievedQuestion.orElse(null));
        verify(testMangementRepository, times(1)).findById(id);
    }

    @Test
    void testGetAllEMcqQuestions() {
        List<McqQuestion> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(Expectedata());
        when(testMangementRepository.findAll()).thenReturn(expectedQuestions);
        List<McqQuestion> retrievedQuestions = testManagementServiceImpl.getAllEMcqQuestions();
        assertEquals(expectedQuestions, retrievedQuestions);
        verify(testMangementRepository, times(1)).findAll();
    }

    @Test
    void testUpdateMcqQuestion() {
        McqQuestion expectedQuestion = Expectedata();

        when(testMangementRepository.save(expectedQuestion)).thenReturn(expectedQuestion);

        McqQuestion updatedQuestion = testManagementServiceImpl.updateMcqQuestion(expectedQuestion);

        assertEquals(expectedQuestion, updatedQuestion);
        verify(testMangementRepository, times(1)).save(expectedQuestion);
    }


    @Test
    void testDeleteEmployeeService() {
           int id=1;
           testManagementServiceImpl.deleteMcqQuestion(id);   
            verify(testMangementRepository, times(1)).deleteById(id);
      }

    

}
