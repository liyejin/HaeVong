package kr.co.heabong.web.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrgVolAddressView {
	
	private String title;
	private int id;
	private int volCategoryId;
	private String photo;
	private String address;
	

}
