package com.EBookStore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long Id;
	
	@Column(unique = true)
	@NotBlank(message = "title is required")
	private String title;

	@Nullable
	private String author; 
	
	private String coverImageLink;
	private String genre;
	
	@Column(name = "number_of_pages")
	private int pagesCount;
	
	@Column(columnDefinition = "varchar(255) default 'FREE'")
	private String bookCategory;//FREE, PREMIUM
	
}
