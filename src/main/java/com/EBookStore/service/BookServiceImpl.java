package com.EBookStore.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.EBookStore.dto.BookDetails;
import com.EBookStore.exceptions.BookNotFoundException;
import com.EBookStore.mapper.BookMapper;
import com.EBookStore.repository.BookRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
	

	private final BookMapper bookMapper;
	private final BookRepo bookRepo;

	@Override
	public List<BookDetails> getAllBooks() {
		return bookRepo.findAll().stream()
				.map(bookMapper::bookToDto).collect(Collectors.toList());
	}

	@Override
	public BookDetails getBook(Long id) {
		return bookMapper.bookToDto(bookRepo.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with id : "+id)));
	}

	@Override
	public List<BookDetails> getBooksByGenre(String genre) {
		return bookRepo.findByGenre(genre).stream()
				.map(bookMapper::bookToDto).collect(Collectors.toList());
	}

	@Override
	public void saveBook(BookDetails bookDetails) {
		bookRepo.save(bookMapper.DtoToBook(bookDetails));
	}

}
