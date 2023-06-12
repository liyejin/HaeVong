package kr.co.heabong.web.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Integer id;
	private String uid;
	private String name; 
	private String email; 
	private String pwd;
	private String identityNumber; 
	private String phoneNumber; 
	private Integer gender; 
	private Integer age; 
	private String signupDate; 
	private String birthDate; 
	private String profilePhoto; 
	private String nickname; 
	
}
