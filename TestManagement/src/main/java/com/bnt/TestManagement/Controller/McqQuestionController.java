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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.bnt.TestManagement.Model.McqQuestion;
import com.bnt.TestManagement.Service.McqQuestionService;


@RestController
@RequestMapping("/testmangementapi/mcqquestions")
public class McqQuestionController {
    Logger logger=LoggerFactory.getLogger(McqQuestionController.class);
    
     @Autowired
     private  McqQuestionService mcqQuestionService;


     @PostMapping
     public ResponseEntity<McqQuestion> createMcqQuestion(@RequestBody McqQuestion mcqQuestion) {
         logger.info("Creating MCQ question: {}", mcqQuestion);
         McqQuestion createdQuestion = mcqQuestionService.saveMcqQuestion(mcqQuestion);
         logger.info("Created MCQ question with ID: {}", createdQuestion.getQuestion_id());
         return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
     }
 
     @PutMapping
     public ResponseEntity<McqQuestion> updateMcqQuestion(@RequestBody McqQuestion mcqQuestion) {
        logger.info("Updating MCQ question with ID: {}", mcqQuestion.getQuestion_id());
         McqQuestion updatedQuestion = mcqQuestionService.updateMcqQuestion(mcqQuestion);
         logger.info("Updated MCQ question: {}", updatedQuestion);
         return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
     }

    @GetMapping
     ResponseEntity<List<McqQuestion>> getAllMcqQuestion(){
        logger.info("Fetching all MCQ questions");
        List<McqQuestion> list1= mcqQuestionService.getAllEMcqQuestions(); 
        logger.info("Fetched All MCQ questions");  
        return new ResponseEntity<>(list1,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<McqQuestion>> getMcqQuestionById(@PathVariable("id") int id){
        logger.info("Fetching MCQ question with ID: {}", id);
        Optional<McqQuestion> optionalMcqQuestion =mcqQuestionService.getMcqQuestionById(id);
        logger.info("MCQ question with ID {} found", id);
        return  new ResponseEntity<>(optionalMcqQuestion,HttpStatus.FOUND);
     }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMcqQuestion(@PathVariable("id") int id){
            logger.info("Deleting MCQ question with ID: {}", id);
            mcqQuestionService.deleteMcqQuestion(id);
            logger.info("Deleted MCQ question with ID: {}", id);
            return  new ResponseEntity<>("User Deleted Successfully",HttpStatus.OK);
        
    }

   @PostMapping("/uploadexcel")
    public ResponseEntity<Object> bulkUploadQuestions(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please upload an Excel file!", HttpStatus.BAD_REQUEST);
        }
        mcqQuestionService.saveMcqQuestionsFromExcel(file);
        return new ResponseEntity<>("Bulk Data Transfer Succefully!", HttpStatus.CREATED);
    }
    

    
}
