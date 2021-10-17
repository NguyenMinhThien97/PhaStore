package com.store.pharmacy.category.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO extends RepresentationModel<CategoryDTO> {

	private String categoryId;
	private String categoryName;
	private String description;
	private Boolean enabled;
}
