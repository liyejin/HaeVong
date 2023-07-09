package kr.co.heabong.web.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Calender {
	private int calenderNum;
	private String calenderTitle;
	private Date calenderDate;
	private int orgVolId;
	private int userId;
}
