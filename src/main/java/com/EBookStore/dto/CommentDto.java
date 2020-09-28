package com.EBookStore.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	private Long id;
	private String text;
    private Long bookId;
    private LocalDate createdDate;
    private String userName;
}
