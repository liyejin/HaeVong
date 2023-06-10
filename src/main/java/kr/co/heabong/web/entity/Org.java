package kr.co.heabong.web.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;
import lombok.Data;

@Data
@Builder
public class Org {

	private int id;
	private String name;
	private String email;
	private String orgNum;
	private String phoneNum;
	private String roadAddress;
	private String address;
	private String profilePhoto;
	private int districtId;
	private int metropolId;
}
