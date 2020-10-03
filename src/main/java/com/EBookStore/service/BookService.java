package com.EBookStore.service;

import java.util.List;

import com.EBookStore.dto.BookDetails;

public interface BookService {
	

	public List<BookDetails> getAllBooks();

	public BookDetails getBook(Long id);

	public List<BookDetails> getBooksByGenre(String genre);

	
	public void saveBook(BookDetails bookDetails);
	
	public void deleteBook(Long id);
	
}
