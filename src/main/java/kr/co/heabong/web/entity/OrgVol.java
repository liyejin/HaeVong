package kr.co.heabong.web.entity;


import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgVol {
	

	private Integer id;
	
	private String title;
	private LocalDateTime regdate;
	private String date;
	private Integer capacity;
	private String content;
	private String roadAddress;
	private String address;
	private Integer orgId;
	private int districtId;
	private int metropolId;
	private int volCategoryId;
}
