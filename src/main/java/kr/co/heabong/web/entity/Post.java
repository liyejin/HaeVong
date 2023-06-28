package kr.co.heabong.web.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	private int id; 
	private String title; 
	private Date regdate; 
	private int userId; 
	private int postCategoryId; 
	private String content;
}
