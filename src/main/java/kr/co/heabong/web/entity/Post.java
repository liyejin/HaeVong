
package kr.co.heabong.web.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

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
