package com.bnt.TestManagement.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bnt.TestManagement.Model.McqQuestion;
import com.bnt.TestManagement.Service.TestManageMentService;




@RestController
@RequestMapping("/testmangementapi/mcqquestions")
public class TestManagementController {
    Logger logger=LoggerFactory.getLogger(TestManagementController.class);
    
     @Autowired
     private  TestManageMentService testManageMentService;


     @PostMapping
     public ResponseEntity<Object> createMcqQuestion(@RequestBody McqQuestion mcqQuestion) {
         logger.info("Creating MCQ question: {}", mcqQuestion);
         McqQuestion createdQuestion = testManageMentService.saveMcqQuestion(mcqQuestion);
         logger.info("Created MCQ question with ID: {}", createdQuestion.getQuestion_id());
         return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
     }
 
     @PutMapping
     public ResponseEntity<Object> updateMcqQuestion(@RequestBody McqQuestion mcqQuestion) {
        logger.info("Updating MCQ question with ID: {}", mcqQuestion.getQuestion_id());
         McqQuestion updatedQuestion = testManageMentService.updateMcqQuestion(mcqQuestion);
         logger.info("Updated MCQ question: {}", updatedQuestion);
         return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
     }

    @GetMapping
     ResponseEntity<Object> getAllMcqQuestion(){
        logger.info("Fetching all MCQ questions");
        List<McqQuestion> list1= testManageMentService.getAllEMcqQuestions(); 
        logger.info("Fetched All MCQ questions");  
        return new ResponseEntity<>(list1,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getMcqQuestionById(@PathVariable("id") int id){
        logger.info("Fetching MCQ question with ID: {}", id);
        Optional<McqQuestion> optionalMcqQuestion =testManageMentService.getMcqQuestionById(id);
        logger.info("MCQ question with ID {} found", id);
        return  new ResponseEntity<Object>(optionalMcqQuestion,HttpStatus.FOUND);
     }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMcqQuestion(@PathVariable("id") int id){
            logger.info("Deleting MCQ question with ID: {}", id);
            testManageMentService.deleteMcqQuestion(id);
            logger.info("Deleted MCQ question with ID: {}", id);
            return  new ResponseEntity<Object>("User Deleted Successfully",HttpStatus.OK);
        
    }
    

    
}
