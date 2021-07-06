package com.store.pharmacy.service;

import com.store.pharmacy.model.CategoryDTO;

public interface CategoryService {

	public String save(CategoryDTO categoryDTO);

	public CategoryDTO findCategory(String categoryId);

	public void update(String categoryId, CategoryDTO categoryDTO);

	public void checkIfDuplicatedCategory(String categoryId);
	
	public void checkIfCategoryExits(String categoryId);
}
