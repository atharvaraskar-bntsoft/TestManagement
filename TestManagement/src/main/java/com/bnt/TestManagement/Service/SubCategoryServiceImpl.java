package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
     Logger logger=LoggerFactory.getLogger(CategoryServiceImpl.class);
   
    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Override
    public SubCategory saveSubCategory(SubCategory subCategory) {
        logger.info("Saving SubCategory: {}", subCategory);
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public Optional<SubCategory> getSubCategoryById(int id) {
        logger.info("Fetching SubCategory with ID: {}", id);
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        return optionalSubCategory;    
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        logger.info("Fetching all SubCategories");
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory updateSubCategory(SubCategory subCategory) {
        logger.info("Updating SubCategory: {}", subCategory);
        return subCategoryRepository.save(subCategory);
        
    }

    @Override
    public void deleteSubCategory(int id) {
        logger.info("Deleting SubCategory with ID: {}", id);
        subCategoryRepository.deleteById(id);  
    }
    

}
