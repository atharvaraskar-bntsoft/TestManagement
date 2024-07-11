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
import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService {
    Logger logger=LoggerFactory.getLogger(CategoryServiceImpl.class);
   
    @Autowired
    CategoryRepository categoryRepository;
    

    @Override
    public Category saveCategory(Category category) { 
        if(category==null){
            throw new DataIsNullException("Category cannot be null");
         }
        Optional<Category> optionalSubCategory =categoryRepository.findByCategoryName(category.getCategoryName()); 
        if(optionalSubCategory.isPresent()){
              throw new DuplicateDataException("Catgory is already Present");
        }
        logger.info("Saving Category: {}", category);
        return categoryRepository .save(category);
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        logger.info("Fetching Category with ID: {}", id);
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            throw new IdNotFoundException("Category not found with ID: " + id);
        }
        return optionalCategory;    
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();   
        if (categories.isEmpty()) {
            throw new DataIsNotPresentException("No categories found");
        }
        logger.info("Fetching all Categories");
        return categories;
    }
    

    @Override
    public Category updateCategory(Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(category.getCategoryId());
        if (!optionalCategory.isPresent()) {
            throw new IdNotFoundException("Category not found with ID: " + category.getCategoryId());
        }
        logger.info("Updating Category: {}", category);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(int id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            throw new IdNotFoundException("Category not found with ID: " + id);
        }
        logger.info("Deleting Category with ID: {}", id);
        categoryRepository.deleteById(id);     
      
    }

}
