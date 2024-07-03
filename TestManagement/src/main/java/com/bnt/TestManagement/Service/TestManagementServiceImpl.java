package com.bnt.TestManagement.Service;


import java.util.*;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bnt.TestManagement.Repository.CategoryRepository;
import com.bnt.TestManagement.Repository.SubCategoryRepository;
import com.bnt.TestManagement.Repository.TestMangementRepository;
import com.bnt.TestManagement.Exception.CategoryNotFoundException;
import com.bnt.TestManagement.Exception.CategorySubCategoryMismatchException;
import com.bnt.TestManagement.Exception.DuplicateDataException;
import com.bnt.TestManagement.Exception.ExcelProcessingException;
import com.bnt.TestManagement.Exception.SubCategoryNotFoundException;
import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.McqQuestion;
import com.bnt.TestManagement.Model.SubCategory;


@Service
public class TestManagementServiceImpl implements TestManageMentService {
    
     Logger logger=LoggerFactory.getLogger(TestManagementServiceImpl.class);
   
    @Autowired
    TestMangementRepository testMangementRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

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

    

    public void saveMcqQuestionsFromExcel(MultipartFile file) {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
    
            List<McqQuestion> questions = new ArrayList<>();
    
            while (rows.hasNext()) {
                Row currentRow = rows.next();
    
            
                if (currentRow.getRowNum() == 0) {
                    continue;
                }
    
               
                String categoryName = getStringValue(currentRow.getCell(1));
                String subCategoryName = getStringValue(currentRow.getCell(2));
                String questionText = getStringValue(currentRow.getCell(3));
                String optionOne = getStringValue(currentRow.getCell(4));
                String optionTwo = getStringValue(currentRow.getCell(5));
                String optionThree = getStringValue(currentRow.getCell(6));
                String optionFour = getStringValue(currentRow.getCell(7));
                String correctOption = getStringValue(currentRow.getCell(8));
    
                int positiveMark;
                if (currentRow.getCell(9).getCellType() == CellType.NUMERIC) {
                    positiveMark = (int) currentRow.getCell(9).getNumericCellValue();
                } else {
                    positiveMark = Integer.parseInt(currentRow.getCell(9).getStringCellValue());
                  
                }
    
                int negativeMark;
                if (currentRow.getCell(10).getCellType() == CellType.NUMERIC) {
                    negativeMark = (int) currentRow.getCell(10).getNumericCellValue();
                } else {
                    negativeMark = Integer.parseInt(currentRow.getCell(10).getStringCellValue());
                }


                Optional<McqQuestion> existingQuestion = testMangementRepository.findByQuestion(questionText);
                if (existingQuestion.isPresent()) {
                    logger.warn("Skipping duplicate question: {}", questionText);
                    continue; 
                }
    
                
                Optional<Category> optionalCategory = categoryRepository.findByCategoryName(categoryName);
                if (!optionalCategory.isPresent()) {
                    throw new CategoryNotFoundException("Category not found: " + categoryName);
                }
                Category category = optionalCategory.get();
    
              
               Optional<SubCategory> optionalSubCategory = subCategoryRepository.findBySubcategoryName(subCategoryName);
                if (!optionalSubCategory.isPresent()) {
                    throw new SubCategoryNotFoundException("SubCategory not found " + subCategoryName);
                }
                SubCategory subCategory = optionalSubCategory.get();
                

            if(subCategory.getCategory().getCategoryId()==category.getCategoryId()){

                
                McqQuestion mcqQuestion = new McqQuestion();
                mcqQuestion.setSubCategory(subCategory);
                mcqQuestion.setQuestion(questionText);
                mcqQuestion.setOption_one(optionOne);
                mcqQuestion.setOption_two(optionTwo);
                mcqQuestion.setOption_three(optionThree);
                mcqQuestion.setOption_four(optionFour);
                mcqQuestion.setCorrect_option(correctOption);
                mcqQuestion.setPositive_mark(positiveMark);
                mcqQuestion.setNegative_mark(negativeMark);
    
                questions.add(mcqQuestion);
                }
                else{
                    throw  new CategorySubCategoryMismatchException(currentRow.getRowNum()); 
                }
            }
            
    
            // Save all McqQuestion objects
            testMangementRepository.saveAll(questions);
    
        } catch (Exception e) {
            throw new ExcelProcessingException("Failed to process Excel file: " + e.getMessage());
        }
    }
    
    private String getStringValue(Cell cell) {
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }
    

  
    

}
