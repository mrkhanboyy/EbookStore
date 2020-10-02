package com.EBookStore.mapper;

import org.springframework.stereotype.Component;

import com.EBookStore.dto.BookDetails;
import com.EBookStore.model.Book;

@Component
public class BookMapper {

	public BookDetails bookToDto(Book book) {
		BookDetails bd = new BookDetails();
		bd.setId(book.getId());
		bd.setTitle(book.getTitle());
		bd.setAuthor(book.getAuthor());
		bd.setGenre(book.getGenre());
		bd.setBookCategory(book.getBookCategory());
		bd.setCoverImageLink(book.getCoverImageLink());
		bd.setPagesCount(book.getPagesCount());
		return bd;
	}
	
	public Book DtoToBook(BookDetails bookDetails) {
		Book book = new Book();
		book.setTitle(bookDetails.getTitle());
		book.setAuthor(bookDetails.getAuthor());
		book.setCoverImageLink(bookDetails.getCoverImageLink());
		book.setBookCategory(bookDetails.getBookCategory());
		book.setGenre(bookDetails.getGenre());
		book.setPagesCount(bookDetails.getPagesCount());
		return book;
	}
	
}
