package com.exam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.entity.quiz.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
