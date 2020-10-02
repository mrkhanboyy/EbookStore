package com.EBookStore.mapper;

import org.springframework.stereotype.Component;

import com.EBookStore.dto.CommentDto;
import com.EBookStore.model.Comment;

@Component
public class CommentMapper {

	public CommentDto CommentToDto(Comment comment) {
		CommentDto cd =  new CommentDto();
		cd.setId(comment.getId());
		cd.setText(comment.getText());
		cd.setUsername(comment.getUsername());
		cd.setCreatedDate(comment.getCreatedDate());
		return cd;
	}
	
}
