<<<<<<< HEAD
package kr.co.heabong.web.entity;

import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;
import lombok.Data;

@Component
@Data
@Builder
public class User {
	private int id; 
	private String name; 
	private String email; 
	private String identityNumber; 
	private String phoneNumber; 
	private String gender; 
	private int age; 
	private String signupDate; 
	private String birthDate; 
	private String profilePhoto; 
	private String nickname; 
}
=======
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
	private String name; 
	private String email; 
	private String identityNumber; 
	private String phoneNumber; 
	private Integer gender; 
	private Integer age; 
	private String signupDate; 
	private String birthDate; 
	private String profilePhoto; 
	private String nickname; 
}
>>>>>>> Joy
