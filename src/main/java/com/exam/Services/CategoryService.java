package com.exam.Services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.entity.quiz.Category;

@Service
public interface CategoryService {
	
	public Category addCategory(Category category);
	
	public Category updateCategory(Category category);
	
	public List<Category> getCategories();
	
	public Category getCategory(Long categoryId);
	
	public void deleteCategory(Long categoryId);
}
