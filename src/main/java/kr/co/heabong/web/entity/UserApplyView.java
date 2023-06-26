package kr.co.heabong.web.entity;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserApplyView {
	private String name;
	private String phoneNumber;
	private int orgVolId;
	private int userId;
	private String status;
	private Date date;

}
