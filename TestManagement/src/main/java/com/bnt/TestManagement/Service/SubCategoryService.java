package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import com.bnt.TestManagement.Model.SubCategory;

public interface SubCategoryService {

    public SubCategory saveSubCategory( SubCategory subCategory);

    public Optional<SubCategory> getSubCategoryById(int id);

    public List<SubCategory> getAllSubCategories();

    public SubCategory updateSubCategory(SubCategory subCategory);

    public void deleteSubCategory(int id);

}
