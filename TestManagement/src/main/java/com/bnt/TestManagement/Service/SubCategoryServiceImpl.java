package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.TestManagement.Exception.DataIsNotPresentException;
import com.bnt.TestManagement.Exception.DataIsNullException;
import com.bnt.TestManagement.Exception.DuplicateDataException;
import com.bnt.TestManagement.Exception.IdNotFoundException;
import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
     Logger logger=LoggerFactory.getLogger(CategoryServiceImpl.class);
   
    @Autowired
    SubCategoryRepository subCategoryRepository;

   

    @Override
    public SubCategory saveSubCategory(SubCategory subCategory) {
        if (subCategory == null) {
              throw new DataIsNullException("SubCategory  cannot be null");
         }
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findBySubcategoryName(subCategory.getSubcategoryName());
        if (optionalSubCategory.isPresent()) {
            throw new DuplicateDataException("SubCatgory is already Present");
        } 
            logger.info("Saving SubCategory: {}", subCategory);
            return subCategoryRepository.save(subCategory);
    }

    @Override
    public Optional<SubCategory> getSubCategoryById(int id) {
        logger.info("Fetching SubCategory with ID: {}", id);
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        if (!optionalSubCategory.isPresent()) {
            throw new IdNotFoundException("SubCategory not found with ID: " + id);
        }
        return optionalSubCategory;    
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        List<SubCategory> subCategories = subCategoryRepository.findAll();  
        if (subCategories.isEmpty()) {
            throw new DataIsNotPresentException("No subcategories found");
        }
        logger.info("Fetching all SubCategories");
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory updateSubCategory(SubCategory subCategory) {
        logger.info("Updating SubCategory: {}", subCategory);
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(subCategory.getSubcategoryId());
        if (!optionalSubCategory.isPresent()) {
             throw new IdNotFoundException("SubCategory not found with ID: " + subCategory.getSubcategoryId());
        }
        return subCategoryRepository.save(subCategory);
        
    }

    @Override
    public void deleteSubCategory(int id) {
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        if (!optionalSubCategory.isPresent()) {
            throw new IdNotFoundException("SubCategory not found with ID: " + id);
        }
        logger.info("Deleting SubCategory with ID: {}", id);
        subCategoryRepository.deleteById(id);  
    }
    

}
