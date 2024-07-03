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
import com.bnt.TestManagement.Repository.TestMangementRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TestManagementServiceImplTest {

     @Mock
     TestMangementRepository testMangementRepository;

    @InjectMocks
    TestManagementServiceImpl testManagementServiceImpl;   
    
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
    void testSaveMcqQuestion() {
        McqQuestion expectedQuestion = ExpectedData();
        when(testMangementRepository.save(expectedQuestion)).thenReturn(expectedQuestion);
        McqQuestion savedQuestion = testManagementServiceImpl.saveMcqQuestion(expectedQuestion);
        assertEquals(expectedQuestion, savedQuestion);
        verify(testMangementRepository, times(1)).save(expectedQuestion);

    }
     
    @Test
    void testGetMcqQuestionById() {
        int id = 1;
        McqQuestion expectedQuestion = ExpectedData();

        when(testMangementRepository.findById(id)).thenReturn(Optional.of(expectedQuestion));

        Optional<McqQuestion> retrievedQuestion = testManagementServiceImpl.getMcqQuestionById(id);

        assertEquals(expectedQuestion, retrievedQuestion.orElse(null));
        verify(testMangementRepository, times(1)).findById(id);
    }

    @Test
    void testGetAllEMcqQuestions() {
        List<McqQuestion> expectedQuestions = new ArrayList<>();
        expectedQuestions.add(ExpectedData());
        when(testMangementRepository.findAll()).thenReturn(expectedQuestions);
        List<McqQuestion> retrievedQuestions = testManagementServiceImpl.getAllEMcqQuestions();
        assertEquals(expectedQuestions, retrievedQuestions);
        verify(testMangementRepository, times(1)).findAll();
    }

    @Test
    void testUpdateMcqQuestion() {
        McqQuestion expectedQuestion = ExpectedData();

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
