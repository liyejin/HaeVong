package kr.co.heabong.web.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Comment {
	private int id;
	private Date date;
	private String content;
	private int userId;
	private int postId;
	private int parentId;
}
