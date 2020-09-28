package com.EBookStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EBookStore.model.Book;
import com.EBookStore.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {

	List<Comment> findByBook(Book book);

}
