package com.exam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Services.CategoryService;
import com.exam.entity.quiz.Category;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {


	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addCategory(@RequestBody Category category){
		return ResponseEntity.ok(this.categoryService.addCategory(category));
	}
	
	//get category
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable("categoryId") Long categoryId) {
		return this.categoryService.getCategory(categoryId);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> getCategories() {
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateCategory(@RequestBody Category category){
		return ResponseEntity.ok(this.categoryService.updateCategory(category));
	}
	
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
		this.categoryService.deleteCategory(categoryId);
	}
	
	

}
