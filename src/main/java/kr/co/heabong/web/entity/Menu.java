package kr.co.heabong.web.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

//import lombok.Builder;
import kr.co.heabong.web.entity.Menu;
import lombok.Data;

import groovy.transform.builder.Builder;
import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//테스트 엔티티

@Builder
@Data
public class Menu {

	private int id; 
	private String korName;
	private String engName;
	private int price; 
	private String img; 
	private Date regDate; 
	private int regMemberId; 
	private int pat; 
	private int protein; 
	private int natrium; 
	private int sugar; 
	private int cafeine; 
	private int lgSizePat; 
	private int lgSizeProtein; 
	private int lgSizeNatrium; 
	private int lgSizeSugar; 
	private int lgSizeCafeine; 
	private int categoryId; 
	private String description;
	
}
