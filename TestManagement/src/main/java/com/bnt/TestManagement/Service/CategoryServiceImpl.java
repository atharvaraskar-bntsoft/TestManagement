package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Repository.CategoryRepository;


@Service
public class CategoryServiceImpl implements CategoryService {
    Logger logger=LoggerFactory.getLogger(CategoryServiceImpl.class);
   
    @Autowired
    CategoryRepository categoryRepository;
    

    @Override
    public Category saveCategory(Category category) {
        logger.info("Saving Category: {}", category);
        return categoryRepository .save(category);
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        logger.info("Fetching Category with ID: {}", id);
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory;    
    }

    @Override
    public List<Category> getAllCategories() {
        logger.info("Fetching all Categories");
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category category) {
        logger.info("Updating Category: {}", category);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(int id) {
        logger.info("Deleting Category with ID: {}", id);
        categoryRepository.deleteById(id);     
      
    }

}
