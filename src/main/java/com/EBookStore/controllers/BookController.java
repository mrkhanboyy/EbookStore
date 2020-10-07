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

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {

	private final BookService bookService;
	
	/**
	 * return a book by bood_id
	 */
	@ApiOperation(value = "return a book by bood_id")
	@GetMapping("/id/{id}")
	public BookDetails getFreeBook(@PathVariable ("id") Long id) {
		return bookService.getBook(id);
	}
	
	/**
	 * return list of all books
	 */
	@ApiOperation(value = "return list of all books ")
	@GetMapping("/books")
	public List<BookDetails> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	/**
	 * return list of books by genre
	 */
	@ApiOperation(value = "return list of books by genre ")
	@GetMapping("/genre/{genre}")
	public List<BookDetails> getBooksByGenre(@PathVariable ("genre") String genre) {
		return bookService.getBooksByGenre(genre);
	}
	
	/**
	 * adds a new book in database . only admin is allowed
	 */
	@ApiOperation(value = " adds a new book in database . only admin is allowed")
	@PostMapping("/add")
	public ResponseEntity<String> addBook(@RequestBody BookDetails bookDetails) {
		bookService.saveBook(bookDetails);
		return new ResponseEntity<String>("Book added successfully", HttpStatus.CREATED);
	}
	
	/**
	 * delete a  book from database by book_id. only admin is allowed
	 */
	@ApiOperation(value = "delete a  book from database by book_id. only admin is allowed")
	@GetMapping("/delete/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable ("id") Long id) {
		bookService.deleteBook(id);
		return new ResponseEntity<String>("Book deleted successfully", HttpStatus.CREATED);
	}
}
