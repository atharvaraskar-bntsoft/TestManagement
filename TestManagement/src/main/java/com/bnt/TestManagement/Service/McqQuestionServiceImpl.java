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
import com.bnt.TestManagement.Repository.McqQuestionRepository;
import com.bnt.TestManagement.Exception.CategoryNotFoundException;
import com.bnt.TestManagement.Exception.CategorySubCategoryMismatchException;
import com.bnt.TestManagement.Exception.DataIsNotPresentException;
import com.bnt.TestManagement.Exception.DataIsNullException;
import com.bnt.TestManagement.Exception.ExcelProcessingException;
import com.bnt.TestManagement.Exception.IdNotFoundException;
import com.bnt.TestManagement.Exception.SubCategoryNotFoundException;
import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Model.McqQuestion;
import com.bnt.TestManagement.Model.SubCategory;


@Service
public class McqQuestionServiceImpl implements McqQuestionService {
    
     Logger logger=LoggerFactory.getLogger(McqQuestionServiceImpl.class);
   
    @Autowired
    McqQuestionRepository mcqQuestionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public McqQuestion saveMcqQuestion(McqQuestion mcqQuestion) {
        if (mcqQuestion == null || mcqQuestion.getQuestion()==null) {
              throw new DataIsNullException("Data cannot be null");
         }                     
        logger.info("Saving MCQ Question: {}", mcqQuestion);
        return mcqQuestionRepository .save(mcqQuestion);  
    }

    @Override
    public Optional<McqQuestion> getMcqQuestionById(int id) {
        Optional<McqQuestion> optionalQuestion  = mcqQuestionRepository.findById(id);
        if (!optionalQuestion .isPresent()) {
            throw new IdNotFoundException("Id Not Found");
        }
        logger.info("Fetching Data with ID: {}", id);
        return optionalQuestion;               
    }

    @Override
    public List<McqQuestion> getAllEMcqQuestions() {
        List<McqQuestion> mcqQuestions = mcqQuestionRepository.findAll();
        if (mcqQuestions.isEmpty()) {
            throw new DataIsNotPresentException("No MCQ questions found");
        }
        logger.info("Fetching all MCQ Questions");
        return mcqQuestions;
    }

    @Override
    public McqQuestion updateMcqQuestion(McqQuestion mcqQuestion) {
        Optional<McqQuestion> optionalQuestion  = mcqQuestionRepository.findById(mcqQuestion.getQuestion_id());
        if (!optionalQuestion .isPresent()) {
            throw new IdNotFoundException("Id Not Found");
        }
        logger.info("Updating MCQ Question: {}", mcqQuestion);
        return mcqQuestionRepository.save(mcqQuestion);
    }

    @Override
    public void deleteMcqQuestion(int id) {
        Optional<McqQuestion> optionalQuestion  = mcqQuestionRepository.findById(id);
        if (!optionalQuestion.isPresent()) {
            throw new IdNotFoundException("Id Not Found");
        }
        logger.info("Deleting MCQ Question with ID: {}", id);
        mcqQuestionRepository.deleteById(id);        
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


                Optional<McqQuestion> existingQuestion = mcqQuestionRepository.findByQuestion(questionText);
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
            mcqQuestionRepository.saveAll(questions);
    
        } catch (Exception e) {
            throw new ExcelProcessingException("Failed to process Excel file: " + e.getMessage());
        }
    }
    
    private String getStringValue(Cell cell) {
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }
    

  
    

}
