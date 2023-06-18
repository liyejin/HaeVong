package kr.co.heabong.web.entity;

import java.sql.Date;

import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Org {

	private int id;
	private String name;
	private String email;
	private String signUpDate;
	private String orgNum;
	private String phoneNum;
	private String roadAddress;
	private String address;
	private String profilePhoto;
	private int districtId;
	private int metropolId;
	private String regNum;
	private String pwd;
}
