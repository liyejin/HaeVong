package kr.co.heabong.web.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;
import lombok.Data;

@Component
@Data
@Builder
public class Post {
	private int id; 
	private String title; 
	private Date regDate; 
	private int userId; 
	private int postCategoryId; 
	private String content;
}
