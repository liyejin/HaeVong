<<<<<<< HEAD
package kr.co.heabong.web.entity;


import java.util.Date;

import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;

import lombok.Data;


@Component
@Builder
@Data
public class OrgVol {
	

	private int id;
	
	private String title;
	private String regdate;
	private Date date;
	private int capacity;
	private String location;
	private String content;
	private String roadAddress;
	private String address;
	private int orgId;
	private int districtId;
	private int metropolId;
	private int volCategoryId;
}
=======
package kr.co.heabong.web.entity;


import java.util.Date;

import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;

import lombok.Data;


@Component
@Builder
@Data
public class OrgVol {
	

	private int id;
	
	private String title;
	private String regdate;
	private Date date;
	private int capacity;
	private String location;
	private String content;
	private String roadAddress;
	private String address;
	private int orgId;
	private int districtId;
	private int metropolId;
	private int volCategoryId;
}
>>>>>>> Joy
