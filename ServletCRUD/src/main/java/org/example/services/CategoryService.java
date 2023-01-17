package org.example.services;

import org.example.model.Category;
import org.example.repository.CategoryRepository;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {
    private CategoryRepository categoryRepository = new CategoryRepository();
    public List<Category> getAllCategory() throws SQLException, ClassNotFoundException {
        return this.categoryRepository.getAllCategory();

    }
    public Category updateCategory(Category category, int categoryId) throws SQLException, ClassNotFoundException{
        return this.categoryRepository.updateCategory(category,categoryId);
    }

    public Category addCategory(Category category) throws SQLException {
        return this.categoryRepository.addCategory(category);
    }

    public void deleteCategory(int category) throws SQLException {
        this.categoryRepository.deleteCategory(category);
    }
}
