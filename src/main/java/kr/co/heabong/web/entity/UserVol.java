package kr.co.heabong.web.entity;

import java.util.Date;

import lombok.Data;

@Data
public class UserVol {
	private int id;
	private String title;
	private Date regDate;
	private Date date;
	private int capacity;
	private String place;
	private String roadAdress;
	private String adress;
	private int userId;
	private int districtId;
	private int metropolId;
	private int volCategoryId;
	private String content;
	
}
