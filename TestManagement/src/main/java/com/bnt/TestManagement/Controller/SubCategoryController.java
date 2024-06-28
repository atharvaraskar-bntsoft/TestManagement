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

import com.bnt.TestManagement.Model.SubCategory;
import com.bnt.TestManagement.Service.SubCategoryService;

@RestController
@RequestMapping("/testmangementapi/subcategory")
public class SubCategoryController {

     Logger logger=LoggerFactory.getLogger(SubCategoryController.class);

     @Autowired
     SubCategoryService subCategoryService;

     @PostMapping
     public ResponseEntity<SubCategory> createubCategory(@RequestBody SubCategory subCategory) {
         logger.info("Creating SubCategory: {}", subCategory);
         SubCategory createdSubCategory = subCategoryService.saveSubCategory(subCategory);
         logger.info("Created Subcategory with ID: {}", createdSubCategory.getSubcategoryId());
         return new ResponseEntity<SubCategory>(createdSubCategory, HttpStatus.CREATED);
     }

     @PutMapping
     public ResponseEntity<SubCategory> updateSubCategory(@RequestBody SubCategory subCategory) {
        logger.info("Updating SubCategory with ID: {}", subCategory.getSubcategoryId());
         SubCategory updatedSubCategory = subCategoryService.updateSubCategory(subCategory);
         logger.info("Updated Category: {}", updatedSubCategory);
         return new ResponseEntity<SubCategory>(updatedSubCategory, HttpStatus.OK);
     }

     @GetMapping
     ResponseEntity<List<SubCategory>> getAllSubCategories(){
        logger.info("Fetching all SubCategories");
        List<SubCategory> list1= subCategoryService.getAllSubCategories(); 
        logger.info("Fetched All  Subcategories");  
        return new ResponseEntity<List<SubCategory>>(list1,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<SubCategory>> getSubCategoryById(@PathVariable("id") int id){
        logger.info("Fetching SubCategory with ID: {}", id);
        Optional<SubCategory> optionalSubCategory =subCategoryService.getSubCategoryById(id);
        logger.info("SubCategory with ID {} found", id);
        return  new ResponseEntity<Optional<SubCategory>>(optionalSubCategory,HttpStatus.FOUND);
     } 
     

     @DeleteMapping("/{id}")
      public ResponseEntity<Object> deleteSubCategory(@PathVariable("id") int id){
            logger.info("Deleting SubCategory with ID: {}", id);
            subCategoryService.deleteSubCategory(id);
            logger.info("Deleted SubCategory with ID: {}", id);
            return  new ResponseEntity<Object>("Subcategory Deleted Successfully",HttpStatus.OK);
        
    }



    


      
}
