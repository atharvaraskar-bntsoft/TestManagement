package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bnt.TestManagement.Repository.TestMangementRepository;

import com.bnt.TestManagement.Model.McqQuestion;

@Service
public class TestManagementServiceImpl implements TestManageMentService {
    
     Logger logger=LoggerFactory.getLogger(TestManagementServiceImpl.class);
   
    @Autowired
    TestMangementRepository testMangementRepository;

    @Override
    public McqQuestion saveMcqQuestion(McqQuestion mcqQuestion) {
        logger.info("Saving MCQ Question: {}", mcqQuestion);
        return testMangementRepository .save(mcqQuestion);
    }

    @Override
    public Optional<McqQuestion> getMcqQuestionById(int id) {
        logger.info("Fetching MCQ Question with ID: {}", id);
        Optional<McqQuestion> optionalQuestion = testMangementRepository.findById(id);
        return optionalQuestion;               
    }

    @Override
    public List<McqQuestion> getAllEMcqQuestions() {
        logger.info("Fetching all MCQ Questions");
        return testMangementRepository.findAll();
    }

    @Override
    public McqQuestion updateMcqQuestion(McqQuestion mcqQuestion) {
        logger.info("Updating MCQ Question: {}", mcqQuestion);
        return testMangementRepository.save(mcqQuestion);
    }

    @Override
    public void deleteMcqQuestion(int id) {
        logger.info("Deleting MCQ Question with ID: {}", id);
        testMangementRepository.deleteById(id);        
    }

}
