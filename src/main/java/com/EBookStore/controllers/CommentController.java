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

import com.EBookStore.dto.CommentDto;
import com.EBookStore.service.CommentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comment")
@AllArgsConstructor
public class CommentController {
	
	private final CommentService commentService;

	@PostMapping("/create")
	public  ResponseEntity<String> createComment(@RequestBody CommentDto commentDto) {
		commentService.save(commentDto);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{bookId}")
	public List<CommentDto> getCommentsByBookId(@PathVariable("bookId") Long id){
		return commentService.getAllCommentsByBookId(id);
	}
}
