package kr.co.heabong.web.entity;

import org.springframework.stereotype.Component;

import groovy.transform.builder.Builder;
import lombok.Data;

@Component
@Data
@Builder
public class VolCategory {

	private int id;
	private String name;
}
