package com.store.pharmacy.Category.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.pharmacy.Category.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

	Category findCategoryByCategoryId(String categoryId);
}
