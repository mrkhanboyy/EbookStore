package com.EBookStore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.EBookStore.dto.BookDetails;
import com.EBookStore.exceptions.BookNotFoundException;
import com.EBookStore.model.Book;
import com.EBookStore.repository.BookRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
	

	private final BookRepo bookRepo;

	@Override
	public List<BookDetails> getAllBooks() {
		return bookRepo.findAll().stream()
				.map(this::bookToDto).collect(Collectors.toList());
	}

	@Override
	public BookDetails getBook(Long id) {
		return bookToDto(bookRepo.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with id : "+id)));
	}

	@Override
	public List<BookDetails> getBooksByGenre(String genre) {
		return bookRepo.findByGenre(genre).stream()
				.map(this::bookToDto).collect(Collectors.toList());
	}

	@Override
	public void saveBook(BookDetails bookDetails) {
		Book book = new Book();
		book.setTitle(bookDetails.getTitle());
		book.setAuthor(bookDetails.getAuthor());
		book.setCoverImageLink(bookDetails.getCoverImageLink());
		book.setBookCategory(bookDetails.getBookCategory());
		book.setGenre(bookDetails.getGenre());
		book.setPagesCount(bookDetails.getPagesCount());
		
		bookRepo.save(book);
	}
	
	private BookDetails bookToDto(Book book) {
		BookDetails bd = new BookDetails();
		bd.setTitle(book.getTitle());
		bd.setAuthor(book.getAuthor());
		bd.setGenre(book.getGenre());
		bd.setBookCategory(book.getBookCategory());
		bd.setCoverImageLink(book.getCoverImageLink());
		bd.setPagesCount(book.getPagesCount());
		return bd;
	}

}
