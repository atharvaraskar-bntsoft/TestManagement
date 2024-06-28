package com.bnt.TestManagement.Service;

import java.util.List;
import java.util.Optional;

import com.bnt.TestManagement.Model.Category;


public interface CategoryService {
    public Category saveCategory( Category category);

    public Optional<Category> getCategoryById(int id);

    public List<Category> getAllCategories();

    public Category updateCategory(Category category);

    public void deleteCategory(int id);

}
