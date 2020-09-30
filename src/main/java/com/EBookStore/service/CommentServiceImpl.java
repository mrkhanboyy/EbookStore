package com.EBookStore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.EBookStore.dto.CommentDto;
import com.EBookStore.exceptions.BookNotFoundException;
import com.EBookStore.exceptions.UserNotFoundException;
import com.EBookStore.mapper.CommentMapper;
import com.EBookStore.model.Book;
import com.EBookStore.model.Comment;
import com.EBookStore.repository.BookRepo;
import com.EBookStore.repository.CommentRepo;
import com.EBookStore.repository.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
	
	private final CommentRepo commentRepo;
	private final BookRepo bookRepo;
	private final UserRepo userRepo;
	private final CommentMapper commentMapper;

	@Override
	public void save(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setText(commentDto.getText());
		comment.setCreatedDate(LocalDate.now());
		comment.setBook(bookRepo.findById(commentDto.getBookId())
				.orElseThrow(() -> new BookNotFoundException("Book not found with id  : "+commentDto.getBookId())));
		comment.setUser(userRepo.findByUsername(commentDto.getUserName())
				.orElseThrow(() ->  new UserNotFoundException("user not found with username : "+commentDto.getUserName())));
		commentRepo.save(comment);
	}

	@Override
	public List<CommentDto> getAllCommentsByBookId(Long id) {
		Book book = bookRepo.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with id  : "+id));
		
		return commentRepo.findByBook(book).stream()
				.map(commentMapper::CommentToDto).collect(Collectors.toList());
	}

}
