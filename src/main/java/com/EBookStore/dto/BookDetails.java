package com.EBookStore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetails {

	private String title;
	private String author;
	private String coverImageLink;
	private String genre;
	private int pagesCount;
	private String bookCategory;
	
}
