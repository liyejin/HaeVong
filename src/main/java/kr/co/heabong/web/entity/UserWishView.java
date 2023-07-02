package kr.co.heabong.web.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//북마크 기능 구현 시작!(미리,도연 23년 6월 30일)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWishView {
	
	private Integer id;
	private Integer wish;
	private String title;
	private LocalDateTime regdate;
	private String date;
	private Integer orgId;
	private Integer volCategoryId;
	private String photo;

}
