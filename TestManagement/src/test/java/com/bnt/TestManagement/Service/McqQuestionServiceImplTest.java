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

import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.McqQuestion;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Repository.McqQuestionRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class McqQuestionServiceImplTest {

     @Mock
     McqQuestionRepository mcqQuestionRepository;

    @InjectMocks
    McqQuestionServiceImpl mcqQuestionServiceImpl;   
    
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
    void testSaveMcqQuestion() {
        McqQuestion expectedQuestion = ExpectedData();
        when(mcqQuestionRepository.save(expectedQuestion)).thenReturn(expectedQuestion);
        McqQuestion savedQuestion = mcqQuestionServiceImpl.saveMcqQuestion(expectedQuestion);
        assertEquals(expectedQuestion, savedQuestion);
        verify(mcqQuestionRepository, times(1)).save(expectedQuestion);

    }
     
    @Test
    void testGetMcqQuestionById() {
        int id = 1;
        McqQuestion expectedQuestion = ExpectedData();
        when(mcqQuestionRepository.findById(id)).thenReturn(Optional.of(expectedQuestion));
        Optional<McqQuestion> retrievedQuestion = mcqQuestionServiceImpl.getMcqQuestionById(id);
        assertEquals(expectedQuestion, retrievedQuestion.orElse(null));
        verify(mcqQuestionRepository, times(1)).findById(id);
    }

    @Test
    void testGetAllEMcqQuestions() {
        List<McqQuestion> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(ExpectedData());
        when(mcqQuestionRepository.findAll()).thenReturn(expectedQuestions);
        List<McqQuestion> retrievedQuestions = mcqQuestionServiceImpl.getAllEMcqQuestions();
        assertEquals(expectedQuestions, retrievedQuestions);
        verify(mcqQuestionRepository, times(1)).findAll();
    }

    @Test
    void testUpdateMcqQuestion() {
        McqQuestion expectedQuestion = ExpectedData();
        when(mcqQuestionRepository.findById(expectedQuestion.getQuestion_id())).thenReturn(Optional.of(expectedQuestion));
        when(mcqQuestionRepository.save(expectedQuestion)).thenReturn(expectedQuestion);
        McqQuestion updatedQuestion = mcqQuestionServiceImpl.updateMcqQuestion(expectedQuestion);
        assertEquals(expectedQuestion, updatedQuestion);
        verify(mcqQuestionRepository, times(1)).save(expectedQuestion);
    }


    @Test
    void testDeleteEmployeeService() {
           int id=1;
           when(mcqQuestionRepository.findById(id)).thenReturn(Optional.of(ExpectedData()));
           mcqQuestionServiceImpl.deleteMcqQuestion(id);   
           verify(mcqQuestionRepository, times(1)).deleteById(id);
      }

    

}
