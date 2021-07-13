package com.store.pharmacy.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CategoryId", length = 20, nullable = false)
	private String categoryId;

	@Column(name = "CategoryName", length = 255, nullable = false)
	private String categoryName;

	@Column(name = "Enabled", length = 1, nullable = false)
	private boolean enabled;

	@Column(name = "Description", length = 255)
	private String description;

	@Column(name = "CreatedBy", length = 255)
	private String createBy;

	@Column(name = "CreatedAt")
	private LocalDateTime createAt;

	@Column(name = "UpdatedBy", length = 255)
	private String updateBy;

	@Column(name = "UpdatedAt")
	private LocalDateTime updateAt;
}
