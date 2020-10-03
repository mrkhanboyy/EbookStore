package com.EBookStore.service;

import java.util.List;

import com.EBookStore.dto.CommentDto;

public interface CommentService {
	
	public void save(CommentDto commentDto);

	public List<CommentDto> getAllCommentsByBookId(Long id);

}
