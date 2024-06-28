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

import com.bnt.TestManagement.Model.Category;
import com.bnt.TestManagement.Service.CategoryService;


@RestController
@RequestMapping("/testmangementapi/category")
public class CategoryController {

    Logger logger=LoggerFactory.getLogger(CategoryController.class);
    
     @Autowired
     private  CategoryService categoryService;


     @PostMapping
     public ResponseEntity<Category> createCategory(@RequestBody Category category) {
         logger.info("Creating Category: {}", category);
         Category createdCategory = categoryService.saveCategory(category);
         logger.info("Created MCQ question with ID: {}", createdCategory.getCategoryId());
         return new ResponseEntity<Category>(createdCategory, HttpStatus.CREATED);
     }

     @PutMapping
     public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        logger.info("Updating Category with ID: {}", category.getCategoryId());
         Category updatedCategory = categoryService.updateCategory(category);
         logger.info("Updated Category: {}", updatedCategory);
         return new ResponseEntity<Category>(updatedCategory, HttpStatus.OK);
     }

     @GetMapping
     ResponseEntity<List<Category>> getAllCategories(){
        logger.info("Fetching all Categories");
        List<Category> list1= categoryService.getAllCategories(); 
        logger.info("Fetched All  categories");  
        return new ResponseEntity<List<Category>>(list1,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Category>> getCategoryById(@PathVariable("id") int id){
        logger.info("Fetching Category with ID: {}", id);
        Optional<Category> optionalCategory =categoryService.getCategoryById(id);
        logger.info("Category with ID {} found", id);
        return  new ResponseEntity<Optional<Category>>(optionalCategory,HttpStatus.FOUND);
     }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") int id){
            logger.info("Deleting Category with ID: {}", id);
            categoryService.deleteCategory(id);
            logger.info("Deleted Category with ID: {}", id);
            return  new ResponseEntity<Object>("Category Deleted Successfully",HttpStatus.OK);
        
    }


     

}
