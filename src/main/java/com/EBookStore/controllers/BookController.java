package com.EBookStore.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EBookStore.dto.BookDetails;
import com.EBookStore.service.BookService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {

	private final BookService bookService;
	
	@GetMapping("/id/{id}")
	public BookDetails getFreeBook(@PathVariable ("id") Long id) {
		return bookService.getBook(id);
	}
	
	
	@GetMapping("/books")
	public List<BookDetails> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@GetMapping("/genre/{genre}")
	public List<BookDetails> getBooksByGenre(@PathVariable ("genre") String genre) {
		return bookService.getBooksByGenre(genre);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addBook(@RequestBody BookDetails bookDetails) {
		bookService.saveBook(bookDetails);
		return new ResponseEntity<String>("Book added successfully", HttpStatus.CREATED);
	}
}
