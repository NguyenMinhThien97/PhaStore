package com.store.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.pharmacy.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

	public Category findCategoryByCategoryId(String categoryId);
}
