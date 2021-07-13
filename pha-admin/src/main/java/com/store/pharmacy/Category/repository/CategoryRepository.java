package com.store.pharmacy.Category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.pharmacy.Category.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

	public Category findCategoryByCategoryId(String categoryId);
}
