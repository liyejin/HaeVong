package kr.co.heabong.web.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVol {
	private int id;
	private String title;
	private LocalDateTime regdate;
	private String date;
	private int capacity;
	private String place;
	private String roadAddress;
	private String address;
	private int userId;
	private int districtId;
	private int metropolId;
	private int volCategoryId;
	private String content;
	
}
