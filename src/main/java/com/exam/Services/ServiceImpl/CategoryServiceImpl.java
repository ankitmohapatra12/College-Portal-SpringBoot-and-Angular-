package com.exam.Services.ServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Repository.CategoryRepository;
import com.exam.Services.CategoryService;
import com.exam.entity.quiz.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository cateRepo;
	
	
	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return this.cateRepo.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		return this.cateRepo.save(category);
	}

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		List<Category> set = new ArrayList<>(this.cateRepo.findAll());
		return set;
	}

	@Override
	public Category getCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return this.cateRepo.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(Long categoryId) {
		// TODO Auto-generated method stub
		cateRepo.deleteById(categoryId);
	}

}
