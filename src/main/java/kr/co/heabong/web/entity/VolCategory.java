package kr.co.heabong.web.entity;

import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VolCategory {

	private int id;
	private String name;  
	private String icon;
}
